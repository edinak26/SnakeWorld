package view.entities

import view.entities.Color
import kotlin.random.Random

open class Colored(
    private var r: Double = 0.0,
    private var g: Double = 0.0,
    private var b: Double = 0.0,
    var colorChangeSpeed: Double = 1.0
) {
    constructor(color: Color, colorChangeSpeed: Double = 1.0)
            : this(color.r,color.g,color.b, colorChangeSpeed)

    var speedR = randomSpeed()
    var speedG = randomSpeed()
    var speedB = randomSpeed()

    private fun randomSpeed() = Random.nextInt(1, 40) * 0.001f

    val activatedR get () = activate(r)
    val activatedG get () = activate(g)
    val activatedB get () = activate(b)

    val color get() = Color(r, g, b)

    private fun activate(color: Double) = Math.sin(color).toFloat()

    fun updateColor() {
        r += speedR * colorChangeSpeed
        g += speedG * colorChangeSpeed
        b += speedB * colorChangeSpeed
    }

}