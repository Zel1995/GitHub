package com.example.simplegithub.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplegithub.di.MainSubcomponent
import com.example.simplegithub.R
import com.example.simplegithub.di.App
import com.example.simplegithub.di.modules.MainActivityModule
import com.example.simplegithub.ui.login.Screens
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var router:Router
    @Inject
    lateinit var navigatorHolder:NavigatorHolder
    lateinit var mainSubcomponent: MainSubcomponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainSubcomponent = (application as App).appComponent.getMainSubcomponent().create(
            MainActivityModule(this)
        )
        mainSubcomponent.injectMainActivity(this)
        if (savedInstanceState == null){
            router.navigateTo(Screens.login())
        }

    }
    private val navigator = AppNavigator(this, R.id.main_container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

}