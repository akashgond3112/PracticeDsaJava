package LinkedList;

import org.w3c.dom.Node;

public class IntroduceLinkedList {
    Node head;
    static class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }

    //Add a node At the front of the linked list
    public void pushFront(int new_data){
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    //Add a node after a given node
    public void insertAfter(Node prev_node,int new_data){

        if(prev_node == null){
            System.out.println("The prev_node cannot be null");
            return;
        }

        Node new_node = new Node(new_data);
        new_node.next = prev_node.next;
        prev_node.next = new_node;

    }

    //Add a node at the end
    public void append(int new_data){
        Node new_node = new Node(new_data);

        if(head == null){
            head = new Node(new_data);
            return;
        }

        new_node.next = null;

        Node last_node = head;
        while(last_node.next != null){
            last_node= last_node.next;
        }

        last_node.next = new_node;
        return;
    }

    public void printList(){
        Node n = head;
        while(n != null){
            System.out.println(n.data + " " );
            n= n.next;
        }
    }

    public static void main(String[] args) {
        IntroduceLinkedList introduceLinkedList = new IntroduceLinkedList();
        introduceLinkedList.head = new Node(1);
        Node second = new Node(2);
        Node third = new Node(3);

        introduceLinkedList.head.next = second;
        second.next=third;
        introduceLinkedList.printList();

        IntroduceLinkedList introduceLinkedList_1 = new IntroduceLinkedList();

        introduceLinkedList_1.append(7);
        introduceLinkedList_1.pushFront(1);
        introduceLinkedList_1.pushFront(3);
        introduceLinkedList_1.append(9);
        introduceLinkedList_1.insertAfter(introduceLinkedList_1.head.next,10);

        introduceLinkedList_1.printList();
    }
}
