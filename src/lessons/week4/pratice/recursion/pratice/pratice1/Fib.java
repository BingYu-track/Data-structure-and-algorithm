package lessons.week4.pratice.recursion.pratice.pratice1;

/**
 * @version 1.0 剑指 Offer 10- I. 斐波那契数列
 * @Description: 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0, F(1)= 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e^9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 * f(2)=1 f(3)=2 f(4)=3 f(5)=5 f(6)=8 f(7)=13 ....f(43)=433494437  f(44)=701408733
 *
 *
 * 提示：
 * 0 <= n <= 100
 *
 * @author: bingyu
 * @date: 2022/5/16
 */
public class Fib {


    public static void main(String[] args) {
        int fib = fib(48);
        System.out.println(fib);
    }

    //递归:但是这里超出时间限制，可以使用"备忘录"的方式避免进行重复计算
    public static int fib_error(int n) {
        if (n > 1) { //大于1后使用F(N) = F(N - 1) + F(N - 2)这个递归公式
            return fib_error(n-2) + fib_error(n-1);
        }else if (n == 1) {
            return 1;
        }
        return 0;
    }

    //使用备忘录
    public static int[] mem;
    public static int fib(int n) {
        mem = new int[n + 1];
        return fibProduce(n);
    }

    //处理
    private static int fibProduce(int n) {
        if (n <= 1) return n;
        if (mem[n] !=0) return mem[n];
        mem[n] = fibProduce(n - 1) % 1000000007 + fibProduce(n - 2) % 1000000007;
        return mem[n] % 1000000007;
    }


    /*
     TODO 迭代法: 0,1,2
     int类型 -2147483648~2147483647
     n=45 预期结果--134903163
                  1134903170
     n=48 预期结果--
     807526948
     512559680
     */
    public static int fib2(int n) {
        int result = 0;
        int k1 = 0; //表示n-1
        int k2 = 0; //表示n-2
        for (int i = 0;i <= n;i++) {
            if (i == 1) {
                result = 1;
                k1 = 1;
            }else if (i > 1) {
                result = (k2 + k1) % 1000000007; //得到当前f(i)的结果，注意题目中记得取模，为了防止int类型越界
                k2 = k1; //将k1赋给k2作为下一个f(i+1)的f(n-2)的数字
                k1 = result; //将f(i)的结果赋给k1作为下一个f(i+1)的f(n-1)的数字
            }
        }
        return result;
    }

}
