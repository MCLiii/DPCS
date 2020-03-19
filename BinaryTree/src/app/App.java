package app;

public class App {
    public static void main(String[] args) throws Exception {
        Tree a=new Tree("james");
        a.add("kim");
        a.add("tim");
        System.out.println(a.search("ti"));
    }
}