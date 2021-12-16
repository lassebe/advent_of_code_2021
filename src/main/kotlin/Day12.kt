fun String.isLowerCase(): Boolean = this.all { it.isLowerCase() }

fun cavePaths(): Int {
    val graph = mutableMapOf<String, MutableList<String>>().withDefault { mutableListOf() }
    var line = readLine()
    while (line != null) {
        val (src, dst) = line.split("-")
        graph[src] = (graph.getValue(src) + mutableListOf(dst)).toMutableList()
        graph[dst] = (graph.getValue(dst) + mutableListOf(src)).toMutableList()
        line = readLine()
    }

    println(graph)
    graph["end"] = mutableListOf()

    val allPaths = graph["start"]!!.map {
        dfs(it, listOf("start"), graph)
    }
        .flatten()
        .filter { it.last() == "end" }

    return allPaths.toSet().size
}


private fun dfs(current: String, path: List<String>, graph: Map<String, MutableList<String>>): List<List<String>> {
    val visitedSmallCave = path.filter { it.isLowerCase() }.groupBy { it }.map { (_, v) -> v.size }.contains(2)

    val visited = path.filter { it.isLowerCase() || it == "start" }.toSet()

    val next = if (visitedSmallCave) {
        graph[current]?.filterNot {
            visited.contains(it)
        } ?: emptyList()
    } else {
        graph[current]?.filter { it != "start" } ?: emptyList()
    }

    return if (
        next.isEmpty()
        || visitedSmallCave && visited.contains(current)
    )
        listOf(path + current)
    else
        next.map {
            dfs(it, path + current, graph)
        }.flatten()
}
