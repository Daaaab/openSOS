package com.meowsoft.opensos.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.meowsoft.opensos.ui.theme.OpenSOSTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }

    @Composable
    private fun ContentView() {
        val navController: NavHostController = rememberNavController()

        OpenSOSTheme {
            NavHost(
                navController = navController,
                startDestination = Destinations.startDestination
            ) {
                Destinations.list.fastForEach { destination ->
                    composable(destination.route) {

                        destination.Content(navController)
                    }
                }
            }
        }
    }
}
