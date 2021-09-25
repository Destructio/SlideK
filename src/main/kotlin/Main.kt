import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
@Preview
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }

    DesktopMaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}

fun main(args: Array<String>) = application {
    val date = SimpleDateFormat("dd-MM").format(Date())
    val path = args[0]
    val monitorID = args[1].toInt()
    val seconds = args[2].toInt() * 1000L
    val subject = args[3]
    val accuracy = args[4].toInt()
    println("Screenshots are located at $path")

    Window(onCloseRequest = ::exitApplication) {
        App()
    }

   /* val bot = ScreenshotBot(monitorID,path,subject,date,seconds,accuracy)
    bot.start()*/
}
