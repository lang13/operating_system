package homework2;

import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        a:while(true){
            System.out.println("请选择调度算法(1/轮转法;2/静态优先数法)");
            String string = input.next();
            input.nextLine();
            //轮转法
            if ("1".equals(string)){
                System.out.println("请输入时间片: ");
                int p = 0;
                p = input.nextInt();
                input.nextLine();
                //添加Job
                b:while(true){
                    System.out.println("请输入进程名、到达时间、服务时间: ");
                    String msg = input.nextLine();
                    //名字
                    String name = msg.substring(0,msg.indexOf(" "));
                    //到达时间
                    msg = msg.substring(msg.indexOf(" ") + 1);
                    int arriveTime = Integer.valueOf(msg.substring(0, msg.indexOf(" ")));
                    //服务时间
                    msg = msg.substring(msg.indexOf(" ") + 1);
                    int serviceTime = Integer.valueOf(msg);
                    //新建Job
                    Job job = new Job(name, arriveTime, serviceTime);
                    Algorithm.addJob(job);
                    //是否继续
                    System.out.println("是否继续录入:1/继续;2/结束");
                    while(true){
                        String is = input.next();
                        input.nextLine();
                        if ("1".equals(is)){
                            continue b;
                        } else if("2".equals(is)){
                            break b;
                        } else{
                            System.out.println("指令输入有误,请重新输入");
                            continue;
                        }
                    }
                }
                //执行调度算法
                List<Job> rr = Algorithm.RR(p);
                //打印
                System.out.println("进程名\t到达时间\t服务时间\t完成时间\t周转时间\t带权周转时间\t");
                for (Job job:rr) {
                    System.out.println(job.getName() + "   \t "
                            + job.getArrivalTime() + "   \t "
                            + job.getServiceTime() + "   \t "
                            + job.getFinishTime() + "   \t "
                            + job.getTurnaroundTime() + "   \t "
                            +job.getWeightedTurnaroundTime() + "   \t");
                }
            } else if("2".equals(string)){
                //添加job
                c:while(true){
                    System.out.println("请输入:作业名、到达时间、服务时间、优先权");
                    String msg = input.nextLine();
                    //job名
                    String name = msg.substring(0,msg.indexOf(" "));
                    //到达时间
                    msg = msg.substring(msg.indexOf(" ") + 1);
                    int arriveTime = Integer.parseInt(msg.substring(0,msg.indexOf(" ")));
                    //服务时间
                    msg = msg.substring(msg.indexOf(" ") + 1);
                    int serviceTime = Integer.parseInt(msg.substring(0,msg.indexOf(" ")));
                    //优先权重
                    msg = msg.substring(msg.indexOf(" ") + 1);
                    int weight = Integer.parseInt(msg);
                    //新建job
                    Job job = new Job(name, arriveTime, serviceTime, weight);
                    Algorithm.addJob(job);
                    //是否继续输入
                    System.out.println("是否继续录入数据:1/继续;2/结束");
                    while(true){
                        String is = input.next();
                        input.nextLine();
                        if ("1".equals(is)){
                            continue c;
                        } else if ("2".equals(is)){
                            break c;
                        } else {
                            break;
                        }
                    }
                }
                //执行调度算法
                List<Job> priority = Algorithm.Priority();
                //输出打印
                System.out.println("进程名\t到达时间\t服务时间\t完成时间\t优先权\t周转时间\t带权周转时间\t");
                for (Job job:priority) {
                    System.out.println(job.getName() + "   \t "
                            + job.getArrivalTime() + "   \t "
                            + job.getServiceTime() + "   \t "
                            + job.getFinishTime() + "   \t "
                            + job.getWeight() + "    \t"
                            + job.getTurnaroundTime() + "   \t "
                            +job.getWeightedTurnaroundTime() + "   \t");
                }
            } else{
                continue a;
            }
        }
    }
}
