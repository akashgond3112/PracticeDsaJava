package main.dsa.linear.LinkedList.SinglyLinkedList;

public class IntroduceLinkedList {
    Node head;

    static class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    //Add a node At the front of the linked list
    public void pushFront(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    //Add a node after a given node
    public void insertAfter(Node prev_node, int new_data) {

        if (prev_node == null) {
            System.out.println("The prev_node cannot be null");
            return;
        }

        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;

    }

    public void insertBefore(Node given_node, int new_data) {
        if (head == null || given_node == null) {
            System.out.println("The given_node cannot be null or the list is empty");
            return;
        }

        Node new_node = new Node(new_data);

        // If the given node is the head node
        if (head == given_node) {
            new_node.next = head;
            head = new_node;
            return;
        }

        // Find the node just before the given node
        Node temp = head;
        while (temp != null && temp.next != given_node) {
            temp = temp.next;
        }

        // If the given node was not found in the list
        if (temp == null) {
            System.out.println("The given node is not present in the list");
            return;
        }

        // Insert the new node before the given node
        new_node.next = given_node;
        temp.next = new_node;
    }

    public void insertAtGivenPosition(int position, int element) {
        if (position <= 0) {
            System.out.println("Invalid position!");
            return;
        }

        Node newNode = new Node(element);

        if (position == 1) { // Insert at the head of the list
            newNode.next = head;
            head = newNode;
            return;
        }

        Node temp = head;
        int count = 1;

        // Traverse the list to find the node at position-1
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }

        // If position is greater than the number of nodes
        if (temp == null) {
            System.out.println("Position out of bounds");
            return;
        }

        // Insert the new node
        newNode.next = temp.next;
        temp.next = newNode;
    }

    //Add a node at the end
    public void append(int new_data) {
        Node new_node = new Node(new_data);

        if (head == null) {
            head = new Node(new_data);
            return;
        }

        new_node.next = null;

        Node last_node = head;
        while (last_node.next != null) {
            last_node = last_node.next;
        }

        last_node.next = new_node;
    }

    public void printList() {
        Node n = head;
        while (n != null) {
            System.out.println(n.data + " ");
            n = n.next;
        }
    }

    public static void main(String[] args) {
        IntroduceLinkedList introduceLinkedList = new IntroduceLinkedList();
        introduceLinkedList.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        introduceLinkedList.head.next = second;
        second.next = third;
        System.out.println("\n Create linked list is: ");
        introduceLinkedList.printList();

        IntroduceLinkedList introduceLinkedList_1 = new IntroduceLinkedList();

        introduceLinkedList_1.append(7);
        introduceLinkedList_1.pushFront(1);
        introduceLinkedList_1.pushFront(3);
        introduceLinkedList_1.append(9);
        introduceLinkedList_1.insertAfter(introduceLinkedList_1.head.next, 10);

        System.out.println("\n Create linked list is: ");
        introduceLinkedList_1.printList();


        introduceLinkedList_1.insertAtGivenPosition(2, 100);

        System.out.println("\n Create linked list is: ");
        introduceLinkedList_1.printList();


        introduceLinkedList_1.insertBefore(introduceLinkedList_1.head.next, 12);

        System.out.println("\n Create linked list is: ");
        introduceLinkedList_1.printList();
    }
}
