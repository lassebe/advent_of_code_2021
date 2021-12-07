import kotlin.math.abs


fun crabMovements(): Int {
    val crabs = readLine()!!.split(",").map {
        it.toInt()
    }
    // part 1 val allDistances = = crabs.toSet().map { start ->
    val allDistances = (0..crabs.maxOf { it }).map { start ->
        crabs.sumOf {
            // part 1  abs(start - it)
            (1..abs(start - it)).sum()
        }
    }

    return allDistances.minOfOrNull { it }!!
}
