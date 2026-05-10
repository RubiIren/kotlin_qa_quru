package frontend.components.list

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import frontend.helpers.Wrappers.Companion.byTestGroup
import frontend.helpers.toPrice

class CartItems(val items: ElementsCollection) {

    fun getItems(): List<CartItem> {
        return items
            .map {
                CartItem(
                    image = it.find(byTestGroup("cart-item-image")),
                    name = it.find(byTestGroup("cart-item-name")).text,
                    price = it.find(byTestGroup("cart-item-unit-price")).text.toPrice(),
                    totalPrice = it.find(byTestGroup("cart-item-price")).text.toPrice(),
                    btnIncrement = it.find(byTestGroup("cart-item-increment")),
                    btnDecrement = it.find(byTestGroup("cart-item-decrement")),
                    btnDeleteItem = it.find(byTestGroup("cart-item-remove")),
                    quantity = it.find(byTestGroup("cart-item-qty")).text.toInt(),
                )
            }
    }
}

data class CartItem(
    val image: SelenideElement,
    val name: String,
    val price: Float,
    val totalPrice: Float,
    val btnIncrement: SelenideElement,
    val btnDecrement: SelenideElement,
    val btnDeleteItem: SelenideElement,
    var quantity: Int,
)