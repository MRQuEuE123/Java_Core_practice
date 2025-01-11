import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}


class Solution {

    private Node cloneUtil(Node node, HashMap<Node, Node> mp) {
        Node newNode = new Node(node.val);
        mp.put(node, newNode);
        for (Node neighbor : node.neighbors) {
            if (!mp.containsKey(neighbor)) {
                newNode.neighbors.add(cloneUtil(neighbor, mp));
            } else {
                newNode.neighbors.add(mp.get(neighbor));
            }
        }
        return newNode;
    }

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> mp = new HashMap<>(); // old - new
        return cloneUtil(node, mp);
    }

    public static void main(String[] args) { // Создаем тестовый граф
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3); // Клонируем граф
        Solution solution = new Solution();
        Node clonedGraph = solution.cloneGraph(node1); // Выводим результат
        System.out.println("Original Node: " + node1.val);
        System.out.println("Cloned Node: " + clonedGraph.val);
    }
}
