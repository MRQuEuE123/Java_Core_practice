import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MinOperations {
    public static int minOperations(int X, int Y) {
        //Queue<int[]> queue = new LinkedList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Set<Integer> visited = new HashSet<>();

        queue.add(new int[]{X, 0});
        visited.add(X);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int value = current[0];
            int operations = current[1];

            if (value == Y) {
                return operations;
            }

            for (int digit = 0; digit <= 9; digit++) {
                int[] newValues = {value + digit, value - digit, value * digit};

                for (int newValue : newValues) {
                    if (newValue >= 0 && newValue <= 100000 &&!visited.contains(newValue)) {
                        visited.add(newValue);
                        queue.add(new int[]{newValue, operations + 1});
                    }
                }
            }
        }

        return -1; // Если невозможно достичь Y
    }

    public static void main(String[] args) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String[] input = reader.readLine().split(" ");
            int X = Integer.parseInt(input[0]);
            int Y  = Integer.parseInt(input[1]);

            System.out.println(minOperations(X, Y));
        }
    }
}
