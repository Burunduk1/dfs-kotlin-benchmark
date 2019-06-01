// multilist in one array
// kotlin-coroutines from https://gist.github.com/elizarov/a6ce4e9b5dc1ffddc1e3c7503ec5b57b

import java.io.*
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.*
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

    val dfs = Rec<Int, Int, Int> { v, p ->
        var depth = 0
        var e = head[v]
        while (e != -1) {
            if (to[e] != p) {
                depth = maxOf(depth, rec(to[e], v))
            }
            e = next[e]
        }
        depth + 1
    }
    System.err.println("time in dfs = %dms".format(measureTimeMillis {
        val depth = dfs(0, -1)
        System.err.println("depth = $depth")
    }))
}


fun main(args: Array<String>) {
    System.err.println("total time = %dms".format(measureTimeMillis {
        solve(System.`in`.bufferedReader())
    }))
}

/**
 * https://gist.github.com/elizarov/a6ce4e9b5dc1ffddc1e3c7503ec5b57b
 * Recursive computation engine for Kotlin that uses suspending functions to keep computation
 * stack on the heap (as opposed to the real stack).
 */
@Suppress("UNCHECKED_CAST")
class Rec<V1, V2, R>(
    private val block: suspend RecScope<V1, V2, R>.(V1, V2) -> R
) : RecScope<V1, V2, R>(), Continuation<R> {
    private var result: Any? = null
    private var cont: Continuation<R>? = null
    private var v1: V1? = null
    private var v2: V2? = null

    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    override fun resumeWith(result: Result<R>) {
        this.result = result.getOrThrow()
        this.cont = null
    }

    operator fun invoke(v1: V1, v2: V2): R {
        set(this, v1, v2)
        run()
        return result as R
    }

    private fun run() {
        val f = block as Function4<Any?, V1?, V2?, Continuation<R>?, Any?>
        while (true) {
            val cont = this.cont ?: break
            val r = f(this, v1, v2, cont)
            if (r !== COROUTINE_SUSPENDED) cont.resume(r as R)
        }
    }

    override suspend fun rec(v1: V1, v2: V2): R = suspendCoroutineUninterceptedOrReturn { cont ->
        set(cont, v1, v2)
        COROUTINE_SUSPENDED
    }

    private fun set(cont: Continuation<R>, v1: V1, v2: V2) {
        this.cont = cont
        this.v1 = v1
        this.v2 = v2
    }
}

@RestrictsSuspension
abstract class RecScope<V1, V2, R> {
    abstract suspend fun rec(v1: V1, v2: V2): R
}
