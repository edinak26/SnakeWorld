import field.Field
import  field.entities.creatures.factories.CreatureFactory
import field.entities.creatures.Creature
import field.entities.creatures.Snake
import field.entities.creatures.factories.SnakeFactory
import view.Drawer

class Game(val squareNumber: Int = 10) {
    val snakes = mutableListOf<Snake>()
    val isEnd: Boolean get() = snakes.isEmpty()
    val field = Field()


    fun create() {
        val snakeFactory: CreatureFactory = SnakeFactory(10, field)
        for (i in 1..250)
            snakes.add(snakeFactory.create() as Snake)
    }

    fun update() {
        snakes.forEach { it.move(field.generateSimpleField(it.visionCenter)) }
        field.removeCollisions()
        snakes.filter{ it.mustDead }.forEach{ it.kill() }
        snakes.removeAll { it.body.size == 0 }
        Drawer.drawSnakes(snakes)
    }

}