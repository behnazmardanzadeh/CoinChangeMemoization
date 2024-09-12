package org.algocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {
    private final static int[] coins = new int[]{1, 5, 10, 25, 50};
    private static long[][] memoization = new long[2000000][5];

    private static int cnt;

    public static void main(String[] args) {
        Arrays.stream(memoization).forEach(amounts -> Arrays.fill(amounts, -1));
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))){

            int amount = Integer.parseInt(reader.readLine());
            long start = System.currentTimeMillis() * 1000;
            cnt = 0;
            long totalCombinations = coinChange(amount, 0);
            long end = System.currentTimeMillis() * 1000;
            long duration = end - start;
            System.out.println(totalCombinations);
            System.out.println(String.format("duration: %,d", duration));
            System.out.println("Recursion Counter: " + cnt);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static long coinChange(int amount, int coinIndex) {
        cnt++;
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