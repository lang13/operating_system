package homework2;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Job  implements Serializable, Cloneable {
    private String Name;
    private int arrivalTime;
    private int serviceTime;
    private int finishTime;
    private int turnaroundTime;
    private double weightedTurnaroundTime;
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Job() {

    }

    public Job(String name, int arrivalTime, int serviceTime) {
        Name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public Job(String name, int arrivalTime, int serviceTime, int weight) {
        Name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.weight = weight;
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
        weightedTurnaroundTime = Double.parseDouble(String.format("%.2f",1.0 * turnaroundTime/serviceTime));
    }

    public double getWeightedTurnaroundTime() {
        return weightedTurnaroundTime;
    }

    public void setWeightedTurnaroundTime(double weightedTurnaroundTime) {
        this.weightedTurnaroundTime = weightedTurnaroundTime;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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
                ", weight=" + weight +
                '}';
    }
}
