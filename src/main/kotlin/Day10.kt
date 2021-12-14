fun part1(s: String): Int = when (s) {
    ")" -> 3
    "]" -> 57
    "}" -> 1197
    ">" -> 25137
    else -> 0
}

fun matchBrackets(): Long {
    var line = readLine()
    val scores = mutableListOf<Long>()
    while (line != null) {
        val score = isCorrupted(line)
        if(score != 0L) scores.add(score)
        line = readLine()
    }
    println(scores.sorted())
    return scores.sorted()[scores.size/2]
}

private fun isCorrupted(line: String): Long {
    val stack = mutableListOf<String>()

    line.chunked(1).forEach {
        when (it) {
            "{" -> stack.add(it)
            "(" -> stack.add(it)
            "<" -> stack.add(it)
            "[" -> stack.add(it)

            "}" -> {
                if (stack.last() != "{") return 0
                stack.removeLast()
            }
            ")" -> {
                if (stack.last() != "(") return 0
                stack.removeLast()
            }
            ">" -> {
                if (stack.last() != "<") return 0
                stack.removeLast()
            }
            "]" -> {
                if (stack.last() != "[") return 0
                stack.removeLast()
            }
        }
    }
    var score = 0L
    while(stack.isNotEmpty()){
        score *= 5
        when(stack.removeLast()) {
            "(" -> score += 1L
            "[" -> score += 2L
            "{" -> score += 3L
            "<" -> score += 4L
        }
    }

    return score
}
