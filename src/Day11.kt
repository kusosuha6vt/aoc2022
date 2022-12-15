import java.math.BigInteger

sealed class Monkey {
    abstract val items: MutableList<BigInteger>
    abstract fun operation(old: BigInteger): BigInteger
    abstract val divBy: BigInteger
    fun test(lvl: BigInteger): Boolean {
        return (lvl % divBy) == 0L.toBigInteger()
    }
    abstract val throwIfTrue: Int
    private fun ifTrue(lvl: BigInteger) {
//        println("(true, [$throwIfTrue], lvl)")
        monkeys[throwIfTrue].items.add(lvl)
    }
    abstract val throwIfFalse: Int
    private fun ifFalse(lvl: BigInteger) {
//        println("(false, [$throwIfFalse], lvl)")
        monkeys[throwIfFalse].items.add(lvl)
    }
    fun go() {
//        println("<New start>")
        for (i in items) {
            val newI = operation(i) % ttt
//            print("$newI -> ")
            if (test(newI)) {
                ifTrue(newI)
            } else {
                ifFalse(newI)
            }
        }
        items.clear()
    }
}

fun MutableList<Int>.toBigInteger(): MutableList<BigInteger> {
    return MutableList<BigInteger>(this.size) { this[it].toBigInteger() }
}

class Monkey0: Monkey() {
    override val items
            = mutableListOf(59.toBigInteger(), 74.toBigInteger(), 65.toBigInteger(), 86.toBigInteger())

    override fun operation(old: BigInteger): BigInteger {
        return old * 19.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 7.toBigInteger()
    override val throwIfTrue: Int
        get() = 6
    override val throwIfFalse: Int
        get() = 2
}

class Monkey1: Monkey() {
    override val items
            = mutableListOf(62, 84, 72, 91, 68, 78, 51).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old + 1.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 2.toBigInteger()
    override val throwIfTrue: Int
        get() = 2
    override val throwIfFalse: Int
        get() = 0
}

class Monkey2: Monkey() {
    override val items
            = mutableListOf(78, 84, 96).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old + 8.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 19.toBigInteger()
    override val throwIfTrue: Int
        get() = 6
    override val throwIfFalse: Int
        get() = 5
}

class Monkey3: Monkey() {
    override val items
            = mutableListOf(97, 86).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old * old
    }

    override val divBy: BigInteger
        get() = 3.toBigInteger()
    override val throwIfTrue: Int
        get() = 1
    override val throwIfFalse: Int
        get() = 0
}
class Monkey4: Monkey() {
    override val items
            = mutableListOf(50).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old + 6.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 13.toBigInteger()
    override val throwIfTrue: Int
        get() = 3
    override val throwIfFalse: Int
        get() = 1
}

class Monkey5: Monkey() {
    override val items
            = mutableListOf(73, 65, 69, 65, 51).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old * 17.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 11.toBigInteger()
    override val throwIfTrue: Int
        get() = 4
    override val throwIfFalse: Int
        get() = 7
}

class Monkey6: Monkey() {
    override val items
            = mutableListOf(69, 82, 97, 93, 82, 84, 58, 63).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old + 5.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 5.toBigInteger()
    override val throwIfTrue: Int
        get() = 5
    override val throwIfFalse: Int
        get() = 7
}

class Monkey7: Monkey() {
    override val items
            = mutableListOf(81, 78, 82, 76, 79, 80).toBigInteger()

    override fun operation(old: BigInteger): BigInteger {
        return old + 3.toBigInteger()
    }

    override val divBy: BigInteger
        get() = 17.toBigInteger()
    override val throwIfTrue: Int
        get() = 3
    override val throwIfFalse: Int
        get() = 4
}

val monkeys = listOf(
    Monkey0(),
    Monkey1(),
    Monkey2(),
    Monkey3(),
    Monkey4(),
    Monkey5(),
    Monkey6(),
    Monkey7(),
)

val ttt = (0 until 8).map { monkeys[it] }.fold(1.toBigInteger()) { acc, el -> acc * el.divBy }

fun main() {
    fun part1(input: List<String>): BigInteger {
        val cnt = MutableList(monkeys.size){ 0.toBigInteger() }
        repeat(10000) {
            for (i in monkeys.indices) {
                val monkey = monkeys[i]
                cnt[i] += monkey.items.size.toBigInteger()
//                println("$i: ${monkey.items.size.toBigInteger()}")
//                if (monkey.items.size > 0)
//                    print("$i: ")
                monkey.go()
            }
//            println("#########################################")
        }
        cnt.sort()
        return cnt.last() * cnt[cnt.size - 2]
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
//    println(part1(testInput))
//    check(part1(testInput) == TODO())

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
