package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ComposeTutorialTheme {
                NavHost(navController = navController, startDestination = Routes.conversation) {
                    composable(Routes.conversation) {
                        Conversation(navController, SampleData.conversationSample)
                    }
                    composable(Routes.settings) {
                        SettingsScreen(navController)
                    }
                }
            }
        }
    }
}
