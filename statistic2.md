## Experiments, time in ms

### dfs time (only dfs), n = 3 000 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 512    | 118      | 169    |
| cpp_dfs -O3       | 391    | 93       | 159    |
| java: ArrayList[] | 982    | 630      | 11751  |
| java: int[][]     | 279    | 5619     | 10848  |
| java: Egor        | 380    | 5419     | 10718  |
| kt: ArrayList[]   | 748    | 165      | 10733  |
| kt: IntArray[]    | 279    | 69       | 10876  |
| kt: multilist[]   | 303    | 5103     | 10290  |
| <span style="color:blue">kt: coroutine</span>     | <span style="color:blue">658</span>    | <span style="color:blue">986</span>      | <span style="color:blue">2151</span>    |

`kt: coroutine` misses cache, see below n = 1 000 000

### dfs time (only dfs), n = 1 000 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 159    | 39       | 54     |
| cpp_dfs -O3       | 123    | 31       | 52     |
| java: ArrayList[] | 217    | 65       | 4525   |
| java: int[][]     | 79     | 1610     | 3399   |
| java: Egor        | 105    | 1544     | 3435   |
| kt: ArrayList[]   | 240    | 77       | 3394   |
| kt: IntArray[]    | 88     | 27       | 3450   |
| kt: multilist[]   | 93     | 1484     | 3217   |
| <span style="color:blue">kt: coroutine</span>     | <span style="color:blue">199</span>    | <span style="color:blue">108</span>      | <span style="color:blue">118</span>    |

### dfs time (only dfs), n = 100 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 11     | 7        | 7      |
| cpp_dfs -O3       | 5      | 4        | 12     |
| java: ArrayList[] | 21     | 26       | 211    |
| java: int[][]     | 10     | 84       | 195    |
| java: Egor        | 12     | 76       | 119    |
| kt: ArrayList[]   | 23     | 22       | 44     |
| kt: IntArray[]    | 10     | 6        | 8      |
| kt: multilist[]   | 8      | 14       | 6      |
| kt: coroutine     | 53     | 46       | 39     |

### dfs time (only dfs), n = 30 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 2      | 1        | 2      |
| cpp_dfs -O3       | 2      | 1        | 1      |
| java: ArrayList[] | 16     | 10       | 11     |
| java: int[][]     | 6      | 10       | 7      |
| java: Egor        | 7      | 11       | 9      |
| kt: ArrayList[]   | 15     | 10       | 10     |
| kt: IntArray[]    | 2      | 1        | 4      |
| kt: multilist[]   | 3      | 4        | 3      |
