import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class video5 {
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
        int src = Integer.parseInt(br.readLine());
        int osrc = src;
        HashSet<Integer> visited = new HashSet<>();
        Hamilition(graph, src, visited, src + "",osrc); 
        
    }
    public static void Hamilition(ArrayList<Edge>[] graph,int src,HashSet<Integer> visited,String psf,int osrc){
        if(visited.size() == graph.length - 1){
            System.out.print(psf);
            boolean closingEdgeCycle = false;
            for (Edge edge : graph[src]) {
                if(edge.nbr == osrc){
                    closingEdgeCycle = true;
                    break;
                }
            }
            if(closingEdgeCycle){
                System.out.println("*");
            }
            else{
                System.out.println(".");
            }
            return;
        }

        visited.add(src);
        for (Edge edge : graph[src]) {
            if(visited.contains(edge.nbr) == false){
                Hamilition(graph,edge.nbr, visited,psf + edge.nbr,osrc);
            }
        }
        visited.remove(src);
    }
}
