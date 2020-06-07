package com.example.kotlinlogin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlinlogin.R
import kotlinx.android.synthetic.main.main_fragment.*

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitButton.setOnClickListener {
            viewModel.onSubmit(
                email = emailEditText.text.toString(),
                password = passwordEditText.text.toString()
            )
        }
        viewModel.stateLiveData.observe(viewLifecycleOwner, Observer { viewState ->
            when (viewState) {
                LoginViewState.Idle -> {
                    progressView.isGone = true
                    emailEditText.error = null
                    passwordEditText.error = null
                    emailEditText.isEnabled = true
                    passwordEditText.isEnabled = true
                    submitButton.isEnabled = true
                }

                LoginViewState.Progress -> {
                    progressView.isVisible = true
                    emailEditText.isEnabled = false
                    passwordEditText.isEnabled = false
                    submitButton.isEnabled = false
                }

                is LoginViewState.Failed -> {
                    progressView.isGone = true
                    emailEditText.error = viewState.message
                    emailEditText.isEnabled = true
                    passwordEditText.isEnabled = true
                    passwordEditText.error = viewState.message
                    submitButton.isEnabled = true

                }

                is LoginViewState.Succedd -> {
                    progressView.isGone = true
                    emailEditText.error = null
                    emailEditText.isEnabled = false
                    passwordEditText.isEnabled = false
                    submitButton.isEnabled = false
                    message.text = viewState.message

                    (view.getContext() as FragmentActivity).supportFragmentManager.beginTransaction()
                        .replace( R.id.container, RecyclerViewFragment.newInstance())
                        .commit()

                }

            }


        })
    }


}



