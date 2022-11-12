package LinkedList.SinglyLinkedList;

public class DeletionOfNodeForGivenPosition {

    Node head;

    void push(int new_Data) {
        Node new_node = new Node(new_Data);

        new_node.next = head;
        head = new_node;
    }

    void delete(int position) {

        if (head == null) {
            return;
        }

        Node temp = head;

        if (position == 0) {
            head = temp.next;
            return;
        }

        for (int i = 0; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null && temp.next == null) {
            return;
        }

        Node next = temp.next.next;

        temp.next = next;
    }

    void printList() {
        Node tNode = head;
        while (tNode != null) {
            System.out.print(tNode.data + " ");
            tNode = tNode.next;
        }
    }

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    public static void main(String[] args) {

        DeletionOfNodeForGivenPosition data = new DeletionOfNodeForGivenPosition();

        data.push(1);
        data.push(2);
        data.push(3);
        data.push(4);
        data.push(5);
        data.push(6);

        System.out.println("\n Create linked list is: ");
        data.printList();

        data.delete(1);

        System.out.println("\n Linked list after deletion is: ");
        data.printList();
    }

}
