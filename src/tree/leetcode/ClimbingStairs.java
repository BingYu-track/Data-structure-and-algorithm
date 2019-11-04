package tree.leetcode;

/**
 * @version 1.0
 * @Description: 爬楼梯问题
 * @author: bingyu
 * @date: 2019/11/4 20:33
 */
public class ClimbingStairs {

    //TODO 方法一：傻递归，指数级时间复杂度
    public static long climbStairs(long n) {
        //1.终止条件
        if (n <= 1) { //当只有一个台阶时，为1
            return 1;
        }
        //2.处理当前层
        return climbStairs(n - 1) + climbStairs(n - 2);
    }


    //TODO 方法二：只使用循环模拟递归，但用内存缓存中间结果
    public static long climbStairs2(int n){
        if(n == 0 || n == 1 || n == 2){return n;}
        int[] mem = new int[n];
        mem[0] = 1;
        mem[1] = 2;
        for(int i = 2; i < n; i++){
            mem[i] = mem[i-1] + mem[i-2];
        }
        return mem[n-1];
    }
    //缺点是空间复杂度为O(n)

    //TODO 改进方法二： 使用三个变量缓存中间结果
    public static long climbStairs3(int n){
        if(n <= 2) return  n;
        int stepOne = 1,stepTwo = 2;
        int result = 0; //存储中间结果
        for(int i = 3;i <= n;++i) {
            result = stepOne + stepTwo;
            stepOne = stepTwo;
            stepTwo = result;
        }
        return  result;
    }


    public static void main(String[] args) {
        long l1 = System.currentTimeMillis();
        System.out.println(climbStairs(25));
        long l2 = System.currentTimeMillis();
        System.out.println(climbStairs3(25));
        long l3 = System.currentTimeMillis();
        long la = (l2 - l1) / 1000;
        long lb = (l3 - l2) / 1000;
        System.out.println("方法一花费时间 ：" + la);
        System.out.println("方法二花费时间 ：" + lb);
    }
}
