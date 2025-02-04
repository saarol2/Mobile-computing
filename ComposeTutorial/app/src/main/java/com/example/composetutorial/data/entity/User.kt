package com.example.composetutorial.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int = 1,
    val username: String
)