package lessons.week4.pratice.recursion.pratice.pratice3;

/**
 * @version 1.0
 * @Description: 面试题 08.01. 三步问题 --争哥解法
 * @author: bingyu
 * @date: 2022/5/18
 */
public class ZgSolved {

    public static void main(String[] args) {
        int i = waysToStep(1000);
        System.out.println(i);
    }

    /*
     争哥解法: 动态规划
    */
    public static int waysToStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = ((dp[i-1]+dp[i-2])%1000000007 + dp[i-3])%1000000007;
        }
        return dp[n];
    }

    //⾮递归实现+优化,前面是用了n+1个长度的数组，空间比较高，这里我们优化只需要3个来记录就行，和我的迭代的解法是一样的
    public int waysToStep2(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        int a = 1;
        int b = 2;
        int c = 4;
        int d = 0;
        for (int i = 4; i <= n; i++) {
            d = ((c+b)%1000000007 + a)%1000000007;
            a = b;
            b = c;
            c = d;
        }
        return d;
    }
}
