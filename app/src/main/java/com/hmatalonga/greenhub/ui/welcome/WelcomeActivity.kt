package com.hmatalonga.greenhub.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.hmatalonga.greenhub.ui.MainActivity
import com.hmatalonga.greenhub.ui.theme.BatteryhubTheme
import com.hmatalonga.greenhub.ui.welcome.views.TermsOfServiceView
import com.hmatalonga.greenhub.util.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : ComponentActivity() {
    companion object {
        val TAG = LogUtils.makeLogTag(this::class.java)!!
    }


    private val viewModel: WelcomeViewModel by lazy {
        ViewModelProvider(this).get(WelcomeViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val appState = viewModel.appState.observeAsState()
            BatteryhubTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    when (appState.value) {
                        is WelcomeState.WelcomeScreen -> WelcomeView(viewModel)
                        else -> TermsOfServiceView(viewModel)

                    }
                }
            }
        }
        observeActivityResult()
    }

    override fun onBackPressed() {
        if(viewModel.appState.value == WelcomeState.TermsOfServiceScreen){
            viewModel.showWelcomeScreen()
        } else {
            super.onBackPressed()
        }
    }

    private fun observeActivityResult() {
        viewModel.appState.observe(this) {
            when (it) {
                is WelcomeState.TosDeclined -> finish()
                is WelcomeState.TosAccepted -> startMainActivity()
                else -> {/* do nothing */
                }
            }
        }
    }

    private fun startMainActivity() {
        LogUtils.logD(TAG, "Proceeding to next activity")
        val intent = Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        ContextCompat.startActivity(this, intent, null)
    }
}