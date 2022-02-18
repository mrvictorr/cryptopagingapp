package com.victorr.cryptoapp.presentation.screen

sealed class Screen(val route: String) {
    object AssetsListScreen : Screen("assets_list_screen")
    object AssetDetailScreen : Screen("asset_detail_screen")
}
