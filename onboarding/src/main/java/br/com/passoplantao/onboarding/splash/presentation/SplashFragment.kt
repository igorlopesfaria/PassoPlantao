package br.com.passoplantao.onboarding.splash.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import br.com.passoplantao.core.base.BaseFragment
import br.com.passoplantao.onboarding.R
import br.com.passoplantao.onboarding.databinding.OnboardingSplashFragmentBinding
import br.com.passoplantao.onboarding.splash.presentation.state.SplashAuthenticationState

class SplashFragment: BaseFragment() {
    private val vm by appViewModel<SplashViewModel>()
    private lateinit var binding : OnboardingSplashFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.onboarding_splash_fragment, container, false)
        binding.lifecycleOwner = this
        binding.vm = vm
        return binding.root
    }

    private fun setupObservers(){

        vm.splashStateLiveData.observe(this,  { splashAuthenticationState ->
            when(splashAuthenticationState){
                is SplashAuthenticationState.FailureAuthentication -> {
                    NavHostFragment.findNavController(this).navigate(
                        SplashFragmentDirections.actionLogin()
                    )
                }
                is SplashAuthenticationState.SuccessAuthentication -> {

                }
            }

        })

    }
}