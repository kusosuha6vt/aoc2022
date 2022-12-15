import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): Int {
        var ans1 = 0L.toBigInteger()
        var ans2 = ans1
        var ans3 = ans1
        var sum = 0L.toBigInteger()
        for (line in input) {
            if (line.isNotBlank()) {
                sum += BigInteger(line)
            } else {
                if (ans1 <= sum) {
                    ans3 = ans2
                    ans2 = ans1
                    ans1 = sum
                } else if (ans2 <= sum) {
                    ans3 = ans2
                    ans2 = sum
                } else if (ans3 <= sum) {
                    ans3 = sum
                }
                sum = BigInteger.ZERO
            }
        }
        assert(sum.toInt() == 0)
        return (ans1 + ans2 + ans3).toInt()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part1(input))
}
