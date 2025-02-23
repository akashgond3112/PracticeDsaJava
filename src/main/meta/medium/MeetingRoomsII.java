package main.meta.medium;

import java.util.Arrays;

/**
 * Meeting Rooms II
 * Difficulty: MediumAccuracy: 43.05%Submissions: 2K+Points: 4
 * Given two arrays start[] and end[] such that start[i] is the starting time of ith meeting and end[i] is the ending time of ith meeting. Return the minimum number of rooms required to attend all meetings.
 *
 * Examples:
 *
 * Input: start[] = [1, 10, 7], end[] = [4, 15, 10]
 * Output: 1
 * Explanation: Since all the meetings are held at different times, it is possible to attend all the meetings in a single room.
 * Input: start[] = [2, 9, 6], end[] = [4, 12, 10]
 * Output: 2
 * Explanation: 1st and 2nd meetings at one room but for 3rd meeting one another room required.
 * Constraints:
 * 1 ≤ start.size() ≤ 105
 * 0 ≤ start[i] < end[i] ≤ 106*/

public class MeetingRoomsII {

	static class SolutionUsingSorting {
		public int minMeetingRooms(int[] start, int[] end) {
			Arrays.sort(start);
			Arrays.sort(end);

			int rooms = 0, maxRooms = 0;
			int i = 0, j = 0;
			int n = start.length;

			while (i < n) {
				if (start[i] < end[j]) {
					// A new meeting starts before the previous one ends
					rooms++;
					maxRooms = Math.max(maxRooms, rooms);
					i++;
				} else {
					// A meeting ends
					rooms--;
					j++;
				}
			}
			return maxRooms;
		}
	}

}
