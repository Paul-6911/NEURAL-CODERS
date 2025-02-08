package com.jcdev.soundtribe.presentation.sign_in

data class SignInFormState (
    val email:String="",
    val emailError:String?=null,
    val password:String="",
    val visualTransformation: Boolean = true,
    val showDialog:Boolean = false
)