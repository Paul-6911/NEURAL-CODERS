package com.jcdev.soundtribe.domain.repository

import com.jcdev.soundtribe.core.Result
import com.jcdev.soundtribe.domain.model.User

interface AuthRepository {
    suspend fun signIn(email:String): Result<User>
}