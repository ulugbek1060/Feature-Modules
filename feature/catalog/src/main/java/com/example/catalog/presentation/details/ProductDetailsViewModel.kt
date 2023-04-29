package com.example.catalog.presentation.details

import com.example.catalog.domain.AddToCartUseCase
import com.example.catalog.domain.GetProductDetailsUseCase
import com.example.catalog.domain.entities.ProductWithCartInfo
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import uz.bigboys.presentation.BaseViewModel
import com.example.catalog.presentation.details.ProductDetailsFragment.Screen
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ua.cn.stu.multimodule.catalog.R
import uz.bigboys.common.Container

class ProductDetailsViewModel @AssistedInject constructor(
    @Assisted private val screen: Screen,
    private val getProductDetailsUseCase: GetProductDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : BaseViewModel() {

    private val addToCartInProgressFlow = MutableStateFlow(false)
    private val productFlow = getProductDetailsUseCase.getProduct(screen.productId)
    val stateLiveValue = combine(
        productFlow,
        addToCartInProgressFlow, ::merge
    )
        .toLiveValue(initialValue = Container.Pending)

    fun reload() = debounce {
        getProductDetailsUseCase.reload()
    }

    fun addToCart() = debounce {
        viewModelScope.launch {
            val state = stateLiveValue.value.getOrNull() ?: return@launch
            try {
                addToCartInProgressFlow.value = true
                addToCartUseCase.addToCart(state.product)
            } finally {
                addToCartInProgressFlow.value = false
            }
        }
    }



    private fun merge(
        productContainer: Container<ProductWithCartInfo>,
        isAddToCartInProgress: Boolean
    ): Container<State> {
        return productContainer.map {
            State(
                productWithCartInfo = it,
                addToCartInProgress = isAddToCartInProgress
            )
        }
    }


    class State(
        private val productWithCartInfo: ProductWithCartInfo,
        private val addToCartInProgress: Boolean,
    ) {
        val product = productWithCartInfo.product
        val showAddToCartProgress: Boolean get() = addToCartInProgress
        val showAddToCartButton: Boolean get() = !addToCartInProgress
        val enableAddToCartButton: Boolean get() = !productWithCartInfo.isInCart
        val addToCartTextRes: Int
            get() = if (productWithCartInfo.isInCart) {
                R.string.catalog_in_cart
            } else {
                R.string.catalog_add_to_cart
            }
    }

    @AssistedFactory
    interface Factory {
        fun create(screen: Screen): ProductDetailsViewModel
    }

}