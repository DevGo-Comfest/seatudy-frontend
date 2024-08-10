package com.comfest.seatudy.ui.auth.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.R
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
    private var isPasswordVisible: Boolean = false

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

        showPassword()
    }

    private fun btnLogin() {
        binding.apply {
            val email = tvEmail.text.toString()
            val password = tvPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginViewModel.login(
                    DataLogin(
                        email,
                        password
                    )
                ).observe(this@LoginActivity) {
                    when (it) {
                        is Resource.Loading -> {
                            binding.loading.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.loading.visibility = View.GONE
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
                            binding.loading.visibility = View.GONE
                            Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }else{
                Toast.makeText(this@LoginActivity, "Recheck your email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showPassword(){
        binding.apply {
            tvPassword.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= (tvPassword.right - tvPassword.compoundDrawables[2].bounds.width())) {
                        // Toggle password visibility
                        if (isPasswordVisible) {
                            // Hide the password
                            tvPassword.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            tvPassword.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.icon_padlock,
                                0,
                                R.drawable.icon_eyes,
                                0
                            )
                        } else {
                            // Show the password
                            tvPassword.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            tvPassword.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.icon_padlock,
                                0,
                                R.drawable.icon_eyes_off,
                                0
                            )
                        }
                        // Move the cursor to the end of the text
                        tvPassword.setSelection(tvPassword.text.length)
                        isPasswordVisible = !isPasswordVisible
                        return@setOnTouchListener true
                    }
                }
                false
            }
        }
    }
}