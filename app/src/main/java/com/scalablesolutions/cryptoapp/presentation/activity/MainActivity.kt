package com.scalablesolutions.cryptoapp.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.scalablesolutions.cryptoapp.presentation.screen.Screen
import com.scalablesolutions.cryptoapp.presentation.screen.assetlist.AssetsListScreen
import com.scalablesolutions.cryptoapp.presentation.ui.theme.ScalableSolutionsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScalableSolutionsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AssetsListScreen.route
                    ) {
                        composable(
                            route = Screen.AssetsListScreen.route
                        ) {
                            AssetsListScreen(navController)
                        }
//                        composable(
//                            route = Screen.CoinDetailScreen.route + "/{coinId}"
//                        ) {
//                            CoinDetailScreen()
//                        }
                    }
                }
            }
        }
    }
}