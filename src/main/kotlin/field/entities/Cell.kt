package field.entities

import DEFAULT_RATING
import FIELD_HEIGHT
import FIELD_WIDTH
import field.Field
import field.entities.Direction.*
import field.entities.creatures.Creature
import org.lwjgl.opengl.GL11
import kotlin.math.absoluteValue

data class FieldCell(
    private var x: Int,
    private var y: Int,
    val entity: Creature,
    val field: Field,
    var neuralValue: Double = 0.0,
    var index: Int = -1
) {
    constructor(
        cords: Pair<Int, Int>,
        entity: Creature,
        field: Field
    ) : this(cords.first, cords.second, entity, field)


    var rating: Int = DEFAULT_RATING

    val coords get() = Pair(x, y)

    var direction: Direction? = null

    val topEdge get() = y
    val bottomEdge get() = y + 1
    val rightEdge get() = x + 1
    val leftEdge get() = x

    val glTop get() = 1 - topEdge * 2 / FIELD_HEIGHT.toDouble()
    val glBottom get() = 1 - bottomEdge * 2 / FIELD_HEIGHT.toDouble()
    val glRight get() = rightEdge * 2 / FIELD_WIDTH.toDouble() - 1
    val glLeft get() = leftEdge * 2 / FIELD_WIDTH.toDouble() - 1

    fun move(direction: Direction) {
        this.direction = direction
        field.move(this)
    }

    fun moveCoords() = when (direction) {
        Up -> y--
        Down -> y++
        Right -> x++
        Left -> x--
        else -> throw Exception("Undefined snake direction")
    }

    fun glDraw() {
        GL11.glBegin(GL11.GL_QUADS)
        GL11.glVertex2d(glLeft, glTop)
        GL11.glVertex2d(glRight, glTop)
        GL11.glVertex2d(glRight, glBottom)
        GL11.glVertex2d(glLeft, glBottom)
        GL11.glEnd()
    }

    fun remove() = field.remove(this)


}

enum class Direction(val index: Int) {
    Up(0), Down(2), Right(1), Left(3);

    fun invert(): Direction? {
        return valueOf(this.index + 2)
    }

    companion object {
        fun valueOf(value: Int): Direction? = values().find { it.index == abs(value) % 4 }

        fun abs(value: Int): Int {
            var result = value
            while (result < 0)
                result += 4
            return result
        }
    }
}
