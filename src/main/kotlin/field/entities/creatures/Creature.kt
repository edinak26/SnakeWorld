package field.entities.creatures

import Colored
import field.entities.FieldCell

abstract class Creature: Colored() {


    val body = mutableListOf<FieldCell>()

    abstract var mustDead: Boolean
    abstract val isDead:Boolean

    abstract val visionRadius:Int
    abstract val visionCenter:Pair<Int,Int>

    abstract fun move(simpleField: Array<Double>)
    abstract fun kill()
    abstract fun glColorDraw()
    abstract fun removeCollision(collisionEntities: List<FieldCell>, entityCell: FieldCell)
}