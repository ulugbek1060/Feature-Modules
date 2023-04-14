package com.example.profile.presentation.edit

import com.example.profile.R
import com.example.profile.domain.EditUsernameUseCase
import com.example.profile.domain.GetProfileUseCase
import com.example.profile.domain.entities.Profile
import com.example.profile.domain.exceptions.EmptyUsernameException
import com.example.profile.presentation.ProfileRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uz.bigboys.common.Container
import uz.bigboys.presentation.BaseViewModel
import javax.inject.Inject


@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val editUsernameUseCase: EditUsernameUseCase,
    private val router: ProfileRouter,
) : BaseViewModel() {

    private val saveInProgressFlow = MutableStateFlow(false)
    private val loadUsernameFlow = MutableStateFlow<Container<Unit>>(Container.Pending)

    val initialUsernameLiveEvent = liveEvent<String>()

    val stateLiveValue = combine(saveInProgressFlow, loadUsernameFlow, ::merge)
        .toLiveValue()

    init {
        load()
    }

    fun saveUsername(username: String) = debounce {
        if (saveInProgressFlow.value) return@debounce
        viewModelScope.launch {
            saveInProgressFlow.value = true
            try {
                editUsernameUseCase.editUsername(username)
                router.goBack()
            } catch (e: EmptyUsernameException) {
                commonUi.toast(resources.getString(R.string.profile_empty_username))
                saveInProgressFlow.value = false
            }
        }
    }

    fun load() = debounce {
        loadScreenInto(loadUsernameFlow) {
            val profile = getProfileUseCase.getProfile()
                .filterIsInstance<Container.Success<Profile>>()
                .first()
                .value
            initialUsernameLiveEvent.publish(profile.username)
        }
    }

    fun goBack() = debounce {
        router.goBack()
    }

    private fun merge(
        isSaveInProgress: Boolean,
        loadContainer: Container<Unit>
    ): Container<State> {
        return loadContainer.map {
            State(isSaveInProgress)
        }
    }

    class State(
        private val isSaveInProgress: Boolean
    ) {
        val showProgress: Boolean get() = isSaveInProgress
        val enableSaveButton: Boolean get() = !isSaveInProgress
    }

}