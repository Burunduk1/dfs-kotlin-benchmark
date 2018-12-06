# dfs-kotlin-benchmark
Lets try to figure out, why dfs written in kotlin is so slow

## input data

input data is a tree  
`gen_graph.kt` generates straight line: 1-2-3-4-..-n

format of input data:

`n`  
`a[1] b[1]`  
`...`  
`a[n-1] b[n-1]`

## cpp solutions

to test, how fast dfs SHOULD work, there is model cpp solution `dfs.cpp`

## kotlin solutions

`dfs.kt` is the most natural implementation 

`dfs2.kt` is attempt to get rid of `ArrayList<Int>`

## log

this folder contains generated benchmarks

## test.sh

this script compiles and runs all generator and all solutions

## current results

on tree with 3 000 000 vertices

full optimized cpp-dfs works about `40 ms`

full optimized kt-dfs works about `17350 ms`

`433` times difference...
