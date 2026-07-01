// Given an array of intervals where
// intervals[i] = [starti, endi], merge
// all overlapping intervals, and return
// an array of the non-overlapping intervals
// that cover all the intervals in the input.

package Intervals;

import java.util.ArrayList;

public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        ArrayList<Integer> previous = new ArrayList<>();

        for (int[] interval : intervals) {
            ArrayList<Integer> current = new ArrayList<>();
            current.add(interval[0]);
            current.add(interval[1]);

            if (previous != null && previous.get(1) <= current.get(0)) {
                arrayList.remove(previous);
                ArrayList<Integer> updated = new ArrayList<>();
                if (current.get(0) <= previous.get(0)) {
                    updated.add(current.get(0));
                } else {
                    updated.add(previous.get(0));
                }
                updated.add(current.get(1));
            } else {
                arrayList.add(current);
            }

            previous = arrayList.get(arrayList.size() - 1);
        }

        int[][] result = new int[arrayList.size()][2];
        for (int i = 0; i < arrayList.size(); i++) {
            int[] element = new int[] {
                arrayList.get(i).get(0),
                arrayList.get(i).get(1),
            };
            result[i] = element;
        }

        return result;
    }
}
