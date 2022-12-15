import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): String {
        val border = input.indexOf("")
        val drawing = input.subList(0, border)
        val queries = input.subList(border + 1, input.size)
        val data = arrayListOf<MutableList<String>>()
        repeat(drawing.last().length / 4 + 1) {
            data.add(arrayListOf())
        }
        for (line in drawing.subList(0, drawing.size - 1)) {
            for (i in line.indices step 4) {
                val curStr = line.substring(i, i + 3)
                if (curStr.isNotBlank()) {
                    val column = i / 4
                    data[column].add(curStr)
                }
            }
        }
        for (line in queries) {
            val (cnt, from, to) = line.split(" ").filterIndexed { index, _ ->
                when (index) {
                    1, 3, 5 -> true
                    else -> false
                }
            }.map { it.toInt() }
            repeat(cnt) {
                data[to - 1].add(0, data[from - 1].removeAt(0))
            }
        }
        return data.map { it[0][1] }.joinToString("")
    }
    fun part2(input: List<String>): String {
        val border = input.indexOf("")
        val drawing = input.subList(0, border)
        val queries = input.subList(border + 1, input.size)
        val data = arrayListOf<MutableList<String>>()
        repeat(drawing.last().length / 4 + 1) {
            data.add(arrayListOf())
        }
        for (line in drawing.subList(0, drawing.size - 1)) {
            for (i in line.indices step 4) {
                val curStr = line.substring(i, i + 3)
                if (curStr.isNotBlank()) {
                    val column = i / 4
                    data[column].add(curStr)
                }
            }
        }
        for (line in queries) {
            val (cnt, from, to) = line.split(" ").filterIndexed { index, _ ->
                when (index) {
                    1, 3, 5 -> true
                    else -> false
                }
            }.map { it.toInt() }
            for (i in cnt-1 downTo 0) {
                data[to - 1].add(0, data[from - 1].removeAt(i))
            }
        }
        return data.map { it[0][1] }.joinToString("")
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
