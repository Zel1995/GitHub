package com.example.simplegithub.ui.registration

import com.example.simplegithub.domain.LoginRepository
import com.example.simplegithub.domain.User
import kotlinx.coroutines.*

class RegistrationPresenter(private val repository: LoginRepository) :
    RegistrationContract.Presenter() {
    private val handler = CoroutineExceptionHandler { _, _ ->
        viewState.setError()
    }


    override fun onRegistration(user: User) {
        MainScope().launch(handler) {
            val result = withContext(Dispatchers.IO) {
                repository.addUser(user)
            }
            viewState.completeRegistration(result)
        }
    }


}