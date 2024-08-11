package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.comfest.seatudy.databinding.ActivityUpdateSyllabusBinding

class UpdateSyllabusActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateSyllabusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateSyllabusBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}