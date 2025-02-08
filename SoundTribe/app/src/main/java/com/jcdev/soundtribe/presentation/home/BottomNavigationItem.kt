package com.jcdev.soundtribe.presentation.home

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem<T>(
    val title:String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route:T,
    val hasNews:Boolean,
    val badgeCount:Int?=null,
    val currentRoute:String
)
