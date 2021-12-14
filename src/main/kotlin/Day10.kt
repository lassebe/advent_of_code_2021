fun score(s: String): Int = when (s) {
    ")" -> 3
    "]" -> 57
    "}" -> 1197
    ">" -> 25137
    else -> 0
}

fun matchBrackets(): Int {
    var line = readLine()
    var score = 0
    while (line != null) {
        score += isCorrupted(line)
        line = readLine()
    }

    return score
}

private fun isCorrupted(line: String): Int {
    val stack = mutableListOf<String>()

    line.chunked(1).forEach {
        when (it) {
            "{" -> stack.add(it)
            "(" -> stack.add(it)
            "<" -> stack.add(it)
            "[" -> stack.add(it)

            "}" -> {
                if (stack.last() != "{") return score(it)
                stack.removeLast()
            }
            ")" -> {
                if (stack.last() != "(") return score(it)
                stack.removeLast()
            }
            ">" -> {
                if (stack.last() != "<") return score(it)
                stack.removeLast()
            }
            "]" -> {
                if (stack.last() != "[") return score(it)
                stack.removeLast()
            }
        }
    }


    return 0
}
