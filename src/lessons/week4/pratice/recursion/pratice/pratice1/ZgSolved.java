package lessons.week4.pratice.recursion.pratice.pratice1;

/**
 * @version 1.0
 * @Description: 剑指 Offer 10- I. 斐波那契数列 ---争哥解法
 * @author: bingyu
 * @date: 2022/5/16
 */
public class ZgSolved {

    public static void main(String[] args) {
        int fib = fib(88);
        System.out.println(fib);
    }

    /*
      争哥思路: 备忘录+递归的时间复杂度分析:O(n)，画出递归树会发现，使用备忘录后，就只会计算n个节点，其它的重复节点都会直接返回

     */
    private static int mod = 1000000007;
    private static int[] memo;
    public static int fib(int n) {
        memo = new int[n+1];
        return fib_r(n);
    }

    private static int fib_r(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo[n] != 0) return memo[n];
        memo[n] = (fib_r(n-1) + fib_r(n-2)) % mod;
        return memo[n];
    }
}
