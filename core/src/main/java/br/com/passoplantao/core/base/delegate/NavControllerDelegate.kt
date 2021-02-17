package br.com.passoplantao.core.base.delegate

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlin.reflect.KProperty

fun AppCompatActivity.navProvider(@IdRes idRes: Int) =
    NavControllerProviderDelegate(idRes)

fun Fragment.navProvider() =
    NavControllerProviderDelegate(0)

class NavControllerProviderDelegate(@IdRes private val idRes: Int) {

    operator fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): NavController {
        return thisRef.findNavController(idRes)
    }

    operator fun getValue(thisRef: Fragment, property: KProperty<*>): NavController {
        return thisRef.findNavController()
    }
}
