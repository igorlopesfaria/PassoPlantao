package br.com.passoplantao.di

import androidx.lifecycle.ViewModelProvider
import br.com.passoplantao.core.base.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}