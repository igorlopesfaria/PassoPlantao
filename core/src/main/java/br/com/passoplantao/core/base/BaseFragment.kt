package br.com.passoplantao.core.base

import br.com.passoplantao.core.base.delegate.ActivityViewModelFragmentDelegate
import br.com.passoplantao.core.base.delegate.FragmentViewModelDelegate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class BaseFragment : DaggerFragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    /**
     * Create a Activity View Model, a shared view model between Activity and Fragment
     */
    inline fun <reified VM : BaseViewModel> appActivityViewModel(): ActivityViewModelFragmentDelegate<VM> =
        ActivityViewModelFragmentDelegate(VM::class) {
            this.requireBaseActivity().vmFactory
        }

    /**
     * Create a Fragment own View Model
     */
    inline fun <reified VM : BaseViewModel> appViewModel(): FragmentViewModelDelegate<VM> =
        FragmentViewModelDelegate(VM::class, this) { factory }

    fun requireBaseActivity() = (this.requireActivity() as BaseActivity)
}
