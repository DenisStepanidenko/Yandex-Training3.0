package dynamicProgramming1.problem23;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();

        if (n == 1) {
            System.out.println(0);
            System.out.println(1);
            System.exit(0);
        }

        long[] dp = new long[n + 1];
        dp[1] = 0;

        int[] index = new int[n + 1];
        index[1] = -1;

        long[] result = new long[n + 1];
        result[1] = 1;

        for (int i = 2; i <= n; i++) {

            long ans1 = dp[i - 1] + 1;
            long ans2 = i % 2 == 0 ? dp[i / 2] + 1 : Long.MAX_VALUE;
            long ans3 = i % 3 == 0 ? dp[i / 3] + 1 : Long.MAX_VALUE;


            if (ans1 <= ans2 && ans1 <= ans3) {
                index[i] = i - 1;
                result[i] = result[i - 1] + 1;
                dp[i] = ans1;
                continue;
            }

            if (ans2 <= ans1 && ans2 <= ans3) {
                index[i] = i / 2;
                result[i] = result[i / 2] * 2;
                dp[i] = ans2;
                continue;
            }

            if (ans3 <= ans1 && ans3 <= ans2) {
                index[i] = i / 3;
                result[i] = result[i / 3] * 3;
                dp[i] = ans3;
            }

        }

        List<Long> ans = new ArrayList<>();

        ans.add((long) n);

        int currIndex = index[n];

        while (currIndex != -1) {

            ans.add(result[currIndex]);
            currIndex = index[currIndex];
        }

        System.out.println(dp[n]);

        for (int i = ans.size() - 1; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
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
