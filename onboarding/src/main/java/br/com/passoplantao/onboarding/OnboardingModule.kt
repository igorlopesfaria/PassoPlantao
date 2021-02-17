package br.com.passoplantao.onboarding

import br.com.passoplantao.onboarding.login.di.LoginModule
import br.com.passoplantao.onboarding.splash.di.SplashModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnboardingModule{
    @ContributesAndroidInjector(
        modules = [
            SplashModule::class,
            LoginModule::class
        ]
    )
    internal abstract fun bindOnboardingActivity(): OnboardingActivity

//    @Binds
//    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}
