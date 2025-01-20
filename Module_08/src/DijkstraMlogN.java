import java.io.*;
import java.util.*;

public class DijkstraMlogN {


    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int NUM = Integer.parseInt(st.nextToken()); // Количество запусков алгоритма
        StringBuilder result = new StringBuilder();

        while (NUM-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // Количество вершин
            int M = Integer.parseInt(st.nextToken()); // Количество рёбер

            List<List<Edge>> graph = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int l = Integer.parseInt(st.nextToken());

                graph.get(a).add(new Edge(b, l));
                graph.get(b).add(new Edge(a, l));
            }


            int start = Integer.parseInt(br.readLine());

            long[] distances = dijkstra(graph, N, start);
            for (int i = 0; i < N; i++) {
                result.append(distances[i] == Long.MAX_VALUE ? 2009000999 : distances[i]).append(" ");
            }
            result.append("\n");
        }

        System.out.print(result);
    }

    private static long[] dijkstra(List<List<Edge>> graph, int N, int start) {
        long[] distances = new long[N];
        Arrays.fill(distances, Long.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(e -> e.weight));
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (current.weight > distances[current.to]) continue;

            for (Edge edge : graph.get(current.to)) {
                long newDist = distances[current.to] + edge.weight;
                if (newDist < distances[edge.to]) {
                    distances[edge.to] = newDist;
                    pq.add(new Edge(edge.to, (int) newDist));
                }
            }
        }

        return distances;
    }
}

