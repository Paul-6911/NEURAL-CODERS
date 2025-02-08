package com.jcdev.soundtribe.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.gson.Gson

@Composable
fun SetupNavigationMenu(
    navController: NavHostController,
    paddingValues: PaddingValues,
    onChangeVisibleBottomBar: (Boolean) -> Unit

) {

    /*NavHost(
        navController = navController,
        startDestination = Musicians
    ) {
        composable<Musicians> {

            onChangeVisibleBottomBar(true)

            DishesScreen(
                paddingValues = paddingValues,
                onClick = {
                    val dishJson = Gson().toJson(it)
                    navController.navigate(Detail(dishJson = dishJson))
                }
            )
        }
        composable<Search> {
            onChangeVisibleBottomBar(true)
            RecipeScreen(paddingValues = paddingValues)
        }
        composable<Settings> {
            onChangeVisibleBottomBar(true)
            //SettingScreen(paddingValues = paddingValues)
            RecipesRegisterScreen(paddingValues = paddingValues)
        }
        composable<Detail> {
            onChangeVisibleBottomBar(false)
            val data = it.toRoute<Detail>()
            val dish = Gson().fromJson(data.dishJson, Dish::class.java)
            requireNotNull(dish)
            DetailScreen(dish = dish)
        }
        //composable(route = ScreenMenu.RecipesRegister.route){
        //    RecipesRegisterScreen(paddingValues = paddingValues)
        //}
    }*/

}