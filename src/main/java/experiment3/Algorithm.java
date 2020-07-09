package experiment3;

import java.util.*;

/**
 * @author Administrator
 */
public class Algorithm {
    private static List<Block> blocks = new ArrayList<>();
    private static List<Job> jobs = new ArrayList<>();

    public static void initBlock(){
        //生成10个不同的随机数
        List<Integer> integers = new ArrayList<>();
        Random random = new Random();
        while(true) {
            int i1 = random.nextInt(100) + 1;
            if (!integers.contains(i1)){
                integers.add(i1);
            }
            if (integers.size() == 10){
                break;
            }
        }
        //初始化10个分区
        for (int i = 0; i < 10; i++) {
            Block block = new Block();
            //名字
            block.setId(String.valueOf(i));
            //初始化大小
            block.setOriginalSize(random.nextInt(100) + 1);
            block.setRemainingSize(block.getOriginalSize());
            //舒适化地址
            block.setAddress(integers.get(i));
            //放入blocks
            blocks.add(block);
        }
        //打印生成的空间
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t原始空间大小\t剩余空间大小\t首地址\t分配的作业");
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            System.out.println(block.getId() + "    \t" +
                            block.getOriginalSize() + "    \t" +
                            block.getRemainingSize() + "    \t\t" +
                            block.getAddress() + "    \t\t" +
                            block.getJobs() + "    \t" +
                            "");
        }
    }

    public static void initJob(){
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Job job = new Job();
            //id
            job.setId(String.valueOf(i));
            //大小
            job.setSize(random.nextInt(70));
            //状态
            job.setState("N");
            //添加jobs
            jobs.add(job);
        }
        //打印
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t作业大小\t对应区块\t状态\t");
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            System.out.println(job.getId() + "  \t" +
                            job.getSize() + "    \t" +
                            job.getBlock() + "   \t" +
                            job.getState() + "   \t");
        }
    }

    /**
     * 首次适应算法
     */
    public static void FF(){
        //先将block按地址从高到低排序
        Comparator<Block> blockComparator = new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o2.getAddress() - o1.getAddress();
            }
        };
        //排序
        Collections.sort(blocks, blockComparator);
        //遍历jobs和blocks分配空间
        for (int i = 0; i < jobs.size(); i++) {
            //取出需要分配空间的job
            Job job = jobs.get(i);
            if ("已完成".equals(job.getState())){
                continue;
            }
            for (int j = 0; j < blocks.size(); j++) {
                //取出block
                Block block = blocks.get(j);
                //对比空间
                //空间足够，可分配
                if (job.getSize() <= block.getRemainingSize()){
                    //更新job的信息
                    job.setState("Y");
                    job.setBlock(Integer.parseInt(block.getId()));
                    //更新block的信息
                    block.setRemainingSize(block.getRemainingSize() - job.getSize());
                    block.addJob(job.getId());
                    //首次适应算法
                    break;
                }
            }
        }
        //分派完打印信息
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t原始空间大小\t剩余空间大小\t首地址\t分配的作业");
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            System.out.println(block.getId() + "    \t" +
                    block.getOriginalSize() + "    \t" +
                    block.getRemainingSize() + "    \t\t" +
                    block.getAddress() + "    \t" +
                    block.getJobs() + "    \t" +
                    "");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t作业大小\t对应区块\t状态\t");
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            System.out.println(job.getId() + "  \t" +
                    job.getSize() + "    \t" +
                    job.getBlock() + "   \t" +
                    job.getState() + "   \t");
        }
    }

    /**
     * 循环首次适应算法
     */
    public static void NF(){
        //先将block按地址从高到低排序
        Comparator<Block> blockComparator = new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o2.getAddress() - o1.getAddress();
            }
        };
        //排序
        Collections.sort(blocks, blockComparator);
        //遍历jobs和blocks分配空间
        for (int i = 0; i < jobs.size(); i++) {
            //获取block
            Job job = jobs.get(i);
            if ("已完成".equals(job.getState())){
                continue;
            }
            for (int j = 0; j < blocks.size(); j++) {
                Block block = blocks.get(j);
                //空间足够
                if (job.getSize() <= block.getRemainingSize()){
                    //更新job的信息
                    job.setState("Y");
                    job.setBlock(Integer.parseInt(block.getId()));
                    //更新block的信息
                    block.setRemainingSize(block.getRemainingSize() - job.getSize());
                    block.addJob(job.getId());
                    //循环首次适应算法
                    if (i != jobs.size() - 1){
                        i++;
                    }
                    if (j != 0){
                        j--;
                    }
                }
            }
        }
        //分派完打印信息
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t原始空间大小\t剩余空间大小\t首地址\t分配的作业");
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            System.out.println(block.getId() + "    \t" +
                    block.getOriginalSize() + "    \t" +
                    block.getRemainingSize() + "    \t\t" +
                    block.getAddress() + "    \t" +
                    block.getJobs() + "    \t" +
                    "");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t作业大小\t对应区块\t状态\t");
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            System.out.println(job.getId() + "  \t" +
                    job.getSize() + "    \t" +
                    job.getBlock() + "   \t" +
                    job.getState() + "   \t");
        }
    }

    /**
     * 最佳适应算法
     */
    public static void BF(){
        //根据block剩余空间，从大到小排序
        Comparator<Block> blockComparator = new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o2.getRemainingSize() - o1.getRemainingSize();
            }
        };
        //循环分配空间
        Collections.sort(blocks, blockComparator);
        for (int i = 0; i < jobs.size(); i++) {
            //获取job
            Job job = jobs.get(i);
            if ("已完成".equals(job.getState())){
                continue;
            }
            for (int j = 0; j < blocks.size(); j++) {
                //获取block
                Block block = blocks.get(j);
                //判断空间是否合适
                if (job.getSize() <= block.getRemainingSize()) {
                    //更新job的信息
                    job.setState("Y");
                    job.setBlock(Integer.parseInt(block.getId()));
                    //更新block的信息
                    block.setRemainingSize(block.getRemainingSize() - job.getSize());
                    block.addJob(job.getId());
                    //循环首次适应算法
                    //blocks排序
                    Collections.sort(blocks, blockComparator);
                    break;
                }
            }
        }
        //分派完打印信息
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t原始空间大小\t剩余空间大小\t首地址\t分配的作业");
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            System.out.println(block.getId() + "    \t" +
                    block.getOriginalSize() + "    \t" +
                    block.getRemainingSize() + "    \t\t" +
                    block.getAddress() + "    \t" +
                    block.getJobs() + "    \t" +
                    "");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t作业大小\t对应区块\t状态\t");
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            System.out.println(job.getId() + "  \t" +
                    job.getSize() + "    \t" +
                    job.getBlock() + "   \t" +
                    job.getState() + "   \t");
        }
    }

    public static void WF(){
        //根据block剩余空间，从小到大排序
        Comparator<Block> blockComparator = new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o1.getRemainingSize() - o2.getRemainingSize();
            }
        };
        //循环分配空间
        Collections.sort(blocks, blockComparator);
        for (int i = 0; i < jobs.size(); i++) {
            //获取job
            Job job = jobs.get(i);
            if("已完成".equals(job.getState())){
                continue;
            }
            for (int j = 0; j < blocks.size(); j++) {
                //block
                Block block = blocks.get(j);
                //判断空间是否合适
                if (job.getSize() <= block.getRemainingSize()) {
                    //更新job的信息
                    job.setState("Y");
                    job.setBlock(Integer.parseInt(block.getId()));
                    //更新block的信息
                    block.setRemainingSize(block.getRemainingSize() - job.getSize());
                    block.addJob(job.getId());
                    //循环首次适应算法
                    //blocks排序
                    Collections.sort(blocks, blockComparator);
                    break;
                }
            }
        }
        //分派完打印信息
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t原始空间大小\t剩余空间大小\t首地址\t分配的作业");
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            System.out.println(block.getId() + "    \t" +
                    block.getOriginalSize() + "    \t" +
                    block.getRemainingSize() + "    \t\t" +
                    block.getAddress() + "    \t" +
                    block.getJobs() + "    \t" +
                    "");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t作业大小\t对应区块\t状态\t");
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            System.out.println(job.getId() + "  \t" +
                    job.getSize() + "    \t" +
                    job.getBlock() + "   \t" +
                    job.getState() + "   \t");
        }
    }

    /**
     * 回收内存
     */
    public static void recovery(){
        //将已经分配了block的job的state和block更新
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            if (-1 != job.getBlock()){
                job.setState("已完成");
                job.setBlock(-1);
            }
        }
        //清理blocks
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (block.getOriginalSize() != block.getRemainingSize()){
                block.setRemainingSize(block.getOriginalSize());
                block.getJobs().clear();
            }
        }
        //从新按照id排序
        Comparator<Block> blockComparator = new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
            }
        };
        Collections.sort(blocks, blockComparator);
        //分派完打印信息
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t原始空间大小\t剩余空间大小\t首地址\t分配的作业");
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            System.out.println(block.getId() + "    \t" +
                    block.getOriginalSize() + "    \t" +
                    block.getRemainingSize() + "    \t\t" +
                    block.getAddress() + "    \t" +
                    block.getJobs() + "    \t" +
                    "");
        }
        System.out.println("-------------------------------------------------------");
        System.out.println("ID\t作业大小\t对应区块\t状态\t");
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            System.out.println(job.getId() + "  \t" +
                    job.getSize() + "    \t" +
                    job.getBlock() + "   \t" +
                    job.getState() + "   \t");
        }
    }
}
