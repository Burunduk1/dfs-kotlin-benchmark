echoerr() { echo "$@" 1>&2; }

function compileJava {
	echoerr compileJava: \"$1.java to $1.jar\"
	echo "Main-Class: $1" > manifest.txt
	javac $1.java || exit 1
	jar -cvfm $1.jar manifest.txt $1.class || exit 1
	rm $1.class manifest.txt
}
function compileKotlin {
	echoerr compileKotlin: \"$1.kt to $1.jar\"
	kotlinc $1.kt -include-runtime -d $1.jar || exit 1
}
function compileCpp {
	echoerr compileCpp: \"$1.cpp\"
	WINFLAGS=-Wl,--stack=512000000 # for Windows OS
	# optimization level should be setted in $1.cpp
	g++ -Wall $WINFLAGS -std=c++14 $1.cpp -o $1 || exit 1 
}
function runJar {
	echoerr runJar: \"$1.jar\"
	java -Xss512M -jar $1.jar $2 || exit 1
}
function testSolution {
	echoerr testSolution: \"$1\", save log to \"$1.log\"
	$1 < graph.in 2> $1.log || exit 1
}

#runJar gen_graph_random 10
#exit

n=1000000
#compileKotlin gen_graph_linear
#compileKotlin gen_graph_bigdepth
compileKotlin gen_graph_random
#runJar gen_graph_linear $n > graph.in
#runJar gen_graph_bigdepth $n > graph.in
runJar gen_graph_random $n > graph.in
for f in *.cpp ; do 
	name=${f/.*/}
	echoerr Process \"$name\"
	compileCpp $name
	testSolution ./$name # warm up
	testSolution ./$name # the check
done
#compileCpp dfs2
#compileKotlin dfs
#compileKotlin dfs2

#testSolution "./dfs" 
#./dfs < graph.in 2> dfs.cpp.log
#./dfs2 < graph.in 2> dfs2.cpp.log
#
#echo "Test kotlin solutions"
#runJar dfs.kt < graph.in 2> dfs.kt.log
#runJar dfs2.kt < graph.in 2> dfs2.kt.log
#
for log in *.log ; do
	echo -- $log
	cat $log
done
