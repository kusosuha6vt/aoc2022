sealed interface Op {
    val ticks: Int
        get() = 1
}

object Noop : Op
data class Addx(var x: Int) : Op {
    override val ticks: Int
        get() = 2
}

fun main() {
    fun part1(input: List<String>): Int {
        val ops = input.map { it.split(" ") }.map {
            when (it[0]) {
                "noop" -> Noop
                "addx" -> Addx(it[1].toInt())
                else -> throw error("something wrong")
            }
        }
        var ticks = 0
        var x = 1
        var sum = 0
        for (op in ops) {
            repeat(op.ticks) {
                ticks++
                if (it + 1 == op.ticks && op is Addx) {
                    x += op.x
                }
                if (ticks + 1 in setOf(20, 60, 100, 140, 180, 220)) {
                    sum += x * (ticks + 1)
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val ops = input.map { it.split(" ") }.map {
            when (it[0]) {
                "noop" -> Noop
                "addx" -> Addx(it[1].toInt())
                else -> throw error("something wrong")
            }
        }
        var ticks = 0
        var x = 1
        var sum = 0
        for (op in ops) {
            repeat(op.ticks) {
                if (ticks + 1 in setOf(20, 60, 100, 140, 180, 220)) {
                    sum += x * (ticks + 1)
                }
                val pos = ticks % 40
                print(if (pos in x - 1..x + 1) '#' else '.')
                if (pos == 39) {
                    println()
                }
                ticks++
                if (it + 1 == op.ticks && op is Addx) {
                    x += op.x
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
//    println(part1(testInput))
    check(part2(testInput) == 13140)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
