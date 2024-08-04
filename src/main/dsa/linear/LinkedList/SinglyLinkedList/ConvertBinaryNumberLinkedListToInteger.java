package main.dsa.linear.LinkedList.SinglyLinkedList;


public class ConvertBinaryNumberLinkedListToInteger {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    Node head;

    public void push(int new_data) {

        Node new_node = new Node(new_data);

        new_node.next = head;
        head = new_node;
    }

    public int getDecimalValue(Node convertBinaryNumberLinkedListToInteger) {

        Node current = convertBinaryNumberLinkedListToInteger;
        int result = 0;
        int counter = 0;

        while (current.next != null) {

            System.out.println(current.data);

            result += (int) (current.data * (Math.pow(2, counter)));
//
            current = current.next;
            counter++;
        }

        return result;
    }

    public static void main(String[] args) {
        /* Start with the empty list */
        ConvertBinaryNumberLinkedListToInteger convertBinaryNumberLinkedListToInteger = new ConvertBinaryNumberLinkedListToInteger();

        int[] data = new int[]{1,0,0,1,0,0,1,1,1,0,0,0,0,0,0};

        for(int i : data){
            convertBinaryNumberLinkedListToInteger.push(i);
        }
        /* Use push() to construct below list
        1.12.1.4.1 */

        /* Check the count function */

        System.out.println(convertBinaryNumberLinkedListToInteger.getDecimalValue(convertBinaryNumberLinkedListToInteger.head));
    }
}
