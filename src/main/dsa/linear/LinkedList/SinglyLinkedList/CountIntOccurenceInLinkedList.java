package main.dsa.linear.LinkedList.SinglyLinkedList;

import java.util.*;

public class CountIntOccurenceInLinkedList {

    public static List<List<Integer>> groupSort(List<Integer> arr) {
        // Write your code here
        HashMap<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>((e1, e2) -> e2.getValue().equals(e1.getValue()) ? e1.getKey()-e2.getKey() : e2.getValue()-e1.getValue());

        List<List<Integer>> res = new ArrayList<>();

        for(int num : arr){
            map.putIfAbsent(num, 0);
            map.put(num,map.get(num)+1);
        }

        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            heap.add(entry);
        }

        while(!heap.isEmpty()){
            Map.Entry<Integer,Integer> entry = heap.poll();
            List<Integer> list= new ArrayList<>();
            list.add(entry.getKey());
            list.add(entry.getValue());
            res.add(list);
        }

        return (List<List<Integer>>) res;

    }
    Node head;
    class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    void push(int new_data){

        Node new_node= new Node(new_data);
        new_node.next= head;
        head= new_node;
    }

    int countSearch(int data){

        Node current= head;
        int count= 0;
        while( current != null){
            if( current.data == data){
                count++;
            }
            current= current.next;
        }

        return count;
    }

    public static void main(String args[])
    {
        CountIntOccurenceInLinkedList llist = new CountIntOccurenceInLinkedList();

        /* Use push() to construct below list
          1->2->1->3->1  */
        llist.push(1);
        llist.push(2);
        llist.push(1);
        llist.push(3);
        llist.push(1);

        /*Checking count function*/
        System.out.println("Count of 1 is " + llist.countSearch(1));
    }
}
