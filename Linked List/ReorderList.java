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

public class ReorderList {

    ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head,
            fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode mid = slow;
        ListNode secondHalf = mid.next;
        secondHalf = reverseLinkedList(secondHalf);
        mid.next = null;
        ListNode firstHalf = head;

        while (firstHalf != null && secondHalf != null) {
            ListNode temp = firstHalf.next;
            firstHalf.next = secondHalf;

            ListNode temp2 = secondHalf.next;
            secondHalf.next = temp;

            firstHalf = temp;
            secondHalf = temp2;
        }
    }
}
