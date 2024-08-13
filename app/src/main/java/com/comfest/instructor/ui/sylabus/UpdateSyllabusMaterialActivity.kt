package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.comfest.instructor.data.source.remote.response.SyllabusMaterial
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityUpdateSyllabusMaterialBinding

class UpdateSyllabusMaterialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateSyllabusMaterialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateSyllabusMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val syllabusMaterial = intent.getParcelableExtra<SyllabusMaterial>("syllabus_material")

        syllabusMaterial.let {item ->
            binding.apply {
                edDescSyllabusMaterial.setText(item?.description)
                edTitleSyllabusMaterial.setText(item?.title)
                edTimeSyllabusMaterial.setText(item?.timeNeeded)
                edLinkCourseMaterial.setText(item?.urlMaterial)
            }
        }


        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }
}