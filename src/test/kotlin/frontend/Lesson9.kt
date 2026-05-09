package frontend

import com.codeborne.selenide.Selenide
import frontend.helpers.BaseUiTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Lesson9 : BaseUiTest() {
    @Test
    @DisplayName("Проверка открытия qa.guru")
    //@Disabled("Необходимо в BaseUiTest раскомментировать настройки для WebDriver")
    fun testOpenGoogle() {
        openBrowser()
        val title = Selenide.title()
        title shouldBe "Курсы тестировщиков - обучение тестированию онлайн с нуля | QA.GURU"
    }


}