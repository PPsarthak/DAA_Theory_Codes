package greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode>{
    int frequency;
    char data;
    HuffmanNode left, right;

    HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = null;
        right = null;
    }
    @Override
    public int compareTo(HuffmanNode hn){
        return this.frequency-hn.frequency;
    }
}


public class Huffman_Coding {
    public static void main(String[] args) {
        String text = "Huffman coding is a data compression algorithm.";
        Map<Character, Integer> freqMap = new HashMap<>();

        // Calculate frequency of each character in the input text
        for (char c : text.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        System.out.println("Frequency of characters in mssg: " + freqMap);

        // Build Huffman tree
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Merge nodes to create Huffman tree
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            HuffmanNode newNode = new HuffmanNode('$', left.frequency + right.frequency);
            newNode.left = left;
            newNode.right = right;
            queue.add(newNode);
        }

        // Print Huffman codes
        HuffmanNode root = queue.peek();
        Map<Character, String> huffmanCodes = new HashMap<>();
        generateCodes(root, "", huffmanCodes);

        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null && Character.isLetter(root.data)) {
            huffmanCodes.put(root.data, code);
        }

        generateCodes(root.left, code + "0", huffmanCodes);
        generateCodes(root.right, code + "1", huffmanCodes);
    }
}

