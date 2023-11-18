package greedy;

import java.util.*;

public class kruskal {
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
        System.out.println("Test Case 1 MST: " + kruskalAlgo(V1, adj1));

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
        System.out.println("Test Case 2 MST: " + kruskalAlgo(V2, adj2));

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
        System.out.println("Test Case 3 MST: " + kruskalAlgo(V3, adj3));
    }
    static class KruskalPair implements Comparable<KruskalPair>{
        int node;
        int parent;
        int weight;

        KruskalPair(int node, int parent, int weight){
            this.node = node;
            this.parent = parent;
            this.weight = weight;
        }
        public int compareTo(KruskalPair kruskalPair){
            return this.weight-kruskalPair.weight;
        }
    }
    static List<List<Integer>> kruskalAlgo(int V, List<List<List<Integer>>> adj){
        List<KruskalPair> edges = new ArrayList<>();
        for(int i=0; i<V; i++){
            for (int j = 0; j < adj.get(i).size(); j++) {
                int adjNode = adj.get(i).get(j).get(0);
                int wt = adj.get(i).get(j).get(1);
                edges.add(new KruskalPair(i, adjNode, wt));
            }
        }
        DisjointSet ds = new DisjointSet(V);
        Collections.sort(edges);
        int mstWeight = 0;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            int wt = edges.get(i).weight;
            int u = edges.get(i).node;
            int v = edges.get(i).parent;
            if (ds.findUParent(u) != ds.findUParent(v)) {
                mstWeight += wt;
                ds.unionBySize(u, v);
            }
        }
        return ans;
    }
}
