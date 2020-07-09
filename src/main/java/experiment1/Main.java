package experiment1;

import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Job> jobs = null;
        //录入进程数据
        A:while(true){
            System.out.println("请输入进程名、优先权、到达时间、需要运行时间");
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
                    continue A;
                } else if ("2".equals(is)) {
                    break A;
                } else {
                    System.out.println("指令输入有误,请重新输入");
                    continue;
                }
            }
        }
        //选择算法
        B:while(true){
            System.out.println("请选择算法:1/先来先服务;2/最短作业优先;3/响应比高者优先;4/优先权优先;5/简单轮转法");
            String string = input.next();
            //先来先服务
            if ("1".equals(string)){
                jobs = Algorithm.FCFS();
                break;
            }else if("2".equals(string)){    //对短作业优先
                jobs = Algorithm.SJF();
                break;
            }else if("3".equals(string)){   //响应高者比优先
                jobs = Algorithm.HRN();
                break;
            }else if("4".equals(string)){   //优先权优先
                try {
                    jobs = Algorithm.WEIGHT();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if("5".equals(string)){   //简单轮转法
                System.out.println("请输入时间片大小");
                int q = input.nextInt();
                jobs = Algorithm.RR(q);
                break;
            }else{
                System.out.println("输入指令有误,请重新输入");
                continue B;
            }
        }
        //打印最终的运行结果
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
