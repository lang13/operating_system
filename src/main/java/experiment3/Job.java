package experiment3;

/**
 * @author Administrator
 */
public class Job {
    private String id;
    private int size;
    private int block = -1;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id='" + id + '\'' +
                ", size=" + size +
                ", block=" + block +
                ", state='" + state + '\'' +
                '}';
    }
}
