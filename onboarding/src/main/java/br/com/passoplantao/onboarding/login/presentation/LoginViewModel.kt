package br.com.passoplantao.onboarding.login.presentation

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import br.com.passoplantao.core.base.BaseViewModel
import br.com.passoplantao.core.extensions.isValidEmail
import br.com.passoplantao.core.extensions.isValidPassword
import br.com.passoplantao.core.result.Error
import br.com.passoplantao.core.result.Result
import br.com.passoplantao.onboarding.R
import br.com.passoplantao.onboarding.login.data.LoginRepository
import br.com.passoplantao.onboarding.login.presentation.model.LoginUserUIModel
import br.com.passoplantao.onboarding.login.presentation.state.LoginAuthenticationState
import br.com.passoplantao.uitoolkit.button.LoaderButton
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named


class LoginViewModel @Inject constructor(
    val repository: LoginRepository,
    @Named("MainScheduler") private val mainScheduler: Scheduler,
    @Named("IOScheduler") private val ioScheduler: Scheduler
) : BaseViewModel() {

    val loginStateLiveData = MutableLiveData<LoginAuthenticationState>()
    val buttonStateLiveData = MutableLiveData<LoaderButton.State>()
    val validatePasswordLiveData = MutableLiveData<Boolean>()
    val validateEmailLiveData = MutableLiveData<Boolean>()
    val showPasswordLiveData = MutableLiveData<Boolean>()
    private var email: String = ""
    private var password: String = ""

    override fun onCreate() {
        buttonStateLiveData.value = LoaderButton.State.DISABLED
        showPasswordLiveData.value = false
    }

    fun doAuthentication() {
        validateEmail()
        validatePassword()
        if (email.isValidEmail() && password.isValidPassword()) {
            compositeDisposable.add(repository.authentication(email, password)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .doOnSubscribe {
                    buttonStateLiveData.value = LoaderButton.State.LOADING
                }
                .subscribe({ repositoryResult ->
                    when (repositoryResult) {
                        is Result.Success -> {
                            buttonStateLiveData.value = LoaderButton.State.NORMAL
                            if(repositoryResult.data.crmApproved)
                                loginStateLiveData.value = LoginAuthenticationState.SuccessAuthenticationCRMApproved
                            else
                                loginStateLiveData.value = LoginAuthenticationState.SuccessAuthenticationCRMUnapproved
                        }
                        is Result.Failure -> {
                            buttonStateLiveData.value = LoaderButton.State.NORMAL
                            loginStateLiveData.value = if(repositoryResult.error is Error.ItemNotFoundError)
                                LoginAuthenticationState.FailureAuthentication
                            else
                                LoginAuthenticationState.FailureServer
                        }
                    }
                }, { thwoable ->
                    Log.e("TESTE", thwoable.message.toString())
                    buttonStateLiveData.value = LoaderButton.State.NORMAL
                    loginStateLiveData.value = LoginAuthenticationState.FailureServer
                }))
        }
    }

    fun shouldShowPassword() {
        showPasswordLiveData.value?.let {
            showPasswordLiveData.value != showPasswordLiveData.value
        }

    }

    fun emailChanged(text: String) {
        email = text
        buttonStateLiveData.value = if (text.isValidEmail() && password.isNotEmpty())
            LoaderButton.State.NORMAL
        else
            LoaderButton.State.DISABLED
    }
    fun onTargetFocusChanged(view: View, hasFocus: Boolean){
        if(!hasFocus && view.id == R.id.emailTextInputEditText) validateEmail()
        else if(!hasFocus && view.id == R.id.passwordTextInputEditText) validatePassword()
    }

    fun passwordChanged(text: String) {
        password = text
        buttonStateLiveData.value = if (text.isNotBlank() && email.isValidEmail())
            LoaderButton.State.NORMAL
        else
            LoaderButton.State.DISABLED
    }

    private fun validateEmail() {
        validateEmailLiveData.value = email.isValidEmail()
    }

    private fun validatePassword() {
        validatePasswordLiveData.value = password.isNotEmpty()
    }
}