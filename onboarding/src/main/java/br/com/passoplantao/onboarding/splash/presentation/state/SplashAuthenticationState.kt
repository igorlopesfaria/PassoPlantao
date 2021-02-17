package br.com.passoplantao.onboarding.splash.presentation.state

import br.com.passoplantao.onboarding.splash.presentation.model.SplashUserUIModel

sealed class SplashAuthenticationState {
    data class SuccessAuthentication(val data: SplashUserUIModel): SplashAuthenticationState()
    object FailureAuthentication: SplashAuthenticationState()
}
