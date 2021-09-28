package ui

import androidx.compose.desktop.DesktopMaterialTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BotUI() {
    var status = mutableStateOf("status")
    val date = SimpleDateFormat("dd-MM").format(Date())
    var path = mutableStateOf("")
    var monitorID = 1
    var seconds = 5 * 1000L
    var subject = "subject"
    var accuracy = 80

        DesktopMaterialTheme {
            ScreenShotPreview()
            SettingsArea()
        }
}
