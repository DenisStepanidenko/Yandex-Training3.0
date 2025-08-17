package dynamicProgramming2.problem30;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();

        int[] values1 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int x = input.nextInt();
            values1[i] = x;
        }

        int m = input.nextInt();
        int[] values2 = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            int x = input.nextInt();
            values2[i] = x;
        }

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (values1[j] == values2[i]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }

            }
        }

        // восстановление ответа

        int i = m;
        int j = n;
        List<Integer> ansReverse = new ArrayList<>();

        while (i != 0 && j != 0) {

            if (values1[j] == values2[i]) {
                ansReverse.add(values1[j]);
                i--;
                j--;
            } else {

                if (dp[i][j] == dp[i][j - 1]) {
                    j--;
                } else {
                    i--;
                }
            }
        }

        for (int k = ansReverse.size() - 1; k >= 0; k--) {
            System.out.print(ansReverse.get(k) + " ");
        }


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
