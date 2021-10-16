package com.example.simplegithub.impl

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.domain.Storage.UserDao
import com.example.simplegithub.domain.User
import com.example.simplegithub.domain.mapping.UserMapperDbToUi
import com.example.simplegithub.domain.mapping.UserMapperUiToDb
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

class LoginRepositoryImpl(
    private val userDao: UserDao,
    private val userMapperDbToUi: UserMapperDbToUi,
    private val userMapperUiToDb: UserMapperUiToDb
) : LoginRepository {

    override fun getUsers(): Flowable<List<User?>> {
        return userMapperDbToUi.transformList(userDao.getAllUsers())
    }

    override fun addUser(user: User): Single<Long> {
        return userDao.addNewUser(userMapperUiToDb.transform(user))
    }

    override fun getByLogin(login: String): Maybe<User> {
        val result = userDao.findByLogin(login)
        return userMapperDbToUi.transform(result)
    }


}