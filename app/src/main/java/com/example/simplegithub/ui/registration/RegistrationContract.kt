package com.example.simplegithub.ui.registration

import com.example.simplegithub.domain.User
import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

class RegistrationContract {
    interface View :MvpView{
        @Skip
        fun setError()
        @Skip
        fun completeRegistration(userId:Long)
    }

    abstract class Presenter: MvpPresenter<View>() {
        abstract fun onRegistration(user: User)
    }
}