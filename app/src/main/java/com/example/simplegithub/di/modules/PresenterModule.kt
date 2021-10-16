package com.example.simplegithub.di.modules

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.ui.login.LoginContract
import com.example.simplegithub.ui.login.LoginPresenter
import com.example.simplegithub.ui.registration.RegistrationContract
import com.example.simplegithub.ui.registration.RegistrationPresenter
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    @Provides
    fun provideLoginPresenter(
        repository: LoginRepository,
        router: Router
    ): LoginContract.Presenter = LoginPresenter(repository, router)

    @Provides
    fun provideRegistrationPresenter(repository: LoginRepository): RegistrationContract.Presenter =
        RegistrationPresenter(repository)
}