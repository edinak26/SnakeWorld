package field.entities.creatures

import field.FOOD_VISION_RADIUS
import field.SNAKE_VISION_RADIUS
import field.entities.CreatureElement
import field.enums.Direction
import neural.network.NeuralNetwork

class Food(
    override val index: Int
) : Creature() {
    override fun eat(element: CreatureElement) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val brain = NeuralNetwork()

    val head get() = body[0]

    var currentDirection: Direction = Direction.randomDirection()

    override val visionCenter get() = head.coords
    override val visionRadius: Int = FOOD_VISION_RADIUS

    override val isDead get() = body.size == 0


    override fun move(simpleField: Array<Double>) {
        val directionIndex = brain.calc(simpleField)
        currentDirection = Direction.valueOf(directionIndex)
        head.move(currentDirection)
    }

    override fun kill() {
        head.remove()
        body.removeAt(0)
    }
}