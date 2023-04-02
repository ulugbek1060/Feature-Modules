package uz.bigboys.presentation.assignable

import uz.bigboys.presentation.live.LiveValue
import uz.bigboys.presentation.live.MutableLiveValue

class LiveValueAssignable<T>(private val liveValue: LiveValue<T>) : Assignable<T> {
   override fun setValue(value: T) {
      (liveValue as? MutableLiveValue<T>)?.setValue(value)
   }
}