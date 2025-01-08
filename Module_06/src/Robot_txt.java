import java.io.*;
import java.util.*;


public class Robot_txt {


    public static void main(String[] args) throws IOException {//"D:\\Java\\2024\\Java_Core_practice\\Module_06\\src\\input.txt"
        File currentDir = new File(".");
        System.out.println("Текущая рабочая директория: " +  currentDir.getAbsolutePath());
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\Java\\2024\\Java_Core_practice\\Module_06\\src\\input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\Java\\2024\\Java_Core_practice\\Module_06\\src\\output.txt"))) {
            String[] input = reader.readLine().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            char[][] map = new char[n][m];

            for (int i = 0; i < n; i++) {
                String in = reader.readLine();
                map[i] = in.toCharArray();
            }
            input = reader.readLine().split(" ");
            int r = Integer.parseInt(input[0]);
            int c = Integer.parseInt(input[1]);
            int q = Integer.parseInt(reader.readLine());
            r--;
            c--;
            String path = reader.readLine();
            int count = countCells(map, r, c, path);
            //System.out.println(count);
            writer.write(String.valueOf(count));

        }
    }

    private static int countCells(char[][] map, int r, int c, String path) {
        Set<String> visited = new HashSet<>();
        visited.add(r + "," + c);
        char direction = 'N';
        for (char move : path.toCharArray()) {
            //System.out.println(move);
            switch (move) {
                case 'L':
                    direction = turnLeft(direction);
                    break;
                case 'R':
                    direction = turnRight(direction);
                    break;
                case 'M':
                    int[] newCoords = moveForward(r, c, direction);
                    int newR = newCoords[0];
                    int newC = newCoords[1];
                    if (newR >= 0 && newR < map.length && newC >= 0 && newC < map[0].length && map[newR][newC] == '.') {
                        r = newR;
                        c = newC;
                        visited.add(r + "," + c);
                    }
                    break;
            }
        }
        //System.out.println(visited);
        return visited.size();
    }

    public static char turnLeft(char direction) {
        return switch (direction) {
            case 'N' -> 'W';
            case 'W' -> 'S';
            case 'S' -> 'E';
            case 'E' -> 'N';
            default -> direction;
        };
    }

    public static char turnRight(char direction) {
        return switch (direction) {
            case 'N' -> 'E';
            case 'E' -> 'S';
            case 'S' -> 'W';
            case 'W' -> 'N';
            default -> direction;
        };
    }

    public static int[] moveForward(int r, int c, char direction) {
        return switch (direction) {
            case 'N' -> new int[]{r - 1, c};
            case 'S' -> new int[]{r + 1, c};
            case 'E' -> new int[]{r, c + 1};
            case 'W' -> new int[]{r, c - 1};
            default -> new int[]{r, c};
        };
    }
}
