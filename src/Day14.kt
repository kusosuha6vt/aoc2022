fun main() {
    data class Coord(val x: Int, val y: Int)

    infix fun Int.to(other: Int): IntRange {
        return if (this <= other) this..other else other..this
    }

    fun generateMap(input: List<String>): MutableSet<Coord> {
        val ans = mutableSetOf<Coord>()
        for (line in input.filter { it.isNotBlank() }) {
            val coords = line.split(" -> ").map{ it.split(",") }.map { it ->
                Coord(it[0].toInt(), it[1].toInt())
            }

            for ((start, finish) in coords.windowed(size=2)) {
                for (x in start.x to finish.x) {
                    for (y in start.y to finish.y) {
                        ans.add(Coord(x, y))
                    }
                }
            }
        }
        val wall = 2 + ans.maxOf { it.y }
        for (x in -1_000_000..1_000_000) {
            ans.add(Coord(x, wall))
        }
        return ans
    }

    fun sandFall(blocked: MutableSet<Coord>): Boolean {
        if (Coord(500, 0) in blocked) {
            return false
        }
        var p = Coord(500, 0)
        val maxHeight = 100000
        while (p.y < maxHeight) {
            var found = false
            for (d in listOf(
                Coord(0, 1),
                Coord(-1, 1),
                Coord(1, 1))) {
                val nxt = Coord(p.x + d.x, p.y + d.y)
                if (nxt !in blocked) {
                    p = nxt
                    found = true
                    break
                }
            }
            if (!found) {
                blocked.add(p)
                return true
            }
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val stones = generateMap(input)
        var ans = 0
        while (sandFall(stones)) {
            ans += 1
        }
        return ans
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
//    println("check: ${part1(testInput)}")
//    check(part1(testInput) == 24)

    val input = readInput("Day14")
    println(part1(input))
    println(part2(input))
}
