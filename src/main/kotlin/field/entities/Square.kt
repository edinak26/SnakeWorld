package field.entities

import field.FIELD_HEIGHT
import field.FIELD_WIDTH

open class Square(
    protected open var x: Int,
    protected open var y: Int
){
    private val topEdge get() = y
    private val bottomEdge get() = y + 1
    private val rightEdge get() = x + 1
    private val leftEdge get() = x

    val glTop get() = 1 - topEdge * 2 / FIELD_HEIGHT.toDouble()
    val glBottom get() = 1 - bottomEdge * 2 / FIELD_HEIGHT.toDouble()
    val glRight get() = rightEdge * 2 / FIELD_WIDTH.toDouble() - 1
    val glLeft get() = leftEdge * 2 / FIELD_WIDTH.toDouble() - 1
}