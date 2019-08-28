package field

import FIELD_HEIGHT
import FIELD_WIDTH
import NEURAL_EMPTY_CELL_VALUE
import NEURAL_FIELD_END_VALUE
import field.entities.FieldCell
import field.entities.creatures.Creature
import randomXCoord
import randomYCoord
import java.util.*


class Pixel : LinkedList<FieldCell>()

class Field {
    private val pixels = Array(FIELD_HEIGHT) { Array(FIELD_WIDTH) { Pixel() } }

    fun createCell(entity: Creature): FieldCell {
        val newCell = generateCell(entity)
        val (x, y) = newCell.coords
        pixels[x][y].add(newCell)
        return newCell
    }

    fun copy(cell: FieldCell): FieldCell {
        val newCell = FieldCell(cell.coords, cell.entity, this)
        val (x, y) = newCell.coords
        pixels[x][y].add(newCell)
        return newCell
    }

    private fun generateCell(entity: Creature): FieldCell {
        var coords: Pair<Int, Int>
        do {
            coords = Pair(randomXCoord(), randomYCoord())
        } while (containsCords(coords))
        return FieldCell(coords, entity, this)
    }

    fun move(fieldCell: FieldCell) {
        removeCell(fieldCell)
        fieldCell.moveCoords()
        addCell(fieldCell)
    }

    private fun addCell(fieldCell: FieldCell) {
        if (isOut(fieldCell.coords)) {
            fieldCell.entity.kill()
        } else {
            val (x, y) = fieldCell.coords
            pixels[x][y].add(fieldCell)
        }
    }


    private fun isOut(coords: Pair<Int, Int>): Boolean {
        if (coords.first >= FIELD_WIDTH || coords.first < 0)
            return true
        if (coords.second >= FIELD_HEIGHT || coords.second < 0)
            return true
        return false
    }

    fun remove(fieldCell: FieldCell) {
        if (!isOut(fieldCell.coords))
            removeCell(fieldCell)
    }

    fun removeCell(fieldCell: FieldCell) {
        val (x, y) = fieldCell.coords
        val isDeleted = pixels[x][y].remove(fieldCell)
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
                cell.entity.removeCollision(collision.filter{it!=cell},cell)
            }
        }
    }

    fun getCollisions(): LinkedList<List<FieldCell>> {
        val collisions = LinkedList<List<FieldCell>>()
        for (pixelsLine in pixels) {
            for (pixel in pixelsLine) {
                if (pixel.size > 1) {
                    val collisionCells = LinkedList<FieldCell>()
                    collisionCells.addAll(pixel)
                    collisions.add(collisionCells)
                }
            }
        }
        return collisions
    }
}
