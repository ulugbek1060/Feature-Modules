package com.example.catalog.domain

import com.example.catalog.domain.entities.Product
import com.example.catalog.domain.repositories.CartRepository
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withTimeout
import uz.bigboys.common.Container
import uz.bigboys.common.Core
import javax.inject.Inject

class AddToCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {

    suspend fun addToCart(product: Product) = withTimeout(Core.localTimeoutMillis) {
        val productIdsInCart = cartRepository.getProductIdentifiersInCart()
            .filterIsInstance<Container.Success<Set<Long>>>()
            .first()
        if (!productIdsInCart.value.contains(product.id)) {
            cartRepository.addToCart(product.id)
        }
    }
}