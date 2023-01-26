import java.io.*;
import java.util.*;

public class video7 {
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    static class Pair {
        int v;
        String psf;

        Pair(int v, String psf) {
            this.v = v;
            this.psf = psf;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vtces = Integer.parseInt(br.readLine());
        ArrayList<Edge>[] graph = new ArrayList[vtces];
        for (int i = 0; i < vtces; i++) {
            graph[i] = new ArrayList<>();
        }
        int edges = Integer.parseInt(br.readLine());
        for (int i = 0; i < edges; i++) {
            String[] parts = br.readLine().split(" ");
            int v1 = Integer.parseInt(parts[0]);
            int v2 = Integer.parseInt(parts[1]);
            int wt = Integer.parseInt(parts[2]);
            graph[v1].add(new Edge(v1, v2, wt));
            graph[v2].add(new Edge(v2, v1, wt));
        }
        boolean[] visited = new boolean[vtces];
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                boolean cycle = isCyclic(graph, i, visited);
                if (cycle) {
                    System.out.println(true);
                    return;
                }
            }
        }
        System.out.println(false);
    }

    public static boolean isCyclic(ArrayList<Edge>[] graph, int src, boolean[] visited) {
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(src, src + ""));
        while (queue.size() > 0) {
            Pair rm = queue.removeFirst();
            if (visited[rm.v] == true) {
                return true;
            }
            visited[rm.v] = true;
            for (Edge edge : graph[rm.v]) {
                if (visited[edge.nbr] == false) {
                    queue.add(new Pair(edge.nbr, rm.psf + edge.nbr));
                }
            }
        }
        return false;
    }
}
