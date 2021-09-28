package model

import java.awt.image.BufferedImage

interface Shotter {
    fun takeShot()
    fun saveShot()


    fun start()
    fun stop()
    fun pause(a: Long)
    fun analyze(): Float

}