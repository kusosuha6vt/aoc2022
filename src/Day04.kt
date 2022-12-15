import java.math.BigInteger

fun main() {
    infix fun IntRange.contains(other : IntRange): Boolean {
        return contains(other.first) && contains(other.last)
    }
    infix fun IntRange.intersects(other: IntRange): Boolean {
        return maxOf(first, other.first) <= minOf(last, other.last)
    }
    fun part1(input: List<String>): Int {
        return input.count { line ->
            val rngs = line.split(",").map {
                val (from, to) = it.split("-").map{ it.toInt() }
                from..to
            }
            rngs[0] contains rngs[1] || rngs[1] contains rngs[0]
        }
    }
    fun part2(input: List<String>): Int {
        return input.count { line ->
            val rngs = line.split(",").map {
                val (from, to) = it.split("-").map{ it.toInt() }
                from..to
            }
            rngs[0] intersects rngs[1]
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
