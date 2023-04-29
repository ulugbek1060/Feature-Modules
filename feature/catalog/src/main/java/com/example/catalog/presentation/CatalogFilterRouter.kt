package com.example.catalog.presentation

import com.example.catalog.domain.entities.ProductFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface CatalogFilterRouter {

    /**
     * Launch filter screen with the predefined product filter
     */
    fun launchFilter(initialFilter: ProductFilter)

    /**
     * Send filter results from the filter screen
     */
    fun sendFilterResults(filter: ProductFilter)

    /**
     * Listen for results sent by filter screen.
     */
    fun receiveFilterResult(): Flow<ProductFilter>

    /**
     * Go back the previous screen.
     */
    fun goBack()

    /**
     * Register back handler for overriding default back button logic.
     */
    fun registerBackHandler(scope: CoroutineScope, handler: () -> Boolean)
}