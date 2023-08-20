package main.Arrray.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author agond
 * @Project Learning
 * @Date 20/08/2023-08-2023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class PascalTriangle {

    public List<List<Integer>> soultion1(int numRows) {
        List<List<Integer>> allrows = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < numRows; i++) {
            row.add(0, 1);
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            allrows.add(new ArrayList<Integer>(row));
        }
        return allrows;
    }

    public List<List<Integer>> soultion2(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < numRows; ++i) {
            Integer[] temp = new Integer[i + 1];
            Arrays.fill(temp, 1);
            ans.add(Arrays.asList(temp));
        }

        for (int i = 2; i < numRows; ++i)
            for (int j = 1; j < ans.get(i).size() - 1; ++j)
                ans.get(i).set(j, ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));

        return ans;
    }

    public List<List<Integer>> soultion3(int numRows) {
        // Create an array list to store the output result...
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        // Base cases...
        if (numRows <= 0) return output;
        // Create an array list to store the prev triangle value for further addition...
        ArrayList<Integer> prev = new ArrayList<Integer>();
        // Inserting for the first row & store the prev array to the output array...
        prev.add(1);
        output.add(prev);
        // For rest of the rows, Traverse all elements through a for loop...
        for (int i = 2; i <= numRows; i++) {
            // Create another array to store the current triangle value...
            ArrayList<Integer> curr = new ArrayList<Integer>();
            curr.add(1);    //first
            // Calculate for each of the next values...
            for (int j = 0; j < prev.size() - 1; j++) {
                // Inserting the addition of the prev array two values...
                curr.add(prev.get(j) + prev.get(j + 1));    //middle
            }
            // Store the number 1...
            curr.add(1);    //last
            // Store the value in the Output array...
            output.add(curr);
            // Set prev is equal to curr...
            prev = curr;
        }
        return output;      // Return the output list of pascal values...
    }
}
