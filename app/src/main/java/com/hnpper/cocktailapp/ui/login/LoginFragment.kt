package com.hnpper.cocktailapp.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.navigator
import com.firebase.ui.auth.AuthUI
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.ui.home.HomeFragment
import com.hnpper.cocktailapp.ui.registration.RegistrationFragment
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.nav_header_main.*

class LoginFragment : RainbowCakeFragment<LoginViewState, LoginViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_login

    private lateinit var sharedPref: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        btnLogin.setOnClickListener {
            val email: String = tietEmail.text.toString().trim()
            val password: String = tietPassword.text.toString().trim()

            if (email.isBlank() && password.isBlank()) {
                tilEmail.error = getString(R.string.error_field_empty)
                tilPassword.error = getString(R.string.error_field_empty)
            } else if (email.isBlank()) {
                tilEmail.error = getString(R.string.error_field_empty)
            } else if (password.isBlank()) {
                tilPassword.error = getString(R.string.error_field_empty)
            } else {
                tilEmail.isErrorEnabled = false
                tilPassword.isErrorEnabled = false

                viewModel.signInWithEmail(email, password)
            }
        }

        tvRegister.setOnClickListener {
           findNavController().navigate(R.id.nav_registration)
        }
    }

    override fun render(viewState: LoginViewState) {
        when (viewState) {
            is LoginStarted -> {
                progressBarLogin.visibility = View.GONE
            }
            is LoginReady -> {

            }
            is LoginSuccessful -> {
                progressBarLogin.visibility = View.GONE
                //username.text = viewState.user.name
                //usermail.text = viewState.user.email

                findNavController().navigate(R.id.nav_search)
            }
            is LoginUnsuccessful -> {
                progressBarLogin.visibility = View.GONE

                Toast.makeText(
                    requireContext(),
                    "Failure!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is Loading -> {
                progressBarLogin.visibility = View.VISIBLE
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
