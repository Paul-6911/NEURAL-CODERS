package com.jcdev.soundtribe.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jcdev.soundtribe.presentation.home.HomeScreen
import com.jcdev.soundtribe.presentation.on_boarding.OnBoardingScreen
import com.jcdev.soundtribe.presentation.sign_in.SignInScreen
import com.jcdev.soundtribe.presentation.welcome.WelcomeScreen

@Composable
fun SetupNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Welcome
    ){
        composable<Welcome> {
            WelcomeScreen {
                navController.popBackStack()
                navController.navigate(OnBoarding)
            }
        }
        composable<OnBoarding> {
            OnBoardingScreen {
                navController.popBackStack()
                navController.navigate(SignIn)
            }
        }
        composable<SignIn> {
            SignInScreen {
                navController.navigate(Home)
            }
        }
        composable<Home> {
            HomeScreen()
        }
    }
}