package com.hmatalonga.greenhub.ui.welcome

import android.app.Application
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.*
import com.hmatalonga.greenhub.ui.MainActivity
import com.hmatalonga.greenhub.ui.domain.BaseViewModel
import com.hmatalonga.greenhub.util.LogUtils
import com.hmatalonga.greenhub.util.SettingsUtils
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class WelcomeViewModel @Inject constructor(app: Application): BaseViewModel(app) {
    private val _appState = MutableLiveData<WelcomeState>(WelcomeState.WelcomeScreen)
    val appState: LiveData<WelcomeState>
        get() = _appState

    val intent = Channel<WelcomeIntent>(Channel.UNLIMITED)

    init {
        if(SettingsUtils.isTosAccepted(context)) {
            startNextActivity()
        } else {
            handleIntent()
        }
    }

    /**
     * handle user intents
     */
    private fun handleIntent() {
        viewModelScope.launch {
            intent.consumeAsFlow().collect {
                when(it){
                    is WelcomeIntent.AcceptTOS -> acceptTOS()
                    is WelcomeIntent.DeclineTOS -> declineTOS()
                    is WelcomeIntent.ShowTOS -> _appState.postValue(WelcomeState.TermsOfServiceScreen)
                }
            }
        }
    }

    /**
     * Accept terms of service and proceed to the next activity
     */
    private fun acceptTOS(){
        SettingsUtils.markTosAccepted(context, true)
        startNextActivity()
    }

    /**
     * Exit the app
     */
    private fun declineTOS(){
        LogUtils.logD(TAG, "Closing app")
        _appState.postValue(WelcomeState.TosDeclined)
    }

    /**
     * Proceed to the next activity.
     */
    private fun startNextActivity(){
        LogUtils.logD(TAG, "Proceeding to next activity")
        val intent = Intent(context, MainActivity::class.java)
            .addFlags(FLAG_ACTIVITY_NEW_TASK)
            .addFlags(FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(context, intent, null)
    }

    fun showWelcomeScreen(){
        _appState.postValue(WelcomeState.WelcomeScreen)
    }
}