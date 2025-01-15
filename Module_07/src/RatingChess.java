import java.io.*;
public class RatingChess {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int u, v, t;
            int[][] graph = new int[n][n];
            for (int i = 0; i < m; i++) {
                input = reader.readLine().split(" ");
                u = Integer.parseInt(input[0]);
                v = Integer.parseInt(input[1]);
                t = Integer.parseInt(input[2]);
                if (t == 1) {
                    graph[u - 1][v - 1] = 1;
                } else {
                    graph[v - 1][u - 1] = 1;
                }
            }
            //System.out.println(Arrays.deepToString(graph));
            for (int i = 0; i < n; i++) {
                if (isReachableFrom(i, graph)) {
                    //System.out.println("Вершина " + (i + 1) + " является доминатором.");
                    System.out.println("YES");
                    return;
                }
            }
            System.out.println("NO");
        }
    }

    private static boolean isReachableFrom(int v, int[][] graph) {
        boolean[] visited=new boolean[graph.length];
        dfs(v,visited,graph);

        for(boolean visit : visited) {
            if(!visit) return false;
        }
        return true;
    }

    private static void dfs(int v, boolean[] visited, int[][] graph) {
        visited[v]= true;
        for (int i = 0; i < graph.length; i++){
            if(graph[v][i]==1 && !visited[i]){
                dfs(i,visited,graph);
            }

        }
    }
}



