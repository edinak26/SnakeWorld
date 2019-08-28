import field.entities.FieldCell
import org.lwjgl.opengl.GL11.*
import java.lang.Exception
import kotlin.math.absoluteValue
import kotlin.random.Random

class FieldCell1(
    var x:Int= randomXCoord(),
    var y:Int= randomYCoord(),
    var height:Int=1,
    var weight: Int=1
):Colored() {
    val cords get() = Pair(x,y)

    val topEdge get() = y
    val bottomEdge get() = y+height
    val rightEdge get() = x+weight
    val leftEdge get() = x

    val glTop get() = 1-topEdge*2/FIELD_HEIGHT.toDouble()
    val glBottom get() = 1-bottomEdge*2/FIELD_HEIGHT.toDouble()
    val glRight get() = rightEdge*2/FIELD_WIDTH.toDouble()-1
    val glLeft get() = leftEdge*2/FIELD_WIDTH.toDouble()-1


    fun moveUp() = if(topEdge>0) up() else y=0
    fun moveDown() = if(bottomEdge< FIELD_HEIGHT) down() else y=FIELD_HEIGHT-height
    fun moveRight() = if(rightEdge< FIELD_WIDTH) right() else x=FIELD_WIDTH-weight
    fun moveLeft() = if(leftEdge>0) left() else x=0

    fun randomMove() = when(Random.nextInt().absoluteValue%4) {
        0 -> moveUp()
        1 -> moveDown()
        2 -> moveRight()
        3 -> moveLeft()
        else -> throw Exception("Random is broken ${Random.nextInt() % 4}")
    }

    fun up(){ y-- }
    fun down(){ y++ }
    fun right(){ x++ }
    fun left(){ x-- }

    fun glColorDraw(){
        changeColor()
        setColor()
        glDraw()
        glColor4f(1f,1f,1f,1f)
    }

    fun glDraw(){
        glBegin(GL_QUADS)
        glVertex2d(glLeft, glTop)
        glVertex2d(glRight, glTop)
        glVertex2d(glRight, glBottom)
        glVertex2d(glLeft, glBottom)
        glEnd()
    }

    fun moveTo(nextFieldCell: FieldCell) {
        x = nextFieldCell.coords.first
        y = nextFieldCell.coords.second
    }
}