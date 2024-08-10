package com.comfest.instructor.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.comfest.instructor.ui.course.CreateCourseActivity
import com.comfest.instructor.ui.home.HomeInstructorFragment
import com.comfest.instructor.ui.profile.ProfileInstructorFragment
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.ActivityMainInstructorBinding

class MainActivityInstructor : AppCompatActivity() {

    private lateinit var binding: ActivityMainInstructorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainInstructorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fragment pertama yg muncul
        replaceFragment(HomeInstructorFragment())


        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                //id yg ada di bottom_nav_menu_instructor
                R.id.home_instructor -> replaceFragment(HomeInstructorFragment())
                R.id.profile_instructor -> replaceFragment(ProfileInstructorFragment())

                else -> {

                }
            }

            true
        }

        binding.btnCreateCourse.setOnClickListener {
            val intent = Intent(this, CreateCourseActivity::class.java)
            startActivity(intent)
        }


    }



    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}