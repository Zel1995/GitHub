package com.example.simplegithub.ui.login

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.domain.User
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class LoginPresenter(
    private val loginRepository: LoginRepository,
    private val router: Router
) : LoginContract.Presenter() {
    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setState(LoginContract.ViewBehavior.IDLE)
    }

    override fun onTryLogin(login: String, password: String) {
        viewState.setState(LoginContract.ViewBehavior.LOADING)
        compositeDisposable += loginRepository.getByLogin(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.password == password) {
                    router.newRootScreen(Screens.main(it.login))
                    viewState.setState(LoginContract.ViewBehavior.SUCCESS)
                }else{
                    viewState.setAuthError(LoginContract.Error.PASSWORD)
                    viewState.setState(LoginContract.ViewBehavior.IDLE)
                }
            }, {
                viewState.setError(it)
            }, {
                viewState.setAuthError(LoginContract.Error.LOGIN)
                viewState.setState(LoginContract.ViewBehavior.ERROR)
            })
    }

    override fun onRegistration() {
        router.navigateTo(Screens.registration())
    }

    override fun onForgotPassword() {
        //open restore password fragment
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}
