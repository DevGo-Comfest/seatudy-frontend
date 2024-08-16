package com.comfest.seatudy.ui.auth.login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.comfest.instructor.ui.MainActivityInstructor
import com.comfest.seatudy.R
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityLoginBinding
import com.comfest.seatudy.domain.model.DataLogin
import com.comfest.seatudy.ui.NavigationActivity
import com.comfest.seatudy.ui.auth.register.RegisterActivity
import com.comfest.seatudy.utils.ToastResource
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
                            val token = it.data?.token ?: ""
                            val role = it.data?.user?.role ?: ""
                            val name = it.data?.user?.name ?: ""
                            loginViewModel.saveTokenUser(token)
                            loginViewModel.saveRoleUser(role)
                            loginViewModel.saveNameUser(name)
                            if (it.data?.user?.role == "user") {
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        NavigationActivity::class.java
                                    )
                                )
                                finish()
                            } else {
                                startActivity(
                                    Intent(
                                        this@LoginActivity,
                                        MainActivityInstructor::class.java
                                    )
                                )
                                finish()
                            }
                        }

                        is Resource.Error -> {
                            binding.loading.visibility = View.GONE
                            ToastResource.toastResource("Login Failed", this@LoginActivity)
                        }
                    }
                }
            } else {
                ToastResource.toastResource("Recheck your email and password", this@LoginActivity)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showPassword() {
        binding.apply {
            tvPassword.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= (tvPassword.right - tvPassword.compoundDrawables[2].bounds.width())) {
                        if (isPasswordVisible) {
                            tvPassword.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                            tvPassword.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.icon_padlock,
                                0,
                                R.drawable.icon_eyes,
                                0
                            )
                        } else {
                            tvPassword.inputType =
                                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                            tvPassword.setCompoundDrawablesWithIntrinsicBounds(
                                R.drawable.icon_padlock,
                                0,
                                R.drawable.icon_eyes_off,
                                0
                            )
                        }
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