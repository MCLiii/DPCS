package app;

public class App {
    public static void main(String[] args){
        Queue queue=new Queue(10);
        queue.inque(1);
        queue.inque(2);
        queue.inque(3);
        System.out.println(queue.deque());
        System.out.println(queue.deque());
        System.out.println(queue.deque());
    }
}