import ui.BotUI
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {


    Window(
        onCloseRequest = ::exitApplication,
        title = "SlideK",
        icon = painterResource("icons/icon.png"),
        state = rememberWindowState(width = 600.dp, height = 900.dp)
    ) {
        BotUI()
    }
}
