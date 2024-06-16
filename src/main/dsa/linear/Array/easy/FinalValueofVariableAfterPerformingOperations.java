package main.dsa.linear.Array.easy;

public class FinalValueofVariableAfterPerformingOperations {

    public static int finalValueAfterOperations(String[] operations) {
        int x = 0;

        for (int i = 0; i < operations.length; i++) {

            if (operations[i].charAt(0) == '+') {
                ++x;
            } else if (operations[i].charAt(0) == '-') {
                --x;
            } else if (operations[i].charAt(operations[i].length()-1) == '+') {
                x++;
            } else if (operations[i].charAt(operations[i].length()-1) == '-') {
                x--;
            }
        }
        return x;

    }

    public static void main(String[] args) {

        int result = finalValueAfterOperations(new String[] { "--X", "X++", "X++" });
        System.out.println(result);
    }

}
