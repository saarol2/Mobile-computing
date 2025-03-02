package com.example.composetutorial

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composetutorial.ui.Conversation
import com.example.composetutorial.ui.SettingsScreen
import com.example.composetutorial.ui.VideoPlayerScreen

@Composable
fun MainNavigation(
    appState: AppState = rememberAppState(),
    context: Context
) {
    NavHost(
        navController = appState.navController,
        startDestination = Routes.conversation
    ) {
        composable(route = Routes.conversation) {
            Conversation(navController = appState.navController, SampleData.conversationSample)
        }

        composable(route = Routes.settings) {
            SettingsScreen(navController = appState.navController, context = context)
        }

        composable(route = Routes.video) {
            VideoPlayerScreen(navController = appState.navController)
        }
    }
}