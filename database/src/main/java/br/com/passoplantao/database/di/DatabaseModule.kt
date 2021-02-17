package br.com.passoplantao.database.di

import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration

@Module
class DatabaseModule {

    @Provides
    fun providesRealmName() : String =
        DATABASE_NAME

    @Provides
    fun providesUserRealmModule() : UserRealmModelModule =
        UserRealmModelModule()

    @Provides
    fun provideRealmConfig(name : String,
                           userRealmModelModule: UserRealmModelModule
    ) : RealmConfiguration {
        try {
            return RealmConfiguration.Builder()
                .name(name)
                .modules(userRealmModelModule)
                .deleteRealmIfMigrationNeeded()
                .build()
        } catch (e: Exception) {
            Realm.deleteRealm(
                RealmConfiguration.Builder()
                    .name(name)
                    .build())

            return RealmConfiguration.Builder()
                .name(name)
                .modules(userRealmModelModule)
                .deleteRealmIfMigrationNeeded()
                .build()
        }
    }

    @Provides
    fun provideRealm(
        configuration: RealmConfiguration
    ): Realm {
        return try {
            Realm.getInstance(configuration)
        } catch (e: Exception) {
            Realm.deleteRealm(
                RealmConfiguration.Builder()
                    .name(configuration.realmFileName)
                    .build()
            )
            Realm.getInstance(configuration)
        }
    }

    companion object{
        private const val DATABASE_NAME = "passoplantaodatabasename.realm"
    }
}