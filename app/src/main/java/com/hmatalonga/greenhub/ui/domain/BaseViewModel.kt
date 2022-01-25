package com.hmatalonga.greenhub.ui.domain

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.hmatalonga.greenhub.util.LogUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(app: Application): AndroidViewModel(app) {
    protected val TAG
        get() =  LogUtils.makeLogTag(this::class.java)

    protected val context
        get() = getApplication<Application>()
}