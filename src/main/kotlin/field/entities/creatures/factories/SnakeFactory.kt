package field.entities.creatures.factories

import field.*
import field.constants.creatures.*
import field.entities.CreatureElement
import field.entities.creatures.Creature
import field.entities.creatures.Snake

class SnakeFactory(
    var length: Int,
    field: Field
) : CreatureFactory(field) {
    override fun create(): Creature {
        val newSnake = createEmptySnake()
        val head = createHead(newSnake)
        newSnake.addHead(head)
        val body = createBody(head)
        newSnake.addElements(body)
        return newSnake
    }

    private fun createEmptySnake(): Snake {
        creaturesNumber++
        return Snake(creaturesNumber)
    }

    private fun createHead(snake: Snake): CreatureElement {
        val head = field.createCell(snake)
        with(head){
            neuralValue = SNAKE_HEAD_NEURAL_VALUE
            rating = SNAKE_HEAD_RATING
            nutrient = SNAKE_HEAD_NUTRIENT
            this.
        }
        return head
    }

    private fun createBody(head: CreatureElement): List<CreatureElement> {
        val body = mutableListOf<CreatureElement>()
        for (i in 1 until length) {
            val bodyElement = createBodyElement(head)
            body.add(bodyElement)
        }
        return body
    }

    private fun createBodyElement(head: CreatureElement): CreatureElement {
        val bodyElement = field.copy(head)
        bodyElement.neuralValue = SNAKE_BODY_NEURAL_VALUE
        bodyElement.rating = SNAKE_BODY_RATING
        return bodyElement
    }

}