import java.io.*;
import java.util.*;
public class video8 {
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
    static class Pair{
        int v;
        String psf;
        int level;
        Pair(int v,String psf,int level){
            this.v = v;
            this.psf = psf;
            this.level = level;
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
        int []visited = new int[vtces];
        Arrays.fill(visited, -1);
        for (int i = 0; i < visited.length; i++) {
            if(visited[i] == -1){
                boolean isCompBipartite = checkForBipartiteCompleteness(graph, i, visited);
                if(isCompBipartite == false){
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }

    public static boolean checkForBipartiteCompleteness(ArrayList<Edge>[]graph,int src,int []visited){
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.add(new Pair(src, src + " ", 0));
        while(q.size() > 0){
            Pair rm = q.removeFirst();
            if(visited[rm.v] != -1){
                if(visited[rm.v] != rm.level){
                    return false;
                }
            }
            else{
                visited[rm.v] = rm.level;
            }

            for (Edge e : graph[rm.v]) {
                if(visited[e.nbr] == -1){
                    q.add(new Pair(e.nbr, rm.psf + e.nbr, rm.level + 1));
                }
            }
        }
        return true;
    }
}
