package greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class kruskal {
    public static void main(String[] args) {

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
