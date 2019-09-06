package field.entities

import field.DEFAULT_RATING
import field.Field
import field.entities.creatures.Creature
import field.enums.Direction
import field.enums.Direction.*

data class CreatureElement(
    override var x: Int,
    override var y: Int,
    val entity: Creature,
    val field: Field,
    var neuralValue: Double = 0.0,
    var index: Int = -1
): Square(x,y) {

    constructor(
        cords: Pair<Int, Int>,
        entity: Creature,
        field: Field
    ) : this(cords.first, cords.second, entity, field)


    var rating: Int = DEFAULT_RATING

    var coords
        get() = Pair(x, y)
        set(coords) {
            x = coords.first
            y = coords.second
        }

    fun move(direction: Direction) {
        field.move(this, direction)
    }

    fun moveCoords(direction: Direction) = when (direction) {
        Up -> y--
        Down -> y++
        Right -> x++
        Left -> x--
    }

    fun remove() = field.remove(this)
    fun moveTo(element: CreatureElement) {
        field.moveTo(this, element)
    }
}


