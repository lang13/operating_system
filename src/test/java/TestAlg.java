import homework2.Algorithm;
import homework2.Job;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class TestAlg {
    public static void main(String[] args) {
        Job job1 = new Job("A",5,4,5);
        Job job2 = new Job("B",4,3,4);
        Job job3 = new Job("C",3,4,3);
        Job job4 = new Job("D",2,2,2);
        Job job5 = new Job("E",1,4,1);

        Algorithm.addJob(job1);
        Algorithm.addJob(job2);
        Algorithm.addJob(job3);
        Algorithm.addJob(job4);
        Algorithm.addJob(job5);

        List<Job> jobs = Algorithm.Priority();
        //打印信息
        System.out.println("进程名\t到达时间\t服务时间\t完成时间\t优先权\t周转时间\t带权周转时间\t");
        for (Job job:jobs) {
            System.out.println(job.getName() + "   \t "
                    + job.getArrivalTime() + "   \t "
                    + job.getServiceTime() + "   \t "
                    + job.getFinishTime() + "   \t "
                    + job.getWeight() + "    \t"
                    + job.getTurnaroundTime() + "   \t "
                    +job.getWeightedTurnaroundTime() + "   \t");
        }
    }
}
