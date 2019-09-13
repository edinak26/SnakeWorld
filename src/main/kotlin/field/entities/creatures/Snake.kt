package field.entities.creatures

import neural.network.NeuralNetwork
import field.SNAKE_VISION_RADIUS
import field.entities.CreatureElement
import field.enums.Direction
import field.enums.SnakeDirection
import kotlin.math.absoluteValue


data class Snake(
    override val index: Int
) : Creature() {

    private val brain = NeuralNetwork()
    val head: CreatureElement get() = body.first
    var currentDirection: Direction = Direction.randomDirection()

    override val visionCenter get() = head.coords
    override val visionRadius: Int = SNAKE_VISION_RADIUS

    override val isDead get() = body.size == 0

    fun addHead(head: CreatureElement) = body.push(head)

    fun addElements(elements: List<CreatureElement>) = elements.forEach { addElement(it) }

    fun addElement(element: CreatureElement) = body.add(element)

    override fun move(simpleField: Array<Double>) {
        moveBody()
        moveHead(simpleField)
    }

    fun moveBody() {
        for (i in body.size - 1 downTo 1) {
            val currentElement = body[i]
            val nextElement = body[i - 1]
            currentElement.moveTo(nextElement)
        }
    }

    private fun moveHead(simpleField: Array<Double>) {
        val directionIndex = brain.calc(simpleField)
        val selectedSnakeDirection =
            SnakeDirection.valueOf(directionIndex) ?: throw Exception("Undefined direction exception")
        currentDirection += selectedSnakeDirection
        head.move(currentDirection)
    }

    override fun eat(element: CreatureElement) {

    }

    override fun kill() {
        body.forEach { it.remove() }
        body.clear()
    }


}
