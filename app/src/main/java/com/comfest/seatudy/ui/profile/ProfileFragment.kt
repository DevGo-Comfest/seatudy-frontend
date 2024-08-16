package com.comfest.seatudy.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.comfest.seatudy.data.Resource
import com.comfest.seatudy.databinding.FragmentProfileBinding
import com.comfest.seatudy.ui.auth.login.LoginActivity
import com.comfest.seatudy.ui.cart.topup.TopUpActivity
import com.comfest.seatudy.utils.ToastResource
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Locale

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
            profileViewModel.deleteLoginUser(false)
            profileViewModel.deleteToken("")
            profileViewModel.deleteName("")
            profileViewModel.deleteRoleUser("")
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            startActivity(intent)
            requireActivity().supportFragmentManager.popBackStack()
        }

        getProfile()
    }

    @SuppressLint("SetTextI18n")
    private fun getProfile() {
        profileViewModel.getToken().observe(viewLifecycleOwner) { token ->
            profileViewModel.getProfile("Bearer $token").observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> {
                        ToastResource.toastResource("Loading", requireContext())
                    }

                    is Resource.Success -> {
                        val profile = it.data?.body()
                        binding.apply {
                            val dataBalance = profile?.balance
                            val formatRupiah = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
                            val formattedString = formatRupiah.format(dataBalance).replace(",00", "")

                            tvNameUser.text = profile?.name
                            tvBalance.text = formattedString
                        }
                    }

                    is Resource.Error -> {
                        ToastResource.toastResource("Error Occurred", requireContext())
                    }
                }
            }
        }
    }
}