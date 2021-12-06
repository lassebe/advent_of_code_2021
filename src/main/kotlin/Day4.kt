data class Board(val id: Int, val values: Map<Int, Pair<Int, Int>>)
data class Bingo(val numbers: List<Int>, val boards: List<Board>)

fun readBingoInput(): Bingo {
    val numbers = readLine()!!.split(",").map { it.toInt() }
    // empty line
    var l = readLine()
    val boards = mutableListOf<Board>()
    var id = 0
    while (l !== null) {
        val card = mutableMapOf<Int, Pair<Int, Int>>()

        for (row in 0..4) {
            val line = readLine()!!.trim()
            line.split("\\s+".toRegex()).map { it.toInt() }.forEachIndexed { column, value ->
                card[value] = Pair(row, column)
            }
        }
        boards.add(Board(id, card))
        id++
        l = readLine()
        if (l == null) break
    }

    return Bingo(numbers, boards)
}

fun playBingo(): Int {
    val input = readBingoInput()
    val marked = mutableListOf<Int>()
    val winners = mutableSetOf<Int>()

    for (n in input.numbers) {
        for (b in input.boards) {
            marked.add(n)
            val score = hasWon(b, marked)
            if (score != -1) {
                winners.add(b.id)
                if (winners.size == input.boards.size) {
                    println(n)
                    println( score)
                    return n * score
                }
            }
        }
    }
    return -1
}

fun hasWon(board: Board, marked: List<Int>): Int {
    val markedIndexes = marked.mapNotNull { board.values[it] }
    val rows = (0..4).map { i-> (0..4).map { Pair(i,it) }}
    val columns = (0..4).map {j -> (0..4).map {Pair(it, j)}}

    listOf(rows,columns).flatten()
        .forEach {
            if(markedIndexes.containsAll(it)) return score(board, markedIndexes)
        }

    return -1
}


fun score(board: Board, markedIndexes: List<Pair<Int, Int>>): Int =
    board.values.entries
        .filterNot { markedIndexes.contains(it.value) }
        .sumOf { it.key }

