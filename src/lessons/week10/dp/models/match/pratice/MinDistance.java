package lessons.week10.dp.models.match.pratice;

/**
 * @version 1.0 编辑距离
 * @Description: 给你两个单词word1 和word2， 请返回将word1转换成word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 * @author: bingyu
 * @date: 2023/6/29
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance m = new MinDistance();
        int res = m.minDistance("intention", "execution");
        System.out.println(res);
    }

    /*
    每次有下面三种决策 dp[i-1][j]
     1.word1子串最后一步插入字符到达word2，说明此时word1的长度低于word2
     2.word1子串最后一步删除字符到达word2，说明此时word1的长度大于word2
     3.word1子串最后一步替换字符到达word2，说明此时word1的长度等于word2
     定义dp[i][j]表示从word1的前i个字符转换成word2的前j个字符的最少操作步数。
    TODO:这题的重点就是如何用方程表示"插入、删除、替换"操作！
     注意我最开始的定义dp[i][j]表示word1[0~i-1]子串转换到word2[0~j-1]所操作的最少操作数，
     1.因此dp[i-1][j-1]表示的就是表示word1[0~i-2]子串转换到word2[0~j-2]所操作的最少操作数，因此word1的前i-1个字符已经变换到word2的
       前j-1个字符的次数，说明word1的前i-1个和word2的前j-1个字符已经完成操作；那么对于word1的第i个怎么变成word2的第j个呢？这两个字符都存在，
       那么只能是替换了；所以当word1[i-1]!=word2[j-1]时，dp[i][j] = dp[i-1][j-1]+1; 否则dp[i][j] = dp[i-1][j-1]
   TODO:
     2.dp[i][j-1] (表示插入)：word1的前i个字符已经变换到word2的前j-1个字符的次数，当前word1的第i步字符都已经用完了，但是word2还差一个字符
     （因为当前只是处理了word2的前j-1个字符），那么插入一个字符就好了；所以dp[i][j] = dp[i][j-1]+1;
   TODO:
     3.dp[i-1][j] (表示删除)： word1的前i-1个字符已经变换到word2的前j个字符的次数，当前word2的最后一个位置j字符已经用了，但是word1
       还差一个字符，那么必须将word1删除最后一个字符就好了；所以dp[i][j] = dp[i-1][j]+1;

        执行用时：5 ms, 在所有 Java 提交中击败了43.50%的用户
        内存消耗：42.5 MB, 在所有 Java 提交中击败了36.61%的用户
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1 == 0) return len2; //word1为空的情况
        if (len2 == 0) return len1; //word2为空的情况
        int[][] dp = new int[len1 + 1][len2 + 1];

        //初始化
        dp[0][0] = 0;
        for (int j = 0;j<=len2;j++) { //word1为空，到达word2的步数(为插入len2的个数)
            dp[0][j] = j;
        }
        for (int i = 0;i<=len1;i++) { //word1[0~len1]动态字符串，到达word2为空字符串的步数(为删除len1的个数)
            dp[i][0] = i;
        }

        for (int i = 1;i<=len1;i++) { //
            for (int j = 1;j<=len2;j++) { //
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                //(1).如果从dp[i - 1][j]过来的，想要到达word2[j]必须在word1删除一个字符(无论最后一个字符是否相等)，因此需要加1
                int x = dp[i - 1][j] + 1;
                //(2).如果从dp[i][j-1]过来的，想要到达word2[j]必须在word1插入一个字符，因此操作数也需要加1
                int y = dp[i][j-1] + 1;
                //(3).如果从dp[i-1][j-1]过来的，说明是替换(最后一个字符如果相等，就不需要进行额外操作)
                int z = dp[i-1][j-1];
                if (c1!=c2) { //末尾字符不一样，需要进行替换操作，操作数加1
                    z++;
                }
                dp[i][j] = Math.min(z,Math.min(x,y));
            }
        }
        return dp[len1][len2];
    }

}
