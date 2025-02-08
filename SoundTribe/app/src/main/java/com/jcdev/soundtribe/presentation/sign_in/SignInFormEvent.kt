package com.jcdev.soundtribe.presentation.sign_in

sealed class SignInFormEvent {
    data class EmailChange(val email:String) : SignInFormEvent()
    data class PasswordChange(val password:String): SignInFormEvent()
    data class VisualTransformationChange(val visualTransformation:Boolean): SignInFormEvent()
    data object Submit: SignInFormEvent()
    data object OnFocusChange : SignInFormEvent()
    data class ShowDialog(val isVisible:Boolean) : SignInFormEvent()
}