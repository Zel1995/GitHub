package com.example.simplegithub.ui.registration

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.simplegithub.domain.User
import com.example.simplegithub.R
import com.example.simplegithub.databinding.FragmentRegistrationBinding
import com.example.simplegithub.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RegistrationFragment : MvpAppCompatFragment(R.layout.fragment_registration),
    RegistrationContract.View {
    @Inject
    lateinit var presenterMoxy: RegistrationContract.Presenter
    private val presenter by moxyPresenter { presenterMoxy }
    lateinit var viewBinding: FragmentRegistrationBinding
    override fun setError(err:Throwable) {
        Toast.makeText(requireContext(), err.message, Toast.LENGTH_SHORT).show()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentRegistrationBinding.bind(view)
        initViews()
    }

    private fun initViews() {
        viewBinding.regBtn.setOnClickListener {
            presenter.onRegistration(initUser())
        }
    }

    private fun initUser(): User {
        with(viewBinding) {
            return User(
                0,
                regLogin.text.toString(),
                regPassword.text.toString(),
                regName.text.toString()
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as? MainActivity)?.mainSubcomponent?.inject(this)

    }

    private fun clearInputs() {
        with(viewBinding) {
            regLogin.setText("")
            regName.setText("")
            regPassword.setText("")
        }
    }

    override fun setRegistrationSuccess() {
        clearInputs()
        Snackbar.make(viewBinding.root, getString(R.string.create_new_acc), Snackbar.LENGTH_SHORT)
            .show()
    }

    override fun setRegistrationConflict() {
        clearInputs()
        Snackbar.make(
            viewBinding.root,
            getString(R.string.login_is_exist),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}