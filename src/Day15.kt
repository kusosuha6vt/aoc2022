//import kotlin.math.abs
//
//fun main() {
//    data class Coord(var x: Int, var y: Int)
//
//    infix fun Coord.dist(other: Coord): Int {
//        return abs(x - other.x) + abs(y - other.y)
//    }
//
////    fun intersect(s1: Coord, d1: Int, s2: Coord, d2: Int): Pair<Coord, Coord> {
////        // dist()
////    }
//
//    fun part1(input: List<String>, ansY: Int): Int {
//        val ans = mutableSetOf<Int>()
//        input.map{
//            val regInt = "(-?[0-9]+)"
//            val m = Regex("Sensor at x=$regInt, y=$regInt: closest beacon is at x=$regInt, y=$regInt").matchEntire(it)!!
//            Pair(Coord(m.groupValues[1].toInt(), m.groupValues[2].toInt()),
//                Coord(m.groupValues[3].toInt(), m.groupValues[4].toInt()))
//        }.forEach { (s, b) ->
//            val d = abs(s.x - b.x) + abs(s.y - b.y)
//            // dist({x, ansY}, s) = d
//            // abs(ansY - s.y) + abs(s.x - x) <= d
//            // abs(s.x - x) <= d - abs(ansY - s.y)
//            val m = d - abs(ansY - s.y)
//            for (x in s.x - m..s.x + m) {
//                if (Coord(x, ansY) != b) {
//                    ans.add(x)
//                }
//            }
//        }
//        return ans.size
//    }
//
//    fun part2(input: List<String>): Int {
//        val data = input.map {
//            val regInt = "(-?[0-9]+)"
//            val m = Regex("Sensor at x=$regInt, y=$regInt: closest beacon is at x=$regInt, y=$regInt").matchEntire(it)!!
//            Pair(
//                Coord(m.groupValues[1].toInt(), m.groupValues[2].toInt()),
//                Coord(m.groupValues[3].toInt(), m.groupValues[4].toInt())
//            )
//        }.map{
//            Pair(it.first, it.first dist it.second)
//        }
//        for (i in data) {
//            for (j in data) {
//
//            }
//        }
//    }
//
//    // test if implementation meets criteria from the description, like:
//    val testInput = readInput("Day15_test")
//    check(part1(testInput, 10) == 26)
//
//    val input = readInput("Day15")
//    println(part1(input, 2000000))
//    println(part2(input))
//}
