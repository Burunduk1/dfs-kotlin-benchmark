import java.io.*
import java.util.*
import kotlin.system.measureTimeMillis

fun solve(input: BufferedReader) {
    val n = input.readLine().toInt()
    val deg = IntArray(n) {0}
    val xs = IntArray(n) {0}
    val ys = IntArray(n) {0}
    val g = Array(n) {IntArray(0) {0}}
    System.err.println("time to read data and build graph = %dms".format(measureTimeMillis {
        for (i in 0 until n - 1) {
        	val s = input.readLine().split(' ')
        	xs[i] = s[0].toInt() - 1
        	ys[i] = s[1].toInt() - 1
            deg[xs[i]]++
            deg[ys[i]]++
        }
        for (i in 0 until n) {
            g[i] = IntArray(deg[i]) {0}
            deg[i] = 0
        }
        for (i in 0 until n - 1) {
            g[xs[i]][deg[xs[i]]++] = ys[i]
            g[ys[i]][deg[ys[i]]++] = xs[i]
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
