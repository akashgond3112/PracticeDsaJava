package main.dsa.two_pointers.medium;

import static java.lang.System.out;

/*
825. Friends Of Appropriate Ages
Medium, Topics, Companies

There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.
A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:

age[y] <= 0.5 * age[x] + 7
age[y] > age[x]
age[y] > 100 && age[x] < 100
Otherwise, x will send a friend request to y.

Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.

Return the total number of friend requests made.

Example 1:
Input: ages = [16,16]
Output: 2
Explanation: 2 people friend request each other.

Example 2:
Input: ages = [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.

Example 3:
Input: ages = [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

Constraints:
n == ages.length
1 <= n <= 2 * 104
1 <= ages[i] <= 120
*/
public class FriendsOfAppropriateAges {

	public static int numFriendRequests(int[] ages) {

		int res = 0;
		int[] ageCount = new int[121];
		for (int age : ages) {
			ageCount[age]++;
		}

		for (int ageA = 1; ageA <= 120; ageA++) {
			if (ageCount[ageA] == 0) continue;
			int ageB = 1;
			do {
				if (ageCount[ageB] == 0) {
					ageB++;
					continue;
				}

				if (ageB <= 0.5 * ageA + 7 || ageB > ageA) {
					ageB++;
					continue;
				}

				res += ageCount[ageA] * ageCount[ageB];

				if (ageA == ageB) {
					res -= ageCount[ageA];
				}
				ageB++;
			} while (ageB <= 120);
		}

		return res;
	}

	public static void main(String[] args) {

		int[] nums3 = { 16, 17, 18 };
		out.println("Output: " + numFriendRequests(nums3)); // Output: 15
	}
}
