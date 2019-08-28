import kotlin.random.Random

/*
fun main(){
    (arrayOf(
        arrayOf(1.0,2.0,3.0,4.0),
        arrayOf(5.0,6.0,7.0,8.0),
        arrayOf(9.0,10.0,4.0,2.0),
        arrayOf(5.0,3.0,1.0,5.0),
        arrayOf(3.0,7.0,8.0,12.0)
    )*arrayOf(
        arrayOf(1.0,2.0,3.0,4.0,5.0,6.0),
        arrayOf(6.0,5.0,4.0,3.0,2.0,1.0),
        arrayOf(8.0,5.0,3.0,12.0,19.0,6.0),
        arrayOf(2.0,4.0,11.0,23.0,20.0,3.0)
    )).print()

}*/

class Matrix(
    val array:Array<Array<Double>>
){
    constructor(h:Int=0,w:Int=0) : this(Array<Array<Double>>(h){ Array<Double>(w){ Random.nextInt(-5000,5000)*0.001}})
    fun print() = print(
        array.joinToString(separator= " ") {
            it.joinToString(postfix = "\n") {
                it.toString()
            }})

    operator fun Matrix.times(matrix: Matrix): Matrix {
        return Matrix(array*matrix.array)
    }

    private operator fun Array<Array<Double>>.times(array:Array<Array<Double>>): Array<Array<Double>> {
        val result=Array(this.size){ emptyArray<Double>()}
        for(i in 0 until this.size){
            result[i]=this[i]*array
        }
        return result
    }




}

private fun Array<Array<Double>>.verticalVector(index:Int):Array<Double> = this.map{it[index]}.toTypedArray()


operator fun Array<Double>.times(array:Array<Array<Double>>): Array<Double> {
    val result=Array(array[0].size){0.0}
    for(i in 0 until array[0].size){
        result[i]=this*array.verticalVector(i)
    }
    return result
}


private operator fun Array<Double>.times(array:Array<Double>):Double{
    var result = 0.0
    for((index,value) in this.withIndex()){
        //println(index)
        result+=value*array[index]
    }
    return result
}

operator fun Array<Double>.times(matrix:Matrix): Array<Double> = this*matrix.array




