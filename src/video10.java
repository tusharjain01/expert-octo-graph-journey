import java.io.*;
import java.util.*;
public class video10 {
    static class Edge{
        int src;
        int nbr;
        int wt;
        Edge(int src,int nbr,int wt){
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }
    static class Pair implements Comparable<Pair>{
        int v;
        int acqv;
        int wsf;
        Pair(int v,int acqv,int wsf){
            this.v = v;
            this.acqv = acqv;
            this.wsf = wsf;
        }
        public int compareTo(Pair o){
            return this.wsf - o.wsf;
        }
    }
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int vtces = Integer.parseInt(br.readLine());
        ArrayList< Edge>[] graph = new ArrayList[vtces];
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
        boolean []visited = new boolean[vtces];
        int src = Integer.parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, -1, 0));
        while (pq.size() > 0) {
            Pair rm = pq.remove();
            if(visited[rm.v] == true){
                continue;
            }
            visited[rm.v] = true;

            if(rm.v != 0){
                System.out.println(rm.v + "via" + rm.acqv + "@" + rm.wsf);
            }
            for (Edge e : graph[rm.v]) {
                if(visited[e.nbr] == false){
                    pq.add(new Pair(e.nbr,rm.v,e.wt));
                }
            }
        }
    }
}
