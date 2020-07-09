import experiment1.Job;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {
    public static void main(String[] args) {
        List<Job> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Job job = new Job(String.valueOf(i),i,i,i);
            list.add(job);
        }
        Job job = list.get(0);
        job.setName("新的名字");
        System.out.println(list);
    }
}
