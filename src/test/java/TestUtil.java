import java.util.Scanner;

public class TestUtil {
    public static void main(String[] args) {
        System.out.println("请输入: ");
        Scanner input = new Scanner(System.in);
        String msg = input.nextLine();
        String name = msg.substring(0,msg.indexOf(" "));
        msg = msg.substring(msg.indexOf(" ") + 1, msg.length());
        String arriveTime = msg.substring(0,msg.length());
        System.out.println(name);
        System.out.println(arriveTime);
    }
}
