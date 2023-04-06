package uz.bigboys.navigation.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.bigboys.common.Container
import uz.bigboys.navigation.domain.GetCartItemsCountUseCase
import uz.bigboys.navigation.domain.GetCurrentUsernameUseCase
import uz.bigboys.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val getCurrentUsernameUseCase: GetCurrentUsernameUseCase,
   private val getCartItemsCountUseCase: GetCartItemsCountUseCase,
   private val router: MainRouter,
) : BaseViewModel() {

   val usernameLiveValue = liveValue<String?>()
   val cartLiveValue = liveValue<CartState>()

   init {
      observeUsername()
      observeCart()
   }

   fun launchCart() = debounce {
      router.launchCart()
   }

   private fun observeUsername() {
      viewModelScope.launch {
         getCurrentUsernameUseCase().collectLatest { container ->
            if (container is Container.Success) {
               usernameLiveValue.value = container.value
            } else {
               usernameLiveValue.value = null
            }
         }
      }
   }

   private fun observeCart() {
      viewModelScope.launch {
         getCartItemsCountUseCase().collectLatest { container ->
            if (container is Container.Success) {
               val maxCount = 9
               val countString = if (container.value > maxCount) {
                  "$maxCount+"
               } else if (container.value == 0) {
                  ""
               } else {
                  container.value.toString()
               }
               cartLiveValue.value = CartState(countString)
            } else {
               cartLiveValue.value = CartState(itemsCountDisplayString = "")
            }
         }
      }
   }

   class CartState(
      val itemsCountDisplayString: String,
   ) {
      val showCartIcon: Boolean get() = itemsCountDisplayString.isNotBlank()
   }
}