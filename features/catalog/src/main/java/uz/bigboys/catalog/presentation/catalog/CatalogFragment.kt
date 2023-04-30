package uz.bigboys.catalog.presentation.catalog

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.ElementListAdapter
import com.elveum.elementadapter.SimpleBindingAdapter
import com.elveum.elementadapter.simpleAdapter
import uz.bigboys.catalog.domain.entities.ProductWithCartInfo
import dagger.hilt.android.AndroidEntryPoint
import uz.bigboys.catalog.R
import uz.bigboys.catalog.databinding.FragmentCatalogBinding
import uz.bigboys.catalog.databinding.ItemProductBinding
import uz.bigboys.presentation.loadResource
import uz.bigboys.presentation.loadUrl
import uz.bigboys.presentation.viewBinding
import uz.bigboys.presentation.views.observe
import uz.bigboys.presentation.views.setupSimpleList

@AndroidEntryPoint
class CatalogFragment : Fragment(R.layout.fragment_catalog) {

    private val binding: FragmentCatalogBinding by viewBinding()
    private val viewModel: CatalogViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = createAdapter()
        with(binding) {
            observeState(adapter)
            setupList(adapter)
            setupListeners()
        }
    }

    private fun FragmentCatalogBinding.setupListeners() {
        sortAndFilterButton.setOnClickListener {
            viewModel.launchFilter()
        }
    }

    private fun FragmentCatalogBinding.setupList(adapter: ElementListAdapter<ProductWithCartInfo>) {
        productsRecyclerView.setupSimpleList()
        productsRecyclerView.adapter = adapter
    }

    private fun FragmentCatalogBinding.observeState(adapter: SimpleBindingAdapter<ProductWithCartInfo>) {
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue) { state ->
            adapter.submitList(state.products)
        }
    }

    private fun createAdapter() = simpleAdapter<ProductWithCartInfo, ItemProductBinding> {
        areItemsSame = { oldItem, newItem -> oldItem.product.id == newItem.product.id }
        areContentsSame = { oldItem, newItem -> oldItem == newItem }
        bind { productWithCartInfo ->
            val product = productWithCartInfo.product
            nameTextView.text = product.name
            shortDescriptionTextView.text = product.shortDetails
            originPriceTextView.text = product.price.text
            if (product.priceWithDiscount == null) {
                priceWithDiscountTextView.isVisible = false
                originPriceTextView.setBackgroundColor(Color.TRANSPARENT)
            } else {
                priceWithDiscountTextView.isVisible = true
                priceWithDiscountTextView.text = product.priceWithDiscount.text
                originPriceTextView.setBackgroundResource(uz.bigboys.theme.R.drawable.core_theme_diagonal_line)
            }
            addToCartImageView.isEnabled = !productWithCartInfo.isInCart
            addToCartImageView.setImageResource(
                if (productWithCartInfo.isInCart)
                    uz.bigboys.theme.R.drawable.core_theme_ic_done
                else
                    R.drawable.ic_add_to_cart
            )
            if (product.photo.isNotBlank()) {
                productImageView.loadUrl(product.photo)
            } else {
                productImageView.loadResource(uz.bigboys.theme.R.drawable.core_theme_placeholder)
            }
            categoryHintTextView.text = getString(R.string.catalog_category, product.category)
        }

        listeners {
            addToCartImageView.onClick { productWithCartInfo ->
                viewModel.addToCart(productWithCartInfo)
            }
            root.onClick { productWithCartInfo ->
                viewModel.launchDetails(productWithCartInfo)
            }
        }
    }
}