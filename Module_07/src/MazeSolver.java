import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MazeSolver {
    // Ниже массивы детализируют все четыре возможных перемещения из ячейки
    private static final int[] row = { -1, 0, 0, 1 };
    private static final int[] col = { 0, -1, 1, 0 };
    private static final char[] dir = { 'U', 'L', 'R', 'D' };

    private static boolean isValid(int[][] mat, boolean[][] visited, int row, int col)
    {
        return (row >= 0) && (row < mat.length) && (col >= 0) && (col < mat[0].length)
                && mat[row][col] == 1 && !visited[row][col];
    }


    // Узел queue
    static class Node
    {
        // (x, y) представляет собой координаты ячейки матрицы, а
        // `dist` представляет их минимальное расстояние от источника
        int x, y, dist;
        String path;

        Node(int x, int y, int dist, String path)
        {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.path = path;
        }
    }


    // Находим кратчайший маршрут в матрице `mat` из источника
    // ячейка (i, j) в ячейку назначения (x, y)
    private static String findShortestPath(int[][] mat, int i, int j, int x, int y)
    {
        // базовый случай: неверный ввод
        if (mat == null || mat.length == 0 || mat[i][j] == 0 || mat[x][y] == 0) {
            return "-1";
        }

        // Матрица `M × N`
        int M = mat.length;
        int N = mat[0].length;

        // создаем матрицу для отслеживания посещенных ячеек
        boolean[][] visited = new boolean[M][N];

        // создаем пустую queue
        Queue<Node> q = new ArrayDeque<>();

        // помечаем исходную ячейку как посещенную и ставим исходный узел в queue
        visited[i][j] = true;
        q.add(new Node(i, j, 0, ""));

        // цикл до тех пор, пока queue не станет пустой
        while (!q.isEmpty())
        {
            // удалить передний узел из очереди и обработать его
            Node node = q.poll();

            // (i, j) представляет текущую ячейку, а `dist` хранит ее
            // минимальное расстояние от источника
            i = node.x;
            j = node.y;
            String path = node.path;

            // если пункт назначения найден, возвращаем путь
            if (i == x && j == y)
            {
                return path;
            }

            // проверяем все четыре возможных перемещения из текущей ячейки
            // и ставим в queue каждое допустимое движение
            for (int k = 0; k < 4; k++)
            {
                // проверяем, можно ли выйти на позицию
                // (i + row[k], j + col[k]) от текущей позиции
                if (isValid(mat, visited, i + row[k], j + col[k]))
                {
                    // отметить следующую ячейку как посещенную и поставить ее в queue
                    visited[i + row[k]][j + col[k]] = true;
                    q.add(new Node(i + row[k], j + col[k], node.dist + 1, path + dir[k]));
                }
            }
        }

        return "-1";
    }

    public static void main(String[] args)  {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\Java\\2024\\Java_Core_practice\\Module_07\\src\\input.txt"));
            String[] dimensions = reader.readLine().split(" ");
            int n = Integer.parseInt(dimensions[0]);
            int m = Integer.parseInt(dimensions[1]);

            int[][] maze = new int[n][m];
            int startX = -1, startY = -1, endX = -1, endY = -1;

            for (int i = 0; i < n; i++) {
                String line = reader.readLine();
                for (int j=0;j<m;j++){
                    char cell = line.charAt(j);
                    if(cell=='S'){
                        startX = i; startY = j; maze[i][j] = 1;
                    }else if(cell =='F'){
                        endX = i; endY = j; maze[i][j] = 1;
                    }else if(cell=='#'){
                        maze[i][j]=0;
                    }
                    else  maze[i][j]=1;
                }
            }

            String path = findShortestPath(maze, startX, startY, endX, endY);
            System.out.println(path.length());
            System.out.println(path);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
