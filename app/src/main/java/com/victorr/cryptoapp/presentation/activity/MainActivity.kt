package com.victorr.cryptoapp.presentation.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.victorr.cryptoapp.presentation.screen.Screen
import com.victorr.cryptoapp.presentation.screen.assetlist.AssetsListScreen
import com.victorr.cryptoapp.presentation.ui.theme.victorrTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            victorrTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AssetsListScreen.route
                    ) {
                        composable(
                            route = Screen.AssetsListScreen.route
                        ) {
                            AssetsListScreen()
                        }
                    }
                }
            }
        }
    }
}