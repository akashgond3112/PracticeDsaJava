package main.dsa.slidingWindow.fixedSize;

import java.util.*;

public class SlidingWindowMaximum {

    public static int[] solution(final List<Integer> arr, int k) {
        int numWindows = arr.size() - k + 1;
        int[] result = new int[numWindows];
        if (arr.size() <= 0 || k == 0) return result;

        int startIndex = 0;
        int endIndex = 0;
        Queue<Integer> trackMaxNumber = new LinkedList<>();

        while (endIndex < arr.size()) {
            //calculation
            while (!trackMaxNumber.isEmpty() && trackMaxNumber.peek() < arr.get(endIndex)) {
                trackMaxNumber.poll();
            }
            trackMaxNumber.add(arr.get(endIndex));

            // increment indexes
            if (endIndex - startIndex + 1 < k) {
                endIndex++;
            } else if (endIndex - startIndex + 1 == k) {

                // calculate answer
                result[startIndex] = trackMaxNumber.peek();
                if(Objects.equals(arr.get(startIndex), trackMaxNumber.peek())){
                    trackMaxNumber.poll();
                }

                // increment indexes
                startIndex++;
                endIndex++;
            }

        }
        // return result
        return result;
    }

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(3);
        input.add(-1);
        input.add(-1);
        input.add(5);
        input.add(3);
        input.add(6);
        input.add(7);
        int k = 3;
        System.out.println(Arrays.toString(solution(input, k)));
    }

}
