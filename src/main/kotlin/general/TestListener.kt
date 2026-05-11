package general

import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.junit.jupiter.api.extension.Extension
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan
import org.openqa.selenium.By

class TestListener : TestExecutionListener {

    override fun testPlanExecutionStarted(testPlan: TestPlan) {   // Вызывается в начале выполнения всего тестового плана
        println("|------ Test Plan Started -----|")
        println("Initializing Configurations...").also { Config.get } // загружает конфигурацию через Config.get
    }

    override fun executionFinished(testIdentifier: TestIdentifier, testExecutionResult: TestExecutionResult) {
        if (testIdentifier.isTest) println("Finished test: ${testIdentifier.displayName} - Result: ${testExecutionResult.status}")
        if (testExecutionResult.status == TestExecutionResult.Status.FAILED && testIdentifier.displayName != "JUnit Jupiter") {
            attachScreenshot()
            savePageSource()
        }
    }

    override fun executionSkipped(testIdentifier: TestIdentifier, reason: String) {
        if (testIdentifier.isTest) println("Skipping test: ${testIdentifier.displayName} - Reason: $reason")
    }

    override fun testPlanExecutionFinished(testPlan: TestPlan) {
        Selenide.closeWebDriver()
        println("|------ Test Plan Finished -----|")
    }

    @Attachment(value = "{name}", type = "image/png")
    fun attachScreenshot(name: String = "SCREENSHOT"): ByteArray? {
        return Screenshots.takeScreenShotAsFile()?.readBytes()
    }

    @Attachment(value = "{name}.html", type = "text/html")
    fun savePageSource(name: String = "Page-Source"): ByteArray? =
        Selenide.element(By.tagName("html"))
            ?.getAttribute("outerHTML")
            ?.toByteArray()
}