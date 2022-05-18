package lessons.week4.pratice.recursion.pratice3;

/**
 * @version 1.0 面试题 08.01. 三步问题
 * @Description: 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，
 *  计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 *
 * 示例1:
 *  输入：n = 3
 *  输出：4
 *  说明: 有四种走法
 *
 * 示例2:
 *  输入：n = 5
 *  输出：13
 *
 * 提示:
 * n范围在[1, 1000000]之间
 *
 * @author: bingyu
 * @date: 2022/5/18
 */
public class WaysToStep {

    public static void main(String[] args) {
        int i = waysToStep2(3); //752119970
        System.out.println(i);
    }

    /*方法1
     我的思路分析：走第一步时有三种，1步/2步/3步;分别迈出后，后面分别剩下n-1、n-2、n-3个台阶；然后针对n-1个台阶，它的第一步也有3种，也是
                1步/2步/3步，因此得到递推公式f(n) = f(n-1) + f(n-2) + f(n-3);终止条件为n=3
    */
    public static int[] mem;
    public static int waysToStep(int n) {
        mem = new int[n + 1];
        return climbStep(n);
    }
    //执行结果: 79 ms	 143.7 MB，很耗性能
    private static int climbStep(int n) {
        if (mem[n] != 0) return mem[n] % 1000000007;
        if (n == 1 || n == 2) return n;
        if (n == 3) return 4;
        //注意这里每个数字都会很大，只要有相加的话，也需要进行取模操作
        mem[n] = (climbStep(n - 1) + climbStep(n - 2)) % 1000000007 + climbStep(n - 3);
        return mem[n] % 1000000007;
    }

    /*
      方法2:使用迭代法
      执行用时: 20ms	38.5MB
    */
    public static int waysToStep2(int n) {
        int result = 0;
        int k1 = 0;
        int k2 = 0;
        int k3 = 0;
        for (int i = 1;i<=n;i++) {
            if (i == 1 || i == 2) {
                result = i;
            }
            if (i == 3) {
                result = 4;
            }
            if (i >= 4) {
                if (i == 4) {
                    k1 = 4;
                    k2 = 2;
                    k3 = 1;
                }
                result = (k1 + k2) % 1000000007 + k3;
                k3 = k2;
                k2 = k1;
                k1 = result % 1000000007;
            }
        }
        return result % 1000000007;
    }

}
