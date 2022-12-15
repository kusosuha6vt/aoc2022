import java.math.BigInteger

interface Elem {
    val size: Int
}

class File(override val size: Int) : Elem

class Dir(var parent: Dir? = null, override var size: Int = 0) : Elem {
    val next = mutableMapOf<String, Elem>()

    fun processLs(input: List<String>) {
        for ((fs, sc) in input.map { it.split(" ").filter { it.isNotBlank() } }) {
            if (next.containsKey(sc))
                continue
            if (fs == "dir") {
                next[sc] = Dir(this)
            } else {
                next[sc] = File(fs.toInt())
            }
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val root = Dir()
        var curDir = root
        fun changeDir(curDir: Dir, path: String): Dir {
            if (path.startsWith("/"))
                return changeDir(root, path.substring(1))
            var dir = curDir
            path.split("/").filter { it.isNotBlank() }.forEach {
                dir = if (it == "..")
                    dir.parent ?: dir
                else
                    dir.next[it] as Dir
            }
            return dir
        }
        for (io in input.joinToString("\n").split("$ ").filter { it.isNotBlank() }.map { it.split("\n").filter { it.isNotBlank() } }) {
            if (io[0] == "ls") {
                curDir.processLs(io.subList(1, io.size))
            } else {
                curDir = changeDir(curDir, io[0].split(" ")[1])
            }
        }

        fun setDirSize(dir: Dir) {
            dir.next.forEach { (name, elem) ->
                if (elem is Dir) {
                    setDirSize(elem)
                }
                dir.size += elem.size
            }
        }
        setDirSize(root)
        var ans = Int.MAX_VALUE
        // need + (70000000 - root) >= 3000000
        // need >= 300000 - 700000 + rooot
        val need = 30000000 - 70000000 + root.size
        fun solve(dir: Dir) {
            if (dir.size in need until ans) {
                ans = dir.size
            }
            dir.next.forEach { (name, elem) ->
                if (elem is Dir) {
                    solve(elem)
                }
            }
        }
        solve(root)
        return ans
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
