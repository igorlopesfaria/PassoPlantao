package br.com.passoplantao.application

import android.content.Context
import androidx.multidex.MultiDex
import br.com.passoplantao.di.AppComponent
import br.com.passoplantao.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm

class PassoPlantaoApplication : DaggerApplication() {
    private lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)

        return appComponent
    }
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Realm.init(this)
    }
}