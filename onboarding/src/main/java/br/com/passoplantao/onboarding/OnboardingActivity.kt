package br.com.passoplantao.onboarding

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.passoplantao.core.base.BaseActivity
import br.com.passoplantao.core.base.delegate.navProvider
import br.com.passoplantao.onboarding.databinding.OnboardingActivityBinding
import dagger.android.support.DaggerAppCompatActivity


class OnboardingActivity: BaseActivity() {
    private lateinit var binding: OnboardingActivityBinding

    private val navController by navProvider(R.id.onboarding_navhost_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.onboarding_activity)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.onboarding_login -> finish()
            else -> onSupportNavigateUp()
        }
    }

}