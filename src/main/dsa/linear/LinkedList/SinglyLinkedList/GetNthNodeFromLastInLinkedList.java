package main.dsa.linear.LinkedList.SinglyLinkedList;

public class GetNthNodeFromLastInLinkedList {
    Node head;

    void getNthNodeFromLastInLinkedList(int data) {

        int length = 0;
        Node current = head;

        while (current != null) {
            current = current.next;
            length++;
        }

        if (length < data) {
            return;
        }

        current = head;

        for (int i = 1; i < length - data; i++) {
            current = current.next;
        }

        System.out.println(current.data);

    }

    void printNthFromLast(Node head, int N)
    {
        int i = 0;

        if (head == null)
            return;
        printNthFromLast(head.next, N);

        if (++i == N)
            System.out.print(head.data);
    }

    void push(int new_data) {

        Node new_node = new Node(new_data);

        new_node.next = head;
        head = new_node;
    }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args)
    {
        GetNthNodeFromLastInLinkedList llist = new GetNthNodeFromLastInLinkedList();
        llist.push(20);
        llist.push(4);
        llist.push(15);
        llist.push(35);

        // Function call
        llist.getNthNodeFromLastInLinkedList(2);
        llist.printNthFromLast(llist.head, 2);
    }
}
