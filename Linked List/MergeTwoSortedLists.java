// You are given the heads of two
// sorted linked lists list1 and list2.
// Merge the two lists into one sorted
// list. The list should be made by splicing
// together the nodes of the first two lists.

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

public class MergeTwoSortedLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode merged = new ListNode();
        ListNode temp = merged;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                temp.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                temp.next = new ListNode(list2.val);
                list2 = list2.next;
            }

            temp = temp.next;
        }

        if (list1 != null) {
            temp.next = list1;
        }

        if (list2 != null) {
            temp.next = list2;
        }

        return merged.next;
    }
}
