package frontend.helpers

import com.codeborne.selenide.Selectors

class Wrappers {
    companion object {
        fun byTestGroup(target: String) = Selectors.by("data-test-group", target)!!
        fun byTestId(target: String) = Selectors.by("data-test-id", target)!!
    }
}