import java.io.*;


public class RouteGraph {


    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            int[][] graph_stops = new int[n][n];
            int[][] graph_rout = new int[n][n];

            for (int i = 0; i < m; i++) {
                String[] in = reader.readLine().split(" ");
                int k = Integer.parseInt(in[0]);
                int[] stops = new int[k];
                for (int j = 0; j < k; j++) {
                    stops[j] = Integer.parseInt(in[j + 1]) - 1;
                }
                for (int j = 0; j < k - 1; j++) {
                    graph_rout[stops[j]][stops[j + 1]] = 1;
                    graph_rout[stops[j + 1]][stops[j]] = 1;
                }
                // Заполняем вторую матрицу смежности
                for (int j = 0; j < k; j++) {
                    for (int l = j + 1; l < k; l++) {
                        graph_stops[stops[j]][stops[l]] = 1;
                        graph_stops[stops[l]][stops[j]] = 1;// Если граф неориентированный
                    }
                }
            }

            // Выводим матрицу смежности
            /*
            for (int[] row : graph_rout) {
                writer.write(Arrays.toString(row));
                writer.newLine();
            }*/
            for (int[] row : graph_rout) {
                for (int i = 0; i < row.length; i++) {
                    if (i > 0) {
                        writer.write(" ");
                    }
                    writer.write(Integer.toString(row[i]));
                }
                writer.newLine();
            }
            for (int[] row : graph_stops) {
                for (int i = 0; i < row.length; i++) {
                    if (i > 0) {
                        writer.write(" ");
                    }
                    writer.write(Integer.toString(row[i]));
                }
                writer.newLine();
            }
            /*
            for (int[] row : graph_stops) {
                writer.write(Arrays.toString(row));
                writer.newLine();
            }*/


            writer.newLine();
        }
    }
}
