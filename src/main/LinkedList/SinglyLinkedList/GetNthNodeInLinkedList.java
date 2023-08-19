package main.LinkedList.SinglyLinkedList;

public class GetNthNodeInLinkedList {

    Node head;

    public int getNthNode(GetNthNodeInLinkedList getNthNodeInLinkedList, int index) {

        Node current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                return current.data;
            }
            count++;
            current = current.next;
        }

        assert (false);
        return 0;
    }

    public void push(int new_data) {

        Node new_node = new Node(new_data);

        new_node.next = head;
        head = new_node;
    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    /* Driver code*/
    public static void main(String args[])
    {
        /* Start with the empty list */
        GetNthNodeInLinkedList  getNthNodeInLinkedList= new GetNthNodeInLinkedList();

        /* Use push() to construct below list
        1.12.1.4.1 */
        getNthNodeInLinkedList.push(1);
        getNthNodeInLinkedList.push(4);
        getNthNodeInLinkedList.push(1);
        getNthNodeInLinkedList.push(12);
        getNthNodeInLinkedList.push(1);

        /* Check the count function */

        System.out.printf("Element at index 3 is %d",
                getNthNodeInLinkedList.getNthNode(getNthNodeInLinkedList, 3));
    }
}
