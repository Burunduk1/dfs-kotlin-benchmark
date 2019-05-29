// ArrayList<Int>[]

import java.io.*
import kotlin.system.measureTimeMillis

fun solve(input: BufferedReader) {
    val n = input.readLine().toInt()
    val g = Array(n) {ArrayList<Int>()}
    System.err.println("time to read data and build graph = %dms".format(measureTimeMillis {
        for (i in 0 until n - 1) {
            val s = input.readLine().split(' ')
            val x = s[0].toInt() - 1
            val y = s[1].toInt() - 1
            g[x].add(y)
            g[y].add(x)
        }
    }))

    fun dfs(v: Int, p: Int = -1) {
        for (x in g[v]) {
            if (x != p) {                
                dfs(x, v)
            }
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
