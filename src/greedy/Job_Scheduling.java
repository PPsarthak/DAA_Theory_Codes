package greedy;

import java.util.Arrays;

public class Job_Scheduling {
    static class Job{
        int id;
        int deadline;
        int profit;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
    public static void main(String[] args) {
        int[] deadlines = {5, 12, 8, 16, 3, 18, 10, 7, 14, 6};
        int[] profits = {82, 45, 93, 67, 28, 76, 55, 39, 61, 88};

        //creating the job array
        Job[] arr = new Job[deadlines.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new Job(i+1, deadlines[i], profits[i]);
        }

        schedule(arr);
    }
    static void schedule(Job[] arr){
        //sort the arr in descending order
        Arrays.sort(arr, (a,b)->(b.profit-a.profit));

        //get the max deadline for creating our arr
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i].deadline);
        }

        //to store which job id is executed at each sec
        int ans[] = new int[max + 1];
        Arrays.fill(ans, -1);

        int count = 0;
        int totalProfit = 0;
        for (int i = 0; i < arr.length; i++) {
            //find the first empty location from the deadline
            int j = arr[i].deadline;
            while(j>0){
                if(ans[j] == -1){
                    ans[j] = arr[i].id;
                    count++;
                    totalProfit+=arr[i].profit;
                    break;
                }
                j--;
            }
        }
        System.out.println("Total profit: " + totalProfit);
        System.out.println("Total Jobs done: " + count);
        System.out.println(Arrays.toString(ans));
    }
}
