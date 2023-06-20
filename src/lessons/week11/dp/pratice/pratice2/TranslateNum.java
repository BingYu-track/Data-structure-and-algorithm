package lessons.week11.dp.pratice.pratice2;

/**
 * @version 1.0 剑指 Offer 46. 把数字翻译成字符串
 * @Description: 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 * 0 <= num < 2^31
 *
 * @author: bingyu
 * @date: 2023/6/19
 */
public class TranslateNum {

    public static void main(String[] args) {
        TranslateNum tn = new TranslateNum();
        int res = tn.translateNum(506);
        System.out.println(res);
    }



    /*
    p[i]表示长度为i的数字拥有的翻译方法；我的思路是每次只获取dp[i-1]的方法
      1  ->  b     1种
      12 ->  bc  m   2种
      122 -> bcc bw  mc 3种
      1225 ->bccf  bwf  mcf + bcz  mz 5种
      12258 -> bccfi", "bwfi", "bczi", "mcfi"和"mzi
    TODO  1.从上面的例子可以看到如果，新的数字和前面构成的数字小于等于25，则dp[i] = dp[i-1] + dp[i-2];
          2.如果新的数字不能和前一个数字构成小于等于25的，则dp[i] = dp[i-1]；也就是说状态转移方程依赖于
          3.注意特殊情况0，如果新添加的数字前面是0，也不能组成新的数字，此时dp[i] = dp[i-1]

      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：38.3 MB, 在所有 Java 提交中击败了50.67%的用户
    */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int length = s.length();
        int[] dp = new int[length + 1];
        //初始化dp[0]、dp[1]，不能初始化dp[2],因为dp[2]既可能有2种，比如12，也可能只有一种，比如58；dp[2] = dp[1] + dp[0]，因此要想等式
        // 成立，dp[0]必须赋值为1
        dp[0] = 1;
        dp[1] = 1;

        //后面直接从dp[2]开始处理
        for (int i = 2;i<=length;i++) { //i表示数字的长度
            int c1 = s.charAt(i - 1) - '0'; //第i-1位置的数字
            int c2 = s.charAt(i - 2) - '0'; //第i-2位置的数字
            int n = c2 * 10 + c1;
            if (n > 25 || c2==0) { //TODO: 大于25或者添加的数字前面的一个数字为0，说明后面不能组成新的字母，因此其翻译方法和之前的一样
                dp[i] = dp[i-1];
            }else {
                dp[i] = dp[i-1] + dp[i-2]; //TODO: 能组成新的，则当前翻译的方法等于前2次翻译方法相加
            }
        }
        return dp[length];
    }

}
