package main.dsa.two_pointers.medium;

import java.util.Arrays;

/*
475. Heaters
Medium
Topics
Companies
Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.

Every house can be warmed, as long as the house is within the heater's warm radius range.

Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.

Notice that all the heaters follow your radius standard, and the warm radius will the same.

Example 1:

Input: houses = [1,2,3], heaters = [2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:

Input: houses = [1,2,3,4], heaters = [1,4]
Output: 1
Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
Example 3:

Input: houses = [1,5], heaters = [2]
Output: 3

Constraints:

1 <= houses.length, heaters.length <= 3 * 104
1 <= houses[i], heaters[i] <= 109
*/
public class Heaters {
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);  // Sort the heaters array

		int radius = 0;
		for (int house : houses) {
			int closestHeaterDistance = findClosestHeaterDistance(house, heaters);
			radius = Math.max(radius, closestHeaterDistance);
		}
		return radius;
	}

	private int findClosestHeaterDistance(int house, int[] heaters) {
		int idx = Arrays.binarySearch(heaters, house);
		if (idx >= 0) {
			return 0;  // House is at the heater position
		} else {
			// idx is negative; the insertion point is -(idx + 1)
			int insertPoint = -(idx + 1);
			int dist1 = insertPoint < heaters.length ? Math.abs(heaters[insertPoint] - house) : Integer.MAX_VALUE;
			int dist2 = insertPoint > 0 ? Math.abs(heaters[insertPoint - 1] - house) : Integer.MAX_VALUE;
			return Math.min(dist1, dist2);
		}
	}
}
