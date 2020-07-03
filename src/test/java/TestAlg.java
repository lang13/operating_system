import experiment1.Algorithm;
import experiment1.Job;

import java.util.List;

public class TestAlg {
    public static void main(String[] args) {
        Job job1 = new Job("A",5,0,5);
        Job job2 = new Job("B",4,0,4);
        Job job3 = new Job("C",3,0,3);
        Job job4 = new Job("D",2,0,2);
        Job job5 = new Job("E",1,0,1);

        Algorithm.addJob(job1);
        Algorithm.addJob(job2);
        Algorithm.addJob(job3);
        Algorithm.addJob(job4);
        Algorithm.addJob(job5);

        List<Job> jobs = null;
        try {
            jobs = Algorithm.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
