I'll create detailed **README cheat sheets** for **Arrays, Queue, Stack, and Linked List** with problem-solving techniques, worst-case **time & space complexity**, and revision-friendly content. These will be structured to help with quick **interview revision**.

---

# **📌 README: Arrays in Java**
## **What is an Array?**
An **array** is a contiguous block of memory that stores elements of the same data type. It supports **random access** using an index.

## **Advantages**
- **Fast access (O(1))** due to direct indexing.
- **Efficient memory allocation**.

## **Disadvantages**
- **Fixed size** (cannot dynamically resize).
- **Expensive insertion & deletion** in the middle.

## **Key Operations & Time Complexity**
| Operation | Technique Used | Time Complexity | Space Complexity |
|-----------|--------------|----------------|----------------|
| **Access** | Direct Indexing | **O(1)** | O(1) |
| **Search** | Linear Search / Binary Search | **O(n) / O(log n)** | O(1) |
| **Insertion** | Shift elements if needed | **O(n)** | O(1) |
| **Deletion** | Shift elements after removal | **O(n)** | O(1) |
| **Sorting** | Merge Sort / Quick Sort | **O(n log n)** | O(n) / O(log n) |

## **Implementation**
```java
// Declaration
int[] arr = {10, 20, 30, 40, 50};

// Accessing elements
System.out.println(arr[2]); // O(1)

// Searching (Linear Search)
int search(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) return i; // O(n)
    }
    return -1;
}

// Sorting an array (Merge Sort - O(n log n))
import java.util.Arrays;
Arrays.sort(arr);
```