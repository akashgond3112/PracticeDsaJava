package main.meta.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * * 1570. Dot Product of Two Sparse Vectors Description Given two sparse vectors, compute their dot
 * product.
 * 
 * Implement class SparseVector:
 * 
 * SparseVector(nums) Initializes the object with the vector nums dotProduct(vec) Compute the dot
 * product between the instance of SparseVector and vec A sparse vector is a vector that has mostly
 * zero values, you should store the sparse vector efficiently and compute the dot product between
 * two SparseVector.
 * 
 * Follow up: What if only one of the vectors is sparse?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0] Output: 8 Explanation: v1 = SparseVector(nums1) ,
 * v2 = SparseVector(nums2) v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8 Example 2:
 * 
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2] Output: 0 Explanation: v1 = SparseVector(nums1) ,
 * v2 = SparseVector(nums2) v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0 Example 3:
 * 
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4] Output: 6
 * 
 * 
 * Constraints:
 * 
 * n == nums1.length == nums2.length 1 <= n <= 10^5 0 <= nums1[i], nums2[i] <= 100
 */
public class DotProductOfTwoSparseVectors {


    /**
     * Approach: HashMap Time Complexity: O(n) Space Complexity: O(n)
     */

    class SparseVector {
        public Map<Integer, Integer> d = new HashMap<>(128);

        SparseVector(int[] nums) {
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0) {
                    d.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            var a = d;
            var b = vec.d;
            if (b.size() < a.size()) {
                var t = a;
                a = b;
                b = t;
            }
            int ans = 0;
            for (var e : a.entrySet()) {
                int i = e.getKey();
                int v = e.getValue();
                ans += v * b.getOrDefault(i, 0);
            }
            return ans;
        }
    }

    /**
     * Optimal approach using tuples to store non-zero values For sparse vectors, we only store
     * non-zero elements to save space
     * 
     */
    class SparseVectorOptimal {
        // List of tuples storing (index, value) pairs for non-zero values
        private List<Pair<Integer, Integer>> nonZeros;

        SparseVectorOptimal(int[] nums) {
            nonZeros = new ArrayList<>();

            // Store only non-zero values with their indices
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nonZeros.add(new Pair<>(i, nums[i]));
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorOptimal vec) {
            int result = 0;
            int p1 = 0, p2 = 0;

            // Two pointer approach to iterate through non-zero elements
            while (p1 < nonZeros.size() && p2 < vec.nonZeros.size()) {
                Pair<Integer, Integer> pair1 = nonZeros.get(p1);
                Pair<Integer, Integer> pair2 = vec.nonZeros.get(p2);

                if (pair1.getKey().equals(pair2.getKey())) {
                    // Matching indices - multiply values and add to result
                    result += pair1.getValue() * pair2.getValue();
                    p1++;
                    p2++;
                } else if (pair1.getKey() < pair2.getKey()) {
                    // Move pointer of vector with smaller index
                    p1++;
                } else {
                    p2++;
                }
            }

            return result;
        }

        /**
         * Inner class for storing index-value pairs If not using Java's built-in Pair class
         */
        private static class Pair<K, V> {
            private final K key;
            private final V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }
    }

}
