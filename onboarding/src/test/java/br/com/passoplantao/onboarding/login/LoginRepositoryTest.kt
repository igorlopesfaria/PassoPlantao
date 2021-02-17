package br.com.passoplantao.onboarding.login

import br.com.passoplantao.onboarding.login.data.LoginRepository
import br.com.passoplantao.onboarding.login.data.LoginRepositoryImpl
import br.com.passoplantao.onboarding.login.data.model.request.LoginRequest
import br.com.passoplantao.onboarding.login.data.model.response.LoginResponse
import br.com.passoplantao.onboarding.login.data.model.response.LoginUserResponse
import br.com.passoplantao.onboarding.login.data.service.LoginService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryTest {
    private lateinit var repository: LoginRepository

    @Mock
    private lateinit var service: LoginService

    @Before
    fun setUp() {
        repository = LoginRepositoryImpl(service)
    }

    @Test
    fun `should authenticate`() {
        given(service.authentication(loginRequest = loginRequestMock))
            .willReturn(Single.just(Response.success(loginResponseMock)))

        repository.authentication(
            email = "frank.sinatra@orquestra.com.br",
            password = "123456"
        )

        verify(service).authentication(loginRequest = loginRequestMock)
    }

    private val loginRequestMock = LoginRequest("frank.sinatra@orquestra.com.br", "123456")

    private val responseLoginMock: Response<LoginResponse>
        get() = Response.success(this.loginResponseMock)

    private val loginResponseMock = LoginResponse(
        token = "abc123456", 
        user =  LoginUserResponse(
            id = 123456,
            name = "Frank",
            surname = "Sinatra",
            email = "frank.sinatra@orquestra.com.br",
            phone = "11999999999",
            crmApproved = true        
        )
    )
}
