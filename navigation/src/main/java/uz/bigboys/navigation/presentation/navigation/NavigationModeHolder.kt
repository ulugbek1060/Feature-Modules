package uz.bigboys.navigation.presentation.navigation

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class NavigationModeHolder @Inject constructor() {

   var navigationMode: NavigationMode = NavigationMode.Stack

}