fun bfs(heights: List<List<Int>>, start: Pair<Int,Int>): List<Pair<Int,Int>> {
    val basin = mutableSetOf<Pair<Int,Int>>()
    val queue = mutableListOf(start)

    while(queue.isNotEmpty()) {
        val curr = queue.removeFirst()
        val (i,j) = curr
        if(basin.contains(curr)) continue
        basin.add(curr)

        if(i > 0 && heights[i-1][j] != 9) queue.add(Pair(i-1, j))
        if(i < heights.size - 1 && heights[i+1][j] != 9) queue.add(Pair(i+1, j))
        if(j > 0 && heights[i][j-1] != 9) queue.add(Pair(i, j-1))
        if(j < heights[0].size - 1 && heights[i][j+1] != 9) queue.add(Pair(i, j+1))
    }

    return basin.toList()
}

fun lavaHeights(): Int {
    var line = readLine()
    val heights = mutableListOf<List<Int>>()
    while (line != null) {
        val numbers = line.chunked(1).map { it.toInt() }
        heights.add(numbers)
        line = readLine()
    }

    val lowPoints = mutableListOf<Pair<Int,Int>>()
    (0 until heights.size).forEach { i ->
        (0 until heights[0].size).forEach { j ->
            val current = heights[i][j]
            if ((i == 0 || heights[i - 1][j] > current)
                && (j == 0 || heights[i][j - 1] > current)
                && (i == heights.size - 1 || heights[i + 1][j] > current)
                && (j == heights[0].size - 1 || heights[i][j + 1] > current)
            ){
                lowPoints.add(Pair(i,j))
            }
        }
    }

    // part 1
    // return lowPoints.size + lowPoints.sumOf { (first, second) -> heights[first][second] }
    val (a,b,c) = lowPoints.map { bfs(heights,it).size }.sortedDescending().subList(0,3)
    return a*b*c
}
