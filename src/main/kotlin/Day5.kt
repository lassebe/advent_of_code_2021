import kotlin.math.abs

fun readHydrothermalVentInput(): Map<Pair<Int, Int>, Int> {
    var l = readLine()
    val pipes = mutableMapOf<Pair<Int, Int>, Int>()
    while (l !== null) {
        val coordinates = l.split(" -> ").map {
            val xy = it.split(",").map { num -> num.toInt() }
            Pair(xy.first(), xy.last())
        }
        val a = coordinates.first()
        val b = coordinates.last()

        if (a.first == b.first) {
            val start = if (a.second < b.second) a else b
            val end = if (start == a) b else a
            (start.second..end.second).forEach {
                val pos = Pair(start.first, it)
                pipes[pos] = (pipes[pos] ?: 0) + 1
            }
        }

        val start = if (a.first < b.first) a else b
        val end = if (start == a) b else a

        if (a.second == b.second) {
            (start.first..end.first).forEach {
                val pos = Pair(it, start.second)
                pipes[pos] = (pipes[pos] ?: 0) + 1
            }

        }

        if (abs(a.first - b.first) == abs(b.second - a.second)) {
            (start.first..end.first).forEachIndexed { index, i ->
                val pos =
                    if (start.second > end.second)
                        Pair(i, start.second - index)
                    else
                        Pair(i, start.second + index)

                pipes[pos] = (pipes[pos] ?: 0) + 1
            }
            println()
        }
        l = readLine()
    }

    return pipes
}

fun avoidPipes(): Int {
    val pipes = readHydrothermalVentInput()
    return pipes.count { entry -> entry.value >= 2 }
}
