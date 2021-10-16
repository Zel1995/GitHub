package com.example.simplegithub.ui.login

import com.example.simplegithub.ui.registration.RegistrationFragment
import com.example.simplegithub.ui.main.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
    fun main(login: String) = FragmentScreen { MainFragment.newInstance(login) }
    fun registration() = FragmentScreen { RegistrationFragment() }
    fun login() = FragmentScreen { LoginFragment() }
}