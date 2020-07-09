package experiment3;

import java.util.Scanner;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //生成作业和空间
        while(true){
            System.out.println("1/随机生成空闲空间;2/随机生成作业;3/结束");
            String string = input.next();
            if ("1".equals(string)){
                Algorithm.initBlock();
            }else if("2".equals(string)){
                Algorithm.initJob();
            }else if ("3".equals(string)){
                break;
            }else{
                System.out.println("输入的指令有误,请重新输入");
                continue;
            }
        }
        //选择算法
        while(true){
            System.out.println("请选择算法");
            System.out.println("1/首次适应算法;2/循环首次适应算法;3/最佳适应算法;4/最差适应算法");
            String string = input.next();
            if("1".equals(string)){
                Algorithm.FF();
                break;
            }else if("2".equals(string)){
                Algorithm.NF();
                break;
            }else if("3".equals(string)){
                Algorithm.BF();
                break;
            }else if("4".equals(string)){
                Algorithm.WF();
                break;
            }else{
                System.out.println("输入指令有误,请重新输入");
                continue;
            }
        }
        //回收空间
        while(true){
            System.out.println("是否回收空间(Y/N)");
            String string = input.next();
            if ("Y".equals(string) || "y".equals(string)){
                Algorithm.recovery();
                break;
            }else if("N".equals(string) || "n".equals(string)){
                return;
            }else{
                System.out.println("指令输入有误,请重新输入");
                continue;
            }
        }
        //二次分配
        while(true) {
            System.out.println("二次分配");
            System.out.println("请选择算法");
            System.out.println("1/首次适应算法;2/循环首次适应算法;3/最佳适应算法;4/最差适应算法");
            String string = input.next();
            if ("1".equals(string)) {
                Algorithm.FF();
                break;
            } else if ("2".equals(string)) {
                Algorithm.NF();
                break;
            } else if ("3".equals(string)) {
                Algorithm.BF();
                break;
            } else if ("4".equals(string)) {
                Algorithm.WF();
                break;
            } else {
                System.out.println("输入指令有误,请重新输入");
                continue;
            }
        }
    }
}
