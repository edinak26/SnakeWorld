package neural.network

/*fun main(){
    val nn = neural.network.NeuralNetwork(2,arrayOf(3,2),4)
    nn.print()
    print(nn.calc(arrayOf(1.0,-1.0)).joinToString(prefix = "  ",separator = " ") { it.toString() })
}*/

class NeuralNetwork(
    input:Int=11*11,
    hidden:Array<Int> = arrayOf(121),
    output:Int = 3
) {
    var network = ArrayList<Matrix>()

    init{
        network.add(Matrix(input, hidden[0]))
        for(i in 1 until hidden.size){
            network.add(Matrix(hidden[i - 1], hidden[i]))
        }
        network.add(Matrix(hidden.last(), output))
    }

    fun calc(input:Array<Double>): Array<Double> {
        var result = input
        for(layer in network) {
            result *= layer
            result = activateVector(result)
        }
        return result
    }

    fun activateVector(vector:Array<Double>):Array<Double>{
        return vector.map { activate(it) }.toTypedArray()
    }

    fun activate(x:Double):Double{
        return 1.0/(1.0+Math.exp(-x))
    }

    fun print(){
        network.forEach{
            it.print()
            print("---------\n")
        }
        print("============\n")
    }
}
