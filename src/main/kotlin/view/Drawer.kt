package view

import field.entities.creatures.Snake
import org.lwjgl.opengl.GL11
import field.entities.Square
import view.entities.Color

class Drawer {
    companion object {

        fun drawSnakes(snakes: MutableList<Snake>) = snakes.forEach { drawSnake(it) }

        private fun drawSnake(snake: Snake) {
            setDrawColor(DEFAULT_SNAKE_BODY_COLOR)
            drawSquares(snake.body)
            setDrawColor(DEFAULT_SNAKE_HEAD_COLOR)
            drawSquare(snake.head)
            setDefaultDrawColor()
        }

        private fun drawSquares(squares: List<Square>) = squares.forEach { drawSquare(it) }

        private fun drawSquare(square: Square) = with(square) {
            GL11.glBegin(GL11.GL_QUADS)
            GL11.glVertex2d(glLeft, glTop)
            GL11.glVertex2d(glRight, glTop)
            GL11.glVertex2d(glRight, glBottom)
            GL11.glVertex2d(glLeft, glBottom)
            GL11.glEnd()
        }


        private fun setDefaultDrawColor() = setDrawColor(DEFAULT_COLOR)

        private fun setDrawColor(color: Color) {
            val (r, g, b) = color
            GL11.glColor3f(r.toFloat(), g.toFloat(), b.toFloat())
        }

    }
}