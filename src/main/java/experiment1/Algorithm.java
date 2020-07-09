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

    /**
     * 先来先服务
     * @return
     */
    public static List<Job> FCFS(){
        //比较器
        Comparator<Job> arriveTimeComp = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //时间由小到大
                return o1.getArriveTime() - o2.getArriveTime();
            }
        };
        //排序,执行
        int time = 0;
        Collections.sort(jobs,arriveTimeComp);
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            //打印正在运行的状态信息
            System.out.println("正在运行的队列");
            System.out.println("----------------------------------------------------");
            System.out.println("进程名\t优先数\t到达时间\t需要运行的时间\t已用CPU时间\t进程状态");
            System.out.println(job.getName() + "    \t"
                    + job.getWeight() + "    \t" +
                    + job.getArriveTime() + "    \t" +
                    + job.getNeedTime() + "    \t\t" +
                    + job.getNeedTime() + "    \t\t" +
                    "R");
            //打印就绪队列
            System.out.println("----------------------------------------------------");
            System.out.println("就绪队列");
            System.out.println("进程名\t提交时间\t服务时间\t所需资源\t状态");
            for (int k = 0; k < jobs.size(); k++) {
                if (k == i){
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
            //执行完毕释放CPU资源
            time += job.getNeedTime();
            job.setRunningTime(job.getNeedTime());
            job.setFinishTime(time);
            job.setFinish(true);
            jobs.set(i,job);
            //线程等待
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return jobs;
    }

    /**
     * 最短作业优先
     * @return
     */
    public static List<Job> SJF(){
        int time = 0;
        List<Job> returnJob = new ArrayList<>();
        List<Job> temp = new ArrayList<>();
        //按照服务时间长短的排序比较器
        Comparator<Job> serviceTimeComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大排序
                return o1.getNeedTime() - o2.getNeedTime();
            }
        };
        //按照到达时间先后的排序比较器
        Comparator<Job> arrivalTimeComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大排序
                return o1.getArriveTime() - o2.getArriveTime();
            }
        };
        //计算finishTime
        int size = jobs.size();
        for (int i = 0; i < size; i++){
            Job job;
            //先按到达时间排序
            Collections.sort(jobs, arrivalTimeComparator);
            if(time == 0){
                job = jobs.remove(0);
                job.setFinishTime(time + job.getNeedTime());
                time += job.getNeedTime();
                returnJob.add(job);
                continue;
            }
            //剩余的排序
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < jobs.size() ; j++) {
                if (time >= jobs.get(j).getArriveTime()){
                    temp.add(jobs.get(j));
                    list.add(j);
                }
            }
            //移除jobs里面的数据
            List<Job> jobList = new ArrayList<>();
            for (int j = 0; j < list.size(); j++) {
                jobList.add(jobs.get(list.get(j)));
            }
            jobs.removeAll(jobList);
            jobList.clear();
            //按服务时间排序

            Collections.sort(temp, serviceTimeComparator);
            //修改finishTime
            job = temp.remove(0);

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

            job.setFinishTime(time + job.getNeedTime());
            time += job.getNeedTime();
            returnJob.add(job);
            //合并temp和jobs
            jobs.addAll(temp);

            //打印就绪队列
            System.out.println("----------------------------------------------------");
            System.out.println("就绪队列");
            System.out.println("进程名\t提交时间\t服务时间\t所需资源\t状态");
            for (int k = 0; k < jobs.size(); k++) {
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

            temp.clear();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return returnJob;
    }

    /**
     * 响应比优先
     * @return
     * @throws InterruptedException
     */
    public static List<Job> WEIGHT() throws InterruptedException {
        //新建比较器用于排序
        Comparator<Job> jobComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //job的weight由大到小排序
                return (o2.getWeight() > o1.getWeight()) ? 1:-1;
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

    /**
     * 简单轮转法
     * @return
     */
    public static List<Job> RR(int q){
        //先对job按照执行顺序排序
        //利用比较器进行排序
        Comparator<Job> jobComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大排序
                return o1.getArriveTime() - o2.getArriveTime();
            }
        };
        //排序
        Collections.sort(jobs, jobComparator);

        //计算结束时间
        int time = 0;
        int j = 0;
        while (j != jobs.size()){
            for (int i = 0; i < jobs.size(); i++) {
                //直接放行完成了的进程
                if (jobs.get(i).isFinish()){
                    continue;
                }
                Job job = jobs.get(i);
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
                    if (k == i){
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

                //时间片内未完成
                if(job.getRunningTime() + q < job.getNeedTime()){
                    job.setRunningTime(job.getRunningTime() + q);
                    jobs.set(i,job);
                    time += q;
                } else{ //时间片内完成了
                    int timeLeft = job.getNeedTime() - job.getRunningTime();
                    job.setRunningTime(job.getNeedTime());
                    time += timeLeft;
                    job.setFinish(true);
                    job.setFinishTime(time);
                    jobs.set(i,job);
                    j++;
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return jobs;
    }

    /**
     * 响应比高者优先
     * @return
     */
    public static List<Job> HRN(){
        //比较器
        //arriveTimeComp
        Comparator<Job> arriveTimeComp = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大
                return o1.getArriveTime() - o2.getArriveTime();
            }
        };
        Comparator<Job> weightComp = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由大到小
                return (o2.getWeight() > o1.getWeight()) ? 1 : -1;
            }
        };
        //计算结束时间
        Collections.sort(jobs, arriveTimeComp);
        int time = 0;
        List<Job> tempJobs = new ArrayList<>();
        for (int i = 0; i < jobs.size(); i++) {
            Job job = null;
            //筛选时间合适的job
            for (Job job1:jobs) {
                if (job1.getArriveTime() <= time){
                    tempJobs.add(job1);
                }
            }
            //获取tempJobs中优先权最大且未完成的job
            Collections.sort(tempJobs, weightComp);
            for (Job job2:tempJobs) {
                if (!job2.isFinish()){
                    job = job2;
                    break;
                }
            }
            //打印正在运行的状态信息
            System.out.println("正在运行的队列");
            System.out.println("----------------------------------------------------");
            System.out.println("进程名\t优先权\t到达时间\t需要运行的时间\t已用CPU时间\t进程状态");
            System.out.println(job.getName() + "    \t"
                    + job.getWeight() + "    \t" +
                    + job.getArriveTime() + "    \t" +
                    + job.getNeedTime() + "    \t\t" +
                    + job.getRunningTime() + "    \t\t" +
                    "R");
            //打印就绪队列
            System.out.println("----------------------------------------------------");
            System.out.println("就绪队列");
            System.out.println("进程名\t优先权\t到达时间\t所需资源\t状态");
            for (int k = 0; k < jobs.size(); k++) {
                Job job1 = jobs.get(k);
                if (job1.equals(job)){
                    continue;
                }
                String condition = (job1.isFinish()) ? "F" : "W";
                System.out.println(job1.getName() + "    \t"
                        + job1.getWeight() + "    \t"
                        + job1.getArriveTime() + "    \t"
                        + job1.getNeedTime() + "    \t"
                        + condition);
            }
            System.out.println("----------------------------------------------------");
            System.out.println("\n");
            //更新job和jobs的数据
            //job
            time += job.getNeedTime();
            job.setFinishTime(time);
            job.setFinish(true);
            //jobs
            //更新等待时间和优先权
            for (int j = 0; j < jobs.size(); j++) {
                Job job1 = jobs.get(j);
                if (job1.isFinish()){
                    continue;
                }
                //更新等待时间
                if (job1.getArriveTime() < time){
                    job1.setWaiteTime(time - job1.getArriveTime());
                    //优先权
                    job1.setWeight(1 + 1.0*job1.getWaiteTime()/job1.getNeedTime());
                }
            }

            //线程睡眠
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return jobs;
    }
}
