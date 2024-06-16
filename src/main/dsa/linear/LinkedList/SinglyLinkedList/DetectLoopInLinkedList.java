package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.HashSet;

public class DetectLoopInLinkedList {

    static Node head;
    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    static void push(int new_data){
        Node new_node= new Node(new_data);
        new_node.next= head;
        head= new_node;
    }

    static boolean detectLoop(Node head){
        HashSet<Node> hashSet = new HashSet<Node>();
        while(hashSet != null){

            if(hashSet.contains(head)){
                return  true;
            }
            hashSet.add(head);
            head= head.next;
        }
        return false;
    }

    public static void main(String[] args) {

        DetectLoopInLinkedList detectLoopInLinkedList = new DetectLoopInLinkedList();
        detectLoopInLinkedList.push(1);
        detectLoopInLinkedList.push(2);
        detectLoopInLinkedList.push(3);
        detectLoopInLinkedList.push(4);
        detectLoopInLinkedList.push(5);

        //create a loop
        detectLoopInLinkedList.head.next.next.next.next = detectLoopInLinkedList.head;

        if(detectLoop(head)){
            System.out.println("DetectLoopInLinkedList");
        }else{
            System.out.println("No detect");
        }

    }
}
