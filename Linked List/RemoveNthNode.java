// Given the head of a linked list, remove the
// nth node from the end of the list and return
// its head.

class ListNode {

    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class RemoveNthNode {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode();
        temp.next = head;

        ListNode ahead = temp,
            behind = temp;

        for (int i = 0; i < n + 1; i++) {
            ahead = ahead.next;
        }

        while (ahead != null) {
            ahead = ahead.next;
            behind = behind.next;
        }

        behind.next = behind.next.next;

        return temp.next;
    }
}
