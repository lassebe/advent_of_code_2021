private val octopuses = mutableListOf<MutableList<Int>>()
private val flashed =
    mutableListOf(
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList(),
        (0 until 10).map { false }.toMutableList()
    )

fun dumboFlashes(): Int {
    repeat(10) {
        octopuses.add(readLine()!!.chunked(1).map { it.toInt() }.toMutableList())
    }

    var flashCount = 0
    var step = 0
    while (true) {
        (0 until 10).forEach { i ->
            (0 until 10).forEach { j ->
                flashed[i][j] = false
                octopuses[i][j]++
            }
        }

        (0 until 10).forEach { i ->
            (0 until 10).forEach { j ->
                if (octopuses[i][j] > 9) flash(i, j)
            }
        }
        var innerFlashCount = 0
        (0 until 10).forEach { i ->
            (0 until 10).forEach { j ->
                if (octopuses[i][j] == 0) innerFlashCount++
            }
        }
        flashCount += innerFlashCount
        step++
        // part 1
        // if(i == 100) break
        if (innerFlashCount == 100) return step
    }

    // part 1
    return flashCount
}

private fun innerFlash(i: Int, j: Int) {
    if (!flashed[i][j]) {
        octopuses[i][j]++
        if (octopuses[i][j] > 9) flash(i, j)
    }
}

fun flash(i: Int, j: Int) {
    if (flashed[i][j]) {
        return
    }
    octopuses[i][j] = 0
    flashed[i][j] = true

    if (i > 0) {
        innerFlash(i - 1, j)
        if (j < octopuses.size - 1)
            innerFlash(i - 1, j + 1)
        if (j > 0)
            innerFlash(i - 1, j - 1)
    }
    if (j > 0) {
        innerFlash(i, j - 1)
        if (i < octopuses.size - 1)
            innerFlash(i + 1, j - 1)
    }

    if (i < octopuses.size - 1) {
        innerFlash(i + 1, j)
        if (j < octopuses.size - 1)
            innerFlash(i + 1, j + 1)

    }
    if (j < octopuses.size - 1)
        innerFlash(i, j + 1)
}
