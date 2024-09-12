package org.algocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Runner {
    private final static int[] coins = new int[]{2, 3, 5};
    private static long[][] memoization = new long[2000000][3];

    public static void main(String[] args) {
        Arrays.stream(memoization).forEach(amounts -> Arrays.fill(amounts, -1));
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))){

            int amount = Integer.parseInt(reader.readLine());
            long totalCombinations = coinChange(amount, 0);
            System.out.println(String.format("Total Combinations: %,d\n", totalCombinations));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static long coinChange(int amount, int coinIndex) {
        if (amount == 0) {
            return 1;
        }
        if (amount < 0 || coinIndex >= coins.length) {
            return 0;
        }
        if (memoization[amount][coinIndex] != -1) {
            return memoization[amount][coinIndex];
        }
        return memoization[amount][coinIndex] = coinChange(amount - coins[coinIndex], coinIndex)
                + coinChange(amount, coinIndex + 1);
    }
}