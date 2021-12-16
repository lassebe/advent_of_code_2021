private fun printPaper(dots: MutableSet<Pair<Int, Int>>, xRange: IntRange, yRange: IntRange) {
    yRange.forEach { y ->
        xRange.forEach { x ->
            if (dots.contains(Pair(x, y)))
                print("#")
            else
                print(".")
        }
        println()
    }
}

private fun countDots(dots: MutableSet<Pair<Int, Int>>, xRange: IntRange, yRange: IntRange): Int {
    var count = 0
    yRange.forEach { y ->
        xRange.forEach { x ->
            if (dots.contains(Pair(x, y)))
                count++
        }
    }
    return count
}

fun foldPaper(): Int {
    var maxX = 0
    var maxY = 0

    // read dots
    val dots = mutableSetOf<Pair<Int, Int>>()
    var line = readLine()
    while (line != "") {
        val (x, y) = line!!.split(",").map { it.toInt() }
        dots.add(Pair(x, y))
        line = readLine()
    }
    maxX = dots.maxOf { it.first }
    maxY = dots.maxOf { it.second }

    // read folds
    val folds = mutableListOf<Pair<String, Int>>()
    line = readLine()
    while (line != null) {
        val (axis, position) = line.split(" ").last().split("=")
        folds.add(axis to position.toInt())
        line = readLine()
    }

    folds.forEach { (axis, position) ->
        if (axis == "y") {
            (position..maxY).forEachIndexed { index, y ->
                (0..maxX).forEach { x ->
                    if (dots.contains(Pair(x, y))) {
                        dots.remove(Pair(x, y))
                        dots.add(Pair(x, position - index))
                    } //else print(".")
                }
//                println()
            }
            maxY = maxOf(maxY - position, position)
        } else {
            (0..maxY).forEach { y ->
                (position..maxX).forEachIndexed { index, x ->
                    if (dots.contains(Pair(x, y))) {
                        dots.remove(Pair(x, y))
                        dots.add(Pair(position - index, y))
                    } //else print(".")
                }
//                println()
            }
            maxX = maxOf(maxX - position, position)
        }

        println(countDots(dots, (0..maxX), (0..maxY)))
    }

    printPaper(dots, (0..maxX), (0..maxY))
    return countDots(dots, (0..maxX), (0..maxY))
}
