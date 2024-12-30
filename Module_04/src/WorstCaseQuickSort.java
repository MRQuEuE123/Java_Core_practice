import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorstCaseQuickSort {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int [] sortedNumbers = generateWorstCaseArray(n);

            for (int number : sortedNumbers) {
                writer.write(number + " ");
            }
            writer.newLine();
        }
    }

    private static int[] generateWorstCaseArray(int n) {
        List<Integer> result = new ArrayList<>();
        generateWorstCaseArrayRec(1, n, result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void generateWorstCaseArrayRec(int start, int end, List<Integer> result) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        result.add(mid);
        generateWorstCaseArrayRec(start, mid - 1, result);
        generateWorstCaseArrayRec(mid + 1, end, result);
    }
}

/*
    private static int[] generateWorstCaseArray(int n) {
        return IntStream.range(0, n).map(i -> n - i).toArray();
    }
*/