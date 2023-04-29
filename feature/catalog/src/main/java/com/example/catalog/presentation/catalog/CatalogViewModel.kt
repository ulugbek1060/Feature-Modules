package com.example.catalog.presentation.catalog

import com.example.catalog.domain.AddToCartUseCase
import com.example.catalog.domain.GetCatalogUseCase
import com.example.catalog.domain.entities.ProductFilter
import com.example.catalog.domain.entities.ProductWithCartInfo
import com.example.catalog.presentation.CatalogFilterRouter
import com.example.catalog.presentation.CatalogRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import uz.bigboys.common.Container
import uz.bigboys.presentation.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val catalogRouter: CatalogRouter,
    private val catalogFilterRouter: CatalogFilterRouter
) : BaseViewModel() {

    private val filterFlow = MutableStateFlow(ProductFilter.EMPTY)

    @OptIn(ExperimentalCoroutinesApi::class)
    val stateLiveValue = filterFlow
        .flatMapLatest { filter ->
            getCatalogUseCase
                .getProducts(filter)
                .map { container ->
                    container.map {
                        State(
                            products = it,
                            filter = filter
                        )
                    }
                }
        }
        .toLiveValue(initialValue = Container.Pending)

    init {
        viewModelScope.launch {
            catalogFilterRouter.receiveFilterResult().collectLatest {
                filterFlow.value = it
            }
        }
    }

    fun launchFilter() = debounce {
        catalogFilterRouter.launchFilter(filterFlow.value)
    }

    fun launchDetails(productWithCartInfo: ProductWithCartInfo) = debounce {
        catalogRouter.launchDetails(productWithCartInfo.product.id)
    }

    fun addToCart(productWithCartInfo: ProductWithCartInfo) = debounce {
        viewModelScope.launch {
            addToCartUseCase.addToCart(productWithCartInfo.product)
        }
    }

    class State(
        val products: List<ProductWithCartInfo>,
        val filter: ProductFilter
    )

}