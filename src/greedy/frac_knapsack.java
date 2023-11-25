package greedy;

import java.util.Arrays;
import java.util.Comparator;

public class frac_knapsack {
    static class Item{
        int value;
        int weight;

        Item(int value, int weight){
            this.value = value;
            this.weight = weight;
        }
    }
    static class itemComparator implements Comparator<Item>{
        public int compare(Item a, Item b){
            return (int) ((a.value*1.0/a.weight) - (b.value*1.0/ b.weight));
        }
    }
    public static void main(String[] args) {
        //here we find the value/weight ratio of all items
        //then try to accommodate as much weight as possible
        int[] values = {100,60,120};
        int[] weights = {20,10,30};
        int W = 50;

        System.out.println(fractionalKnapSack(values,weights,W));
    }
    static double fractionalKnapSack(int[] values, int[] weights, int W){
        //copying elements into arr
        Item[] arr = new Item[values.length];
        for(int i=0; i<values.length; i++){
            arr[i] = new Item(values[i],weights[i]);
        }

        //sorting based on value-by-weight ratio
        Arrays.sort(arr, new itemComparator());

        int currWeight = 0;
        double totalAmt = 0.0;
        for (int i = 0; i < arr.length; i++) {
            if(currWeight + arr[i].weight <= W){
                //we can include entire weight
                currWeight += arr[i].weight;
                totalAmt += arr[i].value;
            }
            else{
                //can't include entire weight, so we take only a fraction of it
                int remainingWeight = W-currWeight;
                double amt = 1.0*arr[i].value*remainingWeight/arr[i].weight;
                totalAmt += amt;
                break;
            }
        }
        return totalAmt;
    }
}
