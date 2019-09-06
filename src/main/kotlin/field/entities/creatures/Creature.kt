package field.entities.creatures

import field.entities.CreatureElement
import java.util.*

abstract class Creature{


    val body: LinkedList<CreatureElement> = LinkedList()

    abstract var mustDead: Boolean
    abstract val isDead:Boolean

    abstract val visionRadius:Int
    abstract val visionCenter:Pair<Int,Int>

    abstract fun move(simpleField: Array<Double>)
    abstract fun kill()
    abstract fun removeCollision(collisionElement: CreatureElement, collisions: List<CreatureElement>)
}