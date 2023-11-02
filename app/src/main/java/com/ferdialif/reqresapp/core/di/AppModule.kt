package com.ferdialif.reqresapp.core.di

import com.ferdialif.reqresapp.domain.api.Login
import com.ferdialif.reqresapp.domain.api.LoginImpl
import com.ferdialif.reqresapp.domain.api.PersonData
import com.ferdialif.reqresapp.domain.api.PersonModelImpl
import com.ferdialif.reqresapp.domain.repository.LoginRepository
import com.ferdialif.reqresapp.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesClientApi(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    @Provides
    @Singleton
    fun providesLoginApi(client: HttpClient): Login {
        return LoginImpl(client)
    }

    @Provides
    @Singleton
    fun providesRequestUser(client: HttpClient): PersonData {
        return PersonModelImpl(client)
    }

    @Provides
    @Singleton
    fun providesPagingRepository(personData: PersonData): PersonRepository {
        return PersonRepository(personData)
    }

    @Provides
    @Singleton
    fun providesLoginRepository(login: Login): LoginRepository {
        return LoginRepository(login)
    }
}