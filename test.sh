echoerr() { echo "$@" 1>&2; }

function runJar {
	#echoerr runJar: \"$1.jar\"
	java -Xss512M -jar $1.jar $2 || exit 1
}
function testSolution {
	log_name=${2}_${test_file/.*/}.log
	echoerr testSolution: \"$1\", test = \"$test_file\", save log to \"${log_name}\"
	($1 < $test_file) 2> "${log_name}" || exit 1
}

n=3000000

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
