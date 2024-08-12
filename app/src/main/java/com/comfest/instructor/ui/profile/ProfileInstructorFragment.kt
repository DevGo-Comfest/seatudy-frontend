package com.comfest.instructor.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.R
import com.comfest.seatudy.databinding.FragmentProfileBinding
import com.comfest.seatudy.databinding.FragmentProfileInstructorBinding
import com.comfest.seatudy.ui.auth.login.LoginActivity
import com.comfest.seatudy.ui.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileInstructorFragment : Fragment() {

    private var _binding: FragmentProfileInstructorBinding? = null


    private val binding get() = _binding!!

    private lateinit var profileInstructorViewModel: ProfileInstructorViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileInstructorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileInstructorViewModel = ViewModelProvider(this)[ProfileInstructorViewModel::class.java]

        binding.btnLogout.setOnClickListener {
            profileInstructorViewModel.saveThemeSetting(false)
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }
    }
}