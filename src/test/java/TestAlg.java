import experiment1.Algorithm;
import experiment1.Job;

import java.util.List;

public class TestAlg {
    public static void main(String[] args) {
        Job job1 = new Job("A",0,0,4);
        Job job2 = new Job("B",0,1,3);
        Job job3 = new Job("C",0,2,4);
        Job job4 = new Job("D",0,3,2);
//        Job job5 = new Job("E",1,4,4);

        Algorithm.addJob(job1);
        Algorithm.addJob(job2);
        Algorithm.addJob(job3);
        Algorithm.addJob(job4);
//        Algorithm.addJob(job5);

        List<Job> jobs = null;
        jobs = Algorithm.HRN();
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
