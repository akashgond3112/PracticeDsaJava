package main.dsa.nonlinear.tree.binary.search.learning;

public class RotatedSortedArrayII {

	public static int search(int[] nums, int n, int target) {
		int low = 0;
		int high = n - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (nums[mid] == target) {
				return mid;
			}

			if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
				low++;
				high--;
				continue;
			}

			if (nums[low] <= nums[mid]) {

				if (nums[low] <= target && target < nums[mid]) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (nums[mid] <= target && target <= nums[high]) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		return -1;
	}


	public static void main(String[] args) {

		int[] nums = new int[] { 10, 11, 12, 13, 4, 4, 1, 2, 3, 4, 5, 6, 4, 4, 7, 8, 9 }; int n = nums.length;
		System.out.println(search(nums, n, 4));
	}
}
