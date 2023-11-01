package main.slidingWindow.medium;

/**
 * @author agond
 * @package main.slidingWindow.medium
 * @Date 01/11/2023
 * @Project PracticeDSA
 */
public class ChessPawnMovement {

    public static String findEndPosition(String startPosition, int rows, int columns) {
        char startColumn = startPosition.charAt(1);
        int startRow = Character.getNumericValue(startPosition.charAt(0));

        int newRow = (startRow + rows - 1) % 8 + 1;
        char newColumn = (char)('a' + ((startColumn - 'a' + columns) % 8));

        return newRow + "" + newColumn;
    }

    public static void main(String[] args) {
        String startPosition = "5h";
        int rows = 11;
        int columns = 25;
        String endPosition = findEndPosition(startPosition, rows, columns);
        System.out.println(endPosition);
    }


}
