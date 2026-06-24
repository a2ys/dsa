import java.util.ArrayList;
import java.util.HashMap;

class Node {

    int key;
    int val;
    Node next;
    Node prev;

    Node() {}

    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }

    Node(int key, int val, Node next, Node prev) {
        this.val = val;
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

class Deque {

    ArrayList<Node> list;
    Node beginning;
    Node end;

    Deque() {
        this.list = new ArrayList<>();
        this.beginning = new Node();
        this.end = new Node();
    }

    Node insertAtBeginning(int key, int val) {
        Node nodeToInsert = new Node(key, val);
        nodeToInsert.next = this.beginning;
        this.beginning.prev = nodeToInsert;
        this.beginning = nodeToInsert;

        return this.beginning;
    }

    Node insertAtEnd(Node node) {
        node.prev = this.end;
        this.end.next = node;
        node.next = null;
        this.end = node;

        return node;
    }

    Node insertAtEnd(int key, int val) {
        Node nodeToInsert = new Node(key, val);
        nodeToInsert.prev = this.end;
        this.end.next = nodeToInsert;
        this.end = nodeToInsert;

        return this.end;
    }

    void removeBeginning() {
        Node temp = this.beginning.next;
        this.beginning = temp;
        temp.prev = null;
    }

    void removeEnd() {
        Node temp = this.end.prev;
        this.end = temp;
        temp.next = null;
    }
}

class LRUCache {

    private int capacity;
    private Deque deque;
    private HashMap<Integer, Node> hashMap;

    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.deque = new Deque();
        this.hashMap = new HashMap<>();
    }

    public int get(int key) {
        if (!hashMap.containsKey(key)) return -1;

        Node node = hashMap.get(key);
        remove(node);
        deque.insertAtEnd(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (hashMap.size() == capacity) {
            deque.removeBeginning();
            hashMap.remove(key);
            Node end = deque.insertAtEnd(key, value);
            hashMap.put(key, end);
        } else {
            Node start = deque.insertAtEnd(key, value);
            hashMap.put(key, start);
        }
    }
}
