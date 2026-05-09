package frontend.helpers

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.SelenideElement
import io.kotest.assertions.AssertionErrorBuilder.Companion.fail

class Extensions {
    companion object {
        fun ElementsCollection.getFirstOrAsserted(text: String): SelenideElement {
            return this.firstOrNull { it.text == text }
                ?.shouldBe(visible)  // Ждём видимости найденного элемента
                ?: fail("Элемент с текстом '$text' не найден")
        }
    }
}