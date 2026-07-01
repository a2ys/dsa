// You have k lists of sorted integers
// in non-decreasing order. Find the
// smallest range that includes at least one number from each of the k lists.

package Heap;

import java.util.List;
import java.util.PriorityQueue;

class Node {

    int value;
    int listNumber;
    int index;

    Node(int value, int listNumber, int index) {
        this.value = value;
        this.listNumber = listNumber;
        this.index = index;
    }
}

public class SmallestRange {

    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> lists = new PriorityQueue<>((a, b) ->
            Integer.compare(a.value, b.value)
        );
        int maxValue = Integer.MIN_VALUE;
        int[] result = new int[2];

        for (int i = 0; i < nums.size(); i++) {
            Node toAdd = new Node(nums.get(i).get(0), i, 0);
            lists.offer(toAdd);
            maxValue = Math.max(maxValue, toAdd.value);
        }

        result[0] = lists.peek().value;
        result[1] = maxValue;

        while (!lists.isEmpty()) {
            Node minimumNode = lists.poll();
            int[] candidateRange = new int[] { minimumNode.value, maxValue };

            if (candidateRange[1] - candidateRange[0] < result[1] - result[0]) {
                result = candidateRange;
            } else if (
                candidateRange[1] - candidateRange[0] ==
                    result[1] - result[0] &&
                candidateRange[0] < result[0]
            ) {
                result = candidateRange;
            }

            if (
                nums.get(minimumNode.listNumber).size() == minimumNode.index + 1
            ) break;

            int listToForward = minimumNode.listNumber;
            Node nextNode = new Node(
                nums.get(listToForward).get(minimumNode.index + 1),
                listToForward,
                minimumNode.index + 1
            );
            maxValue = Math.max(maxValue, nextNode.value);
            lists.offer(nextNode);
        }

        return result;
    }
}
