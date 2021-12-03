fun binaryStringArrayToInt(s: List<String>): Int = Integer.parseInt(s.joinToString(""), 2)
fun invert(s: List<String>): List<String> = s.map { if (it == "1") "0" else "1" }

fun power(): Int {
    val report = mutableListOf<String>()
    while (true) {
        val line = readLine() ?: break
        report.add(line)
    }
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