package main.dsa.linear.Queue;

public class TestQueue {
    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayQueue<Integer>();
        Integer x, y;
        x = 4;
        y = 5;
        queue.offer(x - 1);
        queue.offer(9);
        queue.offer(y % 2);
        y = queue.peek();
        queue.poll();
        queue.offer(x * 2);
        queue.offer(y + 3);
        queue.offer(x);
        System.out.println("Queue<Integer> Element");

        while (!queue.empty()) {
            System.out.println(queue.poll() + "");
        }
    }
}