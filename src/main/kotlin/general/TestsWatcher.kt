package general
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.util.Optional


class TestsWatcher : TestWatcher {
    override fun testSuccessful(context: ExtensionContext) {
        println("Successful test: ${context.displayName}")
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        val errorMessage = cause?.message ?: "no error message"
        println("Failed test: ${context.displayName} -> $errorMessage")
    }

    //есть ли в нем смысл если выводится стандартно от JUnit 5 сообщение для @Disabled тестов
 //   override fun testDisabled(context: ExtensionContext, reason: Optional<String>) {
  //      println("Skipping test: ${context.displayName}, Reason:${reason.orElse("no reason")}") }
}