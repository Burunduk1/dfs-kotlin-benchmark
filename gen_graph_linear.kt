import kotlin.random.Random

fun main(args: Array<String>) {
	val f = System.`out`.bufferedWriter()
	val n = if (args.size >= 1) args[0].toInt() else 3e5.toInt()
	f.write("$n\n")
	for (i in 0 until n - 1) {
		fun addEdge(a: Int, b: Int) {
			f.write("${a + 1} ${b + 1}\n")
		}
		addEdge(i, i + 1)
	}
	f.close()
}
