// A linked list of length n is given such that
// each node contains an additional random pointer,
// which could point to any node in the list, or null.
// Construct a deep copy of the list. The deep copy
// should consist of exactly n brand new nodes,
// where each new node has its value set to the
// value of its corresponding original node.
// Both the next and random pointer of the new
// nodes should point to new nodes in the copied
// list such that the pointers in the original
// list and copied list represent the same list
// state. None of the pointers in the new list
// should point to nodes in the original list.

class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class CopyRandomPointer {

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node curr = head;

        while (curr != null) {
            Node newNode = new Node(curr.val);
            Node temp = curr.next;
            curr.next = newNode;
            newNode.next = temp;
            curr = temp;
        }

        curr = head;

        while (curr != null) {
            if (curr.random != null) curr.next.random = curr.random.next;
            else curr.next.random = curr.random;

            curr = curr.next.next;
        }

        curr = head;
        Node solution = head.next;
        Node temp = solution;

        while (temp != null && temp.next != null) {
            temp = curr.next;
            curr.next = curr.next.next;
            temp.next = temp.next.next;

            curr = curr.next;
            temp = temp.next;
        }

        curr.next = curr.next.next;
        temp.next = null;

        return solution;
    }
}
