import java.io.*;
import java.util.*;

public class DijkstraR {

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader br = new BufferedReader(new FileReader("D:\\Java\\2024\\Java_Core_practice\\Module_08\\input.txt"));
        //BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Java\\2024\\Java_Core_practice\\Module_08\\src\\output.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // Количество городов
        int K = Integer.parseInt(st.nextToken()); // Количество дорог

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, l));
            graph.get(b).add(new Edge(a, l));
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        long result = dijkstra(graph, N, A, B);
        System.out.println(result);
        //writer.write(String.valueOf(result));
        //writer.close();
    }

    private static long dijkstra(List<List<Edge>> graph, int N, int start, int end) {
        long[] distances = new long[N + 1];
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

        return distances[end] == Long.MAX_VALUE ? -1 : distances[end];
    }
}
