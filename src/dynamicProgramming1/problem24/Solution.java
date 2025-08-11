package dynamicProgramming1.problem24;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();

        long[] a = new long[n + 1];
        long[] b = new long[n + 1];
        long[] c = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            long tempA = input.nextLong();
            long tempB = input.nextLong();
            long tempC = input.nextLong();

            a[i] = tempA;
            b[i] = tempB;
            c[i] = tempC;
        }

        if (n == 1) {
            System.out.println(a[1]);
        } else if (n == 2) {
            System.out.println(Math.min(a[1] + a[2], b[1]));
        } else if (n == 3) {
            long ans1 = a[1] + a[2] + a[3];
            long ans2 = b[1] + a[3];
            long ans3 = c[1];
            long ans4 = a[1] + b[2];

            System.out.println(Math.min(Math.min(ans1, ans2), Math.min(ans3, ans4)));
        } else {
            // база

            long[] dp = new long[n + 1];

            long ans1 = a[1] + a[2] + a[3];
            long ans2 = b[1] + a[3];
            long ans3 = c[1];
            long ans4 = a[1] + b[2];

            dp[1] = a[1];
            dp[2] = Math.min(a[1] + a[2], b[1]);
            dp[3] = Math.min(Math.min(ans1, ans2), Math.min(ans3, ans4));

            for (int i = 4; i <= n; i++) {
                dp[i] = Math.min(Math.min(dp[i - 1] + a[i], dp[i - 2] + b[i - 1]), dp[i - 3] + c[i - 2]);
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
