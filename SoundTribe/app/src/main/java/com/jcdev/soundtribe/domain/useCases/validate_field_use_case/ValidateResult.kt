package com.jcdev.soundtribe.domain.useCases.validate_field_use_case

data class ValidateResult(
    val successful:Boolean,
    val errorMessage:String?=null
)