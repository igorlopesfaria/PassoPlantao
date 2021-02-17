package br.com.passoplantao.di

import android.app.Application
import br.com.passoplantao.application.PassoPlantaoApplication
import br.com.passoplantao.database.di.DatabaseModule
import br.com.passoplantao.onboarding.OnboardingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        CommonsModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        ApplicationModule::class,
        OnboardingModule::class
    ]
)
interface AppComponent : AndroidInjector<DaggerApplication> {
    fun inject(application: PassoPlantaoApplication)

    override fun inject(instance: DaggerApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
