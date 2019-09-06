package view.entities

class Color(
    val r: Double,
    val g: Double,
    val b: Double
) {
    operator fun component1(): Double = r
    operator fun component2(): Double = g
    operator fun component3(): Double = b

}