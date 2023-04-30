package uz.bigboys.profile.presentation.edit

import uz.bigboys.profile.domain.EditUsernameUseCase
import uz.bigboys.profile.domain.GetProfileUseCase
import uz.bigboys.profile.domain.entities.Profile
import uz.bigboys.profile.domain.exceptions.EmptyUsernameException
import uz.bigboys.profile.presentation.ProfileRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import uz.bigboys.common.Container
import uz.bigboys.presentation.BaseViewModel
import uz.bigboys.profile.R
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