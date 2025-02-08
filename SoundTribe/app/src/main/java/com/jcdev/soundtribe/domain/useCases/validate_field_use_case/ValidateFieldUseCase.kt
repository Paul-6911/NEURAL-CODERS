package com.jcdev.soundtribe.domain.useCases.validate_field_use_case

import com.jcdev.soundtribe.domain.util.Util

class ValidateFieldUseCase {

    operator fun invoke(email:String): ValidateResult {
        if(email.isEmpty()){
            return ValidateResult(
                successful = false,
                errorMessage = "El campo correo deber tener algun dato"
            )
        }
        if(!Util.isValidEmail(email)){
            return ValidateResult(
                successful = false,
                errorMessage = "El campo correo deber tener un formato correcto"
            )
        }
        return ValidateResult(
            successful = true
        )
    }

}