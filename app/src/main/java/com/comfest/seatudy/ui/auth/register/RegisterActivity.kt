package com.comfest.seatudy.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityRegisterBinding
import com.comfest.seatudy.domain.model.DataRegister
import com.comfest.seatudy.ui.auth.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.btnRegister.setOnClickListener {
            btnRegister()
        }

        binding.btnSigIn.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun btnRegister(){
        binding.apply {
            val name = tvName.text.toString()
            val email = tvEmail.text.toString()
            val password = tvPassword.text.toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                registerViewModel.register(
                    DataRegister(
                        name,
                        email,
                        password
                    )
                ).observe(this@RegisterActivity) {
                    when (it) {
                        is Resource.Loading -> {
                            binding.loading.visibility = View.VISIBLE
                        }

                        is Resource.Success -> {
                            binding.loading.visibility = View.GONE
                            startActivity(
                                Intent(
                                    this@RegisterActivity,
                                    LoginActivity::class.java
                                )
                            )
                            finish()
                        }

                        is Resource.Error -> {
                            binding.loading.visibility = View.GONE
                            Toast.makeText(this@RegisterActivity, "Register Failed", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }else{
                Toast.makeText(this@RegisterActivity, "Recheck your name, email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}