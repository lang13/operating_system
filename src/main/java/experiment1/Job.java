package experiment1;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class Job implements Serializable, Cloneable{
    private String name;
    private double weight;
    private int arriveTime;
    private int needTime;
    private int runningTime;
    private int finishTime;
    private int waiteTime;
    private int turnaroundTime;
    private double weightedTurnaroundTime;
    private boolean isFinish = false;

    public Job() {
    }

    public Job(String name, int weight, int arriveTime, int needTime) {
        this.name = name;
        this.weight = weight;
        this.arriveTime = arriveTime;
        this.needTime = needTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(int arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getNeedTime() {
        return needTime;
    }

    public void setNeedTime(int needTime) {
        this.needTime = needTime;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getWaiteTime() {
        return waiteTime;
    }

    public void setWaiteTime(int waiteTime) {
        this.waiteTime = waiteTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
        //更新周转时间
        //周转时间=完成时间-到达时间
        setTurnaroundTime(finishTime - getArriveTime());
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
        //设置带权周转时间
        //带权周转时间=周转时间/服务时间
        weightedTurnaroundTime = 1.0 * turnaroundTime/needTime;
    }

    public double getWeightedTurnaroundTime() {
        return weightedTurnaroundTime;
    }

    public void setWeightedTurnaroundTime(double weightedTurnaroundTime) {
        this.weightedTurnaroundTime = weightedTurnaroundTime;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Job{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", arriveTime=" + arriveTime +
                ", needTime=" + needTime +
                ", runningTime=" + runningTime +
                ", finishTime=" + finishTime +
                ", turnaroundTime=" + turnaroundTime +
                ", weightedTurnaroundTime=" + weightedTurnaroundTime +
                '}';
    }
}
