import java.util.*

private val weights = mutableListOf<List<Int>>()

fun djikstra(graph: Map<Pair<Int, Int>, List<Pair<Int, Int>>>): Int {
    val target = Pair(weights.size - 1, weights.first().size - 1)

    val distance = mutableMapOf<Pair<Int, Int>, Int>().withDefault { Integer.MAX_VALUE / 4 }
    val prev = mutableMapOf<Pair<Int, Int>, Pair<Int, Int>>()
    distance[Pair(0, 0)] = 0

    val compareByDistance = Comparator<Pair<Int, Int>> { a, b ->
        if (distance.getValue(a) < distance.getValue(b))
            -1
        else if (distance.getValue(a) > distance.getValue(b))
            1
        else 0
    }
    val queue = PriorityQueue(compareByDistance)

    queue.addAll(graph.keys)
    while (queue.isNotEmpty()) {
        val u = queue.remove()
        if (u == target)
            break
        graph.getValue(u).filter { queue.contains(it) }.forEach { v ->
            val alt = distance.getValue(u) + weights[v.first][v.second]
            if (alt < distance.getValue(v)) {
                distance[v] = alt
                prev[v] = u
            }
            queue.remove(v)
            queue.add(v)
        }
    }

    return distance[target]!!
}

fun riskMaze(): Int {
    var line = readLine()
    while (line != null) {
        weights.add(line.chunked(1).map { it.toInt() })
        line = readLine()
    }
    val ogWeights = weights.toList()
    (weights.indices).forEach { i ->
        repeat(4) { j ->
            weights[i] = weights[i] + ogWeights[i].map {
                if ((it + (j + 1)) < 10)
                    (it + (j + 1))
                else
                    ((it + (j + 1)) % 10) + 1
            }
        }
    }
    val poserWeights = weights.toList()

    repeat(4) { i ->
        (poserWeights.indices).forEach { j ->
            weights.add(
                poserWeights[j].map {
                    if ((it + (i + 1)) < 10)
                        (it + (i + 1))
                    else
                        ((it + (i + 1)) % 10) + 1
                }
            )
        }
    }

    val duplicateGraph = mutableMapOf<Pair<Int, Int>, List<Pair<Int, Int>>>().withDefault { emptyList() }

    (weights.indices).forEach { i ->
        (weights.first().indices).forEach { j ->
            if (i - 1 >= 0) {
                duplicateGraph[Pair(i - 1, j)] = duplicateGraph.getValue(Pair(i - 1, j)) + listOf(Pair(i, j))
                duplicateGraph[Pair(i, j)] = duplicateGraph.getValue(Pair(i, j)) + listOf(Pair(i - 1, j))
            }
            if (j - 1 >= 0) {
                duplicateGraph[Pair(i, j - 1)] = duplicateGraph.getValue(Pair(i, j - 1)) + listOf(Pair(i, j))
                duplicateGraph[Pair(i, j)] = duplicateGraph.getValue(Pair(i, j)) + listOf(Pair(i, j - 1))
            }
            if (i + 1 < weights.size) {
                duplicateGraph[Pair(i + 1, j)] = duplicateGraph.getValue(Pair(i + 1, j)) + listOf(Pair(i, j))
                duplicateGraph[Pair(i, j)] = duplicateGraph.getValue(Pair(i, j)) + listOf(Pair(i + 1, j))
            }
            if (j + 1 < weights.first().size) {
                duplicateGraph[Pair(i, j + 1)] = duplicateGraph.getValue(Pair(i, j + 1)) + listOf(Pair(i, j))
                duplicateGraph[Pair(i, j)] = duplicateGraph.getValue(Pair(i, j)) + listOf(Pair(i, j + 1))
            }
        }
    }

    val graph = duplicateGraph.map { (k, v) -> k to v.toSet().toMutableList() }.toMap()


    return djikstra(graph)
}
