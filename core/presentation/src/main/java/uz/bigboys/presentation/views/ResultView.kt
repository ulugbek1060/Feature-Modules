package uz.bigboys.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.isVisible
import uz.bigboys.common.AuthException
import uz.bigboys.common.Container
import uz.bigboys.common.Core
import uz.bigboys.presentation.R
import uz.bigboys.presentation.databinding.CorePresentationPartResultBinding

/**
 * Layout for rendering [Container] results.
 * If [container] has [Container.Success] value -> children are rendered.
 * If [container] has [Container.Pending] value -> progress bar is displayed.
 * If [container] has [Container.Error] value -> an error message and try-again
 * button is displayed.
 */
class ResultView @JvmOverloads constructor(
   context: Context,
   attr: AttributeSet? = null,
   defStyleAttr: Int = 0,
   defStyleRes: Int = 0
) : FrameLayout(context, attr, defStyleAttr, defStyleRes) {

   /**
    * Current [Container] with data assigned to the view
    */
   var container: Container<*> = Container.Pending
      set(value) {
         field = value
         notifyUpdates()
      }

   private var tryAgainListener: (() -> Unit)? = null

   private val binding: CorePresentationPartResultBinding

   init {
      val inflater = LayoutInflater.from(context)
      binding = CorePresentationPartResultBinding.inflate(inflater, this, false)
      addView(binding.root)

      if (isInEditMode) {
         container = Container.Success("")
      } else {
         binding.resultProgressBar.isVisible = false
         binding.resultErrorContainer.isVisible = false
         binding.internalResultContainer.isVisible = false
         children.forEach {
            it.isVisible = false
         }
         container = Container.Pending
      }

      binding.tryAgainButton.setOnClickListener {
         if (isAuthError()) {
            Core.appRestarter.restartApp()
         } else {
            tryAgainListener?.invoke()
         }
      }
   }

   /**
    * Assign try-again listener which is called when [container] has error
    * value ([Container.Error]) and user presses Try Again button. Usually
    * you need to try ro reload data in the [onTryAgain] callback.
    */
   fun setTryAgainListener(onTryAgain: () -> Unit) {
      this.tryAgainListener = onTryAgain
   }

   private fun notifyUpdates() {
      val container = this.container
      binding.resultProgressBar.isVisible = container is Container.Pending
      binding.resultErrorContainer.isVisible = container is Container.Error
      binding.internalResultContainer.isVisible = container !is Container.Success

      if (container is Container.Error) {
         val exception = container.exception
         Core.logger.err(exception)
         binding.resultErrorTextView.text = Core.errorHandler.getUserMessage(exception)
         binding.tryAgainButton.setText(
            if (isAuthError()) R.string.core_presentation_logout
            else R.string.core_presentation_try_again
         )
      }

      children.forEach {
         if (it != binding.root) {
            it.isVisible = container is Container.Success
         }
      }
   }

   private fun isAuthError(): Boolean = container.let {
      it is Container.Error && it.exception is AuthException
   }
}