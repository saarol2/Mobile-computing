package com.example.composetutorial.data.repository

import com.example.composetutorial.data.room.UserDao
import com.example.composetutorial.data.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUsername(userId: Int): Flow<User> {
        return userDao.getUsername(userId)
    }
}