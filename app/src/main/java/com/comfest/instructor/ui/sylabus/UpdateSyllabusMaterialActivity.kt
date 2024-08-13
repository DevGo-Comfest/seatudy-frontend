package com.comfest.instructor.ui.sylabus

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.comfest.instructor.data.source.remote.response.DataSyllabusMaterialResponse
import com.comfest.instructor.data.source.remote.response.SyllabusDetail import com.comfest.instructor.data.source.remote.response.SyllabusMaterial
import com.comfest.instructor.domain.model.RequestUpdateSyllabusMaterial
import com.comfest.instructor.ui.sylabus.adapter.SyllabusMaterialInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityUpdateSyllabusMaterialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateSyllabusMaterialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateSyllabusMaterialBinding
    private lateinit var syllabusViewModel: SyllabusViewModel
    private lateinit var syllabusMaterialInstructorAdapter: SyllabusMaterialInstructorAdapter


    private var tokenUser: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateSyllabusMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)


        syllabusViewModel = ViewModelProvider(this)[SyllabusViewModel::class.java]

        val syllabusMaterial = intent.getParcelableExtra<DataSyllabusMaterialResponse>("syllabus_material")
        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")


        syllabusViewModel.getToken().observe(this) {
            tokenUser = it
        }


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

        binding.btnAddSyllabusMaterial.setOnClickListener {
            binding.apply {
                val titleMaterial = edTitleSyllabusMaterial.text.toString()
                val descMaterial = edDescSyllabusMaterial.text.toString()
                val linkMaterial = edLinkCourseMaterial.text.toString()
                val timeMaterial = edTimeSyllabusMaterial.text.toString()


                val requestUpdate = RequestUpdateSyllabusMaterial(
                    title = titleMaterial,
                    description = descMaterial,
                    urlMaterial = linkMaterial,
                    timeNeeded = timeMaterial
                )

                syllabusViewModel.updateSyllabusMaterial(syllabusMaterial!!.syllabusID, tokenUser!!, requestUpdate).observe(this@UpdateSyllabusMaterialActivity) {
                    when(it) {
                        is Resource.Loading -> {
                        }

                        is Resource.Success -> {
                            Toast.makeText(this@UpdateSyllabusMaterialActivity, "syllabus updated successfully", Toast.LENGTH_SHORT).show()
                            // Optionally, you can navigate back or clear the form
                            finish() // Or any other action like navigating to another activity
                            loadSyllabusMaterial(syllabus!!.syllabusID, tokenUser!!)
                        }

                        is Resource.Error -> {
                            Toast.makeText(this@UpdateSyllabusMaterialActivity, "Failed to update syllabus: ${it.message}", Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            Toast.makeText(
                                this@UpdateSyllabusMaterialActivity,
                                "Recheck your input syllabus update",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }


    private fun loadSyllabusMaterial(syllabusMaterialId: Int, token: String) {
        syllabusViewModel.getSyllabusMaterialById(syllabusMaterialId, token).observe(this) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    Log.d("SyllabusMaterial", "Data: ${it.data?.body()?.syllabusMaterial}")
                    val syllabusMaterials = it.data?.body()?.syllabusMaterial
                    it.data?.body()?.syllabusMaterial.let { syllabusDetail ->
                        syllabusMaterialInstructorAdapter.setSyllabusMaterial(syllabusDetail!!)
                    }
                    Log.d("SyllabusMaterialInstructorActivity", "Syllabus materials received: $syllabusMaterials")
                    Toast.makeText(this@UpdateSyllabusMaterialActivity, "Success get syllabuses...", Toast.LENGTH_SHORT).show()

                }
                is Resource.Error -> {
                    Toast.makeText(this@UpdateSyllabusMaterialActivity, "Error get syllabuses...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}