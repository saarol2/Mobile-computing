package com.example.composetutorial

import SampleData
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composetutorial.ui.SettingsScreen
import com.example.composetutorial.ui.ToDoScreen

@Composable
fun MainNavigation(
    appState: AppState = rememberAppState(),
    context: Context
) {
    NavHost(
        navController = appState.navController,
        startDestination = Routes.todo
    ) {
        composable(route = Routes.todo) {
            ToDoScreen(navController = appState.navController, SampleData.conversationSample)
        }

        composable(route = Routes.settings) {
            SettingsScreen(navController = appState.navController, context = context)
        }
    }
}