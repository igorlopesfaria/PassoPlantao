package br.com.passoplantao.onboarding.splash.data

import br.com.passoplantao.core.result.Error
import br.com.passoplantao.core.result.Result
import br.com.passoplantao.database.entity.UserEntity
import io.reactivex.*
import io.realm.Realm
import javax.inject.Inject

interface SplashRepository {
    fun authenticationDB(): Observable<Result<UserEntity>>
}

class SplashRepositoryImpl @Inject constructor(): SplashRepository {
    override fun authenticationDB(): Observable<Result<UserEntity>> {
        return Observable.fromCallable {
            try {
                val userEntity = Realm.getDefaultInstance().where(UserEntity::class.java).findFirst()
                userEntity?.let {
                    return@fromCallable Result.Success(data = userEntity)
                } ?: run {
                    return@fromCallable Result.Failure(error = Error.ItemNotFoundError)
                }
            } catch (e: Exception){
                return@fromCallable Result.Failure(error = Error.DatabaseInternError)
            }
        }
    }
}

