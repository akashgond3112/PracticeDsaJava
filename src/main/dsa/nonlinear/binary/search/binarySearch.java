package main.dsa.nonlinear.binary.search;

public class binarySearch {

    public static int binarySearchMethod(int[] arr, int k, int low, int high) {

        int mid = 0;

        if (low > high)
            return 0;
        else
            mid = (low + high) / 2;

        if (k == arr[mid]) {
            return mid;
        } else if (k < arr[mid]) {
            return binarySearchMethod(arr, k, low, mid - 1);
        } else {
            return binarySearchMethod(arr, k, mid + 1, high);
        }
    }

    public static void main(String[] args) {

        int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
        
        System.out.println(binarySearchMethod(arr, 5, 0, arr.length));
    }

}
