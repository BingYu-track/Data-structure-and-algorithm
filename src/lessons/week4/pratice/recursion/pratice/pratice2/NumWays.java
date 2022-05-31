package lessons.week4.pratice.recursion.pratice.pratice2;

/**
 * @version 1.0 剑指 Offer 10- II. 青蛙跳台阶问题
 * @Description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个第n级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e^9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 *
 * 提示：
 * 0 <= n <= 100
 *
 * @author: bingyu
 * @date: 2022/5/16
 */
public class NumWays {

    public static void main(String[] args) {
        int kind = numWays2(48);
        System.out.println(kind);
    }

    /*  TODO: 我的思路是用排列组合公式求解，但是这样的解法是错误的，因为涉及阶乘，阶乘从14！开始就已经超过了int数据类型，导致计算结果不对，因此不
              要使用该方法！
       1,2,3,4,5,6 = 11112 11121 11211 12111 21111 1个2(n-1)种
                     1122 1221 2211 2121 2112 1212 2个2() 4!/2!(4-2)! = 4*3*2/(2*2)=24/4=6 (使用排列组合公式，求两个2在4个位置的排列组合)
                     ........................      n个2() C
       1,2,3,4,5 = 1112 1121 1211 2111
                   122 212 221 3!/2!(1)! =

      f(0)=1、f(1)=1 f(2)=2 f(3)=3(1) f(4)=5(3) f(5)=8(6) f(6)=13(11)

      1.每次跳1步(固定)
      2.不固定的是:在之间包含1步和2步的不同组合排列
      3.每次跳2步(固定)
      1932053504 / 479001600
     */
    public static int numWays_error(int n) {
        int result = 0;
        int k = n / 2; //得到最多有多少个2
        for (int i=1;i<=k;i++) { //这里i表示的是当前指定有多少个2
            int postion = n - i*2 + i; //一共所处的位置
            //注意这里阶乘会数据会很大，轻松超过int的数据类型，所以导致计算结果错误
            int j = factorial(postion);
            int j1 = factorial(i);
            int j2 = factorial(postion - i);
            int currentResult = j/(j1 * j2);   //postion!/i!(postion-i)!;
            result += currentResult;
            result = result % 1000000007;
        }
        return result + 1;
    }

    //求阶乘
    public static int factorial(int number) {
//        if (number >= 1000000007) {
//            number = number  % 1000000007;
//        }
        if (number > 1) {
            return number * factorial(number - 1);
        }
        return 1;
    }

    /*
    TODO: 推荐1  使用争哥的思路，递归进行解决
            f(n)我们知道踏出第一步时只有两种可能，要么跳1步，要么跳2步；
                1.假设我们第一步是跳了1步开始，假设1步后总共有k1种跳法
                2.同样我们假设我们第一步跳的是2步，假设2步后总共有k2种跳法
            那么可以得到f(n) = k1 + k2;同时我们知道跳了1步后，就剩下了n-1个台阶，那么n-1个台阶有多少走法？就相当于求f(n-1)；
            同理第一步跳2步，后面就是求f(n-2)因此，这里拆分成了2个子问题，我们可以用递归来解决
     */
    private static int[] mem; //使用全局变量用来记录每次递归的结果，避免重复计算
    public static int numWays(int n) {
        mem = new int[n + 1]; //因为数组是从0开始，因此我们需要加1，使其末尾下标等于n，TODO:千万注意备忘录必须套在递归函数外面
        return climb(n);
    }

    public static int climb(int n) {
        //mem = new int[n + 1]; 不能将"备忘录"放到递归函数，因为每次递归调用，备忘录会被改变
        if (n == 0 || n == 1){
            return 1;
        }
        if (mem[n]!=0) {
            return mem[n];
        }
        mem[n] = climb(n - 1) % 1000000007  + climb(n - 2) % 1000000007; //每次都要进行取模
        return mem[n] % 1000000007; //对结果取模
    }


    /*
     TODO 推荐方法二--使用迭代求解
     */
    public static int numWays2(int n) {
        int result = 0;
        int k1 = 0; //n-1
        int k2 = 0; //n-2
        for (int i = 0;i<=n;i++) {
            if (i<2) {
                result = 1;
                k1 = 1;
                k2 = 1;
            }else {
                result = (k1 + k2) % 1000000007;
                k2 = k1;
                k1 = result;
            }
        }
        return result;
    }

}
