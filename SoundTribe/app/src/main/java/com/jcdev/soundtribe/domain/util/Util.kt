package com.jcdev.soundtribe.domain.util

object Util {
    fun isValidEmail(correo: String): Boolean {
        // Expresión regular para validar correos electrónicos
        val regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex()
        return regex.matches(correo)
    }
}