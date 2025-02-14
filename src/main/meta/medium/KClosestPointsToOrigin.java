package main.meta.medium;

import java.util.PriorityQueue;

/*
 * * 973. K Closest Points to Origin Medium Topics Companies Given an array of points where
 * points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest
 * points to the origin (0, 0).
 * 
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 +
 * (y1 - y2)2).
 * 
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the
 * order that it is in).
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: points = [[1,3],[-2,2]], k = 1 Output: [[-2,2]] Explanation: The distance between (1, 3)
 * and the origin is sqrt(10). The distance between (-2, 2) and the origin is sqrt(8). Since sqrt(8)
 * < sqrt(10), (-2, 2) is closer to the origin. We only want the closest k = 1 points from the
 * origin, so the answer is just [[-2,2]]. Example 2:
 * 
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2 Output: [[3,3],[-2,4]] Explanation: The answer
 * [[-2,4],[3,3]] would also be accepted.
 * 
 * 
 * Constraints:
 * 
 * 1 <= k <= points.length <= 104 -104 <= xi, yi <= 104
 */
public class KClosestPointsToOrigin {

/**
 * K Closest Points to Origin Solutions Using Different Heap Approaches
 */
class Solutions {
    /**
     * Approach: Using Min Heap
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    class SolutionUsingMinHeap {
        public int[][] kClosest(int[][] points, int k) {
            if (points == null || points.length == 0 || k <= 0) {
                return new int[0][0];
            }
            
            // Create min heap based on distance from origin
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> 
                (a[0] * a[0] + a[1] * a[1]) - (b[0] * b[0] + b[1] * b[1])
            );
            
            // Add all points to min heap
            for (int[] point : points) {
                minHeap.offer(point);
            }
            
            // Get k closest points
            int[][] result = new int[k][2];
            for (int i = 0; i < k; i++) {
                result[i] = minHeap.poll();
            }
            
            return result;
        }
    }

    /**
     * Approach: Using Max Heap
     * Time Complexity: O(n log k)
     * Space Complexity: O(k)
     */
    class SolutionUsingMaxHeap {
        public int[][] kClosest(int[][] points, int k) {
            if (points == null || points.length == 0 || k <= 0) {
                return new int[0][0];
            }
            
            // Create max heap of size k based on distance from origin
            PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> 
                (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])
            );
            
            // Process points
            for (int[] point : points) {
                maxHeap.offer(point);
                // If heap size exceeds k, remove the farthest point
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
            
            // Get k closest points
            int[][] result = new int[k][2];
            for (int i = k - 1; i >= 0; i--) {
                result[i] = maxHeap.poll();
            }
            
            return result;
        }
    }

    /**
     * Helper method to calculate distance from origin
     * Note: We don't need to calculate actual distance (sqrt),
     * comparing squared distances is sufficient
     */
    private int getDistanceFromOrigin(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
    


}
