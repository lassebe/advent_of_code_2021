fun binaryStringArrayToInt(s: List<String>): Int = Integer.parseInt(s.joinToString(""), 2)
fun invert(s: List<String>): List<String> = s.map { if (it == "1") "0" else "1" }

fun majority(
    report: List<String>,
    comparison: (Char, Char) -> Boolean = { a, b -> a == b },
    index: Int = 0
): List<String> {
    val ones = report.count { it[index] == '1' }
    val zeroes = report.count { it[index] == '0' }

    val mostCommon = if (ones >= zeroes) '1' else '0'
    val filtered = report.filter { comparison(it[index], mostCommon) }

    return if (filtered.size == 1)
        filtered
    else
        majority(filtered, comparison, index + 1)
}

fun readInput(): List<String> {
    val report = mutableListOf<String>()
    while (true) {
        val line = readLine() ?: break
        report.add(line)
    }
    return report
}

fun lifeSupportRating(): Int {
    val report = readInput()
    val oxygen = binaryStringArrayToInt(majority(report))
    val co2 = binaryStringArrayToInt(majority(report, { a: Char, b: Char -> a != b }))
    return oxygen * co2
}

fun power(): Int {
    val report = readInput()

    val bits = report[0].length
    val majority = report.size / 2
    val result = mutableListOf<String>()

    for (i in 0 until bits) {
        val ones = report.count { it[i] == '1' }
        result.add(if (ones > majority) "1" else "0")
    }
    val gamma = binaryStringArrayToInt(result)
    val epsilon = binaryStringArrayToInt(invert(result))

    return gamma * epsilon
}