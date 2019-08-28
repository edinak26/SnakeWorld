import org.lwjgl.opengl.GL11
import kotlin.random.Random

open class Colored(
    var r: Float = 0.0f,
    var g: Float = 0.0f,
    var b: Float = 0.0f
) {
    val speedR = randomSpeed()
    val speedG = randomSpeed()
    val speedB = randomSpeed()

    fun randomSpeed() = Random.nextInt(1, 40) * 0.001f

    fun changeColor() {
        r += Random.nextFloat() * speedR
        g += Random.nextFloat() * speedG
        b += Random.nextFloat() * speedB
    }

    fun setColor() = GL11.glColor3f(
        Math.sin(r.toDouble()).toFloat(),
        Math.sin(g.toDouble()).toFloat(),
        Math.sin(b.toDouble()).toFloat()
    )
}