/*
 * Copyright (c) 2017 Hugo Matalonga & João Paulo Fernandes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hmatalonga.greenhub.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.hmatalonga.greenhub.BuildConfig;
import com.hmatalonga.greenhub.GreenHubApp;
import com.hmatalonga.greenhub.R;
import com.hmatalonga.greenhub.tasks.DeleteSessionsTask;
import com.hmatalonga.greenhub.tasks.DeleteUsagesTask;
import com.hmatalonga.greenhub.util.SettingsUtils;
import com.yariksoffice.lingver.Lingver;

import static com.hmatalonga.greenhub.util.LogUtils.logI;
import static com.hmatalonga.greenhub.util.LogUtils.makeLogTag;

import java.util.Locale;
import java.util.Objects;

public class SettingsActivity extends BaseActivity {

    private static final String TAG = makeLogTag(SettingsActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar_actionbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0, 0);
    }

    public static class SettingsFragment extends PreferenceFragment implements
            SharedPreferences.OnSharedPreferenceChangeListener {
        public SettingsFragment() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            final String versionName = BuildConfig.DEBUG ?
                    BuildConfig.VERSION_NAME + " (Debug)" :
                    BuildConfig.VERSION_NAME;

            final Locale currentLocale = Objects.requireNonNull(
                    SettingsUtils.fetchAppLanguage(getActivity()));
            final String currentLanguage = currentLocale
                    .getDisplayName(currentLocale);


            findPreference(SettingsUtils.PREF_APP_VERSION).setSummary(versionName);
            findPreference(SettingsUtils.PREF_APP_LANGUAGE).setSummary(currentLanguage);

            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_DATA_HISTORY));
            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_UPLOAD_RATE));
            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_TEMPERATURE_RATE));
            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_TEMPERATURE_WARNING));
            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_TEMPERATURE_HIGH));
            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_NOTIFICATIONS_PRIORITY));
            bindPreferenceSummaryToValue(findPreference(SettingsUtils.PREF_APP_LANGUAGE));

            SettingsUtils.registerOnSharedPreferenceChangeListener(getActivity(), this);
        }

        @Override
        public void onDestroy() {
            SettingsUtils.unregisterOnSharedPreferenceChangeListener(getActivity(), this);
            super.onDestroy();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            final Context context = getActivity().getApplicationContext();
            final GreenHubApp app = (GreenHubApp) getActivity().getApplication();
            final Preference preference = findPreference(key);

            switch (key) {
                case SettingsUtils.PREF_SAMPLING_SCREEN:
                    // Restart GreenHub Service with new settings
                    logI(TAG, "Restarting GreenHub Service because of preference changes");
                    app.stopGreenHubService();
                    app.startGreenHubService();
                    Answers.getInstance().logCustom(new CustomEvent("Preference Change")
                            .putCustomAttribute(
                                    "Sampling on Screen",
                                    String.valueOf(SettingsUtils.isSamplingScreenOn(context))
                            ));
                    break;
                case SettingsUtils.PREF_DATA_HISTORY:
                    bindPreferenceSummaryToValue(preference);
                    // Delete old data history
                    final int interval = SettingsUtils.fetchDataHistoryInterval(context);
                    new DeleteUsagesTask().execute(interval);
                    new DeleteSessionsTask().execute(interval);
                    break;
                case SettingsUtils.PREF_APP_LANGUAGE:
                    bindPreferenceSummaryToValue(preference);
                    updateAppLanguage();
                    break;
                case SettingsUtils.PREF_UPLOAD_RATE:
                case SettingsUtils.PREF_NOTIFICATIONS_PRIORITY:
                case SettingsUtils.PREF_TEMPERATURE_RATE:
                case SettingsUtils.PREF_TEMPERATURE_HIGH:
                /*
                case SettingsUtils.PREF_POWER_INDICATOR:
                    if (SettingsUtils.isPowerIndicatorShown(context)) {
                        Notifier.startStatusBar(context);
                        app.startStatusBarUpdater();
                    } else {
                        Notifier.closeStatusBar();
                        app.stopStatusBarUpdater();
                    }
                    Answers.getInstance().logCustom(new CustomEvent("Preference Change")
                            .putCustomAttribute(
                                    "Power Indicator",
                                    String.valueOf(SettingsUtils.isPowerIndicatorShown(context))
                            ));
                    break;
                */
                case SettingsUtils.PREF_TEMPERATURE_WARNING:
                    bindPreferenceSummaryToValue(preference);
                    break;
                case SettingsUtils.PREF_REMAINING_TIME:

                    break;
            }
        }

        private void updateAppLanguage(){
            Locale locale = SettingsUtils.fetchAppLanguage(getActivity());
            assert locale != null;
            Lingver.getInstance().setLocale(getActivity(), locale.getLanguage());
            // refresh screen
            Intent intent = getActivity().getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getActivity().finish();
            startActivity(intent);
        }

        private void bindPreferenceSummaryToValue(Preference preference) {
            String stringValue = PreferenceManager
                    .getDefaultSharedPreferences(preference.getContext())
                    .getString(preference.getKey(), "");

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);
            } else if (preference instanceof EditTextPreference) {
                EditTextPreference editTextPreference = (EditTextPreference) preference;
                stringValue = stringValue.replaceFirst("^0+(?!$)", "");

                editTextPreference.setText(stringValue);
                preference.setSummary(stringValue.replaceFirst("^0+(?!$)", ""));
            }
        }
    }
}
