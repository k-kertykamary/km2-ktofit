import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.kkk.kopilot.MainView

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MainView()
    }
}