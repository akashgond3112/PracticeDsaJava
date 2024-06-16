package main.dsa.linear.LinkedList.SinglyLinkedList;

public class SearchElementInLinkedList {
    Node head;

    void push(int new_data) {

        Node node = new Node(new_data);

        node.next = head;
        head = node;

    }

    boolean search(Node head, int data) {

        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean recursiveSearch(Node head, int x)
    {
        // Base case
        if (head == null)
            return false;

        // If key is present in current node,
        // return true
        if (head.data == x)
            return true;

        // Recur for remaining list
        return search(head.next, x);
    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    // Driver code
    public static void main(String args[])
    {

        // Start with the empty list
        SearchElementInLinkedList llist = new SearchElementInLinkedList();

        /*Use push() to construct below list
        14->21->11->30->10  */
        llist.push(10);
        llist.push(30);
        llist.push(11);
        llist.push(21);
        llist.push(14);

        // Function call
        if (llist.search(llist.head, 21))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
