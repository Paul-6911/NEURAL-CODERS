package com.jcdev.soundtribe.presentation.on_boarding

import androidx.annotation.DrawableRes
import com.jcdev.soundtribe.R

data class OnBoardingPage(
    @DrawableRes
    val image:Int,
    val title:String,
    val description:String
)

fun getDataOnboarding():List<OnBoardingPage>{
    return listOf(
        OnBoardingPage(
            image = R.drawable.imagen_1,
            title = "Conecta con la Música",
            description = "Descubre nuevos talentos y conecta con músicos y fans de todo el mundo.\n" +
                    "Vive la música en cada interacción."
        ),
        OnBoardingPage(
            image = R.drawable.imagen_2,
            title = "Promociona Tu Talento",
            description = "Comparte tus creaciones y alcanza el reconocimiento que mereces.\n" +
                    "Nuestra plataforma te impulsa para que tu música llegue a más oídos."
        ),
        OnBoardingPage(
            image = R.drawable.imagen_3,
            title = "Únete a la Comunidad",
            description = "Forma parte de una red innovadora que conecta a músicos y fans con seguridad y confianza.\n" +
                    "Juntos, transformamos el mundo de la música."
        ),
    )
}