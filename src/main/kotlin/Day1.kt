fun depth(): Int {
    var increases = 0
    var a: Int = readLine()!!.toInt()
    var b: Int = readLine()!!.toInt()
    var c: Int = readLine()!!.toInt()
    var curr = a + b + c

    while (true) {
        val next = readLine()?.toInt() ?: return increases
        if (next + curr - a > curr) {
            increases++
        }
        curr -= a
        curr += next
        a = b
        b = c
        c = next
    }
}