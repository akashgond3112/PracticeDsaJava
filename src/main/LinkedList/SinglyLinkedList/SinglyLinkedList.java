package main.LinkedList.SinglyLinkedList;

public class SinglyLinkedList {

    static class Node{
        int data;
        Node next;
    }

    static void printList(Node n)
    {
        // Iterate till n reaches null
        while (n != null) {
            // Print the data
            System.out.print(n.data + " ");
            n = n.next;
        }
    }

    public static void main(String[] args) {
        Node head = null;
        Node second = null;
        Node third = null;

        head = new Node();
        second = new Node();
        third = new Node();

        head.data = 1;
        head.next = second;

        second.data =2;
        second.next = third;

        third.data =3;
        third.next = null;

        printList(head);
    }
}
