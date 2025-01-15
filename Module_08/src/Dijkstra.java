import java.io.*;
import java.util.*;

public class Dijkstra {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int S = Integer.parseInt(input[1])-1;
            int F = Integer.parseInt(input[2])-1;
            int[][] graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                String[] in = reader.readLine().split(" ");
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(in[j]);
                }
            }
            //System.out.println(Arrays.deepToString(graph));

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[S] = 0;
            boolean[] visited = new boolean[n];
            int[] prev = new int[n];
            Arrays.fill(prev, -1);

            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> dist[node])); pq.add(S);
            //System.out.println(pq);

            while (!pq.isEmpty()) {
                int u= pq.poll();
                if (visited[u]) continue;
                visited[u] = true;
                for(int v=0;v<n;v++){
                    if(graph[u][v] !=-1 && !visited[v]){
                        int newDist= dist[u]+graph[u][v];
                        if(newDist<dist[v]){
                            dist[v]=newDist;
                            prev[v]=u;
                            pq.add(v);
                        }
                    }
                }
            }

            if(dist[F]==Integer.MAX_VALUE){
                System.out.println(-1);
            }
            else {
                List<Integer> path = new ArrayList<>();
                for(int at =F; at!=-1;at=prev[at]){
                    path.add(at+1);
                }
                Collections.reverse(path);
                for (int node : path) { System.out.print(node + " "); }
            }

        }
    }
}
