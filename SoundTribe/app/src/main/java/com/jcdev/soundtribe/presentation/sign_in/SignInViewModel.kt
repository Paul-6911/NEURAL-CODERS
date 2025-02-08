package com.jcdev.soundtribe.presentation.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcdev.soundtribe.core.Result
import com.jcdev.soundtribe.domain.useCases.sign_in_use_case.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    val signInUseCase: SignInUseCase
): ViewModel() {

    //Estados componentes
    var formState by mutableStateOf(SignInFormState())

    //Estados pantalla
    var state by mutableStateOf(SignInState())

    //Eventos
    fun onEvent(event: SignInFormEvent){
        when(event){
            is SignInFormEvent.EmailChange -> formState = formState.copy(email = event.email)
            SignInFormEvent.OnFocusChange -> formState = formState.copy(emailError = null)
            is SignInFormEvent.PasswordChange -> formState = formState.copy(password = event.password)
            is SignInFormEvent.ShowDialog -> formState = formState.copy(showDialog = event.isVisible)
            SignInFormEvent.Submit -> signIn()
            is SignInFormEvent.VisualTransformationChange -> formState = formState.copy(visualTransformation = event.visualTransformation)
        }
    }

    private fun signIn(){

        state = state.copy( isLoading = true )

        viewModelScope.launch {
            delay(3000)

            try{
                val response = withContext(Dispatchers.IO){
                    signInUseCase(formState.email)
                }

                when(response){
                    is Result.Error -> state = state.copy(isLoading = false, error = response.message, success = null)
                    is Result.Success -> state = state.copy(isLoading = false, success = response.data, error = null)
                    is Result.Validation -> {
                        formState = formState.copy(emailError = response.message)
                        state = state.copy(isLoading = false)
                    }
                }

            }catch (e: Exception){
                state = state.copy(error = e.message)
            }finally {
                state = state.copy(isLoading = false)
            }
        }

    }

}