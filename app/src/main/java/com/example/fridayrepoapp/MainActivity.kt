package com.example.fridayrepoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fridayrepoapp.data.Product
import com.example.fridayrepoapp.ui.screens.DetailScreen
import com.example.fridayrepoapp.ui.screens.HomeScreen
import com.example.fridayrepoapp.ui.theme.FridayRepoAppTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FridayRepoAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "homeScreen") {
                    composable(route = "homeScreen") {
                        HomeScreen(navController = navController)
                    }
                    composable(
                        route = "detailScreen/{product}",
                        arguments = listOf(
                            navArgument("product") { type = NavType.StringType }
                        )
                    ) { backStackEntry ->
                        val productJson = backStackEntry.arguments?.getString("product")
                        val product = Gson().fromJson(productJson, Product::class.java)
                        DetailScreen(product = product, navController = navController)
                    }
                }
            }
        }
    }
}
