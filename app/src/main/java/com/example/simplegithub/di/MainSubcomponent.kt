package com.example.simplegithub.di

import com.example.simplegithub.di.modules.MainActivityModule
import com.example.simplegithub.di.modules.PresenterModule
import com.example.simplegithub.ui.login.LoginFragment
import com.example.simplegithub.ui.registration.RegistrationFragment
import com.example.simplegithub.ui.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [PresenterModule::class, MainActivityModule::class])
interface MainSubcomponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(mainActivityModule: MainActivityModule): MainSubcomponent
    }

    fun injectMainActivity(activity: MainActivity)
    fun injectLoginFragment(loginFragment: LoginFragment)
    fun inject(registrationFragment: RegistrationFragment)
}