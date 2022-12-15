fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
        for ((fs, sc) in input.map { Pair(it.take(it.length / 2), it.takeLast(it.length - it.length / 2)) }) {
            val chars = fs.toCharArray().toHashSet().intersect(sc.toCharArray().toHashSet())
            sum += chars.sumOf {
                when (it) {
                    in 'a'..'z' -> it.code - 'a'.code + 1
                    in 'A'..'Z' -> it.code - 'A'.code + 27
                    else -> 0
                }
            }
        }
        return sum
    }

//    fun getSet(s: String): Set<Char> {
//        val (fs, sc) = Pair(s.take(s.length / 2), s.takeLast(s.length - s.length / 2))
//        return fs.toCharArray().toHashSet().intersect(sc.toCharArray().toHashSet())
//    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for ((a, b, c) in input.chunked(3)) {
            val chars = a.toCharArray().toHashSet().intersect(b.toCharArray().toHashSet())
                .intersect(c.toCharArray().toHashSet())
            sum += chars.sumOf {
                when (it) {
                    in 'a'..'z' -> it.code - 'a'.code + 1
                    in 'A'..'Z' -> it.code - 'A'.code + 27
                    else -> 0
                }
            }
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
