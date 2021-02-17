package br.com.passoplantao.onboarding.login.presentation.state

import br.com.passoplantao.onboarding.login.presentation.model.LoginUserUIModel

sealed class LoginAuthenticationState {
    object SuccessAuthenticationCRMApproved: LoginAuthenticationState()
    object SuccessAuthenticationCRMUnapproved: LoginAuthenticationState()
    object FailureAuthentication: LoginAuthenticationState()
    object FailureServer: LoginAuthenticationState()
}
