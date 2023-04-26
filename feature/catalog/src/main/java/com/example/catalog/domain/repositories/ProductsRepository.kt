package com.example.catalog.domain.repositories

import com.example.catalog.domain.entities.Price
import com.example.catalog.domain.entities.Product
import com.example.catalog.domain.entities.ProductFilter
import kotlinx.coroutines.flow.Flow
import uz.bigboys.common.Container

interface ProductsRepository {

    /**
     * Listen for all products which match the specified filter.
     * @return infinite flow, always success; errors are delivered to [Container]
     */
    fun getProducts(filter: ProductFilter): Flow<Container<List<Product>>>

    /**
     * Get the product info by ID.
     */
    suspend fun getProduct(id: Long): Product

    /**
     * Get min possible product price.
     */
    suspend fun getMinPossiblePrice(): Price

    /**
     * Get max possible product price.
     */
    suspend fun getMaxPossiblePrice(): Price

    /**
     * Get all available product categories.
     */
    suspend fun getAllCategories(): List<String>

}