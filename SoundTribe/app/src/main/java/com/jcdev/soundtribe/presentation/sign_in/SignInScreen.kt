package com.jcdev.soundtribe.presentation.sign_in

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jcdev.soundtribe.R
import com.jcdev.soundtribe.presentation.common.AlertCustom
import com.jcdev.soundtribe.presentation.common.ButtonBasic
import com.jcdev.soundtribe.presentation.common.ImageBasic
import com.jcdev.soundtribe.presentation.common.LoadingScreen
import com.jcdev.soundtribe.presentation.common.OutlinedTextFieldBasic
import com.jcdev.soundtribe.presentation.common.SpacerComponent
import com.jcdev.soundtribe.presentation.common.TextBasic

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    onNavigationHome: () -> Unit
) {

    val state = viewModel.state
    val context = LocalContext.current

    LoadingScreen(showLoading = viewModel.state.isLoading)

    LaunchedEffect(key1 = Unit) {
        /*val worker = OneTimeWorkRequest.Builder(
            SyncWorkManager::class.java
        ).build()*/

        /*val worker = PeriodicWorkRequestBuilder<SyncWorkManager>(
            15,TimeUnit.MINUTES
        ).build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            "SyncData",
            ExistingPeriodicWorkPolicy.REPLACE,
            worker
        )*/
        //context.scheduleWork()
    }


    LaunchedEffect(key1 = state.success, key2 = state.error) {
        if (state.success != null) {
            onNavigationHome()
        }
        if (state.error != null) {
            viewModel.onEvent(SignInFormEvent.ShowDialog( isVisible = true))
        }

    }

    if(viewModel.formState.showDialog){
        AlertCustom(
            title = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                    append("Mensaje de error: ")
                }
                append(state.error ?: "")
            },
            dismiss = {
                viewModel.onEvent(SignInFormEvent.ShowDialog( isVisible = false))
            }
        )
    }




    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SignInHeader()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(start = 24.dp, end = 24.dp, top = 24.dp),
        ) {
            SignInContent(viewModel = viewModel)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
        ) {

        }


    }

}

@Composable
fun SignInHeader(modifier: Modifier = Modifier) {
    ImageBasic(
        image = R.drawable.logo,
        description = "logo",
        modifier = modifier.size(150.dp)
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInContent(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel
) {
    val focusManager = LocalFocusManager.current

    TextBasic(
        text = "Login", style = TextStyle(
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
    )
    SpacerComponent(modifier = Modifier.height(16.dp))
    OutlinedTextFieldBasic(
        text = viewModel.formState.email,
        onValueChange = {
            viewModel.onEvent(SignInFormEvent.EmailChange(it))
        },
        textLabel = "Email",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
                viewModel.onEvent(SignInFormEvent.OnFocusChange)
            }
        ),
        trailingIcon = {
            IconButton(onClick = {
                //email = ""
                viewModel.onEvent(SignInFormEvent.EmailChange(""))
            }) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear"
                )
            }
        },
        isError = viewModel.formState.emailError != null
    )
    if (viewModel.formState.emailError != null) {
        TextBasic(
            text = viewModel.formState.emailError ?: "",
            style = TextStyle(fontSize = 10.sp),
            color = Color.Red
        )
    }
    SpacerComponent(modifier = Modifier.height(8.dp))
    OutlinedTextFieldBasic(
        text = viewModel.formState.password,
        onValueChange = {
            //password = it
            viewModel.onEvent(SignInFormEvent.PasswordChange(it))
        },
        textLabel = "Password",
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {

            }
        ),
        trailingIcon = {
            IconButton(onClick = {
                //visualTransformation = !visualTransformation
                viewModel.onEvent(SignInFormEvent.VisualTransformationChange(!viewModel.formState.visualTransformation))
            }) {
                Icon(
                    imageVector = if (viewModel.formState.visualTransformation) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = "Visibility"
                )
            }
        },
        visualTransformation = if (viewModel.formState.visualTransformation) PasswordVisualTransformation() else VisualTransformation.None,
        isError = false
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        ButtonBasic(
            text = "Ingresar",
            onClick = {
                //viewModel.signIn(email,password)
                viewModel.onEvent(SignInFormEvent.Submit)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
    }
}