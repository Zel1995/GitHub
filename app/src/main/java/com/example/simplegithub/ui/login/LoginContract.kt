package com.example.simplegithub.ui.login

import moxy.MvpPresenter
import moxy.MvpView
import moxy.viewstate.strategy.alias.Skip

abstract class LoginContract {
    enum class ViewBehavior{
        IDLE,LOADING,SUCCESS,ERROR
    }
    enum class Error{
        LOGIN,PASSWORD
    }
    interface View :MvpView{
        @Skip
        fun setState(state: ViewBehavior)
        @Skip
        fun setAuthError(error:Error)
        @Skip
        fun setError(err:Throwable)
    }

    abstract class Presenter: MvpPresenter<View>() {
        abstract fun onTryLogin(login:String,password:String)
        abstract fun onRegistration()
        abstract fun onForgotPassword()
    }
}