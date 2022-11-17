package LinkedList.SinglyLinkedList;

public class GetMiddleOfLinkedList {
    static Node head;

    void push(int new_data) {

        Node new_node = new Node(new_data);

        new_node.next = head;
        head = new_node;


    }

    void printList() {
        Node head = GetMiddleOfLinkedList.head;
        while (head != null) {
            System.out.print(head.data + "-> ");
            head = head.next;
        }
        System.out.println("null");
    }

    void printMiddle(){

        Node slow_ptr=head;
        Node fast_ptr=head;

        while (fast_ptr != null && fast_ptr.next != null) {
            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;
        }

        assert slow_ptr != null;
        System.out.println("The middle of the linked list is :" + slow_ptr.data + "\n");
    }


    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        GetMiddleOfLinkedList ll = new GetMiddleOfLinkedList();

        for(int i = 5; i > 0; i--)
        {
            ll.push( i);
            ll.printList();
            ll.printMiddle();
        }
    }


}
