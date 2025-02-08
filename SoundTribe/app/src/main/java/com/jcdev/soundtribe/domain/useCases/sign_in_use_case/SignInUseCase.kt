package com.jcdev.soundtribe.domain.useCases.sign_in_use_case

import com.jcdev.soundtribe.core.Result
import com.jcdev.soundtribe.domain.model.User
import com.jcdev.soundtribe.domain.repository.AuthRepository
import com.jcdev.soundtribe.domain.useCases.validate_field_use_case.ValidateFieldUseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
    val validateFieldUseCase: ValidateFieldUseCase
) {

    suspend operator fun invoke(email: String): Result<User>{
        val emailValidation = validateFieldUseCase(email)
        if (!emailValidation.successful){
            return Result.Validation(message = emailValidation.errorMessage ?: "")
        }

        return repository.signIn(email)
    }
}