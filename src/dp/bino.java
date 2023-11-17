package dp;

import java.util.ArrayList;
import java.util.List;

public class bino {
    public static void main(String[] args) {
        int n = 5;
        int k = 2;

        //brute
        long result = factorial(n) / (factorial(k) * factorial(n - k));
        System.out.println(result);

        //dp
        int[][] dp = new int[n+1][n+1];
        System.out.println(dp(n,k,dp));

        //pascal triangle properties
        System.out.println(getRow(n).get(k));
    }
    // my approach
    static List<Integer> getRow(int n) {
        List<Integer> ans = new ArrayList<>();

        //always add 1 to the list
        ans.add(1);

        // base case
        if(n == 0){
            return ans;
        }
        if(n == 1){
            ans.add(1);
            return ans;
        }

        for(int i=1; i<n; i++){
            double temp = ((double)(n-i+1)/i)*(ans.get(i-1));
            temp += 0.5;
            ans.add((int)temp);
        }

        //last element is always 1
        ans.add(1);
        return ans;
    }
    static int dp(int n, int k, int[][] dp){
        if(n == 1 || k == 0 || n == k){
            return 1;
        }
        if(dp[n][k]!=0){
            return dp[n][k];
        }

        int ans = dp(n-1, k, dp) + dp(n-1, k-1, dp);
        dp[n][k] = ans;

        return ans;
    }
    static long factorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
