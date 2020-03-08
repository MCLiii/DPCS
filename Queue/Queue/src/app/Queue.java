package app;

public class Queue {
    private int[] data;
    private int head;
    private int tail;
	public Queue(int num){
        data=new int[num];
        head=-1;
        tail=-1;
    }
    public void inque(int num){
        if(!isFull()){
            if(isEmpty())
                head++;
            tail++;
            data[tail%data.length]=num;
        }else{
            System.out.println("full");
        }
    }
    public int deque(){
        if(!isEmpty()){
            int a = data[head%data.length];
            if(head==tail){
                head=-1;tail=-1;
            }else{
                head++;
            }
            return a;
        }
        return -1;
    }
    public boolean isEmpty(){
        return head==-1 && tail==-1;
    }
    public boolean isFull(){
        return head==(tail+1)%10;
    }
}