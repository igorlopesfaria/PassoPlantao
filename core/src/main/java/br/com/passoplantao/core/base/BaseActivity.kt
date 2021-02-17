package br.com.passoplantao.core.base

import br.com.passoplantao.core.base.delegate.ViewModelDelegate
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelFactory

    inline fun <reified VM : BaseViewModel> appViewModel(): ViewModelDelegate<VM> =
        ViewModelDelegate(VM::class, this) { this.vmFactory }
}
