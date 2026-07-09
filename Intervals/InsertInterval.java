package Intervals;

import java.util.ArrayList;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> list = new ArrayList<>();
        boolean added = false;

        for (int i = 0; i < intervals.length; i++) {
            if (!added && newInterval[0] <= intervals[i][1] || newInterval[1] <= intervals[i][0]) {
                list.add(newInterval);
                added = true;
            } else {
                list.add(intervals[i]);
            }
        }

        ArrayList<int[]> mergedList = new ArrayList<>();
        int[] previous = new int[2];

        for (int[] interval : list) {
            int[] current = { interval[0], interval[1] };

            if (previous != null && previous[1] <= current[0]) {
                mergedList.remove(previous);
                int[] updated = { Math.min(previous[0], current[0]), current[1]};
                mergedList.add(updated);
            } else {
                mergedList.add(current);
            }

            previous = mergedList.get(mergedList.size() - 1);
        }

        int[][] result = new int[mergedList.size()][2];
        int curr = 0;
        for (int[] interval : mergedList) {
            result[curr++] = interval;
        }

        return result;
    }
}
