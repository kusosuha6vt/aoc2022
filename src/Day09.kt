import kotlin.math.abs

data class P(var x: Int, var y: Int)
enum class Direction(val dir: String) {
    U("U"),
    D("D"),
    L("L"),
    R("R"),
}

fun main() {
    fun part1(input: List<String>): Int {
        val h = P(0, 0)
        val t = P(0, 0)
        val s = mutableSetOf<P>()
        val l = mutableListOf(h)
        for ((dir, steps) in input.map { it.split(" ") }.map { Pair(it[0], it[1].toInt()) }) {
            repeat(steps) {
                s.add(t.copy())
                when (Direction.valueOf(dir)) {
                    Direction.U -> h.y += 1
                    Direction.D -> h.y -= 1
                    Direction.L -> h.x -= 1
                    Direction.R -> h.x += 1
                }
                val dr = h.x - t.x
                val dc = h.y - t.y
                if (abs(dr) <= 1 && abs(dc) <= 1) {
                } else if (abs(dr) >= 2 && abs(dc) >= 2) {
                    throw error("wtf")
                } else if (abs(dr) >= 2) {
                    t.y = h.y
                    t.x = if (t.x < h.x) h.x - 1 else h.x + 1
                } else if (abs(dc) >= 2) {
                    t.x = h.x
                    t.y = if (t.y < h.y) h.y - 1 else h.y + 1
                }
                s.add(t.copy())
            }
        }
        return s.size
    }

    fun part2(input: List<String>): Int {
        val h = P(0, 0)
        val t = mutableListOf(P(0, 0), P(0, 0), P(0, 0), P(0, 0), P(0, 0), P(0, 0), P(0, 0), P(0, 0), P(0, 0))
        val s = mutableSetOf<P>()
        for ((dir, steps) in input.map { it.split(" ") }.map { Pair(it[0], it[1].toInt()) }) {
            repeat(steps) {
                 s.add(t[8].copy())
                when (Direction.valueOf(dir)) {
                    Direction.U -> h.y += 1
                    Direction.D -> h.y -= 1
                    Direction.L -> h.x -= 1
                    Direction.R -> h.x += 1
                }
                for (i in 0..8) {
                    val nx = if(i == 0) h.copy() else t[i - 1]
                    val t = t[i]
                    val dr = nx.x - t.x
                    val dc = nx.y - t.y
                    if (abs(dr) <= 1 && abs(dc) <= 1) {
                    } else if (abs(dr) >= 2 && abs(dc) >= 2) {
                        t.x = if (t.x < nx.x) nx.x - 1 else nx.x + 1
                        t.y = if (t.y < nx.y) nx.y - 1 else nx.y + 1
                    } else if (abs(dr) >= 2) {
                        t.y = nx.y
                        t.x = if (t.x < nx.x) nx.x - 1 else nx.x + 1
                    } else if (abs(dc) >= 2) {
                        t.x = nx.x
                        t.y = if (t.y < nx.y) nx.y - 1 else nx.y + 1
                    }
                }
                s.add(t[8].copy())
            }
        }
        println(s.size)
        return s.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    println(part1(testInput))
    check(part2(testInput) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
