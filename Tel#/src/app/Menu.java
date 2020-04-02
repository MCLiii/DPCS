package app;

import java.io.RandomAccessFile;
import java.util.Scanner;

public class Menu {
    private static Backend tel;
    private static AES256 aes256;
    private static Scanner scan;

    public static void entrance(RandomAccessFile info, AES256 aes) throws Exception {
        aes256 = aes;
        scan = new Scanner(System.in);
        tel = new Backend(info);
        String caseNum = "0";
        while (!caseNum.equals("5")) {
            System.out.println(
                    "\n1)Search\n2)Change\n3)Add Number\n4)Remove\n5)exit");
            System.out.print("input: ");
            caseNum = scan.next();
            switch (caseNum) {
                case "1":
                    search();
                    break;
                case "2":
                    change();
                    break;
                case "3":
                    System.out.print("\nName: ");
                    String name = scan.next();
                    System.out.print("Number: ");
                    String num = scan.next();
                    System.out.println("Name: " + name + " Number: " + num);
                    tel.add(name, num);
                    break;
                case "4":
                    System.out.println("\nName to delete:");
                    tel.delByName(scan.next());
                    break;
                case "5":
                    aes.disarmShutDownHook();
                    System.out.println("\nsave?(Y/N): ");
                    String choice=scan.next();
                    if(choice=="y"|choice=="Y")
                        aes.save();
                    System.out.println("Exiting...");
                    aes256.del();
                    scan.close();
                    break;
                default:
                    System.out.println("Not available");
            }
            if (!caseNum.equals("5")) {
                System.out.print("any key to continue...");
                System.in.read();
                System.out.println("\n\n\n");
            }
        }
    }
    private static void search() throws Exception {
        System.out.println("\n1)Search Number\n2)Search Name");
        System.out.print("input: ");
        switch(scan.next()){
            case "1":
                System.out.print("\nInput Number: ");
                System.out.println(tel.searchNum(scan.next()));
                break;
            case "2":
                System.out.print("\nInput Name: ");
                System.out.println(tel.search(scan.next()));
                break;
            default:
                System.out.println("\nError");
                search();
                break;
        }
    } 
    private static void change() throws Exception {
        System.out.println("\n1)Change Number\n2)Change Name");
        switch(scan.next()){
            case "1":
                System.out.print("\nWho to change? : ");
                String aName = scan.next();
                System.out.print("New Number");
                tel.change(aName, scan.next());
                break;
            case "2":
                System.out.print("\nWhich Number? : ");
                String aNum = scan.next();
                System.out.println("New name");
                tel.changebyNum(aNum, scan.next());
                break;
            default:
                System.out.println("Error");
                change();
                break;
        }
    }
}