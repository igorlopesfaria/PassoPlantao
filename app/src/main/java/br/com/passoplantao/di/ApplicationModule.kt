package br.com.passoplantao.di

import android.app.Application
import android.content.ContentResolver
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    fun provideContentResolver(application: Application): ContentResolver = application.contentResolver

    @Provides
    @Singleton
    fun providesPackageManager(application: Application): PackageManager = application.packageManager

}