package homework1;

import java.util.*;

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

    public static List<Job> FCFS(){
        //先对job按照执行顺序排序
        //FCFS算法按照arrivalTime有小到大的顺序开始执行
        //利用比较器进行排序
        Comparator<Job> jobComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大排序
                return o1.getArrivalTime() - o2.getArrivalTime();
            }
        };
        Collections.sort(jobs, jobComparator);

        //求完成时间、周转时间、带权周转时间
        int time = 0;
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            //修改job的完成时间
            job.setFinishTime(time + job.getServiceTime());
            //修改jobs
            jobs.set(i, job);
            //修改time
            time += job.getServiceTime();
        }
        return jobs;
    }

    /**
     * 短作业优先
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
                return o1.getServiceTime() - o2.getServiceTime();
            }
        };
        //按照到达时间先后的排序比较器
        Comparator<Job> arrivalTimeComparator = new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                //由小到大排序
                return o1.getArrivalTime() - o2.getArrivalTime();
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
                job.setFinishTime(time + job.getServiceTime());
                time += job.getServiceTime();
                returnJob.add(job);
                continue;
            }
            //剩余的排序
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < jobs.size() ; j++) {
                if (time >= jobs.get(j).getArrivalTime()){
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
            job.setFinishTime(time + job.getServiceTime());
            time += job.getServiceTime();
            returnJob.add(job);
            //合并temp和jobs
            jobs.addAll(temp);
            temp.clear();
        }
        return returnJob;
    }
}
