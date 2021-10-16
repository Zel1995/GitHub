package com.example.simplegithub.di.modules

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.domain.Storage.UserDao
import com.example.simplegithub.domain.mapping.UserMapperDbToUi
import com.example.simplegithub.domain.mapping.UserMapperUiToDb
import com.example.simplegithub.impl.LoginRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideLoginRepository(userDao: UserDao): LoginRepository = LoginRepositoryImpl(
        userDao,
        UserMapperDbToUi(),
        UserMapperUiToDb()
    )
}