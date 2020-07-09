package experiment3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class Block {
    private String id;
    private int originalSize;
    private int remainingSize;
    private int address;
    private List<String> jobs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(int originalSize) {
        this.originalSize = originalSize;
    }

    public int getRemainingSize() {
        return remainingSize;
    }

    public void setRemainingSize(int remainingSize) {
        this.remainingSize = remainingSize;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public List<String> getJobs() {
        return jobs;
    }

    public void setJobs(List<String> jobs) {
        this.jobs = jobs;
    }

    public void addJob(String Id){
        jobs.add(Id);
    }

    @Override
    public String toString() {
        return "Block{" +
                "id='" + id + '\'' +
                ", originalSize=" + originalSize +
                ", remainingSize=" + remainingSize +
                ", address=" + address +
                ", jobs=" + jobs +
                '}';
    }
}
