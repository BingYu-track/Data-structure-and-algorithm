package leetcode.dp.L072EditDistance;

/**
 * @version 1.0: 72. 编辑距离
 * @Description: 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
 * 你可以对一个单词进行如下三种操作：
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 示例 1：
 * 输入：word1 = "horse",
 *      word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention",
 *      word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *
 *
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 * @author: bingyu
 * @date: 2023/8/24
 */
public class MinDistance {

    public static void main(String[] args) {
        MinDistance m = new MinDistance();
        String word1 = "sea";
        String word2 = "eat";
        int res = m.minDistance2(word1, word2);
        System.out.println(res);
    }

    /*TODO: 为什么之前做过的题，我再做一遍还是没思路? 说明我还是没有真正的理解
      插入、删除、替换，求word1变word2的最少操作数
      1.定义dp[i][j]表示word1以第i个字符结尾的子串到word2以第j字符结尾的子串匹配，用的最少操作数
       两个字符串长度不一样
      2.初始化--两个空字符串

      3.动态转移方程
      开始肯定是一个字符一个字符的进行比较，字符相同不会增加操作数，只有当字符不同时需要进行操作
      abcd 和 acd  这里我不知道是该替换还是删除?
      TODO:注意这里有个误区，我们不需要判断是做删除还是替换，因为word1 > word2只有这两种操作，因此,我们只需要分别求出删除操作的最小操作数和替换
       操作的最小操作数，然后进行比较即可！
      当遇到不同的字符个数时
      (1) 因为dp[i-1][j]表示word1[1~i-1]和word2[1~j]匹配的操作数，word2的字符都已经用完了，word2还剩最后一个字符，
          那么如果想word1[1~i-1]和word2[1~j]并强行保持匹配，那么就只有将word1[i]这个字符删除。(word1.length > word2.length)

      (2) 同理dp[i][j-1]表示word1[1~i]和word2[1~j-1]匹配的操作数，word1的字符都已经用完了，word2还剩最后一个字符word2[j]
          那么如果想word1[1~i] 和 word2[1~j]强行保持匹配，只有将word1插入一个和word2[j]一样的字符才行
         (word1.length < word2.length) 至少存在一次插入操作(替换或者插入)

      (3) dp[i][j] = dp[i-1][j-1] + 1   (word1.length == word2.length)肯定都是替换操作，因为增加对应后面还有删除，删除对应后面还要增加，
          徒劳增加操作次数.TODO： 这个想法是错的，就算两个单词长度相同，替换操作也不一定是最小的操作数，例如sea和eat。替换需要3次，但是先删除s再插入t只要
                             两次！因此下面if对单词长度判断处理的代码是错误的，应该所有操作都要进行一次比较!
    */
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        //初始化
        dp[0][0] = 0;
        //dp[0][j]
        for (int j = 1;j < word2.length()+1;j++) {
            dp[0][j] = j;
        }
        //dp[i][0]
        for (int i = 1;i < word1.length()+1;i++) {
            dp[i][0] = i;
        }
        for (int i = 1;i<dp.length;i++) {
            for (int j = 1;j<dp[0].length;j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                //TODO:我当时为何要这样分类处理呢？
                if (c1==c2) {
                    dp[i][j] = dp[i-1][j-1]; //字符相同，不需要增加操作TODO:注意这个别忘了
                }else {
                    if (word1.length() == word2.length()) { //1.两个单词长度相等，只有替换操作才是最小的 TODO：错误的想法
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }else if (word1.length() > word2.length()) { //2.word1长度大于word2长度，只有删除或者替换操作才是最小的操作数， TODO：错误的想法
                        dp[i][j] = Math.min(dp[i-1][j-1] + 1 , dp[i-1][j] + 1);
                    }else {      //3.word1长度小于word2长度，只有插入或者替换操作才能得到最小的操作数 TODO：错误的想法
                        dp[i][j] = Math.min(dp[i-1][j-1] + 1 , dp[i][j-1] + 1);
                    }
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    /*

    */
    public int minDistance2(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        //初始化
        dp[0][0] = 0;
        //dp[0][j]
        for (int j = 1;j < word2.length()+1;j++) {
            dp[0][j] = j;
        }
        //dp[i][0]
        for (int i = 1;i < word1.length()+1;i++) {
            dp[i][0] = i;
        }
        for (int i = 1;i<dp.length;i++) {
            for (int j = 1;j<dp[0].length;j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                //TODO:我当时为何要这样分类处理呢？
                if (c1==c2) {
                    dp[i][j] = dp[i-1][j-1]; //字符相同，不需要增加操作TODO:注意这个别忘了
                }else {
                    //字符不同
                    dp[i][j] = Math.min(dp[i-1][j-1] + 1 , Math.min(dp[i-1][j] + 1 , dp[i][j-1] + 1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

}
