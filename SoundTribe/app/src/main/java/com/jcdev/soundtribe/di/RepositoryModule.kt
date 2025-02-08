package com.jcdev.soundtribe.di

import android.content.SharedPreferences
import com.jcdev.soundtribe.data.repository.AuthRepositoryImp
import com.jcdev.soundtribe.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        @Named("provideSharePreferencesEncrypted") sharedPreferences: SharedPreferences,
    ): AuthRepository {
        return AuthRepositoryImp(sharedPreferences)
    }
}