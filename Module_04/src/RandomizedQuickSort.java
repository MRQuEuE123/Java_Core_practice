import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RandomizedQuickSort {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> numbers = new ArrayList<>();
            String[] input = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                numbers.add(Integer.parseInt(input[i]));
            }

            List<Integer> sortedNumbers = randomizedQuickSort(numbers);
            for (int number : sortedNumbers) {
                writer.write(number + " ");
            }
            writer.newLine();
        }
    }

    public static List<Integer> randomizedQuickSort(List<Integer> list) {
        if (list.size() <= 1) {
            return list; // тут и сортировать нечего
        }


        //int pivot = list.getLast();
        // int pivot = list.get(0);
        int pivot = list.getFirst();
        List<Integer> cSmall = new ArrayList<>();
        List<Integer> cEqual = new ArrayList<>();
        List<Integer> cLarge = new ArrayList<>();

        for (int num : list) {
            if (num < pivot) {
                cSmall.add(num); // элементы меньшие m
            } else if (num > pivot) {
                cLarge.add(num); // элементы большие m
            } else {
                cEqual.add(num); // элементы равные m
            }
        }

        List<Integer> sortedSmall = randomizedQuickSort(cSmall); // рекурсивный вызов алгоритма
        List<Integer> sortedLarge = randomizedQuickSort(cLarge); // рекурсивный вызов алгоритма

        List<Integer> sortedList = new ArrayList<>();
        sortedList.addAll(sortedSmall);
        sortedList.addAll(cEqual);
        sortedList.addAll(sortedLarge);
        return sortedList; // объединяем c_small, m и c_large в итоговый список c_sorted
    }
}

