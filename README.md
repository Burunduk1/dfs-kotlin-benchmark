# dfs-kotlin-benchmark
Lets try to figure out, why dfs written in java/kotlin is so slow

## input data

input data is a tree  

`gen_graph_line.kt` generates straight line: 1-2-3-4-..-n

`gen_graph_bigdepth.kt` generates 1->2,3; 3->4,5; 5->6,7; ...

`gen_graph_random.kt` generates edges [random(1,i), i+1]

format of input data:

`n`  
`a[1] b[1]`  
`...`  
`a[n-1] b[n-1]`

## solutions

cpp: `cpp_dfs1.cpp` multilist in one array (no stl), compile with -O0 

cpp: `cpp_dfs2.cpp` multilist in one array (no stl), compile with -O3

java: `java_dfs1.java` ArrayList<Integer>[]

java: `java_dfs2.java` int[][]

java: `java_dfs3_Egor.java` uses class [Graph](https://github.com/EgorKulikov/yaal/blob/master/lib/main/net/egork/graph/Graph.java) from `net.egork.graph`

kt: `kt_dfs1.kt` ArrayList<Int>[]

kt: `kt_dfs2.kt` IntArray[]

kt: `kt_dfs3.kt` multilist in one array

## Experiment results

| Processor                   | OS           | Compilers                | Statistic                      |
|-----------------------------|--------------|--------------------------|--------------------------------|
| Intel G4600 3.6 GHz         | Windows 10   | g++ 6.3.0, java64 11.0.1 | [statistic.md](statistic.md)   |
| Intel Core i5-5200U 2.2 GHz | Ubuntu 16.04 | g++ 7.1.0, java64 1.8    | [statistic2.md](statistic2.md) |

All java/kotlin solutions work extremly slow
on linear-tree tests. Why?
