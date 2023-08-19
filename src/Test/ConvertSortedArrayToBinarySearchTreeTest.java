package Test;

import main.Arrray.easy.ConvertSortedArrayToBinarySearchTree;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author agond
 * @Project Learning
 * @Date 19/08/2023-08-2023
 * Copyright (C) 2023 Newcastle University, UK
 */
public class ConvertSortedArrayToBinarySearchTreeTest {

    @Test
    public void testSortedArrayToBST() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(nums);
    }
}