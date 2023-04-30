package uz.bigboys.orders.presentation.create

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.elveum.elementadapter.ElementListAdapter
import com.elveum.elementadapter.simpleAdapter
import uz.bigboys.orders.domain.entities.CartItem
import uz.bigboys.orders.domain.entities.Field
import uz.bigboys.orders.domain.entities.Recipient
import dagger.hilt.android.AndroidEntryPoint
import uz.bigboys.orders.R
import uz.bigboys.orders.databinding.FragmentCreateOrderBinding
import uz.bigboys.orders.databinding.ItemOrderProductBinding
import uz.bigboys.presentation.live.observeEvent
import uz.bigboys.presentation.viewBinding
import uz.bigboys.presentation.views.observe
import uz.bigboys.presentation.views.setupSimpleList

@AndroidEntryPoint
class CreateOrderFragment : Fragment(R.layout.fragment_create_order) {

    private val binding: FragmentCreateOrderBinding by viewBinding()
    private val viewModel: CreateOrderViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val adapter = setupOrderItemsList()
            observeState(adapter)
            observeEvents()
            setupListeners()
        }
    }

    private fun FragmentCreateOrderBinding.observeState(adapter: ElementListAdapter<CartItem>) {
        root.observe(viewLifecycleOwner, viewModel.stateLiveValue) { state ->
            createOrderButton.isEnabled = state.enableCreateOrderButton
            progressBar.isInvisible = !state.showProgressBar
            totalPriceValueTextView.text = state.cart.totalPrice.text
            adapter.submitList(state.cart.cartItems)
        }
    }

    private fun FragmentCreateOrderBinding.observeEvents() {
        val errorMessage = getString(R.string.orders_empty_field)
        viewModel.emptyFieldErrorLiveEvent.observeEvent(viewLifecycleOwner) {
            val input = when (it) {
                Field.FIRST_NAME -> firstNameTextInput
                Field.LAST_NAME -> lastNameTextInput
                Field.ADDRESS -> addressTextInput
            }
            input.isErrorEnabled = true
            input.error = errorMessage
        }
    }

    private fun FragmentCreateOrderBinding.setupListeners() {
        root.setOnClickListener { viewModel.load() }
        createOrderButton.setOnClickListener {
            viewModel.createOrder(makeRecipient())
        }
        firstNameEditText.addTextChangedListener { firstNameTextInput.error = null }
        lastNameEditText.addTextChangedListener { lastNameTextInput.error = null }
        addressEditText.addTextChangedListener { addressTextInput.error = null }
    }

    private fun FragmentCreateOrderBinding.setupOrderItemsList(): ElementListAdapter<CartItem> {
        val adapter = createAdapter()
        productsRecyclerView.setupSimpleList()
        productsRecyclerView.adapter = adapter
        return adapter
    }

    private fun createAdapter(): ElementListAdapter<CartItem> =
        simpleAdapter<CartItem, ItemOrderProductBinding> {
            areItemsSame = { oldItem, newItem -> oldItem.productId == newItem.productId }
            bind { cartItem ->
                productNameTextView.text =
                    getString(R.string.orders_item_text, cartItem.name, cartItem.quantity)
                priceTextView.text = cartItem.price.text
            }
        }

    private fun FragmentCreateOrderBinding.makeRecipient(): Recipient {
        return Recipient(
            firstName = firstNameEditText.text.toString(),
            lastname = lastNameEditText.text.toString(),
            address = addressEditText.text.toString(),
            comment = commentEditText.text.toString(),
        )
    }
}