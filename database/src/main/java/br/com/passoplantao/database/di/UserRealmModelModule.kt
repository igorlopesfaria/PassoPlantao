package br.com.passoplantao.database.di

import br.com.passoplantao.database.entity.UserEntity
import io.realm.annotations.RealmModule

@RealmModule(classes = [UserEntity::class], library = true)
class UserRealmModelModule