package main.dsa.slidingWindow.fixedSize;

public class FindTheKBeautyOfANumber {

    public static int divisorSubstrings(int num, int k) {

        String result = "";
        int startIndex = 0;
        int endIndex = 0;
        int beautyNumber = 0;

        while (endIndex < Integer.toString(num).length()) {

            result = result + Integer.parseInt(String.valueOf(Integer.toString(num).charAt(endIndex)));

            if (endIndex - startIndex + 1 == k) {
                if (Integer.parseInt(result) != 0 && num % Integer.parseInt(result) == 0) {
                    beautyNumber++;
                }
                result = result.substring(1);
                startIndex++;
            }
            endIndex++;

        }

        return beautyNumber;

    }

    public static void main(String[] args) {
        int num = 430043, k = 2;
        System.out.println(divisorSubstrings(num, k));
    }

}
