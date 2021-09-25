import kotlinx.coroutines.delay
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.abs

class ScreenshotBot(
    private val numMon: Int,
    private val path: String,
    private val subject: String,
    private val date: String,
    private val seconds: Long,
    private val  accuracy: Int ): MyRobot(), Shotter {

    override val name: String
        get() = "ScreenshotBot ver. 1.0"
    override var status: String = "Offline"

    private var i: Long = 1
    private var needStop = false

    private lateinit var lastShot: BufferedImage
    private lateinit var currentShot: BufferedImage


    override fun takeShot() {
        println("Try to take screenshot: $subject $date №$i.png")
        try {
            if (numMon == 1){
                currentShot = Robot().createScreenCapture(Rectangle(Toolkit.getDefaultToolkit().screenSize))
                return
            }
            else if (numMon == 2) {
                val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
                val gs = ge.screenDevices
                val gd = gs[1]
                val gc = gd.configurations
                val gcBounds = gc[0].bounds
                currentShot = Robot().createScreenCapture(Rectangle(gcBounds))
                return
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: AWTException) {
            e.printStackTrace()
        }
        currentShot = BufferedImage(1920,1080,1)
    }

    override fun saveShot() {
        println("Try to save screenshot $i")
        try {
            ImageIO.write(currentShot, "png", File(path, "$subject $date №$i.png"))
        }
        catch (e: IOException){
            e.printStackTrace()
        }
        println("Screenshot $i made successfully!")
    }

    override fun start() {
        println("Starting screenshoting")
        status = "Online"

        lastShot = BufferedImage(1920,1080,1) //TODO: fix this initialization

        while (!needStop)
        {
            takeShot()

            if (analyze() < accuracy) {
                lastShot = currentShot
                i++
                saveShot()
            }

            pause(seconds)
        }
    }

    override fun stop() {
        println("Stopping!")
        status = "Stopped"
        needStop = true
    }

    override fun pause(a: Long) {
        status = "Paused" //Is it actually work?
        //delay(a)
        Thread.sleep(a)
    }
    override fun analyze(): Float {
        var pxDiff = 0L
        val widthA = currentShot.width
        val widthB = lastShot.width
        val heightA = currentShot.height
        val heightB = lastShot.height

        val pxTotal = widthA.toLong() * heightA

        if (widthA == widthB && heightA == heightB) {
            for (x in 0 until widthA) {
                for (y in 0 until heightA) {
                    if (!isSameColor(currentShot.getRGB(x, y), lastShot.getRGB(x, y)))
                        pxDiff++
                }
            }
        }

        return if (pxDiff == 0L) {
            println("Совпадение c предыдущим: " + 100 + "%")
            100f
        } else {
            val result = (1 - pxDiff.toFloat() / pxTotal) * 100
            println("Совпадение c предыдущим: $result%")
            result
        }
    }
    private fun isSameColor(a: Int, b: Int): Boolean {
        return abs(abs(a) - abs(b)) < 10000 && abs(abs(b) - abs(a)) < 10000
    }

    private fun showDots(a: BufferedImage, b: BufferedImage) {
        if (a.width == b.width && a.height == b.height) {
            var x = 0
            while (x < a.width) {
                var y = 0
                while (y < a.height) {
                    val red = Color(255, 0, 0)
                    a.setRGB(x, y, red.rgb)
                    y += a.height / 100
                }
                x += a.width / 100
            }
        }
    }
}