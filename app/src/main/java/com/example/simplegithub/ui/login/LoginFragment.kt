package com.example.simplegithub.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.simplegithub.R
import com.example.simplegithub.databinding.FragmentLoginBinding
import com.example.simplegithub.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class LoginFragment : MvpAppCompatFragment(R.layout.fragment_login), LoginContract.View {
    @Inject
    lateinit var presenterMoxy: LoginContract.Presenter
    private val presenter by moxyPresenter { presenterMoxy }
    private lateinit var viewBinding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentLoginBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            enterBtn.setOnClickListener {
                presenter.onTryLogin(
                    loginEdit.text.toString(),
                    passwordEdit.text.toString()
                )
            }
            registrationBtn.setOnClickListener {
                presenter.onRegistration()
            }
            forgotPasswordBtn.setOnClickListener {
                presenter.onForgotPassword()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? MainActivity)?.mainSubcomponent?.injectLoginFragment(this)
    }

    override fun setState(state: LoginContract.ViewBehavior) {
        hideViews()
        when (state) {
            LoginContract.ViewBehavior.IDLE -> {
                viewBinding.contentLayout.visibility = View.VISIBLE
            }
            LoginContract.ViewBehavior.SUCCESS -> {
                viewBinding.contentLayout.visibility = View.VISIBLE
                Toast.makeText(context, getString(R.string.success), Toast.LENGTH_SHORT).show()
            }
            LoginContract.ViewBehavior.ERROR -> {
                viewBinding.contentLayout.visibility = View.VISIBLE
                Snackbar.make(viewBinding.root, getString(R.string.error), Snackbar.LENGTH_SHORT)
                    .show()
            }
            LoginContract.ViewBehavior.LOADING -> {
                viewBinding.contentLayout.visibility = View.INVISIBLE
                viewBinding.progress.visibility = View.VISIBLE
            }
        }
    }


    private fun hideViews() {
        viewBinding.contentLayout.visibility = View.INVISIBLE
        viewBinding.progress.visibility = View.GONE
    }

    override fun setAuthError(error: LoginContract.Error) {
        when (error) {
            LoginContract.Error.LOGIN -> viewBinding.loginEdit.setError(getErrorMsgByCode(error))
            LoginContract.Error.PASSWORD -> viewBinding.passwordEdit.setError(
                getErrorMsgByCode(error)
            )
        }

    }

    private fun getErrorMsgByCode(error: LoginContract.Error): String {
        return when (error) {
            LoginContract.Error.LOGIN -> getString(R.string.login_error)
            LoginContract.Error.PASSWORD -> getString(R.string.password_error)
        }
    }

}