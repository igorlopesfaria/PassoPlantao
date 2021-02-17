package br.com.passoplantao.core.base.delegate

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.passoplantao.core.base.BaseViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

class ActivityViewModelFragmentDelegate<T : BaseViewModel>(
    private val clazz: KClass<T>,
    private val vmFactory: () -> ViewModelProvider.Factory
) : ReadOnlyProperty<Fragment, T> {

    var cache: T? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return cache?.let {
            it
        } ?: run {
            val activity = thisRef.requireActivity()
            ViewModelProvider(activity, vmFactory.invoke()).get(clazz.java).apply {
                activity.lifecycle.addObserver(this)
            }.also {
                cache = it
            }
        }
    }
}