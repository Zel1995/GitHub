package com.example.simplegithub.domain.mapping

import com.example.simplegithub.domain.Storage.UserEntity
import com.example.simplegithub.domain.User

class UserMapperDbToUi {
    fun transform(userEntity: UserEntity?): User? {
        return with(userEntity) {
            this?.let{User(id, login, password, name)}
        }
    }

    fun transformList(users: List<UserEntity?>): List<User?> {
       return users.map { it?.let { User(it.id, it.login, it.password, it.name)}  }
    }
}