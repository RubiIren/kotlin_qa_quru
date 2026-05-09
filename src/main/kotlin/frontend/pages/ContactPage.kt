package frontend.pages

import com.codeborne.selenide.Selenide.element
import io.qameta.allure.Step
import org.openqa.selenium.By

class ContactPage {

    private val txtNameProject get() = element(By.ByClassName("contact-title"))

    @Step("Получить название раздела ")
    fun getNameProject(): String {
        return txtNameProject.text
    }

}