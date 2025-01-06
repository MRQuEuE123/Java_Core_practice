import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Change {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer> denominations = new ArrayList<>();
            String[] input = reader.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                denominations.add(Integer.parseInt(input[i]));
            }
            int k = Integer.parseInt(reader.readLine());

            int[] coinsUsed = new int[k + 1];
            int[] coinCount = new int[k + 1];

            int coins = calculate_coins(denominations, k, coinCount, coinsUsed);
            System.out.println("Количество монет для суммы "+ k+" будет "+ coins);
            System.out.println("и будет состоять из :");
            printCoins(coinsUsed, k);
            System.out.println();
            System.out.println(Arrays.toString(coinsUsed));

            writer.newLine();
        }
    }

    public static int calculate_coins(List<Integer> coinValueList, int change, int[] minCoins, int[] coinsUsed) {
        for (int cents = 0; cents <= change; cents++) {
            int coinCount = cents;
            int newCoin = 1;
            for (int j : coinValueList) {
                if (j <= cents) {
                    if (minCoins[cents - j] + 1 < coinCount) {
                        coinCount = minCoins[cents - j] + 1;
                        newCoin = j;
                    }
                }
            }
            minCoins[cents] = coinCount;
            coinsUsed[cents] = newCoin;
        }
        return minCoins[change];
    }

    public static void printCoins(int[] coinsUsed, int change) {
        int coin = change;
        while (coin > 0) {
            int thisCoin = coinsUsed[coin];
            System.out.print(thisCoin+" ");
            coin = coin - thisCoin;
        }

    }
}
