import java.io.*
import kotlin.system.measureTimeMillis

fun solve(input: BufferedReader) {
    val n = input.readLine().toInt()
    val head = IntArray(n) {-1}
    val m = 2 * (n - 1)
    val next = IntArray(m) {-1}
    val to = IntArray(m) {-1}
    var edges = 0
    fun add(a: Int, b: Int) {
        next[edges] = head[a]
        to[edges] = b
        head[a] = edges++
    }
    System.err.println("time to read data and build graph = %dms".format(measureTimeMillis {
        for (i in 0 until n - 1) {
            val s = input.readLine().split(' ')
            val x = s[0].toInt() - 1
            val y = s[1].toInt() - 1
            add(x, y)
            add(y, x)
        }
    }))

    fun dfs(v: Int, p: Int = -1) {
        var e = head[v]
        while (e != -1) {
            if (to[e] != p) {
                dfs(to[e], v)
            }
            e = next[e]
        }
    }
    System.err.println("time in dfs = %dms".format(measureTimeMillis {
        dfs(0)
    }))
}


fun main(args: Array<String>) {
    System.err.println("total time = %dms".format(measureTimeMillis {
        solve(System.`in`.bufferedReader())
    }))
}
