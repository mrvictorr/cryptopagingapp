package com.scalablesolutions.cryptoapp.presentation.screen

sealed class Screen(val route: String) {
    object AssetsListScreen: Screen("assets_list_screen")
}
