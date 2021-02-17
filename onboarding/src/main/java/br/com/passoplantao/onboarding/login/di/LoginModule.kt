package br.com.passoplantao.onboarding.login.di

import androidx.lifecycle.ViewModel
import br.com.passoplantao.core.base.ViewModelKey
import br.com.passoplantao.onboarding.login.data.LoginRepository
import br.com.passoplantao.onboarding.login.data.LoginRepositoryImpl
import br.com.passoplantao.onboarding.login.data.service.LoginService
import br.com.passoplantao.onboarding.login.presentation.LoginFragment
import br.com.passoplantao.onboarding.login.presentation.LoginViewModel
import br.com.passoplantao.onboarding.splash.presentation.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit


@Module
abstract class LoginModule {
    @ContributesAndroidInjector
    abstract fun loginFragment(): LoginFragment

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(SplashViewModel::class)
//    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideLoginService(retrofit: Retrofit): LoginService =
            retrofit.create(LoginService::class.java)
    }
}

