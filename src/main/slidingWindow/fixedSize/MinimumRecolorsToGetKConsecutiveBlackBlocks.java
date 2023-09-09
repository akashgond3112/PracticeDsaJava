package main.slidingWindow.fixedSize;

public class MinimumRecolorsToGetKConsecutiveBlackBlocks {
    public static int minimumRecolors(String blocks, int k) {
        int result = 0;
        int startIndex = 0;
        int endIndex = 0;
        int min = Integer.MAX_VALUE;

        while (endIndex < blocks.length()) {

            if (blocks.charAt(endIndex) == 'W') {
                result++;
            }

            if (endIndex - startIndex + 1 < k) {
                endIndex++;
            }else if (endIndex - startIndex + 1 == k) {
                min = Math.min(min, result);
                if (blocks.charAt(startIndex) == 'W') {
                    result--;
                }
                startIndex++;
                endIndex++;
            }

        }

        return min;
    }

    public static void main(String[] args) {
        String blocks = "WBBWWBBWBW";
        int k = 7;
        System.out.println(minimumRecolors(blocks, k));
    }

}
