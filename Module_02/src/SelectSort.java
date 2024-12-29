import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SelectSort {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            int[] numbers = new int[n];
            String[] input = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(input[i]);
            }

            selectSort(numbers);
            for (int number : numbers) {
                writer.write(number + " ");
            }
            writer.newLine();
        }
    }


    public static void selectSort(int[] a){
        int min_indx;
        for(int i = 0; i < a.length; i++){
            min_indx=i;
            for(int j = i+1; j < a.length; j++){
                if(a[j] < a[min_indx]){
                    min_indx=j;
                }
            }
            int temp = a[i];
            a[i] = a[min_indx];
            a[min_indx] = temp;
        }

    }
}
