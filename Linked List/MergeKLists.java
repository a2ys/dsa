// You are given an array of k linked-lists
// lists, each linked-list is sorted in ascending
// order.
// Merge all the linked-lists into one sorted
// linked-list and return it.

import java.util.PriorityQueue;

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

public class MergeKLists {

    PriorityQueue<ListNode> heap = new PriorityQueue<>((node1, node2) ->
        Integer.compare(node1.val, node2.val)
    );

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode();
        ListNode curr = result;

        for (ListNode node : lists) {
            if (node != null) heap.offer(node);
        }

        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) heap.offer(node.next);
        }

        return result.next;
    }
}
