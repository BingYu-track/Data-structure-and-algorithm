package lessons.week10.dp.models.match.pratice;

/**
 * @version 1.0 最长公共子序列(需要多次复习)
 * @Description: 给定两个字符串text1和text2，返回这两个字符串的最长公共子序列 的长度。如果不存在 公共子序列,返回 0。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但"aec"不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和text2 仅由小写英文字符组成。
 *
 * @author: bingyu
 * @date: 2023/7/5
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
//        String text1 = "ezupkr";
//        String text2 = "ubmrapg";
        String text1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String text2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        int res = lcs.longestCommonSubsequence(text1, text2);
        System.out.println(res);
    }

    //

    /*
     TODO：注意子序列和子串的定义，子序列是主串中的字符构成序列，这些字符不一定是连续的，但是相对顺序一定是保持不变，子串必须是连续的
      例如:  abcde主串中的 ace是子序列，而不是子串，因为不是连续的；还有的题目是求的最长公共子串
      子串：原序列中必须连续的一段
      子序列：原序列中可以不连续的一段
       注意：无论是子串和子序列，元素的顺序都是原序列中的顺序

       开始寻找状态转移方程；
       如果t1[i] == t2[j]，即两个主串中有i和j位置的字符是匹配的，则可能有下面三种决策:
       1. (i+1,j+1)  各向后移动一位去匹配
       2. (i+1,j不变) i向后移动一位去匹配
       3. (i不变,j+1) j向后移动一位去匹配

       如果t1[i] != t2[j]，即两个主串中有i和j位置的字符不匹配，同样有上面那三种决策
       (i+1,j+1)、(i+1,j不变)、(i不变,j+1)
       因此到达(i,j)这个状态，即开始匹配t1[i]和t2[j]只有可能从上一个阶段的(i-1,j-1)、(i-1,j)、(i,j-1)这几个状态转移过来
       定义: dp[i][j]表示长度为i的t1子串和长度是j的t2子串的最长公共子序列长度也就是说: t1[0~i-1]和t2[0~j-1]的最长公共子序列长度

       TODO： 那么其状态转移方程为:(注意看的是看末尾两个字符)
         注意官方题解上dp[i][j]表示的是text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列，注意这里的i和j表示的不是下标，表示的是text1的前i个字符
         以及text2的前j个字符。
         例如abe 和 abde   dp[2][3] 表示的是text1[1] 和 text2[2]

       TODO: 前面定义i,j定义前i个字符和前j个字符比较难理解，下面是我重新进行的定义
        自定义dp[i][j]表示的是text1[0,i]和text2[0,j]下标的最长公众子序列，i和j为字符串中的下标，
        1.dp[i][j] = dp[i-1][j-1] + 1                (text1[i]=text2[j])
           以abe 和 abde为例ab和abd，最后一个元素都是e即，后面一位是匹配的字符
        2.dp[i][j] = max(dp[i-1][j],dp[i][j-1])      (text1[i]!=text2[j])
           以abc 和 abde为例


        问1: 在text1[i] == text2[j]的情况下，当前字母选择不匹配的情况下，dp[i-1][j]或者dp[i][j-1]有没有可能超过dp[i-1][j-1] + 1?
        答: 可以考虑用反证法证明，假设max(dp[i-1][j], dp[i][j - 1])超过了dp[i - 1][j - 1] + 1, 这时候不妨设dp[i - 1][j]具有最长子序列x，
            并且其长度超过dp[i - 1][j - 1] + 1。
           (1).我们可以看到dp[i - 1][j]就是比dp[i - 1][j - 1]多包含一个text2[j]元素，因此dp[i - 1][j]的最长公共子序列
                顶多就是dp[i - 1][j - 1]加上text2[j]这最后一个元素，即dp[i - 1][j]<=dp[i - 1][j - 1] + 1，不可能大于
                dp[i - 1][j - 1] + 1，同理dp[i][j-1]也是这样。

           (2).当 text1[i] != text2[j] 时，说明两个子字符串的最后一位不相等,此时的状态 dp[i][j] 应该是 dp[i - 1][j]和dp[i][j - 1]
           的最大值,因为最后一个字符不相等，如果取dp[i-1][j-1]其子串的范围少于子串text1[0~i-1]和子串text2[0~j]匹配的范围，因此只可能从
           dp[i - 1][j]和dp[i][j - 1]去找匹配的公共最长子序列，而不是从dp[i - 1][j-1]去找。


        问2: 当前text1[i]!=text2[j]会不会存在dp[i][j]=dp[i-1][j]+1或者dp[i][j]=dp[i][j-1]+1的这种情况呢？
        答: 如果text1[i]=text2[j]，那么最长的公共子序列一定是dp[i-1][j-1] + 1，这个没有任何疑问。
        我们以abe和abde为例，dp[2][3] = dp[2][2] + 1 看看这个情况是否成立? 乍一看好像是这么回事
                             abe      abe abd
        但是发现这是建立在text1[2]=text2[3]的基础上的，即dp[i][j] = dp[i][j-1] + 1 (text1[i]=text2[j])
        由于text1[i]=text2[j]，同样知道dp[i][j] = dp[i-1][j-1] + 1，所以text1[i]!=text2[j]是不可能存在
        dp[i][j]=dp[i-1][j]+1或者dp[i][j]=dp[i][j-1]+1这种情况的，如果有加1，就说明text1[i]=text2[j]
        TODO: 即dp[i][j] = dp[i-1][j-1] + 1 (text1[i]=text2[j]) 是包含 dp[i][j-1] + 1这种情况的
        执行用时：19 ms, 在所有 Java 提交中击败了66.38%的用户
        内存消耗：47.5 MB, 在所有 Java 提交中击败了39.97%的用户

    */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2]; //因为我dp[i][j]定义的是下标，因此空间只需要和原数组空间相等即可
        //该如何进行初始化?
        // 1个字符和任何一个字符匹配长度都只有1,i表示text1的下标，j表示text2的下标
        for (int j = 0;j<len2;j++) {
            if (text1.charAt(0) == text2.charAt(j)) { //匹配，赋值为1，因为只有text1的首字符字符和后面的匹配，无轮怎么匹配，长度永远是1
                    dp[0][j] = 1;
            }else {
                if (j-1>=0) {
                    dp[0][j] = dp[0][j-1]; //不匹配，和原来长度一样不变
                }
            }
        }
        for (int i = 0;i<len1;i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            }else {
                if (i-1>=0) {
                    dp[i][0] = dp[i-1][0]; //不匹配
                }
            }
        }
        //开始进行状态转移
        for (int i = 1;i<len1;i++) {
            for (int j = 1;j<len2;j++) { //i和j进行匹配
                if (text1.charAt(i) == text2.charAt(j)) { //dp[i-1][j]==0是为了
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1-1][len2-1];
    }



}
