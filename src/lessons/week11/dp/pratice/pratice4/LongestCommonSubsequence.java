package lessons.week11.dp.pratice.pratice4;

/**
 * @version 1.0 最长公共子序列
 * @Description: 给定两个字符串text1 和text2，返回这两个字符串的最长公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
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
 * @date: 2023/6/21
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
//        String text1 = "ezupkr";
//        String text2 = "ubmrapg";
        String text1 = "oxcpqrsvwf";
        String text2 = "shmtulqrypy";
        int res = lcs.longestCommonSubsequence(text1, text2);
        System.out.println(res);
    }


    /*
      思路，我dp一开始定义就错了吗
     正确的dp定义: dp[i][j] 表示在text1[0,i]区间的序列和在text2[0,j]区间序列的最长公共子序列
      i=0,j>0时，text1序列为空，没有公共序列dp[0][j] = 0;
      i>0,j=0时，text2序列为空，没有公共序列dp[i][0] = 0;
      i=0，j=0,dp[0][0] = 0;

     TODO: i>0,j>0时
      如果序列最后一个字符,text1和text2是相等的，那么最长公共序列dp[i][j] = dp[i-1][j-1] + 1; dp[i-1][j-1]表示去除最后一个字符的最长公共序列
      如果序列最后一个字符，text1和text2不相等，那么dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
      如何理解dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]) 这个式子？ dp[i-1][j]表示i-1长度的text1和j长度的text2构成的最长公共序列
      例如 text1: acf                ac         acf
                    最后一位不同---->
          text2: cfe                cfe        cf
        由于最后一位不相同，因此只能继承Math.max(dp[i-1][j] , dp[i][j-1])

        执行用时：19 ms, 在所有 Java 提交中击败了66.29%的用户
        内存消耗：47.3 MB, 在所有 Java 提交中击败了80.60%的用户
    */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];
        //初始化
        for (int i = 0;i<=len1;i++) {
            dp[i][0] = 0;
        }
        for (int j = 0;j<=len2;j++) {
            dp[0][j] = 0;
        }
        //从i=1,j=1开始处理
        for (int i = 1;i<=len1;i++) {
            for (int j = 1;j<=len2;j++) { //text1子序列和text2子序列不断进行组合
                if (text1.charAt(i-1)==text2.charAt(j-1)) { //如果最后一个字符相等
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    //不相等
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }


}
