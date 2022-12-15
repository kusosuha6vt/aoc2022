import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        val s = input[0]
        for (i in 0..s.length - 14) {
            if (s.substring(i, i + 14).toCharArray().toHashSet().size == 14) {
                return i + 14
            }
        }
        return s.length
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
//    check(part1(testInput) == 7)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
