import java.util.Arrays

fun main() {
    fun part1(input: List<String>): Int =
        (0 until  input.size * input[0].length).map {
                ij -> Pair(ij / input[0].length, ij % input[0].length)
        }.count { (i, j) ->
            listOf(
                Pair(0, 1),
                Pair(1, 0),
                Pair(0, -1),
                Pair(-1, 0),
            ).minOf { (dx, dy) ->
                generateSequence(Pair(i + dx, j + dy)) { (i0, j0) ->
                    Pair(i0 + dx, j0 + dy)
                }.takeWhile { (i0, j0) ->
                    i0 in input.indices && j0 in input[0].indices
                }.map { (i0, j0) ->
                    input[i0][j0]
                }.maxOrNull()?:('0' - 1)
            } < input[i][j]
        }

    fun part2(input: List<String>): Int =
        (0 until  input.size * input[0].length).map {
                ij -> Pair(ij / input[0].length, ij % input[0].length)
        }.maxOf{ (i, j) ->
            listOf(
                Pair(0, 1),
                Pair(1, 0),
                Pair(0, -1),
                Pair(-1, 0),
            ).map { (dx, dy) ->
                generateSequence(Pair(i + dx, j + dy)) { (i0, j0) ->
                    Pair(i0 + dx, j0 + dy)
                }.takeWhile { (i0, j0) ->
                    i0 in input.indices && j0 in input[0].indices &&
                            ((i + dx == i0 && j + dy == j0) ||
                                input[i0 - dx][j0 - dy] < input[i][j])
                }.count()
            }.reduce{ sum, element -> sum * element }
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
