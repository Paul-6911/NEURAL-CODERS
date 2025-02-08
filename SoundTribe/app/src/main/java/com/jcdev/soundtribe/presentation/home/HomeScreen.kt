package com.jcdev.soundtribe.presentation.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jcdev.soundtribe.navigation.Explore
import com.jcdev.soundtribe.navigation.Musicians
import com.jcdev.soundtribe.navigation.Search
import com.jcdev.soundtribe.navigation.SetupNavigationMenu
import com.jcdev.soundtribe.presentation.common.TopBarComponent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    val items = listOf(
        BottomNavigationItem(
            title = "Musicians",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            hasNews = false,
            route = Musicians,
            currentRoute = "com.jotadev.jetcompose_2024_ii_ecoeats.navigation.Musicians"
        ),
        BottomNavigationItem(
            title = "Search",
            selectedIcon = Icons.Filled.Search,
            unSelectedIcon = Icons.Outlined.Search,
            hasNews = false,
            badgeCount = 4,
            route = Search,
            currentRoute = "com.jotadev.jetcompose_2024_ii_ecoeats.navigation.Search"
        ),
        BottomNavigationItem(
            title = "Explore",
            selectedIcon = Icons.Filled.GridView,
            unSelectedIcon = Icons.Outlined.GridView,
            hasNews = true,
            route = Explore,
            currentRoute = "com.jotadev.jetcompose_2024_ii_ecoeats.navigation.Explore"
        )
    )

    var selectedItemIndex by remember {
        mutableStateOf(0)
    }

    val navController = rememberNavController()

    var bottomBarVisible by remember {
        mutableStateOf(true)
    }

    val currentRoute = navController.currentBackStackEntryAsState()?.value?.destination?.route
    println(currentRoute)
    LaunchedEffect(key1 = currentRoute){
        selectedItemIndex = items.indexOfFirst {
            it.currentRoute == currentRoute
        }.takeIf { it != -1 } ?: 0
        println("$selectedItemIndex")
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarComponent(
                imageVector = if(bottomBarVisible) Icons.Filled.Menu else Icons.AutoMirrored.Filled.ArrowBack,
                onIconClick = {
                    if(!bottomBarVisible){
                        bottomBarVisible = true
                        navController.popBackStack()
                    }
                }
            )
        },
        bottomBar = {
            if(bottomBarVisible) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                                navController.navigate(item.route) {
                                    //popUpTo(navController.graph.findStartDestination().id){
                                    //    saveState = true
                                    //}
                                    launchSingleTop = true
                                    restoreState = true

                                }
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (item.badgeCount != null) {
                                            Badge {
                                                Text(text = item.badgeCount.toString())
                                            }
                                        } else if (item.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (index == selectedItemIndex)
                                            item.selectedIcon
                                        else
                                            item.unSelectedIcon,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->

        SetupNavigationMenu(
            navController = navController,
            paddingValues = paddingValues,
            onChangeVisibleBottomBar = {
                bottomBarVisible = it
            }
        )
    }

}