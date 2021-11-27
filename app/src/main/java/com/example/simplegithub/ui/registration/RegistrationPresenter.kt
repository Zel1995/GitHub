package com.example.simplegithub.ui.registration

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.domain.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class RegistrationPresenter(private val repository: LoginRepository) :
    RegistrationContract.Presenter() {
    val compositeDisposable = CompositeDisposable()


    override fun onRegistration(user: User) {
        compositeDisposable += repository.addUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it == -1L) {
                    viewState.setRegistrationConflict()
                } else {
                    viewState.setRegistrationSuccess()
                }
            }, {
                viewState.setError(it)
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}