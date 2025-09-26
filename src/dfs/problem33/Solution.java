package dfs.problem33;

import java.io.*;
import java.util.*;

public class Solution {

    private static Reader input = new Reader();


    public static void main(String[] args) {

        int n = input.nextInt();
        int m = input.nextInt();

        List<List<Vertex>> graph = new ArrayList<>();

        HashSet<Vertex> vertices = new HashSet<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        Map<Integer, Vertex> vertexMap = new HashMap<>();

        for (int i = 0; i < m; i++) {

            int x = input.nextInt();
            int y = input.nextInt();

            Vertex vertexX = vertexMap.computeIfAbsent(x, k -> new Vertex(x));
            Vertex vertexY = vertexMap.computeIfAbsent(y, k -> new Vertex(y));

            graph.get(x).add(vertexY);
            graph.get(y).add(vertexX);

            vertices.add(vertexX);
            vertices.add(vertexY);

        }

        boolean[] visited = new boolean[n + 1];

        boolean[] flag = new boolean[]{true};

        for (Vertex v : vertices) {

            if (!visited[v.id]) {
                dfs(graph, 1, v, visited, flag);
            }
        }

        System.out.println(flag[0] ? "YES" : "NO");


    }

    public static void dfs(List<List<Vertex>> graph, int colour, Vertex vertex, boolean[] visited, boolean[] flag) {

        visited[vertex.id] = true;
        vertex.setColour(3 - colour);

        for (Vertex v : graph.get(vertex.id)) {

            if (!visited[v.id]) {
                dfs(graph, vertex.colour, v, visited, flag);
            } else {

                if (vertex.colour == v.colour) {
                    flag[0] = false;
                }

            }
        }
    }

    static class Vertex {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return id == vertex.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }

        public int colour = 0;
        public int id;

        public Vertex(int id) {
            this.id = id;
        }

        public void setColour(int colour) {
            this.colour = colour;
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
