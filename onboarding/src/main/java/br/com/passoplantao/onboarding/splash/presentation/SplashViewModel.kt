package br.com.passoplantao.onboarding.splash.presentation

import androidx.lifecycle.MutableLiveData
import br.com.passoplantao.core.base.BaseViewModel
import br.com.passoplantao.core.result.Result
import br.com.passoplantao.onboarding.splash.data.SplashRepository
import br.com.passoplantao.onboarding.splash.presentation.model.SplashUserUIModel
import br.com.passoplantao.onboarding.splash.presentation.state.SplashAuthenticationState
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Inject
import javax.inject.Named

class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository,
    @Named("MainScheduler") private val mainScheduler: Scheduler,
    @Named("IOScheduler") private val ioScheduler: Scheduler
) : BaseViewModel() {

    val splashStateLiveData = MutableLiveData<SplashAuthenticationState>()

    override fun onCreate() {
        super.onCreate()
        doAuthenticationDB()
    }

    private fun doAuthenticationDB() {
        compositeDisposable.add(
            splashRepository.authenticationDB()
                .delay(3, SECONDS)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(
                    { repositoryResult ->
                        when (repositoryResult) {
                            is Result.Success -> {
                                if (repositoryResult.data.crmApproved)
                                    splashStateLiveData.value = SplashAuthenticationState.SuccessAuthentication(SplashUserUIModel(repositoryResult.data))
                                else
                                    splashStateLiveData.value = SplashAuthenticationState.FailureAuthentication
                            }
                            is Result.Failure -> {
                                splashStateLiveData.value = SplashAuthenticationState.FailureAuthentication
                            }
                        }
                    },
                    {
                        splashStateLiveData.value = SplashAuthenticationState.FailureAuthentication
                    }
                )
        )
    }
}