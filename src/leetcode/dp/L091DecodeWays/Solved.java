package leetcode.dp.L091DecodeWays;

/**
 * @version 1.0  91.解码方法
 * @Description: 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * 要 解码 已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
 * "AAJF" ，将消息分组为 (1 1 10 6)
 * "KJF" ，将消息分组为 (11 10 6)
 * 注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
 * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。
 * 题目数据保证答案肯定是一个 32 位 的整数。
 *
 * 示例 1：
 * 输入：s = "12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 *
 * 示例 2：
 * 输入：s = "226"
 * 输出：3
 * 解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * 示例 3：
 * 输入：s = "06"
 * 输出：0
 * 解释："06" 无法映射到 "F" ，因为存在前导零（"6" 和 "06" 并不等价）。
 *
 *
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含数字，并且可能包含前导零。
 * @author: bingyu
 * @date: 2023/8/28
 */
public class Solved {

    public static void main(String[] args) {
        Solved n = new Solved();
        String s = "2201";
        int res = n.numDecodings(s);
        System.out.println(res);
    }


    /*
     官方题解，推荐该方法
    */
    public int numDecodings(String s) {
        if (s.length()==0) return 0;
        int n =s.length();
        s = "#" + s; //这里在前面加一个字符是为了作为一个哨兵，这样dp[i]表示的就是字符串s的第i位所代表的结果，而且，可以避免dp[i-2]越界
        int[] dp = new int[n+1];

        //初始化
        dp[0] = 1;
        dp[1] = s.charAt(1)=='0'? 0 : 1; //如果是0字符，就没有解，否则

        for (int i=2; i<=n; i++) {
            //第i位置字符不为0，说明该字符是可以单独存在的，因此可以以dp[i-1]的组合数为基础
            if (s.charAt(i)!='0') {
                dp[i] += dp[i-1];
            }

            //如果第i位置字符在0~6范围，i-1位置字符在1~2范围，那么i-1和i这2个字符可以构成一个合法的组合，这样我们又可以以dp[i-2]的组合数作为基础
            if (s.charAt(i)>='0' && s.charAt(i)<='6' && (s.charAt(i-1)=='1'||s.charAt(i-1)=='2')) {
                dp[i] += dp[i-2];
                //第i位置字符在7~9范围，并且第i-1位置字符是1，那么同样可以组合成合法的数字，因此也可以以dp[i-2]的组合数作为基础
            } else if (s.charAt(i)>='7' && s.charAt(i)<='9' && s.charAt(i-1)=='1') {
                dp[i]+=dp[i-2];
            }

            // cout<<i<<" "<<dp[i]<<endl;
        }

        return dp[n];
    }


}
