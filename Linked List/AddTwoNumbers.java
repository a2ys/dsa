// You are given two non-empty linked lists
// representing two non-negative integers.
// The digits are stored in reverse order,
// and each of their nodes contains a single
// digit. Add the two numbers and return the
// sum as a linked list.

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

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode();
        ListNode mover = result;
        int carry = 0;

        while (l1 != null && l2 != null) {
            int ans = l1.val + l2.val + carry;
            carry = ans / 10;
            mover.val = ans % 10;

            if (l1.next != null || l2.next != null) {
                mover.next = new ListNode();
                mover = mover.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int ans = l1.val + carry;

            carry = ans / 10;
            mover.val = ans % 10;

            if (l1.next != null) {
                mover.next = new ListNode();
                mover = mover.next;
            }
            l1 = l1.next;
        }

        while (l2 != null) {
            int ans = l2.val + carry;

            carry = ans / 10;
            mover.val = ans % 10;

            if (l2.next != null) {
                mover.next = new ListNode();
                mover = mover.next;
            }
            l2 = l2.next;
        }

        if (carry == 1) {
            mover.next = new ListNode(carry);
        }

        return result;
    }
}
