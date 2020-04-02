package app;

import java.io.Console;
import java.io.File;
import java.io.RandomAccessFile;
import java.security.SecureRandom;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//登陆及文件加密
public class AES256 {
    private RandomAccessFile encrypted;
    private RandomAccessFile plainText;
    private String password;
    private String username;
    private Scanner scan;
    private Thread shutThread;

    // instance field
    public AES256() {
        armShutDownHook();
    }

    // 登陆菜单
    public RandomAccessFile login() throws Exception {
        scan = new Scanner(System.in);
        int choice = 0;
        while (choice != 1 && choice != 2) {
            System.out.println("1)Login\n2)Register");
            System.out.print("Select:");
            choice = scan.nextInt();
        }
        if (choice == 2) {
            newEncrypt();
            return plainText;
        } else {
            decryption();
            return plainText;
        }
    }

    // 新建用户
    public void newEncrypt() throws Exception {
        Console console=System.console();
        do {
            username = console.readLine("\nUsername: ");
        } while (new File(username + ".aes").exists());
        boolean flag = true;
        while (flag) {
            password = new String(console.readPassword("password: "));
            String confirm = new String(console.readPassword("confirm password: "));
            if (!confirm.equals(password)) {
                System.out.println("Different password");
            } else
                flag = false;
        }
        plainText = new RandomAccessFile(username + ".dat", "rw");
        encrypted = new RandomAccessFile(username + ".aes", "rw");
    }

    // dat存为aes
    public void save() throws Exception {
        plainText.seek(0);
        byte[] temp = new byte[(int) plainText.length()];
        plainText.read(temp);
        encrypted.setLength(0);
        encrypted.write(encrypt(temp, password.getBytes()));
    }

    // 删除明文
    public void del() {
        File del = new File(username + ".dat");
        del.deleteOnExit();
    }

    // 用户解密
    public void decryption() throws Exception {
        do {
            System.out.print("\nusename: ");
            username = scan.next();
        } while (!new File(username + ".aes").exists());
        Console console=System.console();
        password = new String(console.readPassword("password: "));
        encrypted = new RandomAccessFile(username + ".aes", "rw");
        byte[] temp = new byte[(int) encrypted.length()];
        encrypted.read(temp);
        byte[] plain = decrypt(temp, password.getBytes());
        plainText = new RandomAccessFile(username + ".dat", "rw");
        plainText.write(plain);
    }

    // 生成密钥
    private SecretKey generateKey(byte[] key) throws Exception {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key);
        KeyGenerator gen = KeyGenerator.getInstance("AES");
        gen.init(256, random);
        return gen.generateKey();
    }

    // 数据加密
    public byte[] encrypt(byte[] plainBytes, byte[] key) throws Exception {
        SecretKey secKey = generateKey(key);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secKey);
        return cipher.doFinal(plainBytes);
    }

    // 数据解密
    public byte[] decrypt(byte[] cipherBytes, byte[] key) {
        try {
            SecretKey secKey = generateKey(key);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            return cipher.doFinal(cipherBytes);
        } catch (Exception e) {
            System.out.print("Wrong Password!\npassword: ");
            return decrypt(scan.next().getBytes(), key);
        }
    }

    // 强制保存及删除明文
    public void armShutDownHook() {
        Runtime.getRuntime().addShutdownHook(shutThread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("Thanks for using the application");
                System.out.println("Exiting...");
                try {save();} catch (Exception e) {e.getMessage();}
                del();
                }
        }));
    }
    public void disarmShutDownHook(){
        shutThread.stop();
    }
}