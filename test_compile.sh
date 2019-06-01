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
	# WINFLAGS=-Wl,--stack=512000000 # for Windows OS
	# optimization level should be setted in $1.cpp
	g++ -Wall $WINFLAGS -std=c++14 $1.cpp -o $1 || exit 1 
}

# Compile all
for f in *.cpp  ; do compileCpp  ${f/.*/}; done
for f in *.kt   ; do compileKt   ${f/.*/}; done
for f in *.java ; do compileJava ${f/.*/}; done
