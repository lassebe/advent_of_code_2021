fun depth(): Int {
    var increases = 0
    var a: Int = readLine()!!.toInt()
    var b: Int = readLine()!!.toInt()
    var c: Int = readLine()!!.toInt()

    while (true) {
        val d = readLine()?.toInt() ?: return increases

        if (d > a) {
            increases++
        }
        a = b
        b = c
        c = d
    }
}