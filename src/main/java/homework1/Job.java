package homework1;

/**
 * @author Administrator
 */
public class Job {
    private String Name;
    private int arrivalTime;
    private int serviceTime;
    private int finishTime;
    private int turnaroundTime;
    private double weightedTurnaroundTime;

    public Job() {

    }

    public Job(String name, int arrivalTime, int serviceTime) {
        Name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
        //完成时间得出就能求得周周时间
        setTurnaroundTime(this.finishTime - arrivalTime);
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
        //周转时间求得就能马上求得带权周转时间
        weightedTurnaroundTime = 1.0 * turnaroundTime/serviceTime;
    }

    public double getWeightedTurnaroundTime() {
        return weightedTurnaroundTime;
    }

    public void setWeightedTurnaroundTime(double weightedTurnaroundTime) {
        this.weightedTurnaroundTime = weightedTurnaroundTime;
    }

    @Override
    public String toString() {
        return "Job{" +
                "Name='" + Name + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", serviceTime=" + serviceTime +
                ", finishTime=" + finishTime +
                ", turnaroundTime=" + turnaroundTime +
                ", weightedTurnaroundTime=" + weightedTurnaroundTime +
                '}';
    }
}
