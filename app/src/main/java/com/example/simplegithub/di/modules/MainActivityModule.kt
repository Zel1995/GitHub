package com.example.simplegithub.di.modules

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val activity:AppCompatActivity) {
    @Provides
    fun provideFragmentManager():FragmentManager = activity.supportFragmentManager
}