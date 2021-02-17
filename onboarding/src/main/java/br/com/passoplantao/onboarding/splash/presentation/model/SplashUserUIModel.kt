package br.com.passoplantao.onboarding.splash.presentation.model

import br.com.passoplantao.database.entity.UserEntity
import br.com.passoplantao.onboarding.login.data.model.response.LoginUserResponse

class SplashUserUIModel(
    val id: Long,
    val name: String,
    val surname: String
){
    constructor(entity: UserEntity) : this(
        id= entity.id,
        name = entity.name,
        surname = entity.surname
    )
}
