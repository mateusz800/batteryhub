package com.hmatalonga.greenhub.ui.welcome

sealed class WelcomeIntent {
    object ShowTOS: WelcomeIntent()
    object AcceptTOS: WelcomeIntent()
    object DeclineTOS: WelcomeIntent()
}