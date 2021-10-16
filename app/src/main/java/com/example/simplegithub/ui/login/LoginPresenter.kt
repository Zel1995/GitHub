package com.example.simplegithub.ui.login

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.domain.User
import com.github.terrakok.cicerone.Router
import kotlinx.coroutines.*

class LoginPresenter(
    private val loginRepository: LoginRepository,
    private val router: Router
) : LoginContract.Presenter() {
    private val handler = CoroutineExceptionHandler { _, _ ->
        viewState.setState(LoginContract.ViewBehavior.ERROR)
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(LoginContract.ViewBehavior.IDLE)
    }
    override fun onTryLogin(login: String, password: String) {
        MainScope().launch {
            viewState.setState(LoginContract.ViewBehavior.LOADING)
            val user = withContext(Dispatchers.IO) {
                delay(1000)
                loginRepository.getByLogin(login)
            }
            setViewStateByUserData(user, password)
        }
    }

    private fun setViewStateByUserData(user: User?, password: String) {
        if (user != null) {
            if (user.password == password) {
                router.newRootScreen(Screens.main(user.login))
                viewState.setState(LoginContract.ViewBehavior.SUCCESS)
            } else {
                viewState.setAuthError(LoginContract.Error.PASSWORD)
                viewState.setState(LoginContract.ViewBehavior.IDLE)
            }
        } else {
            viewState.setAuthError(LoginContract.Error.LOGIN)
            viewState.setState(LoginContract.ViewBehavior.ERROR)
        }

    }

    override fun onRegistration() {
        router.navigateTo(Screens.registration())
    }

    override fun onForgotPassword() {
        //open restore password fragment
    }

}
