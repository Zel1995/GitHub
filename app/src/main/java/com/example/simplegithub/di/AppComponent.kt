package com.example.simplegithub.di

import com.example.simplegithub.di.modules.ApplicationModule
import com.example.simplegithub.di.modules.DataBaseModule
import com.example.simplegithub.di.modules.RepositoryModule
import com.example.simplegithub.di.modules.RouterModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, ApplicationModule::class, DataBaseModule::class, RouterModule::class])
interface AppComponent {

    fun getMainSubcomponent(): MainSubcomponent.Factory
}