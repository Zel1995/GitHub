package com.example.simplegithub.ui.main

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.simplegithub.R
import com.example.simplegithub.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {
    companion object {
        const val LOGIN_KEY = "LOGIN_KEY"
        fun newInstance(login: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(LOGIN_KEY, login)
                }
            }
    }

    lateinit var viewBinding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentMainBinding.bind(view)
        view.findViewById<ImageView>(R.id.image_view).animate().setDuration(1000).rotationBy(360f)
            .rotationYBy(360f)
        viewBinding.appTv.text = arguments?.getString(LOGIN_KEY)

    }
}