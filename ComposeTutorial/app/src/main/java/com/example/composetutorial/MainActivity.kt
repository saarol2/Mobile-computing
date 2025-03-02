package com.example.composetutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {

    private lateinit var sensorHandler: SensorHandler
    private lateinit var notificationHandler: NotificationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHandler = NotificationHandler(this)
        sensorHandler = SensorHandler(this) { lightLevel ->
            handleLightLevelChange(lightLevel)
        }
        setContent {
            ComposeTutorialTheme {
                MainNavigation(context = applicationContext)
            }
        }
    }

    private fun handleLightLevelChange(lightLevel: Float) {
        if (lightLevel < 10) {
            notificationHandler.sendNotification(
                "Low Light Detected",
                "The ambient light level is low ($lightLevel lux). Tap to open the app."
            )
        }
    }
}
