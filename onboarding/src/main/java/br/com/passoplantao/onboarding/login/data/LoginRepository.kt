package br.com.passoplantao.onboarding.login.data

import android.util.Log
import br.com.passoplantao.onboarding.login.data.model.request.LoginRequest
import br.com.passoplantao.onboarding.login.data.model.response.LoginResponse
import br.com.passoplantao.onboarding.login.data.model.response.LoginUserResponse
import br.com.passoplantao.onboarding.login.data.service.LoginService
import io.reactivex.Single
import retrofit2.Response
import br.com.passoplantao.core.result.Result
import br.com.passoplantao.core.result.Error
import br.com.passoplantao.database.entity.UserEntity
import io.realm.Realm
import java.lang.Exception
import javax.inject.Inject

interface LoginRepository {
    fun authentication(email: String, password: String): Single<Result<LoginUserResponse>>
}

class LoginRepositoryImpl @Inject constructor(private val service: LoginService): LoginRepository {

    override fun authentication(email: String, password: String): Single<Result<LoginUserResponse>> =
        service.authentication(LoginRequest(email, password))
            .map {
                it.toResult()
            }

    private fun Response<LoginResponse>.toResult(): Result<LoginUserResponse> =
        if (this.code() == 200 && this.body() != null) {
            try {
                saveUser(this.body()!!.user)
                Result.Success(data = this.body()!!.user)
            } catch (e: Exception) {
                Log.e("TESTE", "asdasdasd")
                Result.Failure(Error.DatabaseInternError)
            }


        } else if (this.code() == 400) {
            Result.Failure(Error.ItemNotFoundError)
        } else {
            Result.Failure(Error.ServerInternError)
        }

    private fun saveUser(user: LoginUserResponse) {
        Realm.getDefaultInstance().insert(UserEntity(
            id = user.id,
            name = user.name,
            surname = user.surname,
            email = user.email,
            crmApproved = user.crmApproved,
            crm = user.crm,
            specialityId = user.specialtyId,
            phone = user.phone
        ))
    }

}

