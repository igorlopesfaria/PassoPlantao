package br.com.passoplantao.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.os.Build
import br.com.passoplantao.BuildConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class CommonsModule {
    @Binds
    abstract fun provideContext(application: Application): Context

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideIsDebug(): Boolean = BuildConfig.DEBUG

        @JvmStatic
        @Provides
        fun provideAppResources(application: Application): Resources = application.resources

        @JvmStatic
        @Provides
        @Named("IOScheduler")
        fun provideIOScheduler(): Scheduler = Schedulers.io()

        @JvmStatic
        @Provides
        @Named("MainScheduler")
        fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

        @JvmStatic
        @Provides
        @Singleton
        fun provideLocalePtBr(): Locale = Locale("pt", "BR")
    }
}