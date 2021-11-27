package com.example.simplegithub.domain

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface LoginRepository {

    fun getUsers(): Flowable<List<User?>>
    fun addUser(user: User): Single<Long>
    fun getByLogin(login: String): Maybe<User>
}