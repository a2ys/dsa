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
}

class LRUCache {

    private int capacity;
    private Node HEAD, TAIL;
    private HashMap<Integer, Node> hashMap;

    private void remove(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        node.prev = null;
        node.next = null;
    }

    private void addAtEnd(Node node) {
        Node prevEnd = TAIL.prev;
        prevEnd.next = node;
        node.prev = prevEnd;
        node.next = TAIL;
        TAIL.prev = node;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.HEAD = new Node();
        this.TAIL = new Node();
        this.HEAD.next = this.TAIL;
        this.TAIL.prev = this.HEAD;
    }

    public int get(int key) {
        Node accessedNode = this.hashMap.get(key);
        if (accessedNode == null) return -1;

        remove(accessedNode);
        addAtEnd(accessedNode);

        return accessedNode.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        Node newNode = this.hashMap.get(key);
        if (newNode != null) {
            newNode.val = value;
            remove(newNode);
            addAtEnd(newNode);

            return;
        }

        if (this.hashMap.size() == capacity) {
            Node victim = this.HEAD.next;
            remove(victim);
            this.hashMap.remove(victim.key);
        }

        newNode = new Node(key, value);
        this.hashMap.put(key, newNode);
        addAtEnd(newNode);
    }
}
