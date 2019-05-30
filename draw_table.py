# | soution/tree      | random | bigdepth | linear |
# |-------------------|--------|----------|--------|
# | cpp_dfs -O0       |  146   |   32     |  44    |
# | cpp_dfs -O3       |  89    |   10     |  17    |
# | java: ArrayList[] |  255   |   79     |  6350  |
# | java: int[][]     |  104   | 2972     |  5932  |
# | java: Egor        |  131   | 2900     |  5905  |
# | kt: ArrayList[]   |  228   |   45     |  6378  |
# | kt: IntArray[]    |  78    |   23     |  5672  |
# | kt: multilist[]   |  94    | 2708     |  5635  |

import re

generators = [
	("random", "gen_graph_random"),
	("bigdepth", "gen_graph_bigdepth"), 
	("linear", "gen_graph_linear")
]

solutions = [
	("cpp_dfs -O0", "cpp_dfs1"),
	("cpp_dfs -O3", "cpp_dfs2"),
	("java: ArrayList[]", "java_dfs1"),
	("java: int[][]", "java_dfs2"),
	("java: Egor", "java_dfs3_Egor"),
	("kt: ArrayList[]", "kt_dfs1"),
	("kt: IntArray[]", "kt_dfs2"),
	("kt: multilist[]", "kt_dfs3"),
]

title = "soution/tree"

titleLen = max(len(title), max(map(lambda x: len(x[0]), solutions)))
lens = [titleLen] + [max(5, len(g)) for g, _ in generators]

def fixLen(s, x):
	while len(s) < x:
		s += " "
	return s
def drawRow(a):
	for s, l in zip(a, lens):
		print("| %s " % fixLen(s, l), end = "")
	print("|")
def drawLine():
	for l in lens:
		print("|%s" % ("-" * (l + 2)), end = "")
	print("|")

def readTime(fileName):
	with open(fileName) as file:
		data = "\n".join(file.readlines())
		# time in dfs = 89ms
		m = re.search(r'time in dfs = (\d*)ms', data)
		return m.group(1)
	
drawRow([title] + list(list(zip(*generators))[0]))
drawLine()
for solName, solFile in solutions:
	# cpp_dfs1_gen_graph_bigdepth.log
	drawRow([solName] + list(map(lambda g: readTime("%s_%s.log" % (solFile, g[1])), generators)))

