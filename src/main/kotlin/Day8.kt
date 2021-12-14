fun stringify(set: Set<Char>): String = set.joinToString("")

fun signalDigits(): Int {
    var input = readLine()
    var count = 0

    while (input !== null) {
        val inAndOut = input.split(" | ").map {
            it.split(" ").map { it.toSortedSet() }
        }
        val wires = inAndOut[0]
        val output = inAndOut[1]

        var one = emptySet<Char>()
        var four = emptySet<Char>()
        var seven = emptySet<Char>()
        var eight = emptySet<Char>()

        val zeroSixNine = mutableListOf<Set<Char>>()
        val twoThreeFive = mutableListOf<Set<Char>>()
        wires.forEach {
            when (it.size) {
                2 -> one = it
                3 -> seven = it
                4 -> four = it
                5 -> twoThreeFive.add(it)
                6 -> zeroSixNine.add(it)
                7 -> eight = it
            }
        }

        val nine = zeroSixNine.first { it.minus(four).size == 2 }
        val zero = zeroSixNine.first { it != nine && seven.minus(it).isEmpty() }
        val six = zeroSixNine.first { it != nine && it != zero }

        val two = twoThreeFive.first { it.minus(nine).size == 1 }
        val five = twoThreeFive.first { it != two && it.minus(one).size == 4 }
        val three = twoThreeFive.first { it != two && it != five }

        // part 1
        // count += output.count { o -> o.size == 2 || o.size == 3 || o.size == 4 || o.size == 7 }

        count += output.joinToString("") {
            when (it) {
                one -> "1"
                two -> "2"
                three -> "3"
                four -> "4"
                five -> "5"
                six -> "6"
                seven -> "7"
                eight -> "8"
                nine -> "9"
                zero -> "0"
                else -> ""
            }
        }.toInt()


        input = readLine()
    }



    return count
}
