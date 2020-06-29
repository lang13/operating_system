package homework2;

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
     * 轮转法
     * @return
     */
    public static List<Job> RR(int q){
        //先对job按照执行顺序排序
        //利用比较器进行排序
       Comparator<Job> jobComparator = new Comparator<Job>() {
           @Override
           public int compare(Job o1, Job o2) {
               //由小到大排序
               return o1.getArrivalTime() - o2.getArrivalTime();
           }
       };
        //排序
        Collections.sort(jobs, jobComparator);

        //计算结束时间
        List<Job> returnJobs = new ArrayList<>(jobs.size());
        //深度拷贝
        for (Job job:jobs) {
            try {
                returnJobs.add((Job) job.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        int time = 0;
        int j = 0;
        while (j != jobs.size()){
            for (int i = 0; i < jobs.size(); i++) {
                //直接放行ServiceTime为0的job
                if (jobs.get(i).getServiceTime() == 0){
                    continue;
                }
                //时间片内为完成
                if(jobs.get(i).getServiceTime() > q){
                    Job job = jobs.get(i);
                    job.setServiceTime(job.getServiceTime() - q);
                    jobs.set(i,job);
                    time += q;
                } else{ //时间片内完成了
                    Job job = jobs.get(i);
                    int timeLeft = job.getServiceTime();
                    job.setServiceTime(0);
                    time += timeLeft;
                    jobs.set(i,job);
                    //设置returnJobs的结束时间
                    Job job1 = returnJobs.get(i);
                    job1.setFinishTime(time);
                    returnJobs.set(i,job1);
                    //结束标识符j++
                    j++;
                }
            }
        }
        jobs.clear();
        return returnJobs;
    }

    public static List<Job> Priority(){
        //按照优先数排序
        //先对job按照执行顺序排序
        //利用比较器进行排序
        Comparator<Job> jobComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大排序
                return o1.getWeight() - o2.getWeight();
            }
        };
        //排序
        Collections.sort(jobs, jobComparator);
        //计算结束时间
        int time = 0;
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            job.setFinishTime(job.getServiceTime() + time);
            jobs.set(i, job);
            time += job.getServiceTime();
        }
        return jobs;
    }
}
