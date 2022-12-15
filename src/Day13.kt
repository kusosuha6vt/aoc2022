

fun String.toList(): List<Any> {
    val stack = mutableListOf<MutableList<Any>>()
    var i = 0
    while (i < this.length - 1) {
        if (this[i] == ',') {
            i += 1
        } else if (this[i] == '[') {
            val newList = mutableListOf<Any>()
            stack.lastOrNull()?.add(newList)
            stack.add(newList)
            i += 1
        } else if (this[i] == ']') {
            stack.removeLast()
            i += 1
        } else {
            var n = 0
            while (this[i].isDigit()) {
                n = n * 10 + this[i].digitToInt()
                i += 1
            }
            stack.last().add(n)
        }
    }
    return stack[0]
}

fun compareLists(a: Any, b: Any): Int {
    if (a is Int && b is Int) {
        return a.compareTo(b)
    } else if (a is List<*> && b is List<*>) {
        for (i in 0 until minOf(a.size, b.size)) {
            val res = compareLists(a[i]!!, b[i]!!)
            if (res != 0) {
                return res
            }
        }
        return a.size.compareTo(b.size)
    } else if (a is List<*> && b is Int) {
        return compareLists(a, listOf(b))
    } else if (a is Int && b is List<*>) {
        return compareLists(listOf(a), b)
    } else {
        throw error("Wrong types")
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var i = 0
        var sum = 0
        for ((a, b) in input.filter { it.isNotBlank() }.chunked(2)) {
            val listA = a.toList()
            val listB = b.toList()
            val res = compareLists(listA, listB)
            if (res < 0) {
                sum += i + 1
            }
            i += 1
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val allLists = input.filter { it.isNotBlank() }.map { it.toList() }
        val pos2 = allLists.count { compareLists(it, listOf(listOf(2))) < 0 } + 1
        val pos6 = allLists.count { compareLists(it, listOf(listOf(6))) < 0 } + 2
        return pos2 * pos6
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    check(part2(testInput) == 140)

    val input = readInput("Day13")
    println(part1(input))
    println(part2(input))
}
