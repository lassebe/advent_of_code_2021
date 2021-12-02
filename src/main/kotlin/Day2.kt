fun pilot(): Pair<Int, Int> {
    var x = 0
    var y = 0
    var aim = 0

    while (true) {
        val line = readLine() ?: return Pair(x, y)
        val (key, rest) = line.split(" ")
        val magnitude = rest.toInt()
        when (key) {
            "down" -> {
                aim += magnitude
            }
            "up" -> {
                aim -= magnitude
            }
            "forward" -> {
                x += magnitude
                y += magnitude * aim
            }
        }
    }
}