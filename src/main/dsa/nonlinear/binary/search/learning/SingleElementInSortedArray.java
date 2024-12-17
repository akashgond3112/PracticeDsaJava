package main.dsa.nonlinear.binary.search.learning;

public class SingleElementInSortedArray {

	public static int getSingleElementInSortedArrayUsingBruteForce(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		if (arr.length == 1)
			return arr[0];

		int low = 0;
		int high = arr.length - 1;
		for (int i = low; i <= high; i++) {

			if (i == 0 && arr[i] != arr[i + 1])
				return arr[i];
			else if (i == arr.length - 1 && arr[i] != arr[high])
				return arr[i];
			else {
				if (arr[i] != arr[i + 1] && arr[i] != arr[i - 1]) {
					return arr[i];
				}
			}

		}
		return -1;
	}

	public static int getSingleElementInSortedArrayUsingRecursive(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		if (arr.length == 1 || (arr[0] != arr[1]) || (arr[arr.length - 1] != arr[arr.length - 2]))
			return arr[0];

		int low = 1;
		int high = arr.length - 2;

		while (low <= high) {
			int mid = (low + high) / 2;

			if (arr[mid] != arr[mid + 1] && arr[mid] != arr[mid - 1]) {
				return arr[mid];
			}

			if (mid % 2 == 1 && arr[mid] == arr[mid - 1] || mid % 2 == 0 && arr[mid] == arr[mid + 1]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

		}
		return -1;
	}

	public static void main(String[] args) {

		int[] arr = { 1, 1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 7, 8, 8 };

		System.out.println(getSingleElementInSortedArrayUsingBruteForce(arr));
		System.out.println(getSingleElementInSortedArrayUsingRecursive(arr));
	}
}
