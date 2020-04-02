package app;

public class App {
    public static void main(String[] args) throws Exception {
        AES256 aes256=new AES256();
        Menu.entrance(aes256.login(),aes256);
    }
}