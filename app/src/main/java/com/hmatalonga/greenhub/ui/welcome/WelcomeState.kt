package com.hmatalonga.greenhub.ui.welcome

sealed class WelcomeState {
    object WelcomeScreen: WelcomeState()
    object TermsOfServiceScreen: WelcomeState()
    object TosAccepted: WelcomeState()
    object TosDeclined: WelcomeState()
}