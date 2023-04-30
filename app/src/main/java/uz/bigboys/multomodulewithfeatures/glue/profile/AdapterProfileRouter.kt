package uz.bigboys.multomodulewithfeatures.glue.profile

import uz.bigboys.profile.presentation.ProfileRouter
import uz.bigboys.multomodulewithfeatures.R
import uz.bigboys.navigation.GlobalNavComponentRouter
import javax.inject.Inject

class AdapterProfileRouter @Inject constructor(
    private val globalNavComponentRouter: GlobalNavComponentRouter
) : ProfileRouter {

    override fun launchEditUsername() {
        globalNavComponentRouter.launch(R.id.editProfileFragment)
    }

    override fun goBack() {
        globalNavComponentRouter.pop()
    }

    override fun restartApp() {
        globalNavComponentRouter.restart()
    }
}