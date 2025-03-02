package com.example.composetutorial

import android.content.Context
import androidx.room.Room
import com.example.composetutorial.data.repository.TaskRepository
import com.example.composetutorial.data.repository.UserRepository
import com.example.composetutorial.data.room.AppDatabase

object Graph {
    lateinit var database: AppDatabase
        private set

    lateinit var appContext: Context
        private set

    val userRepository by lazy {
        UserRepository(database.userDao())
    }

    val taskRepository by lazy {
        TaskRepository(database.taskDao())
    }

    fun provide(context: Context) {
        appContext = context
        database = Room.databaseBuilder(context, AppDatabase::class.java, "data.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}
