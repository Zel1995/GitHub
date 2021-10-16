package com.example.simplegithub.domain.mapping

import com.example.simplegithub.domain.Storage.UserEntity
import com.example.simplegithub.domain.User
import io.reactivex.Flowable
import io.reactivex.Maybe

class UserMapperDbToUi {
    fun transform(userEntity: Maybe<UserEntity>): Maybe<User> {
        return userEntity.map { entity ->
            User(
                entity.id,
                entity.login,
                entity.password,
                entity.name
            )
        }
    }

    fun transformList(users: Flowable<List<UserEntity?>>): Flowable<List<User?>> {
        return users.map {
            it.map { userEntity ->
                userEntity?.let { User(it.id, it.login, it.password, it.name) }
            }
        }
    }
}