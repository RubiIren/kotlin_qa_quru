package general

import backend.controllers.Controllers
import backend.helpers.AuthorizationHelper
import backend.helpers.GarbageCollector
import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import io.qameta.allure.Attachment
import org.junit.platform.engine.TestExecutionResult
import org.junit.platform.launcher.TestExecutionListener
import org.junit.platform.launcher.TestIdentifier
import org.junit.platform.launcher.TestPlan
import org.openqa.selenium.By

class TestListener : Controllers(), TestExecutionListener {
    private val authHelper = AuthorizationHelper()

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
        println("|------ Test Plan Finished -----|")
        Selenide.closeWebDriver()
        println("|------ GarbageCollector -----|")
        GarbageCollector.user.forEach { id ->
            users.deleteUserById(token = authHelper.getAdminToken(), id = id).also { println("Deleted User: $id") }
        }
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