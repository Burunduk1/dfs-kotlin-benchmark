#include <cstdio>
#include <ctime>
#include <vector>

using namespace std;

const int bufs_len = 1 << 13;

int rbuf_pos, rbuf_len;
unsigned char rbuf[bufs_len];

int readChar() {
    if (rbuf_pos == rbuf_len)
        rbuf_pos = 0, rbuf_len = fread(rbuf, 1, bufs_len, stdin);
    if (rbuf_pos == rbuf_len)
        return -1; // end of file
    return rbuf[rbuf_pos++];
}

int readInt() {
    int x = 0, c = readChar(), neg = 0;
    while (c != -1 && c < 32)
        c = readChar();
    if (c == '-')
        neg = 1, c = readChar();
    while ('0' <= c && c <= '9')
        x = x * 10 + c - '0', c = readChar();
    return neg ? -x : x;
}

template <class T>
struct List {
    vector<T> data;
    vector<int> head, next;
    int n;
    List(int N, int SIZE) : data(SIZE), head(N, -1), next(SIZE), n(0) { }
    void add(int i, T x) {
        next[n] = head[i], data[n] = x, head[i] = n++;
    }
};

void dfs(int v, int p, const List<int> &g) {
    for (int i = g.head[v]; i != -1; i = g.next[i]) {
        int x = g.data[i];
        if (x != p) {
            dfs(x, v, g);
        }
    }
}

int main() {
    int n = readInt();
	List<int> g(n, 2 * n);
    for (int i = 0; i < n - 1; i++) {
        int x = readInt() - 1;
        int y = readInt() - 1;
        g.add(x, y);
        g.add(y, x);
    }
    fprintf(stderr, "time to read data and build graph = %.0fms\n", 1e3 * clock() / CLOCKS_PER_SEC);

    double tmp = clock();
    dfs(0, -1, g);
    fprintf(stderr, "time in dfs = %.0fms\n", 1e3 * (clock() - tmp) / CLOCKS_PER_SEC);

    fprintf(stderr, "total time = %.0fms\n", 1e3 * clock() / CLOCKS_PER_SEC);
}
