package com.example.composetutorial

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class SensorHandler(
    context: Context,
    private val onLightLevelChanged: (Float) -> Unit
) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val lightSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

    init {
        lightSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_LIGHT) {
            Log.d("TAG", "onSensorChanged")
            val lightLevel = event.values[0]
            onLightLevelChanged(lightLevel)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Not needed
    }
}
