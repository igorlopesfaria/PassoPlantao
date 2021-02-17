package br.com.passoplantao.onboarding.login.data.model.response

import com.squareup.moshi.Json

data class LoginResponse(
    val token: String,
    val user: LoginUserResponse
)

class LoginUserResponse(
    val id: Long,
    val name: String,
    val surname: String,
    @Json(name="cell_phone") val phone: String,
    val email: String,
    @Json(name="crm_approved") val crmApproved: Boolean,
    val crm: String,
    @Json(name = "specialty_id") val specialtyId: Int
)