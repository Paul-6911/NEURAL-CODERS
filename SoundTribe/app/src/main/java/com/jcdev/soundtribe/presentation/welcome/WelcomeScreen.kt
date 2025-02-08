package com.jcdev.soundtribe.presentation.welcome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jcdev.soundtribe.R
import com.jcdev.soundtribe.presentation.common.ButtonBasic
import com.jcdev.soundtribe.presentation.common.ImageBasic
import com.jcdev.soundtribe.presentation.common.SpacerComponent
import com.jcdev.soundtribe.presentation.common.TextBasic
import com.jcdev.soundtribe.preview.PreviewDefault

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {

            WelcomeHeader()

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ) {
            WelcomeContent(
                onClick = {
                    onClick()
                }
            )
        }
    }

}

@Composable
fun WelcomeContent(
    onClick: () -> Unit
) {

    ImageBasic(
        image = R.drawable.fondo_inicio,
        description = "Fondo inicial",
        modifier = Modifier.fillMaxWidth()
    )


    ButtonBasic(
        text = "Empezar",
        modifier = Modifier
            .padding(bottom = 16.dp),
        onClick = {
            onClick()
        },
        containerColor = Color.White,
        contentColor = Color.Black
    )



}

@Composable
fun WelcomeHeader() {

    Column {
        ImageBasic(
            image = R.drawable.logo,
            description = "ecoeats logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
        )
        SpacerComponent(modifier = Modifier.padding(top = 8.dp))

        TextBasic(
            text = "Conéctate con la música, conéctate con el mundo.",
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }


}

@PreviewDefault
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onClick = {})
}