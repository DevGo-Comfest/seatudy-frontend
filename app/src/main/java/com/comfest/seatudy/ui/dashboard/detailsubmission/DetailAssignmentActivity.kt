package com.comfest.seatudy.ui.dashboard.detailsubmission

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.ActivityDetailSubmissionBinding
import com.comfest.seatudy.domain.model.DataUrlAssignment
import com.comfest.seatudy.ui.NavigationActivity
import com.comfest.seatudy.utils.ToastResource.toastResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailAssignmentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailSubmissionBinding
    private lateinit var submissionViewModel: DetailAssignmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSubmissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        submissionViewModel = ViewModelProvider(this)[DetailAssignmentViewModel::class.java]

        data()
        sendAssignment()
    }

    @SuppressLint("SetTextI18n")
    private fun data() {
        val title = intent.getStringExtra("TITLE")
        val time = intent.getStringExtra("TIME")
        val description = intent.getStringExtra("DESCRIPTION")

        binding.tvTitleAssignment.text = title
        binding.tvTime.text = "$time Day"
        binding.tvDescription.text = description
    }

    private fun sendAssignment() {
        val id = intent.getStringExtra("ID").toString()

        binding.btnSendAssignment.setOnClickListener {
            val link = binding.tvLinkAssignment.text.toString()
            submissionViewModel.getToken().observe(this) { token ->
                submissionViewModel.sendAssignment(id, DataUrlAssignment(link), "Bearer $token").observe(this) { data ->
                    when(data){
                        is Resource.Loading -> {
                            toastResource("Loading", this@DetailAssignmentActivity)
                        }
                        is Resource.Success -> {
                            Toast.makeText(this, "Success Send Submission", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, NavigationActivity::class.java))
                        }
                        is Resource.Error -> {
                            toastResource("Error Ocurred", this@DetailAssignmentActivity)
                        }
                    }
                }
            }
        }
    }
}