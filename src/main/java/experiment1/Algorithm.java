package experiment1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Administrator
 */
public class Algorithm {
    private static List<Job> jobs = new ArrayList<>();

    public static void addJob(Job job){
        jobs.add(job);
    }

    public static List<Job> getJobs(){
        return jobs;
    }

    public static List<Job> run() throws InterruptedException {
        //新建比较器用于排序
        Comparator<Job> jobComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //job的weight由大到小排序
                return o1.getWeight() - o2.getWeight();
            }
        };
        //开始执行
        int i = 0;
        int time = 0;
        while(i != jobs.size()){
            //按照权重排序
            Collections.sort(jobs, jobComparator);
            //优先权最大的获取cpu资源,然后执行
            Job job = null;
            int j = 0;
            for (j = 0; j < jobs.size(); j++) {
                if (jobs.get(j).isFinish()){
                    //如果权重最大的已经执行完成,则跳过
                    continue;
                }
                job = jobs.get(j);
                break;
            }
            //打印正在运行的状态信息
            System.out.println("正在运行的队列");
            System.out.println("----------------------------------------------------");
            System.out.println("进程名\t优先数\t到达时间\t需要运行的时间\t已用CPU时间\t进程状态");
            System.out.println(job.getName() + "    \t"
                            + job.getWeight() + "    \t" +
                            + job.getArriveTime() + "    \t" +
                            + job.getNeedTime() + "    \t\t" +
                            + job.getRunningTime() + "    \t\t" +
                            "R");
            //打印就绪队列
            System.out.println("----------------------------------------------------");
            System.out.println("就绪队列");
            System.out.println("进程名\t提交时间\t服务时间\t所需资源\t状态");
            for (int k = 0; k < jobs.size(); k++) {
                if (k == j){
                    continue;
                }
                Job job1 = jobs.get(k);
                String condition = (job1.isFinish()) ? "F" : "W";
                System.out.println(job1.getName() + "    \t"
                                + job1.getArriveTime() + "    \t"
                                + job1.getRunningTime() + "    \t"
                                + job1.getNeedTime() + "    \t"
                                + condition);
            }
            System.out.println("----------------------------------------------------");
            System.out.println("\n");
            //进程使用完时间片,更新进程的数据
            //系统的运行时间time++
            time++;
            //runningTime++
            job.setRunningTime(job.getRunningTime() + 1);
            //优先权--
            //判断优先数是否到0
            if (0 != job.getWeight()){
                job.setWeight(job.getWeight() - 1);
            }
            //判断是否已经执行完
            if (job.getRunningTime() == job.getNeedTime()){
                job.setFinish(true);
                //设置结束时间
                job.setFinishTime(time);
                //结束标识符i++;
                i++;
            }
            //放回jobs中
            jobs.set(j,job);
            //休眠500毫米
            Thread.sleep(1000);
        }
        return jobs;
    }
}
