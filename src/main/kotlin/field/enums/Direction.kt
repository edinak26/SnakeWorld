package field.enums

import kotlin.random.Random

enum class Direction(val index: Int) {
    Up(0), Right(1), Down(2), Left(3);

    operator fun plus(selectedSnakeDirection: SnakeDirection): Direction {
        val selectedDirection = selectedSnakeDirection.index-1
        val newDirection = index+selectedDirection
        return valueOf(newDirection)
    }


    companion object {
        fun randomDirection() = valueOf(Random.nextInt(0, 4))

        fun valueOf(value: Int): Direction =
            values().find { it.index == abs(value) % 4 } ?: throw Exception("Undefined direction value")

        private fun abs(value: Int): Int {
            var result = value
            while (result < 0)
                result += 4
            return result
        }
    }
}