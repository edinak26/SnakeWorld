package field.entities.creatures

import neural.network.NeuralNetwork
import SNAKE_VISION_RADIUS
import field.entities.FieldCell
import field.enums.Direction
import org.lwjgl.opengl.GL11
import kotlin.math.absoluteValue
import kotlin.random.Random


data class Snake(
    var length: Int = 5,
    var index: Int
) : Creature() {
    override var mustDead: Boolean = false

    val brain = NeuralNetwork()
    val head get() = body[0]

    override val visionCenter get() = head.coords
    override val isDead get() = body.size == 0
    override val visionRadius: Int = SNAKE_VISION_RADIUS

    fun addElement(element: FieldCell) = body.add(element)
    fun addElements(elements: List<FieldCell>) = elements.forEach { addElement(it) }


    override fun move(simpleField: Array<Double>) {
        val resultArray = brain.calc(simpleField)
        val result = resultArray.indexOf(resultArray.max()) - 1
        moveBody()

        if (head.direction == null) {
            head.direction = Direction.valueOf(Random.nextInt(0,4))
        }
        head.move(
            Direction.valueOf(result + head.direction!!.index)
                ?: throw Exception("Undefined direction")
        )
    }

    fun moveBody() {
        for ((cell, nextCell) in body.asReversed().zipWithNext()) {
            val direction = nextCell.direction
            if (direction != null)
                cell.move(direction)
        }
    }

    override fun glColorDraw() { //TODO refactor
        changeColor()
        setColor()

        GL11.glColor4f(1f, 1f, 1f, 1f)
        body.forEach { it.glDraw() }
        GL11.glColor4f(1f, 1f, 1f, 1f)

        GL11.glColor4f(0.5f, 0.2f, 0.4f, 1f)
        body[0].glDraw()
        GL11.glColor4f(1f, 1f, 1f, 1f)
    }

    override fun kill() {
        body.forEach { it.remove() }
        body.removeAll { true }
    }


    override fun removeCollision(collisionEntities: List<FieldCell>, entityCell: FieldCell) {
        if (entityCell == head) {
            mustDead = collisionEntities
                .any { it.rating.absoluteValue >= head.rating.absoluteValue }

        }
    }

}
