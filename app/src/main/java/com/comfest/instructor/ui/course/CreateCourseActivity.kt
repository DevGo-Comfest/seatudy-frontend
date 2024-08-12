package com.comfest.instructor.ui.course

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.comfest.instructor.data.dummy.CategoryCourse
import com.comfest.instructor.data.dummy.LevelCourse
import com.comfest.instructor.domain.model.RequestCreateCourse
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityCreateCourseBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.UUID

@AndroidEntryPoint
class CreateCourseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateCourseBinding
    private var selectedImageBitmap: Bitmap? = null
    private lateinit var createCourseViewModel: CourseViewModel


    private var tokenUser: String? = null
    private var imageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createCourseViewModel = ViewModelProvider(this)[CourseViewModel::class.java]

        setupAdapterSpinner()

        createCourseViewModel.getToken().observe(this) {token ->
            tokenUser = token
        }


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

        binding.btnUploadImg.setOnClickListener {
            val imageFile = bitmapToFile(selectedImageBitmap!!)
            val requestFile = imageFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
            createCourseViewModel.uploadImage(tokenUser!!, body).observe(this@CreateCourseActivity) {
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@CreateCourseActivity, "Loading Image Upload", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@CreateCourseActivity, "Image uploaded successfully", Toast.LENGTH_SHORT).show()
                        imageUrl = it.data?.imageUrl
                        binding.imgPreview.visibility = View.GONE
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@CreateCourseActivity, "Upload failed", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@CreateCourseActivity,
                            "Check your image",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        binding.btnCreate.setOnClickListener {
            createCourse()
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
            CategoryCourse("Android"),
            CategoryCourse("Web"),
            CategoryCourse("Desktop"),
            CategoryCourse("Multiplatform"),
            CategoryCourse("Game"),
            CategoryCourse("Machine Learning"),
            CategoryCourse("Data Scientist"),
            CategoryCourse("React"),
            CategoryCourse("DevOps"),
            CategoryCourse("Cloud"),
        )

        val levelCourses = listOf(
            LevelCourse("advanced"),
            LevelCourse("intermediate"),
            LevelCourse("beginner")
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
    private fun bitmapToFile(bitmap: Bitmap): File {
        val file = File(cacheDir, "temp_image_${UUID.randomUUID()}.jpg")
        file.createNewFile()

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        file.writeBytes(byteArray)

        return file
    }


    private fun createCourse() {
        binding.apply {
            val title = edTitleCourse.text.toString()
            val description = edDescCourse.text.toString()
            val price = edPriceCourse.text.toString()
            val category = spinnerCategory.selectedItem.toString()
            val difficultyLevel = spinnerLevel.selectedItem.toString()

            if (title.isEmpty() || description.isEmpty() || price.toInt() <= 0 || imageUrl.isNullOrEmpty()) {
                Toast.makeText(this@CreateCourseActivity, "Please fill in all the fields and upload an image", Toast.LENGTH_SHORT).show()
            }

            val requestCreateCourse = RequestCreateCourse(
                title = title,
                description = description,
                price = price.toInt(),
                category = category,
                difficultyLevel = difficultyLevel,
                image_url = imageUrl!!
            )

            createCourseViewModel.crateCourse(tokenUser!!, requestCreateCourse).observe(this@CreateCourseActivity) {
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@CreateCourseActivity, "Creating course...", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@CreateCourseActivity, "Course created successfully", Toast.LENGTH_SHORT).show()
                        // Optionally, you can navigate back or clear the form
                        finish() // Or any other action like navigating to another activity
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@CreateCourseActivity, "Failed to create course: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@CreateCourseActivity,
                            "Recheck your input course",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}