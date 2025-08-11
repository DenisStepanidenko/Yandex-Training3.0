package dynamicProgramming1.problem22;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();
        int k = input.nextInt();

        if (n == 1) {
            System.out.println(1);
        } else if (n == 2) {
            System.out.println(1);
        } else {

            long[] dp = new long[n + 1];

            dp[1] = 1;
            dp[2] = 1;

            for (int i = 3; i <= n; i++) {

                long count = 0;

                int j = i - 1;

                while (j >= 0 && j >= (i - k)) {
                    count += dp[j];
                    j--;
                }

                dp[i] = count;

            }

            System.out.println(dp[n]);
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
