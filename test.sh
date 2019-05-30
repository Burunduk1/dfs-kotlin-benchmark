echoerr() { echo "$@" 1>&2; }

function compileJava {
	echoerr compileJava: \"$1.java to $1.jar\"
	echo "Main-Class: $1" > manifest.txt
	javac $1.java || exit 1
	jar -cvfm $1.jar manifest.txt *.class || exit 1
	rm *.class manifest.txt
}
function compileKt {
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
	#echoerr runJar: \"$1.jar\"
	java -Xss512M -jar $1.jar $2 || exit 1
}
function testSolution {
	log_name=${2}_${test_file/.*/}.log
	echoerr testSolution: \"$1\", test = \"$test_file\", save log to \"${log_name}\"
	($1 < $test_file) 2> "${log_name}" || exit 1
}

# Compile all
for f in *.cpp  ; do compileCpp  ${f/.*/}; done
for f in *.kt   ; do compileKt   ${f/.*/}; done
for f in *.java ; do compileJava ${f/.*/}; done
#exit

n=100000

# name=kt_dfs3
# compileKt $name
# for test_file in *.in ; do
# 	testSolution "runJar $name" $name
# done

for gen_f in gen*.kt ; do
	gen_name=${gen_f/.*/}
	echoerr Generator $gen_name
	test_file=${gen_name}.in
	#compileKotlin ${gen_name}
	runJar ${gen_name} $n > $test_file
	for f in cpp*.cpp   ; do name=${f/.*/}; testSolution "./$name" $name; done
	for f in java*.java ; do name=${f/.*/}; testSolution "runJar $name" $name; done
	for f in kt*.kt     ; do name=${f/.*/}; testSolution "runJar $name" $name; done
done

for log in *.log ; do
	echo -- $log
	cat $log
done

python3 draw_table.py > statistic_tmp.md
