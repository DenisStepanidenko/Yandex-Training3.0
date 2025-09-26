package dfs.problem32;

import java.io.*;
import java.util.*;

public class Solution {

    private static Reader input = new Reader();

    public static void main(String[] args) {

        int n = input.nextInt();
        int m = input.nextInt();

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {

            int x = input.nextInt();
            int y = input.nextInt();

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        boolean[] visited = new boolean[n + 1];
        int comp = 1;

        for(int i = 1; i <= n; i++) {

            if(!visited[i]) {
                dfs(graph, visited, i, map, comp);

                comp++;
            }

        }

        System.out.println(map.keySet().size());


        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {

            System.out.println(entry.getValue().size());

            for(Integer i : entry.getValue()) {
                System.out.print(i + " ");
            }
            System.out.println();
        }


    }

    public static void dfs(List<List<Integer>> graph, boolean[] visited, int vertex, Map<Integer, List<Integer>> map, int comp) {

        visited[vertex] = true;
        List<Integer> vertexInComponenta = map.computeIfAbsent(comp, k -> new ArrayList<>());
        vertexInComponenta.add(vertex);

        for(Integer neighbor : graph.get(vertex)) {

            if (!visited[neighbor]) {
                dfs(graph, visited, neighbor, map, comp);
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
