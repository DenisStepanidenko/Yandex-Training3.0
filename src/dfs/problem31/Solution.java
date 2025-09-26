package dfs.problem31;

import java.io.*;
import java.util.*;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();
        int m = input.nextInt();

        List<HashSet<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new HashSet<>());
        }

        for (int i = 0; i < m; i++) {

            int x = input.nextInt();
            int y = input.nextInt();

            if (x == y) continue;

            HashSet<Integer> neighborsX = graph.get(x);
            HashSet<Integer> neighborsY = graph.get(y);

            if (neighborsX.contains(y)) continue;

            neighborsX.add(y);
            neighborsY.add(x);

        }


        TreeSet<Integer> answer = new TreeSet<>();
        boolean[] visited = new boolean[n + 1];

        dfs(graph, visited, 1, answer);

        System.out.println(answer.size());

        for(Integer vertex : answer){
            System.out.print(vertex + " ");
        }


    }

    public static void dfs(List<HashSet<Integer>> graph, boolean[] visited, int vertex, TreeSet<Integer> answer){

        visited[vertex] = true;
        answer.add(vertex);

        for(Integer neighbor : graph.get(vertex)){

            if(!visited[neighbor]){
                dfs(graph, visited, neighbor, answer);
            }
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
