package uz.bigboys.profile.presentation.edit

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import uz.bigboys.presentation.live.observeEvent
import uz.bigboys.presentation.viewBinding
import uz.bigboys.presentation.views.observe
import uz.bigboys.profile.R
import uz.bigboys.profile.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment(R.layout.fragment_edit_profile) {

    private val binding: FragmentEditProfileBinding by viewBinding()
    private val viewModel: EditProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            observeEvents(savedInstanceState)
            setupListeners()
            observeState()
        }
    }

    private fun FragmentEditProfileBinding.observeEvents(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.initialUsernameLiveEvent.observeEvent(viewLifecycleOwner) {
                usernameEditText.setText(it)
            }
        }
    }

    private fun FragmentEditProfileBinding.setupListeners() {
        saveButton.setOnClickListener {
            viewModel.saveUsername(usernameEditText.text.toString())
        }
        cancelButton.setOnClickListener {
            viewModel.goBack()
        }
        root.setTryAgainListener { viewModel.load() }
    }

    private fun FragmentEditProfileBinding.observeState() {
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue) { state ->
            saveButton.isEnabled = state.enableSaveButton
            progressBar.isInvisible = !state.showProgress
        }
    }
}