package com.comfest.instructor.ui.course

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.comfest.instructor.data.dummy.CategoryCourse
import com.comfest.instructor.data.dummy.LevelCourse
import com.comfest.seatudy.databinding.ActivityCreateCourseBinding

class CreateCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCourseBinding
    private var selectedImageBitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapterSpinner()


        binding.btnCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermission.launch(Manifest.permission.CAMERA)
            } else {
                openCamera()
            }
        }

        binding.btnGallery.setOnClickListener {
            openGallery()
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }


    //permision camera
    private val requestCameraPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            // Handle permission denied case
            Toast.makeText(this, "HARUS OK", Toast.LENGTH_SHORT).show()
        }
    }


    //ambil gambar dari camera lalu di display di img preview
    private val captureImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            selectedImageBitmap = imageBitmap
            binding.imgPreview.setImageBitmap(imageBitmap)
        }
    }


    //ambil gambar dari galery lalu di display di img preview
    private val selectImageFromGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri: Uri? = result.data?.data
            imageUri?.let {
                val imageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                selectedImageBitmap = imageBitmap
                binding.imgPreview.setImageBitmap(imageBitmap)
            }
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        captureImage.launch(cameraIntent)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        selectImageFromGallery.launch(galleryIntent)
    }


    private fun setupAdapterSpinner() {
        val categories = listOf(
            CategoryCourse("Networking"),
            CategoryCourse("Cybersecurity"),
            CategoryCourse("Web Development"),
            CategoryCourse("Mobile Development"),
            CategoryCourse("Desktop Development"),
        )

        val levelCourses = listOf(
            LevelCourse("Advanced"),
            LevelCourse("Intermediate"),
            LevelCourse("Beginner")
        )

        val categoryNames = categories.map { it.name }
        val levelNames = levelCourses.map { it.level }


        //adapter category
        val adapterCategory = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapterCategory



        //adapter dificutly level
        val adapterLevel = ArrayAdapter(this, android.R.layout.simple_spinner_item, levelNames)
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLevel.adapter = adapterLevel
    }
}