package field.entities.creatures

import field.entities.CreatureElement
import java.util.*
import kotlin.math.absoluteValue

abstract class Creature {

    abstract val index: Int

    val body: LinkedList<CreatureElement> = LinkedList()

    var mustDie: Boolean = false

    abstract val isDead: Boolean

    abstract val visionRadius: Int
    abstract val visionCenter: Pair<Int, Int>

    abstract fun move(simpleField: Array<Double>)
    abstract fun eat(element: CreatureElement)
    abstract fun kill()

    fun removeCollisions(creatureElement: CreatureElement, collisionElements: List<CreatureElement>) =
        collisionElements.forEach { collisionElement -> removeCollision(creatureElement, collisionElement) }

    private fun removeCollision(creatureElement: CreatureElement, collisionElement: CreatureElement) =
        if (creatureElement.rating >= collisionElement.rating)
            eat(collisionElement)
        else
            kill()

}