package lessons.week11.dp.pratice.pratice5;

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
        int res = m.minDistance("horse", "ros");
        System.out.println(res);
    }

    /*
    每次有下面三种决策
     1.最后一步插入字符到达word2
     2.最后一步删除字符到达word2
     3.最后一步替换字符到达word2
     定义dp[i][j]表示从word1的前i个字符转换成word2的前j个字符的最少操作步数。
      规则1: 当其中一个单词是空串时，其最少步数就是另外一个非空串单词的字符个数。
      规则2:
     */
    public int minDistance(String word1, String word2) {

        return 0;
    }

}
