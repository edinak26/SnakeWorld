package field.enums

enum class SnakeDirection(val index: Int) {
    Left(0),Straight(1),Right(2);
    companion object{
        fun valueOf(index: Int) = values().find{ it.index==index }


    }
}