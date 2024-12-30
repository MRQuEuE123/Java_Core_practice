import java.io.*;
import java.util.Random;

public class RSelect {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] numbers = new int[n];
            String[] input = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(input[i]);
            }
            int k = Integer.parseInt(reader.readLine());

            int kElement = rselect(numbers, 0, numbers.length - 1, k - 1);
            writer.write(String.valueOf(kElement));
            writer.newLine();
        }
    }

    private static int rselect(int[] numbers, int left, int right, int k) {
        // Base case: if the array has only one element, return it
        if (left == right) {
            return numbers[left];
        }

        // Choose a random pivot and partition the array
        Random rand = new Random();
        int pivotIndex = left + rand.nextInt(right - left + 1);
        pivotIndex = partition(numbers, left, right, pivotIndex);

        // Determine which side to recurse on
        if (k == pivotIndex) {
            return numbers[k];
        } else if (k < pivotIndex) {
            return rselect(numbers, left, pivotIndex - 1, k);
        } else {
            return rselect(numbers, pivotIndex + 1, right, k);
        }
    }
    private static int partition(int[] numbers, int left, int right, int pivotIndex) {
        int pivotValue = numbers[pivotIndex];
        // Move pivot to the end
        swap(numbers, pivotIndex, right);
        int storeIndex = left;

        // Move all elements smaller than pivot to the left
        for (int i = left; i < right; i++) {
            if (numbers[i] < pivotValue) {
                swap(numbers, i, storeIndex);
                storeIndex++;
            }
        }

        // Move pivot to its final place
        swap(numbers, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
