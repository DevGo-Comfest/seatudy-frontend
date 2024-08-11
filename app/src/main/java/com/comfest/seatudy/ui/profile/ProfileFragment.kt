package com.comfest.seatudy.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentProfileBinding
import com.comfest.seatudy.ui.auth.login.LoginActivity
import com.comfest.seatudy.ui.cart.topup.TopUpActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.btnTopUp.setOnClickListener {
           startActivity(Intent(requireContext(), TopUpActivity::class.java))
        }

        binding.btnSignOut.setOnClickListener {
            profileViewModel.saveThemeSetting(false)
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().supportFragmentManager.popBackStack()
        }

        getProfile()
    }

    @SuppressLint("SetTextI18n")
    private fun getProfile(){
        profileViewModel.getToken().observe(viewLifecycleOwner) { token ->
            profileViewModel.getProfile("Bearer $token").observe(viewLifecycleOwner){
                when(it){
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        val profile = it.data?.body()
                        binding.apply {
                            tvNameUser.text = profile?.name
                            tvBalance.text = "Rp. ${profile?.balance.toString()}"
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "Error Occurred", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}