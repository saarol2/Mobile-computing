package com.example.composetutorial.data.repository

import com.example.composetutorial.data.room.TaskDao
import com.example.composetutorial.data.entity.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun addTask(task: Task) {
        taskDao.insertTask(task)
    }

    fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}
