package com.example.simplegithub.domain.Storage

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(indices = arrayOf(Index(value = ["login"],unique = true)))
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val login: String,
    val password: String,
    val name: String
)