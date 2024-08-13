package com.comfest.instructor.ui.sylabus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.source.remote.response.DataSyllabusMaterialResponse
import com.comfest.instructor.data.source.remote.response.SyllabusDetail
import com.comfest.instructor.domain.model.RequestCreateSyllabusMaterial
import com.comfest.instructor.ui.sylabus.adapter.SyllabusMaterialInstructorAdapter
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivitySyllabusMaterialInstructorBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SyllabusMaterialInstructorActivity : AppCompatActivity(), SyllabusMaterialInstructorAdapter.OnItemClickListener {
    private lateinit var binding: ActivitySyllabusMaterialInstructorBinding
    private lateinit var syllabusAdapter: SyllabusMaterialInstructorAdapter
    private lateinit var syllabusViewModel: SyllabusViewModel

    private var tokenUser: String? = null
    private var syllabusId: Int? = null
    private var syllabusMaterialId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySyllabusMaterialInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        syllabusViewModel = ViewModelProvider(this)[SyllabusViewModel::class.java]

        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")
        syllabusId = syllabus?.syllabusID


        binding.apply {
            tvTitleSyllabus.text = syllabus?.title
            tvDescSyllabus.text = syllabus?.description
        }

        syllabusAdapter = SyllabusMaterialInstructorAdapter(this)

        binding.rvList.adapter = syllabusAdapter
        binding.rvList.layoutManager = LinearLayoutManager(this)


        syllabusViewModel.getToken().observe(this){token ->
            loadSyllabusMaterial(syllabusId!!, token)
            tokenUser = token
        }


        binding.btnAddSyllabusMaterial.setOnClickListener {
            addSyllabusMaterial()
        }

    }

    private fun addSyllabusMaterial() {
        binding.apply {
            val titleMaterial = edTitleSyllabusMaterial.text.toString()
            val descMaterial = edDescSyllabusMaterial.text.toString()
            val linkMaterial = edLinkCourseMaterial.text.toString()
            val dueMaterial = edTimeSyllabusMaterial.text.toString()

            if (titleMaterial.isEmpty() || descMaterial.isEmpty() || linkMaterial.isEmpty() || dueMaterial.isEmpty()) {
                Toast.makeText(this@SyllabusMaterialInstructorActivity, "Please fill field", Toast.LENGTH_SHORT).show()
            }

            val requestCreateSyllabusMaterial = RequestCreateSyllabusMaterial(
                title = titleMaterial,
                description = descMaterial,
                urlMaterial = linkMaterial,
                timeNeeded = dueMaterial,
                syllabusID = syllabusId!!
            )

            syllabusViewModel.createSyllabusMaterial(tokenUser!!, requestCreateSyllabusMaterial).observe(this@SyllabusMaterialInstructorActivity) {
                when(it) {
                    is Resource.Loading -> {
                        Toast.makeText(this@SyllabusMaterialInstructorActivity, "Creating syllabus...", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(this@SyllabusMaterialInstructorActivity, "Syllabus created successfully", Toast.LENGTH_SHORT).show()
                        syllabusMaterialId = it.data?.body()?.syllabusMaterial?.syllabusMaterialID
                        loadSyllabusMaterial(syllabusId!!, tokenUser!!)
                        Log.d("SyllabusMaterialInstructorActivity", "ID Syllabus material $syllabusMaterialId")
                    }

                    is Resource.Error -> {
                        Toast.makeText(this@SyllabusMaterialInstructorActivity, "Failed to create syllabus: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(
                            this@SyllabusMaterialInstructorActivity,
                            "Recheck your input syllabus",
                            Toast.LENGTH_SHORT
                        ).show()
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
                        syllabusAdapter.setSyllabusMaterial(syllabusDetail!!)
                    }
                    Log.d("SyllabusMaterialInstructorActivity", "Syllabus materials received: $syllabusMaterials")
                    Toast.makeText(this@SyllabusMaterialInstructorActivity, "Success get syllabuses...", Toast.LENGTH_SHORT).show()

                }
                is Resource.Error -> {
                    Toast.makeText(this@SyllabusMaterialInstructorActivity, "Error get syllabuses...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onUpdateClick(syllabusMaterial: DataSyllabusMaterialResponse) {
        val intent = Intent(this, UpdateSyllabusMaterialActivity::class.java)
        intent.putExtra("syllabus_material", syllabusMaterial)

        val syllabus = intent.getParcelableExtra<SyllabusDetail>("syllabus")
        intent.putExtra("syllabus", syllabus)
        startActivity(intent)
    }

    override fun onDeleteClick(syllabusMaterial: DataSyllabusMaterialResponse) {
        syllabusViewModel.deleteSyllabusMaterial(syllabusMaterial.syllabusMaterialID, tokenUser!!).observe(this@SyllabusMaterialInstructorActivity) {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    loadSyllabusMaterial(syllabusMaterial.syllabusMaterialID, tokenUser!!)
                    Toast.makeText(this@SyllabusMaterialInstructorActivity, "Success delete syllabus material", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    Toast.makeText(this@SyllabusMaterialInstructorActivity, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}