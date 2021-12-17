fun polymer(): Long {
    val rules = mutableMapOf<Pair<Char, Char>, Char>()
    val polymer = readLine()!!
    readLine()

    var line = readLine()
    while (line != null) {
        val (from, to) = line.split(" -> ")
        val (p1, p2) = from.chunked(1)
        rules[p1[0] to p2[0]] = to.toCharArray().first()
        line = readLine()
    }

    var pairMap = mutableMapOf<Pair<Char, Char>, Long>().withDefault { 0L }
    (0 until polymer.length - 1).forEach { i ->
        val pair = polymer[i] to polymer[i + 1]
        pairMap[pair] = pairMap.getValue(pair) + 1L
    }

    val charCounts = mutableMapOf<Char, Long>().withDefault { 0L }
    // there's no List<Pair<K,V>>.toMutableMap() 😢 and doing toMap().toMutableMap() feels silly
    polymer.groupBy { it }.map { (k, v) -> k to v.size.toLong() }.toMap(charCounts)

    repeat(40) {
        val newMap = mutableMapOf<Pair<Char, Char>, Long>().withDefault { 0L }
        pairMap.forEach { (k, v) ->
            val new = rules[k]!!
            charCounts[new] = charCounts.getValue(new) + v

            val (f, s) = k
            val pairOne = f to new
            val pairTwo = new to s
            newMap[pairOne] = newMap.getValue(pairOne) + v
            newMap[pairTwo] = newMap.getValue(pairTwo) + v
        }
        pairMap = newMap
        if (it == 9) println(answer(charCounts.values))
    }


    return answer(charCounts.values)
}

private fun answer(counts: Collection<Long>) = counts.maxOf { it } - counts.minOf { it }