package uz.bigboys.presentation.views

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container
import uz.bigboys.presentation.live.LiveValue
import uz.bigboys.presentation.observeState

/**
 * Observe the specified [liveData] and call [onSuccess] callback
 * if the [liveData] contains [Container.Success] value.
 */

fun <T> ResultView.observe(
   owner: LifecycleOwner,
   liveData: LiveData<Container<T>>,
   onSuccess: (T) -> Unit
) {
   liveData.observe(owner) {
      container = it
      if (it is Container.Success) {
         onSuccess(it.value)
      }
   }
}

/**
 * Observe the specified [liveValue] and call [onSuccess] callback
 * if the [liveValue] contains [Container.Success] value.
 */
fun <T> ResultView.observe(
   owner: LifecycleOwner,
   liveValue: LiveValue<Container<T>>,
   onSuccess: (T) -> Unit
) {
   liveValue.observe(owner) {
      container = it
      if (it is Container.Success) {
         onSuccess(it.value)
      }
   }
}

/**
 * Observe the specified [flow] and call [onSuccess] callback
 * if the [flow] contains [Container.Success] value.
 */
fun <T> ResultView.observe(
   owner: LifecycleOwner,
   flow: Flow<Container<T>>,
   onSuccess: (T) -> Unit
) {
   flow.observeState(owner) {
      container = it
      if (it is Container.Success) {
         onSuccess(it.value)
      }
   }
}