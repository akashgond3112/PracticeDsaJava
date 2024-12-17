package main.dsa.nonlinear.binary.search.learning;

public class FindPeakElement {

	public static int findPeakElement(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}

		if (arr.length == 1)
			return arr[0];

		if (arr[0] > arr[1])
			return arr[1];

		if (arr[arr.length - 1] < arr[arr.length - 2])
			return arr[arr.length - 1];


		int low = 1;
		int high = arr.length - 2;
		while (low <= high) {
			int mid = low + (high - low) / 2;

			// positive case
			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return arr[mid];
			} else if (arr[mid] > arr[mid - 1]) { // eliminate left
				low = mid + 1;
			} else if (arr[mid] < arr[mid + 1]) { // eliminate right
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		int[] arr = new int[] { 1, 3, 2, 7, 9 };
		System.out.println(findPeakElement(arr));
	}
}
