import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


fun main(args: Array<String>) = runBlocking {

    val date = SimpleDateFormat("dd-MM").format(Date())
    val path = args[0]
    val monitorID = args[1].toInt()
    val seconds = args[2].toInt() * 1000L
    val subject = args[3]
    val accuracy = args[4].toInt()
    println("Screenshots are located at $path")

    val bot = ScreenshotBot(monitorID,path,subject,date,seconds,accuracy)
    bot.start()
}
