package field.entities.creatures.factories

import field.Field
import field.entities.creatures.Creature

abstract class CreatureFactory (
    val field: Field
){
    var creaturesNumber = 0
    abstract fun create(): Creature
}