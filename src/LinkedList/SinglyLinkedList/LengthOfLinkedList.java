package LinkedList.SinglyLinkedList;

public class LengthOfLinkedList {
    Node head;

    public void push(int new_data) {
        Node new_node = new Node(new_data);

        new_node.next = head;
        head = new_node;
    }

    int getCount() {

        Node temp = head;
        int new_count = 0;

        while (temp != null) {
            new_count++;
            temp = temp.next;
        }

        return new_count;
    }

    /* Returns count of nodes in linked list */
    public int getCountRec(Node node)
    {
        // Base case
        if (node == null)
            return 0;

        // Count is this node plus rest of the list
        return 1 + getCountRec(node.next);
    }

    /* Wrapper over getCountRec() */
    public int getCountNew() { return getCountRec(head); }

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public static void main(String[] args)
    {
        /* Start with the empty list */
        LengthOfLinkedList llist = new LengthOfLinkedList();
        llist.push(1);
        llist.push(3);
        llist.push(1);
        llist.push(2);
        llist.push(1);

        // Function call
        System.out.println("Count of nodes is "
                + llist.getCount());
        System.out.println("Count of nodes is "
                + llist.getCountNew());
    }


}
