package br.com.passoplantao.database.entity

import io.realm.RealmObject

open class UserEntity(
    var id: Long = 0L,
    var name: String = "",
    var surname: String = "",
    var phone: String = "",
    var email: String = "",
    var specialityId: Int = 0,
    var crm: String = "",
    var crmApproved: Boolean = false
): RealmObject()
