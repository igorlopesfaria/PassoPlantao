package br.com.passoplantao.onboarding.login.presentation.model

import br.com.passoplantao.onboarding.login.data.model.response.LoginUserResponse

class LoginUserUIModel(
    val id: Long,
    val name: String,
    val surname: String,
    val phone: String,
    val email: String,
    val crmApproved: Boolean
){
    constructor(response: LoginUserResponse) : this(
        id= response.id,
        name = response.name,
        surname = response.surname,
        phone = response.phone,
        email= response.email,
        crmApproved = response.crmApproved
    )
}
