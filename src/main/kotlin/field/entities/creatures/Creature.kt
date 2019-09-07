package field.entities.creatures

import field.entities.CreatureElement
import java.util.*
import kotlin.math.absoluteValue

abstract class Creature{

    abstract val index:Int

    val body: LinkedList<CreatureElement> = LinkedList()

    var mustDie: Boolean = false

    abstract val isDead:Boolean

    abstract val visionRadius:Int
    abstract val visionCenter:Pair<Int,Int>

    abstract fun move(simpleField: Array<Double>)
    abstract fun kill()
    fun removeCollision(collisionElement: CreatureElement, collisions: List<CreatureElement>) = when(collisionElement){
        s>0->" "
        else -> ""
    }
    fun choseAction(ownRating:Int,secondRating:Int)= when{
        ownRating.absoluteValue > secondRating.absoluteValue ->
        ownRating.absoluteValue < secondRating.absoluteValue ->
        ownRating < secondRating ->
    }

    fun isEat()
}