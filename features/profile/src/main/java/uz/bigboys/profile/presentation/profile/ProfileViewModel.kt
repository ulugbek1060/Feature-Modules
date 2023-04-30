package uz.bigboys.profile.presentation.profile

import uz.bigboys.profile.domain.GetProfileUseCase
import uz.bigboys.profile.domain.LogoutUseCase
import uz.bigboys.profile.presentation.ProfileRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.bigboys.common.Container
import uz.bigboys.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val router: ProfileRouter,
) : BaseViewModel() {

    val profileLiveValue = getProfileUseCase.getProfile()
        .toLiveValue(Container.Pending)

    fun reload() = debounce {
        getProfileUseCase.reload()
    }

    fun logout() = debounce {
        viewModelScope.launch {
            logoutUseCase.logout()
            router.restartApp()
        }
    }

    fun editUsername() = debounce {
        router.launchEditUsername()
    }

}