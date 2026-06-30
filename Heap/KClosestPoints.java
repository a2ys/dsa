// Given an array of points where points[i] = [xi, yi]
// represents a point on the X-Y plane and an integer k,
// return the k closest points to the origin (0, 0).

package Heap;

import java.util.PriorityQueue;

public class KClosestPoints {

    public int[][] kClosest(int[][] points, int k) {
        int[][] result = new int[k][2];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            double aDist = Math.sqrt(Math.pow(a[0], 2) + Math.pow(a[1], 2));
            double bDist = Math.sqrt(Math.pow(b[0], 2) + Math.pow(b[1], 2));

            return Double.compare(bDist, aDist);
        });

        for (int[] point : points) {
            maxHeap.offer(point);

            if (maxHeap.size() > k) maxHeap.poll();
        }

        for (int i = 0; i < k; i++) {
            result[i] = maxHeap.poll();
        }

        return result;
    }
}
