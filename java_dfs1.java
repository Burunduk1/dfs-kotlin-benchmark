// simple ArrayList<Integer>[]

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class java_dfs1 implements Runnable {
    public void dfs(int v, int p, ArrayList<Integer>[] g) {
        for (int x : g[v])
            if (x != p)
                dfs(x, v, g);
    }
    public static void main(String[] args) {
        new Thread(null, new java_dfs1(), "1", 1 << 29).start();
    }
    public void run() {
        try {
            long t0 = System.currentTimeMillis();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.valueOf(in.readLine());
            ArrayList<Integer>[] g = new ArrayList[n];
            for (int i = 0; i < n; i++) 
                g[i] = new ArrayList<Integer>();
            for (int i = 0; i < n - 1; i++) {
                StringTokenizer s = new StringTokenizer(in.readLine());
                int x = Integer.valueOf(s.nextToken()) - 1;
                int y = Integer.valueOf(s.nextToken()) - 1;
                g[x].add(y);
                g[y].add(x);
            }
            long t1 = System.currentTimeMillis();
            System.err.format("time to read data and build graph = %dms\n", t1 - t0);

            dfs(0, -1, g);
            long t2 = System.currentTimeMillis();
            System.err.format("time in dfs = %dms\n", t2 - t1);
            System.err.format("total time = %dms\n", t2 - t0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
