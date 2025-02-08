package com.jcdev.soundtribe.data.repository

import android.content.SharedPreferences
import com.jcdev.soundtribe.core.Result
import com.jcdev.soundtribe.domain.model.User
import com.jcdev.soundtribe.domain.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named

class AuthRepositoryImp @Inject constructor(
    @Named("provideSharePreferencesEncrypted") val sharedPreferences: SharedPreferences,

): AuthRepository {
    override suspend fun signIn(email: String): Result<User> {
        return Result.Success(User(1, "jalfaro@gmail.com"))
    }
}