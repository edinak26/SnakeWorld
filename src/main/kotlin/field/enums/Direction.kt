package field.enums

enum class Direction(val index: Int) {
    Up(0), Right(1), Down(2), Left(3);


    companion object {
        fun valueOf(value: Int): Direction? = values().find { it.index == abs(value) % 4 }

        fun abs(value: Int): Int {
            var result = value
            while (result < 0)
                result += 4
            return result
        }
    }
}