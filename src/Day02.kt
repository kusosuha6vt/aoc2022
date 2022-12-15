import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for ((a, tp) in input.map { it.split(' ') }) {
            val add = when(tp) {
                "X" -> -1
                "Y" -> 0
                "Z" -> 1
                else -> -2
            }
            val b = Char((a[0].code - 'A'.code + add + 3) % 3 + 'X'.code).toString()
            sum += when(b) {
                "X" -> 1
                "Y" -> 2
                "Z" -> 3
                else -> 0
            }
            sum += when(a + b) {
                "AY" -> 6
                "BZ" -> 6
                "CX" -> 6
                "AX" -> 3
                "BY" -> 3
                "CZ" -> 3
                else -> 0
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
