package LinkedList;

public class DeletionFromLinkedList extends IntroduceLinkedList{

    DeletionFromLinkedList(){
        super();
    }

    //Delete the first occurrence of the element
    public void deleteNode(int index){
        Node temp= head, prev =null;

        if(temp != null && temp.data == index){
            head =temp.next;
            return;
        }

        while(temp != null && temp.data != index){
            prev = temp;
            temp = temp.next;
        }

        if(temp == null){
            return;
        }
        prev.next = temp.next;
    }

    public static void main(String[] args) {

        DeletionFromLinkedList d = new DeletionFromLinkedList();

        d.pushFront(7);
        d.pushFront(1);
        d.pushFront(3);
        d.pushFront(2);

        System.out.println("\nCreated Linked list is:");
        d.printList();

        d.deleteNode(1);

        System.out.println(
                "\nLinked List after Deletion of 1:");
        d.printList();

    }
}
