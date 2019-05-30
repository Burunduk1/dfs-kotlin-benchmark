// Fastest dfs by aman28rwt from https://codeforces.com/problemset/status/1088/problem/E?order=BY_CONSUMED_TIME_ASC
// int[][]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class java_dfs2 implements Runnable {
    int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0; i < n; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    public int dfs(int[][] g, int v, int p) {
        int depth = 0;
        for (int x : g[v])
            if (x != p) {
                depth = Math.max(depth, dfs(g, x, v));
            }   
        return depth + 1;
    }

    public static void main(String[] args) {
        new Thread(null, new java_dfs2(), "1", 1 << 29).start();
    }
    public void run() {
        try {
            long t0 = System.currentTimeMillis();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.valueOf(in.readLine());
            int[] from = new int[n - 1], to = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer s = new StringTokenizer(in.readLine());
                from[i] = Integer.valueOf(s.nextToken()) - 1;
                to[i] = Integer.valueOf(s.nextToken()) - 1;
            }
            int[][] g = packU(n, from, to);
            long[] dp = new long[n];
            long t1 = System.currentTimeMillis();
            System.err.format("time to read data and build graph = %dms\n", t1 - t0);

            int depth = dfs(g, 0, -1);
            long t2 = System.currentTimeMillis();
            System.err.format("depth = %d\n", depth);
            System.err.format("time in dfs = %dms\n", t2 - t1);
            System.err.format("total time = %dms\n", t2 - t0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
