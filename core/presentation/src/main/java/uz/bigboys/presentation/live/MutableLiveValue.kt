package uz.bigboys.presentation.live

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class MutableLiveValue<T>(
   private val liveData: MutableLiveData<T> = MutableLiveData()
) : LiveValue<T> {

   override fun observe(lifecycleOwner: LifecycleOwner, listener: (T) -> Unit) {
      this.liveData.observe(lifecycleOwner, listener)
   }

   override fun requireValue(): T {
      return this.liveData.value
         ?: throw java.lang.IllegalStateException("Value has not been assigned yet")
   }

   override fun getValue(): T? {
      return this.liveData.value
   }

   fun setValue(value: T) {
      this.liveData.value = value
   }
}