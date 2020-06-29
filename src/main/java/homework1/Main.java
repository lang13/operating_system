package homework1;

import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Job> jobs = null;
        A:while (true){
            //输入job信息
            a:while(true){
                System.out.println("请输入job名");
                String jobName = input.next();

                System.out.println("输入到达时间");
                int arriveTime = input.nextInt();

                System.out.println("请输入服务时间");
                int serviceTime = input.nextInt();

                Job job = new Job(jobName, arriveTime, serviceTime);
                Algorithm.addJob(job);

                //判断是否继续录入
                while (true){
                    System.out.println("是否继续录入job(Y/N)");
                    String is = input.next();
                    if ("Y".equals(is) || "y".equals(is)){
                        continue a;
                    }else if ("N".equals(is) || "n".equals(is)){
                        break a;
                    }else {
                        System.out.println("指令输入有误,请重新输入");
                        continue;
                    }
                }
            }

            //选择调度算法
            while(true){
                System.out.println("请选择调度算法(1:FCFS/2:SJF)");
                String is = input.next();
                if ("1".equals(is)){
                    jobs = Algorithm.FCFS();
                    break;
                } else if ("2".equals(is)){
                    jobs = Algorithm.SJF();
                    break;
                } else{
                    System.out.println("指令输入有误");
                    continue;
                }
            }
            //打印信息
            System.out.println("进程名\t到达时间\t服务时间\t完成时间\t周转时间\t带权周转时间\t");
            for (Job job:jobs) {
                System.out.println(job.getName() + "   \t "
                                + job.getArrivalTime() + "   \t "
                                + job.getServiceTime() + "   \t "
                                + job.getFinishTime() + "   \t "
                                + job.getTurnaroundTime() + "   \t "
                                +job.getWeightedTurnaroundTime() + "   \t");
            }
            //是否继续
            while(true){
                System.out.println("是否继续");
                String is = input.next();
                if ("y".equals(is) || "Y".equals(is)){
                    continue A;
                } else if("n".equals(is) || "N".equals(is)){
                    break A;
                }else {
                    System.out.println("输入的指令有误,请重新输入");
                    continue;
                }
            }
        }
    }
}
