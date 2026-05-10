package frontend.cart

import frontend.components.list.ProductItem
import frontend.helpers.BaseUiTest
import frontend.pages.MainPage
import io.kotest.matchers.equality.shouldBeEqualToDifferentTypeIgnoringFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CartTest : BaseUiTest() {

    @Test
    @DisplayName("Проверка товара в корзине")
    fun testProductsInCart() {
        MainPage()
            .open()
            .getPopularProducts()
            .first().btnIncrement.click()

        val firstCartItem = MainPage()
            .navigateHeader()
            .clickLink("Cart")
            .navigateCartPopup()
            .getCartProducts()
            .first()

        val firstPopularItem = MainPage().getPopularProducts().first()

        firstPopularItem.name shouldBe firstCartItem.name
        firstPopularItem.quantity shouldBe firstCartItem.quantity
        firstPopularItem.image shouldBe firstCartItem.image
        firstPopularItem.price shouldBe firstCartItem.price

        firstPopularItem.shouldBeEqualToDifferentTypeIgnoringFields(
            firstCartItem,
            ProductItem::description,
            ProductItem::btnDecrement,
            ProductItem::btnIncrement,
            ProductItem::image
        )
    }
}