package field.entities.creatures.factories

import SNAKE_BODY_NEURAL_VALUE
import SNAKE_BODY_RATING
import SNAKE_HEAD_NEURAL_VALUE
import SNAKE_HEAD_RATING
import field.Field
import field.entities.FieldCell
import field.entities.creatures.Creature
import field.entities.creatures.Snake

class SnakeFactory(
    var length: Int = 5,
    field: Field
) : CreatureFactory(field) {
    override fun create(): Creature {
        val newSnake = createEmptySnake()
        val head = createHead(newSnake)
        newSnake.addElement(head)
        val body = createBody(head)
        newSnake.addElements(body)
        return newSnake
    }

    private fun createEmptySnake(): Snake {
        creaturesNumber++
        return Snake(length, creaturesNumber)
    }

    private fun createHead(snake: Snake): FieldCell {
        val head = field.createCell(snake)
        head.neuralValue = SNAKE_HEAD_NEURAL_VALUE
        head.rating = SNAKE_HEAD_RATING
        return head
    }

    private fun createBody(head: FieldCell): List<FieldCell> {
        val body = mutableListOf<FieldCell>()
        for (i in 1 until length) {
            val bodyElement = createBodyElement(head)
            body.add(bodyElement)
        }
        return body
    }

    private fun createBodyElement(head: FieldCell): FieldCell {
        val bodyElement = field.copy(head)
        bodyElement.neuralValue = SNAKE_BODY_NEURAL_VALUE
        bodyElement.rating = SNAKE_BODY_RATING
        return bodyElement
    }

}