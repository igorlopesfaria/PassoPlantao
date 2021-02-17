package br.com.passoplantao.onboarding.login.data.service

import br.com.passoplantao.onboarding.login.data.model.request.LoginRequest
import br.com.passoplantao.onboarding.login.data.model.response.LoginResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("/credentials")
    fun authentication(@Body loginRequest: LoginRequest) : Single<Response<LoginResponse>>
}

//@POST("users")
//suspend fun signUpAsync(@Body request: SignUpRequest): SignUpResponse
//
//@GET("credentials/{email}")
//suspend fun resetPasswordAsync(@Path(value = "email") email: String): ResetPasswordResponse
//
//@GET("specialties")
//suspend fun fetchSpecialitiesAsync(): List<SpecialityResponse>