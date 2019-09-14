package field

import field.constants.FIELD_HEIGHT
import field.constants.FIELD_WIDTH
import field.constants.NEURAL_EMPTY_CELL_VALUE
import field.constants.NEURAL_FIELD_END_VALUE
import field.entities.CreatureElement
import field.entities.Pixel
import field.entities.creatures.Creature
import field.enums.Direction
import java.util.*


class Field {
    private val pixels = Array(FIELD_HEIGHT) { Array(FIELD_WIDTH) { Pixel() } }

    fun createCell(entity: Creature): CreatureElement {
        val newCell = generateCell(entity)
        val (x, y) = newCell.coords
        pixels[x][y].add(newCell)
        return newCell
    }

    fun copy(cell: CreatureElement): CreatureElement {
        val newCell = CreatureElement(cell.coords, cell.entity, this)
        val (x, y) = newCell.coords
        pixels[x][y].add(newCell)
        return newCell
    }

    private fun generateCell(entity: Creature): CreatureElement {
        var coords: Pair<Int, Int>
        do {
            coords = Pair(randomXCoord(), randomYCoord())
        } while (containsCords(coords))
        return CreatureElement(coords, entity, this)
    }

    fun move(element: CreatureElement, direction: Direction) {
        removeCell(element)
        element.moveCoords(direction)
        addCell(element)
    }

    fun moveTo(moveElement: CreatureElement, moveTo: CreatureElement) {
        removeCell(moveElement)
        moveElement.coords = moveTo.coords
        addCell(moveElement)
    }

    private fun addCell(element: CreatureElement) {
        if (isOut(element.coords)) {
            element.entity.kill()
        } else {
            val (x, y) = element.coords
            pixels[x][y].add(element)
        }
    }


    private fun isOut(coords: Pair<Int, Int>): Boolean {
        if (coords.first >= FIELD_WIDTH || coords.first < 0)
            return true
        if (coords.second >= FIELD_HEIGHT || coords.second < 0)
            return true
        return false
    }

    fun remove(element: CreatureElement) {
        if (!isOut(element.coords))
            removeCell(element)
    }

    fun removeCell(element: CreatureElement) {
        val (x, y) = element.coords
        val isDeleted = pixels[x][y].remove(element)
        if (!isDeleted)
            throw Exception("Cell not found, can not be deleted")
    }


    private fun containsCords(coords: Pair<Int, Int>) =
        pixels.any { it.any { it.any { it.coords == coords } } }


    fun generateSimpleField(center: Pair<Int, Int>, radius: Int = 5): Array<Double> {
        val (x, y) = center
        val simpleField = LinkedList<Double>()
        for (i in x - radius..x + radius)
            for (j in y - radius..y + radius)
                simpleField.add(getSimpleValue(i, j))
        return simpleField.toTypedArray()
    }


    fun getSimpleValue(x: Int, y: Int) =
        if (isInsideField(x, y))
            getPixelSimpleValue(pixels[x][y])
        else
            NEURAL_FIELD_END_VALUE


    private fun getPixelSimpleValue(pixel: Pixel) =
        if (pixel.isEmpty())
            NEURAL_EMPTY_CELL_VALUE
        else
            pixel.first.neuralValue


    private fun isInsideField(x: Int, y: Int): Boolean {
        val isXInside = x >= 0 && x < pixels.size
        val isYInside = y >= 0 && y < pixels[0].size
        return isXInside && isYInside
    }

    fun removeCollisions() {
        val collisions = getCollisions()
        for (collision in collisions) {
            for (cell in collision) {
                cell.entity.removeCollisions(cell,collision.filter{it!=cell})
            }
        }
    }

    fun getCollisions(): LinkedList<List<CreatureElement>> {
        val collisions = LinkedList<List<CreatureElement>>()
        for (pixelsLine in pixels) {
            for (pixel in pixelsLine) {
                if (pixel.size > 1) {
                    val collisionCells = LinkedList<CreatureElement>()
                    collisionCells.addAll(pixel)
                    collisions.add(collisionCells)
                }
            }
        }
        return collisions
    }


}
