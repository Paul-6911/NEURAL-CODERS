package com.jcdev.soundtribe.presentation.sign_in

import com.jcdev.soundtribe.domain.model.User

data class SignInState (
    val isLoading:Boolean = false,
    val error:String? = null,
    val success: User? = null
)