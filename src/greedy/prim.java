package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class prim {
    public static void main(String[] args) {
        /*
        THE FOLLOWING TEST CASES ARE GENERATED USING CHAT-GPT
        */

        // Test Case 1
        int V1 = 5;
        List<List<List<Integer>>> adj1 = new ArrayList<>(V1);
        for (int i = 0; i < V1; i++) {
            adj1.add(new ArrayList<>());
        }
        // Add edges for Test Case 1
        adj1.get(0).add(Arrays.asList(1, 2));
        adj1.get(0).add(Arrays.asList(2, 3));
        adj1.get(1).add(Arrays.asList(0, 2));
        adj1.get(1).add(Arrays.asList(2, 5));
        adj1.get(2).add(Arrays.asList(0, 3));
        adj1.get(2).add(Arrays.asList(1, 5));
        adj1.get(2).add(Arrays.asList(3, 1));
        adj1.get(3).add(Arrays.asList(2, 1));
        adj1.get(3).add(Arrays.asList(4, 4));
        adj1.get(4).add(Arrays.asList(3, 4));
        System.out.println("Test Case 1 MST: " + primAlgo(V1, adj1));

        // Test Case 2
        int V2 = 4;
        List<List<List<Integer>>> adj2 = new ArrayList<>(V2);
        for (int i = 0; i < V2; i++) {
            adj2.add(new ArrayList<>());
        }
        // Add edges for Test Case 2
        adj2.get(0).add(Arrays.asList(1, 1));
        adj2.get(0).add(Arrays.asList(2, 3));
        adj2.get(1).add(Arrays.asList(0, 1));
        adj2.get(1).add(Arrays.asList(2, 1));
        adj2.get(2).add(Arrays.asList(0, 3));
        adj2.get(2).add(Arrays.asList(1, 1));
        adj2.get(2).add(Arrays.asList(3, 4));
        adj2.get(3).add(Arrays.asList(2, 4));
        System.out.println("Test Case 2 MST: " + primAlgo(V2, adj2));

        // Test Case 3
        int V3 = 6;
        List<List<List<Integer>>> adj3 = new ArrayList<>(V3);
        for (int i = 0; i < V3; i++) {
            adj3.add(new ArrayList<>());
        }
        // Add edges for Test Case 3
        adj3.get(0).add(Arrays.asList(1, 1));
        adj3.get(0).add(Arrays.asList(2, 3));
        adj3.get(1).add(Arrays.asList(0, 1));
        adj3.get(1).add(Arrays.asList(2, 2));
        adj3.get(2).add(Arrays.asList(0, 3));
        adj3.get(2).add(Arrays.asList(1, 2));
        adj3.get(2).add(Arrays.asList(3, 1));
        adj3.get(3).add(Arrays.asList(2, 1));
        adj3.get(3).add(Arrays.asList(4, 2));
        adj3.get(4).add(Arrays.asList(3, 2));
        adj3.get(4).add(Arrays.asList(5, 3));
        adj3.get(5).add(Arrays.asList(4, 3));
        System.out.println("Test Case 3 MST: " + primAlgo(V3, adj3));
    }
    static List<List<Integer>> primAlgo(int V, List<List<List<Integer>>> adj){
        PriorityQueue<PrimPair> pq = new PriorityQueue<PrimPair>((x, y)->x.weight - y.weight);
        boolean[] visited = new boolean[V];
        List<List<Integer>> mst = new ArrayList<>();
        //assuming src = 0
        pq.offer(new PrimPair(0,0,-1));
        int sum = 0;
        while(!pq.isEmpty()){
            //if you do this then the space to store the object is reduced
            //no you are just using space to store 2 int
            //on the other hand, you would use Object + 2 int if you had polled first
            int node = pq.peek().node;
            int weight = pq.peek().weight;
            int parent = pq.peek().parent;
            pq.poll();

            if(!visited[node]){
                visited[node] = true;
                //only if current node is not the start node bcoz we don't want to add source node immediately
                if(parent!=-1){
                    List<Integer> edge = new ArrayList<>();
                    edge.add(node);
                    edge.add(parent);
                    mst.add(edge);
                    sum += weight;
                }
                //check for the neighbours of current node
                for(int i=0; i<adj.get(node).size(); i++){
                    int adjNode = adj.get(node).get(i).get(0);
                    int edgeWt = adj.get(node).get(i).get(1);
                    //only if adjacent nodes are not visited, add them to PQ, otherwise leave it
                    if(!visited[adjNode]) pq.offer(new PrimPair(adjNode, edgeWt, node));
                }
            }

        }
//        System.out.println("The sum of MST is: " + sum);
        return mst;
    }
    static class PrimPair{
        int node;
        int weight;
        int parent;

        public PrimPair(int node, int weight, int parent) {
            this.node = node;
            this.weight = weight;
            this.parent = parent;
        }
    }
}
