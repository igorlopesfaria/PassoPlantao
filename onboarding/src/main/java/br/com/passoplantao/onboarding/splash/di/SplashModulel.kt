package br.com.passoplantao.onboarding.splash.di

import androidx.lifecycle.ViewModel
import br.com.passoplantao.core.base.ViewModelKey
import br.com.passoplantao.onboarding.splash.data.SplashRepository
import br.com.passoplantao.onboarding.splash.data.SplashRepositoryImpl
import br.com.passoplantao.onboarding.splash.presentation.SplashFragment
import br.com.passoplantao.onboarding.splash.presentation.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class SplashModule {
    @ContributesAndroidInjector
    abstract fun nameSplashFragment(): SplashFragment

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    abstract fun bindSplashRepository(repository: SplashRepositoryImpl): SplashRepository
}
