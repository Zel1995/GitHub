package com.example.simplegithub.domain.mapping

import com.example.simplegithub.domain.Storage.UserEntity
import com.example.simplegithub.domain.User

class UserMapperUiToDb {
    fun transform(user: User): UserEntity {
        return with(user) {
            UserEntity(id, login, password, name)
        }
    }
}