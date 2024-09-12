package org.algocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MaxHeightRunner {
    private final static int[] coins = new int[]{2, 3, 5};
    private static long[][] memoization = new long[2000000][3];
    private static int cnt;
    private static int maxHeight;

    public static void main(String[] args) {
        Arrays.stream(memoization).forEach(amounts -> Arrays.fill(amounts, -1));
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

            int amount = Integer.parseInt(reader.readLine());
            cnt = 0;
            maxHeight = 0;
            long totalCombinations = coinChange(amount, 0, 0);
            System.out.println(String.format("\nTotal Combinations: %,d\n", totalCombinations));
            System.out.println(String.format("Recursion Counter: %,d\n", cnt));
            System.out.println("Max Height: " + maxHeight);

            System.out.print("\n    ");
            for (int i = 0; i < 3; i++){
                System.out.print(coins[i] + "  ");
            }
            System.out.println();
            for(int i = 0; i <= amount; i++) {
                System.out.print(i + "  ");
                for (int j = 0; j < 3; j++) {
                    if (memoization[i][j] >= 0 && memoization[i][j] < 10) {
                        System.out.print(" ");
                    }
                    System.out.print(memoization[i][j] + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long coinChange(int amount, int coinIndex, int height) {
        cnt++;
        if (amount == 0) {
            if (height > maxHeight) {
                maxHeight = height;
            }
            return 1;
        }
        if (amount < 0 || coinIndex >= coins.length) {
            if (height > maxHeight) {
                maxHeight = height;
            }
            return 0;
        }
        if (memoization[amount][coinIndex] != -1) {
            System.out.println("Yes! memoization[" + amount + "][" + coins[coinIndex] + "] = " + memoization[amount][coinIndex]);
            return memoization[amount][coinIndex];
        }

        memoization[amount][coinIndex] = coinChange(amount - coins[coinIndex], coinIndex, height + 1)
                + coinChange(amount, coinIndex + 1, height + 1);

        //System.out.println("memoization[" + amount + "][" + coins[coinIndex] + "] = " + memoization[amount][coinIndex]);
        return memoization[amount][coinIndex];
    }
}