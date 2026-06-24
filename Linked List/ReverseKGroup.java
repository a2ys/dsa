// Given the head of a linked list, reverse
// the nodes of the list k at a time, and
// return the modified list.

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

public class ReverseKGroup {

    ListNode reverse(ListNode head) {
        ListNode curr = head,
            prev = null;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode result = dummy;

        ListNode curr = head;

        while (curr != null) {
            ListNode curHead = curr;
            ListNode curTail = null;
            int i = 0;

            while (i < k && curr != null) {
                curTail = curr;
                curr = curr.next;
                i++;
            }

            if (i < k) return result.next;

            curTail.next = null;
            ListNode newHead = reverse(curHead);
            curHead.next = curr;
            dummy.next = newHead;
            dummy = curHead;
        }

        return result.next;
    }
}
