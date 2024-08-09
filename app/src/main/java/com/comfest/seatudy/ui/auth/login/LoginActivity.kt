package com.comfest.seatudy.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityLoginBinding
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.ui.NavigationActivity
import com.comfest.seatudy.ui.auth.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnSigIn.setOnClickListener {
            btnLogin()
        }

        binding.btnSigUp.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun btnLogin() {
        binding.apply {
            val email = tvEmail.text.toString()
            val password = tvPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty())
                loginViewModel.login(
                    DataLogin(
                        email,
                        password
                    )
                ).observe(this@LoginActivity) {
                    when (it) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            loginViewModel.saveThemeSetting(true)
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    NavigationActivity::class.java
                                )
                            )
                            finish()
                        }

                        is Resource.Error -> {
                            Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
        }

    }
}