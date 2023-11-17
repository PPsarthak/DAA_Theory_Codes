package dp;

import java.util.Arrays;

public class obst {
    public static void main(String[] args) {
        // the element at 0-th index is just padding
        int keys[] = { 10, 12, 20 };
        int freq[] = { 34, 8, 50 };

        // it is assumed that keys are in sorted manner
        // if not, then sort keys and then according update the freq array

        int[][] dp = new int[keys.length][keys.length];
        for (int i = 0; i < keys.length; i++) dp[i][i] = freq[i];

        System.out.println(memoize(freq, dp, 0, keys.length - 1));
    }

    static int memoize(int[] freq, int[][] dp, int i, int j) {
        if (i > j) return 0;

        if (dp[i][j] != 0) return dp[i][j];

        int weight = getWeight(freq, i, j);

        int minCost = Integer.MAX_VALUE;

        // One by one consider all elements as root and recursively find dp of the BST
        // compare the dp with min and update min if needed
        for (int k = i; k <= j; k++) {
            int wt = memoize(freq, dp, i, k - 1) + memoize(freq, dp, k + 1, j) + weight;
            minCost = Math.min(wt, minCost);
        }

        return dp[i][j] = minCost;
    }

    static int getWeight(int[] freq, int i, int j) {
        int wt = 0;
        for (int k = i; k <= j; k++) {
            wt += freq[k];
        }
        return wt;
    }
}
