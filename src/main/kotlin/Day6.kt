import java.math.BigInteger

fun lanternFish(): BigInteger {
    val fish = readLine()!!.split(",").map {
        it.toInt() }
    val fishMap = mutableMapOf<Int, BigInteger>()

    fish.forEach {
        fishMap[it] = fishMap.getOrDefault(it, BigInteger.ZERO).plus(BigInteger.ONE)
    }

    (0 until 256).forEach {
        val ready = fishMap.getOrDefault(0, BigInteger.ZERO)
        (0..7).forEach {
            fishMap[it] = fishMap.getOrDefault(it+1, BigInteger.ZERO)
        }
        fishMap[6] = fishMap[6]!! + ready
        fishMap[8] = ready
    }
    return fishMap.values.reduce { acc, it -> acc.plus(it) }
}
