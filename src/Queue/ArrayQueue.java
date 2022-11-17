package Queue;

public class ArrayQueue<E> implements Queue<E> {
    private E[] theArray;
    private int front;
    private int back;
    private int currentSize;
    private static final int DEFAULT_CAPACITY = 20;

    private int increment(int x) {
        return (x + 1) % theArray.length;
    }

    public ArrayQueue() {
        theArray = (E[]) new Object[DEFAULT_CAPACITY];
        makeEmpty();
    }

    private void makeEmpty() {
        front = currentSize = 0;
        back = -1;
    }

    public boolean empty() {
        return currentSize == 0;
    }

    public boolean offer(E x) {
        if (currentSize == theArray.length) {
            return false;
        }
        back = increment(back);
        theArray[back] = x;
        currentSize++;
        return true;
    }

    public E peek() {
        if (empty()) {
            return null;
        }
        return theArray[front];
    }

    public int size() {
        return currentSize;
    }

    public E poll() {
        if (empty()) {
            return null;
        }
        E frontItem = theArray[front];
        front = increment(front);
        currentSize--;
        return frontItem;
    }
}