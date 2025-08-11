package dynamicProgramming1.problem25;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();

        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int x = input.nextInt();
            arr[i] = x;
        }

        Arrays.sort(arr);

        int[] dp = new int[n + 1];
        dp[1] = arr[2] - arr[1];
        dp[2] = arr[2] - arr[1];


        int i = 3;
        while (i <= n) {
            dp[i] = Math.min(dp[i - 1] + arr[i] - arr[i - 1], dp[i - 2] + arr[i] - arr[i - 1]);
            i++;
        }

        System.out.println(dp[n]);

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
