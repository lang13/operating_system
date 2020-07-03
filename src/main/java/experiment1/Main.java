package experiment1;

import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //输入job
        a:while(true){
            System.out.println("请输入进程名、优先数、到达时间和需要运行时间");
            String msg = input.nextLine();
            //进程名
            String name = msg.substring(0,msg.indexOf(" "));
            //优先数
            msg = msg.substring(msg.indexOf(" ") + 1);
            int weight = Integer.valueOf(msg.substring(0, msg.indexOf(" ")));
            //到达时间
            msg = msg.substring(msg.indexOf(" ") + 1);
            int arriveTime = Integer.valueOf(msg.substring(0, msg.indexOf(" ")));
            //需要运行时间
            msg = msg.substring(msg.indexOf(" ") + 1);
            int needTime = Integer.valueOf(msg);
            //新建job
            Job job = new Job(name, weight, arriveTime, needTime);
            //存入job
            Algorithm.addJob(job);
            //是否继续
            System.out.println("是否继续录入:1/继续;2/结束");
            while(true) {
                String is = input.next();
                input.nextLine();
                if ("1".equals(is)) {
                    continue a;
                } else if ("2".equals(is)) {
                    break a;
                } else {
                    System.out.println("指令输入有误,请重新输入");
                    continue;
                }
            }
        }
        System.out.println("开始运行");
        System.out.println("----------------------------------------------------");
        List<Job> jobs = null;
        try {
            jobs = Algorithm.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("----------------------------------------------------");

        //运行结束
        System.out.println("运行结束");
        //打印信息
        System.out.println("进程名\t到达时间\t服务时间\t完成时间\t优先权\t周转时间\t带权周转时间\t");
        for (Job job:jobs) {
            System.out.println(job.getName() + "   \t "
                    + job.getArriveTime() + "   \t "
                    + job.getNeedTime() + "   \t "
                    + job.getFinishTime() + "   \t "
                    + job.getWeight() + "    \t"
                    + job.getTurnaroundTime() + "   \t "
                    +job.getWeightedTurnaroundTime() + "   \t");
        }
    }
}
