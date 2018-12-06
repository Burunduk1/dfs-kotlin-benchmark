echo "Compile generator"
kotlinc gen_graph.kt -include-runtime -d gen_graph.jar || exit

echo "Build graph"
java -jar gen_graph.jar

echo "Compile cpp solutions"
WINFLAGS=-Wl,--stack=512000000 # for Windows OS
g++ -O2 -Wall $WINFLAGS -std=c++14 dfs.cpp -o dfs
g++ -O3 -Wall $WINFLAGS -std=c++14 dfs.cpp -o dfs2 

echo "Compile kotlin solutions"
kotlinc dfs.kt -include-runtime -d dfs.jar
kotlinc dfs2.kt -include-runtime -d dfs2.jar

echo "Test cpp solutions"
./dfs < graph.in 2> dfs.cpp.log
./dfs2 < graph.in 2> dfs2.cpp.log

echo "Test kotlin solutions"
java -Xss512M -jar dfs.jar < graph.in 2> dfs.kt.log
java -Xss512M -jar dfs2.jar < graph.in 2> dfs2.kt.log

for log in *.log ; do
	echo -- $log
	cat $log
done
