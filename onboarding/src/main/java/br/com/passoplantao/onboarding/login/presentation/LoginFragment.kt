package br.com.passoplantao.onboarding.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.com.passoplantao.core.base.BaseFragment
import br.com.passoplantao.onboarding.R
import br.com.passoplantao.onboarding.databinding.OnboardingLoginFragmentBinding
import br.com.passoplantao.onboarding.login.presentation.state.LoginAuthenticationState

class LoginFragment: BaseFragment() {
    private val vm by appViewModel<LoginViewModel>()
    private lateinit var binding : OnboardingLoginFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.onboarding_login_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.vm = vm
        return binding.root
    }

    private fun setupObservers(){
        vm.loginStateLiveData.observe(this, { state ->
            when (state) {
                is LoginAuthenticationState.SuccessAuthenticationCRMApproved -> {
                }
                is LoginAuthenticationState.SuccessAuthenticationCRMUnapproved -> {
                }
                is LoginAuthenticationState.FailureAuthentication -> {
                }
                is LoginAuthenticationState.FailureServer -> {
                }
            }
        })

        vm.buttonStateLiveData.observe(this, { state ->
            binding.authenticationButton.setState(state)
        })

        vm.validateEmailLiveData.observe(this, { validEmail ->
        })

        vm.validatePasswordLiveData.observe(this, { validEmßail ->
        })

        vm.showPasswordLiveData.observe(this, { showPassword ->
        })


    }
}