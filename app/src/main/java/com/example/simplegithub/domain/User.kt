package com.example.simplegithub.domain

data class User(
    val id: Long = 0,
    val login: String,
    val password: String,
    val name: String
)