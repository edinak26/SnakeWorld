package field

import kotlin.math.absoluteValue
import kotlin.random.Random

fun randomXCoord() = Random.nextInt().absoluteValue% FIELD_WIDTH
fun randomYCoord() = Random.nextInt().absoluteValue% FIELD_HEIGHT
