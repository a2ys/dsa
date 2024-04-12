class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public void insertAtBeginning(int val) {
        Node newNode =  new Node(val);
        newNode.next = head;
        head = newNode;
    }

    public void insertAtEnd(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void delete(int val) {
        if (this.head == null) return;

        if (head.val == val) {
            head = head.next;
            return;
        }

        Node previous = head;
        Node current = head.next;

        while (current != null) {
            if (current.val == val) {
                previous.next = current.next;
                return;
            }
            previous = current;
            current = current.next;
        }
    }

    public void display() {
        Node current = head;

        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
