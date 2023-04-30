package uz.bigboys.profile.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.bigboys.presentation.viewBinding
import uz.bigboys.presentation.views.observe
import uz.bigboys.profile.R
import uz.bigboys.profile.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewModel: ProfileViewModel by viewModels()
    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            setupListeners()
            observeProfile()
        }
    }

    private fun FragmentProfileBinding.setupListeners() {
        editProfileButton.setOnClickListener { viewModel.editUsername() }
        logoutButton.setOnClickListener { viewModel.logout() }
        root.setTryAgainListener { viewModel.reload() }
    }

    private fun FragmentProfileBinding.observeProfile() {
        root.observe(viewLifecycleOwner, viewModel.profileLiveValue) { profile ->
            binding.emailTextView.text = profile.email
            binding.usernameTextView.text = profile.username
            binding.createdAtTextView.text = profile.createdAtString
        }
    }
}