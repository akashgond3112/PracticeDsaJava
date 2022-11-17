package Queue;

public interface Queue<E>
{
    public boolean empty();
    public int size();
    public boolean offer(E x);
    public E peek(); // returns null if queue empty
    public E poll(); // returns null if queue empty
}