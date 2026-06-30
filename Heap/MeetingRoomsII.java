// Given two arrays start[] and end[] such
// that start[i] is the starting time of ith
// meeting and end[i] is the ending time of
// ith meeting. Return the minimum number
// of rooms required to attend all meetings.

package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MeetingRoomsII {

    public int minMeetingRooms(int[] start, int[] end) {
        int[][] intervals = new int[start.length][2];
        int numberOfRooms = 0;
        for (int i = 0; i < start.length; i++) {
            intervals[i][0] = start[i];
            intervals[i][1] = end[i];
        }

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int[] interval : intervals) {
            int startTime = interval[0];
            int endTime = interval[1];

            if (!minHeap.isEmpty() && minHeap.peek() <= startTime) {
                minHeap.poll();
            }
            minHeap.offer(endTime);

            numberOfRooms = Math.max(numberOfRooms, minHeap.size());
        }

        return numberOfRooms;
    }
}
