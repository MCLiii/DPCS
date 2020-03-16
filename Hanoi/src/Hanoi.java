public class Hanoi{
    public static void main(String[]args){
        hanoi(20, "a", "b", "c");
    }
    public static void hanoi(int num,String a,String b, String c){
        if(num==1)
            System.out.println("move "+a+" to "+c);
        else{
            hanoi(num-1, a, c, b);
            hanoi(1, a, b,c);
            hanoi(num-1, b,a,c);
        }
    }
}