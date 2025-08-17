package dynamicProgramming2.problem26;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();
        int m = input.nextInt();

        int[][] values = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= m; j++) {
                int x = input.nextInt();

                values[i][j] = x;
            }
        }

        int[][] dp = new int[n + 1][m + 1];

        for (int j = 0; j <= m; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        dp[1][1] = values[1][1];
        for (int j = 2; j <= m; j++) {
            dp[1][j] = values[1][j] + Math.min(dp[1][j - 1], dp[0][j]);
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = values[i][j] + Math.min(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        System.out.println(dp[n][m]);
    }


    static class Reader extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;
        // standard input

        public Reader() {
            this(System.in, System.out);
        }

        public Reader(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input

        public Reader(String problemName) throws IOException {
            super(problemName + ".out");
            r = new BufferedReader(new FileReader(problemName));
        }

        // returns null if no more input
        String nextLine() {
            String str = "";
            try {
                str = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(r.readLine());
                }
                return st.nextToken();
            } catch (Exception e) {
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {

            return Long.parseLong(next());
        }
    }
}
