package uz.bigboys.common.flow

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import uz.bigboys.common.Core

class DefaultLazyFlowSubjectFactory(
   private val dispatcher: CoroutineDispatcher,
   private val globalScope: CoroutineScope = Core.globalScope,
   private val cacheTimeoutMillis: Long = 1000
) : LazyFlowSubjectFactory {

   override fun <T> create(loader: ValueLoader<T>): LazyFlowSubject<T> {
      return DefaultLazyFlowSubject(loader, dispatcher, globalScope, cacheTimeoutMillis)
   }

}