## Experiments, time in ms

### dfs time (only dfs), n = 3 000 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 492    | 96       | 138    |
| cpp_dfs -O3       | 435    | 90       | 155    |
| java: ArrayList[] | 774    | 177      | 20237  |
| java: int[][]     | 287    | 9305     | 18162  |
| java: Egor        | 378    | 8912     | 17814  |
| kt: ArrayList[]   | 758    | 206      | 19907  |
| kt: IntArray[]    | 265    | 68       | 18261  |
| kt: multilist[]   | 328    | 8795     | 17916  |
| kt: coroutine     | 633    | 543      | 830    |

### dfs time (only dfs), n = 1 000 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       |  146   |   32     |  44    |
| cpp_dfs -O3       |  89    |   10     |  17    |
| java: ArrayList[] |  255   |   79     |  6350  |
| java: int[][]     |  104   | 2972     |  5932  |
| java: Egor        |  131   | 2900     |  5905  |
| kt: ArrayList[]   |  228   |   45     |  6378  |
| kt: IntArray[]    |  78    |   23     |  5672  |
| kt: multilist[]   |  94    | 2708     |  5635  |
| kt: coroutine     | 192    | 163      | 329    |

### dfs time (only dfs), n = 100 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 6      | 4        | 5      |
| cpp_dfs -O3       | 5      | 4        | 5      |
| java: ArrayList[] | 53     | 48       | 298    |
| java: int[][]     | 36     | 194      | 481    |
| java: Egor        | 36     | 144      | 440    |
| kt: ArrayList[]   | 21     | 21       | 23     |
| kt: IntArray[]    | 5      | 4        | 284    |
| kt: multilist[]   | 5      | 58       | 374    |
| kt: coroutine     | 32     | 46       | 32     |

### dfs time (only dfs), n = 30 000

| soution/tree      | random | bigdepth | linear |
|-------------------|--------|----------|--------|
| cpp_dfs -O0       | 2      | 0        | 0      |
| cpp_dfs -O3       | 2      | 1        | 2      |
| java: ArrayList[] | 40     | 47       | 40     |
| java: int[][]     | 45     | 47       | 48     |
| java: Egor        | 36     | 32       | 44     |
| kt: ArrayList[]   | 7      | 6        | 9      |
| kt: IntArray[]    | 3      | 0        | 4      |
| kt: multilist[]   | 2      | 0        | 3      |
