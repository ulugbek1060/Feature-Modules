package uz.bigboys.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * Default arg name for holding screen args in fragments. Use this name
 * if you want to integrate your navigation with the core.
 */
const val ARG_SCREEN = "screen"

/**
 * Get screen args attached to the [Fragment].
 */
inline fun <reified T : BaseScreen> Fragment.args(): T {
   return requireArguments().serializable(ARG_SCREEN)
      ?: throw IllegalStateException("Screen args don't exist")
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
   Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
   else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}

inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
   Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
   else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
}