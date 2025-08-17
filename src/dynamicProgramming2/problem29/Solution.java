package dynamicProgramming2.problem29;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {


        int n = input.nextInt();

        if (n == 0) {
            System.out.println(0);
            System.out.println(0 + " " + 0);
            System.exit(0);
        }

        int[] values = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int x = input.nextInt();
            values[i] = x;
        }

        int[][] dp = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {

                dp[i][j] = Integer.MAX_VALUE;
            }
        }


        dp[1][0] = values[1];
        if (dp[1][0] > 100) {
            dp[1][1] = dp[1][0];
        }

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j < n; j++) {

                if (values[i] <= 100) {
                    int ans = Math.min(dp[i - 1][j + 1], (dp[i - 1][j] + values[i]));
                    if (ans < 0) break;
                    dp[i][j] = ans;
                } else {

                    if (j != 0) {
                        int ans = Math.min(dp[i - 1][j + 1], (dp[i - 1][j - 1] + values[i]));
                        if (ans < 0) break;
                        dp[i][j] = ans;
                    } else {
                        int ans = Math.min(dp[i - 1][j + 1], (dp[i - 1][j] + values[i]));
                        if (ans < 0) break;
                        dp[i][j] = ans;
                    }

                }


            }
        }


        int ansCount = Integer.MAX_VALUE;

        int countCouponsFree = 0; // неиспользованные купоны

        for (int j = n; j >= 0; j--) {

            if (dp[n][j] < ansCount) {
                ansCount = dp[n][j];
                countCouponsFree = j;
            }
        }

        int i = n;
        int j = countCouponsFree;

        int countCouponsUse = 0;
        List<Integer> ansDays = new ArrayList<>();

        while (i != 1) {

            if (values[i] <= 100) {
                if (dp[i][j] - values[i] == dp[i - 1][j]) {
                    i--;
                    continue;
                }

                if (dp[i][j] == dp[i - 1][j + 1]) {
                    countCouponsUse++;
                    ansDays.add(i);
                    i--;
                    j++;

                }
            } else {

                if (j != 0) {

                    if (dp[i][j] - values[i] == dp[i - 1][j - 1]) {
                        i--;
                        j--;
                        continue;
                    }

                    if (dp[i][j] == dp[i - 1][j + 1]) {
                        countCouponsUse++;
                        ansDays.add(i);
                        i--;
                        j++;

                    }
                } else {
                    if (dp[i][j] - values[i] == dp[i - 1][j]) {
                        i--;
                        continue;
                    }

                    if (dp[i][j] == dp[i - 1][j + 1]) {
                        countCouponsUse++;
                        ansDays.add(i);
                        i--;
                        j++;

                    }
                }
            }


        }


        System.out.println(ansCount);
        System.out.println(countCouponsFree + " " + countCouponsUse);
        for (int k = ansDays.size() - 1; k >= 0; k--) {
            System.out.println(ansDays.get(k));
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
