package dynamicProgramming2.problem27;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

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

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = values[i][j] + Math.max(dp[i][j - 1], dp[i - 1][j]);
            }
        }

        int ans = dp[n][m];

        List<String> path = new ArrayList<>();

        int i = n;
        int j = m;

        while (i != 1 || j != 1) {

            int current = dp[i][j] - values[i][j];

            if (j != 1) {

                if (current == dp[i][j - 1]) {
                    path.add("R");
                    j--;
                    continue;
                }

            }

            if (i != 1) {

                if (current == dp[i - 1][j]) {
                    path.add("D");
                    i--;
                }
            }

        }

        System.out.println(ans);

        StringBuilder ansPath = new StringBuilder();

        for (int k = path.size() - 1; k >= 0; k--) {
            ansPath.append(path.get(k)).append(" ");
        }
        System.out.println(ansPath);

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
