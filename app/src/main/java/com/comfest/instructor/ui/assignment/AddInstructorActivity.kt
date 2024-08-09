package com.comfest.instructor.ui.assignment

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfest.instructor.data.dummy.InstructorData
import com.comfest.instructor.ui.assignment.adapter.AddInstructorAdapter
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityAddInstructorBinding

class AddInstructorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddInstructorBinding
    private lateinit var addInstructorAdapter: AddInstructorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupRecyclerView()
        loadedInstructor()

        binding.btnAddInstructor.setOnClickListener {
            val selectedItems = addInstructorAdapter.getSelectedItem()
            Toast.makeText(this, "Selected items: ${selectedItems.map { it.name }}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setupRecyclerView() {
        addInstructorAdapter = AddInstructorAdapter()
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = addInstructorAdapter
        }
    }


    private fun loadedInstructor() {
        val name = "Joko Anwar"

        val dataInstructor = listOf(
            InstructorData(name),
            InstructorData(name),
            InstructorData("Nughoro Samudra"),
            InstructorData("Bapak Budi"),
            InstructorData("Bapak Jono"),
            InstructorData("Aziz Samsudin"),
            InstructorData("Wahyu Kusuma"),
        )

        addInstructorAdapter.setInstructor(dataInstructor)
    }
}