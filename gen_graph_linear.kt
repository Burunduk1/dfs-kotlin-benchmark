import java.io.File
import kotlin.random.Random

fun main(args: Array<String>) {
	val f = File("graph.in").printWriter()
	val n = 3e6.toInt()
	f.println(n)
	for (i in 1 until n)
		f.print("%d %d\n".format(i, i + 1))
	f.close()
}
