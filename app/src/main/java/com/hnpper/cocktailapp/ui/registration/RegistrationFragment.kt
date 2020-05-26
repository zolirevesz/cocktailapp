package com.hnpper.cocktailapp.ui.registration

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.navigation.BackPressAware
import co.zsmb.rainbowcake.navigation.navigator
import com.hnpper.cocktailapp.R
import com.hnpper.cocktailapp.model.User
import com.hnpper.cocktailapp.ui.home.HomeFragment
import com.hnpper.cocktailapp.utilities.RegexProvider
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : RainbowCakeFragment<RegistrationViewState, RegistrationViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_registration

    private lateinit var sharedPreferences: SharedPreferences
    private var permissionsGranted = false

    private lateinit var user: User
    private var userPassword = ""
    private var photoUri: Uri? = null

    private var regex = RegexProvider()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user = User("","","","","", mutableListOf())

        setupLoginDataViews()
    }

    override fun onStart() {
        super.onStart()

        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        permissionsGranted =
            sharedPreferences.getBoolean(getString(R.string.permissions_granted), false)
    }

    private fun setupLoginDataViews() {
        btnRegister.setOnClickListener {
            val email = tietEmail.text.toString().trim()
            val password = tietPassword1.text.toString().trim()
            val confirmPassword = tietPassword2.text.toString().trim()

            var isValid = true

            if (email.isEmpty() || !regex.emailPattern.matches(email)) {
                tilEmail.error = getString(R.string.error_email)
                isValid = false
            } else {
                tilEmail.isErrorEnabled = false
            }

            if (password != confirmPassword) {
                tilPassword1.error = getString(R.string.error_password)
                tilPassword2.error = getString(R.string.error_password)
                isValid = false
            } else if (password.length < 8) {
                tilPassword1.error = getString(R.string.error_password_length)
                tilPassword2.error = getString(R.string.error_password_length)
                isValid = false
            } else {
                tilPassword1.isErrorEnabled = false
                tilPassword2.isErrorEnabled = false
            }

            if (isValid) {
                user.email = email
                userPassword = password

            }
        }
    }

    override fun render(viewState: RegistrationViewState) {
        when (viewState) {
            is RegistrationReady -> {
                progressBar.visibility = View.GONE
                btnRegister.setOnClickListener{
                    viewModel.registerWithEmail(user, userPassword, Uri.parse(""))
                    findNavController().navigate(R.id.nav_home)
                }
            }
            is RegistrationFailed -> {
                progressBar.visibility = View.GONE

                Toast.makeText(
                    requireContext(),
                    "Something went wrong!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
            is RegistrationInProgress -> {
                progressBar.visibility = View.VISIBLE
            }
        }
    }

}