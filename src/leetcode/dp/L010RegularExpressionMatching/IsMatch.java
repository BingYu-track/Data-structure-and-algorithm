package leetcode.dp.L010.RegularExpressionMatching;


/**
 * @version 1.0  10.正则表达式匹配
 * @Description: 给你一个字符串s和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 *
 *
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3：
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 提示：
 * 1 <= s.length <= 20
 * 1 <= p.length <= 20
 * s 只包含从 a-z 的小写字母。
 * p 只包含从 a-z 的小写字母，以及字符.和 *。保证每次出现字符 * 时，前面都匹配到有效的字符
 * @author: bingyu
 * @date: 2023/8/17
 */
public class IsMatch {

    public static void main(String[] args) {
        IsMatch im = new IsMatch();
//        boolean res = im.isMatch("abbba", "ab*.");
//        boolean res = im.isMatch("abc", ".*");
//        boolean res = im.isMatch("abcd", ".*d");
        boolean res = im.isMatch("cab", "c*a*b");
        //abc  a.c* true
        //ab   a.c* true
        System.out.println(res);
    }


    /*
     正则表达式匹配: dp[i][j]就代表s[1...i]和p[1...j]的字符串是否匹配。(以1 ~ index为起点)
     假设dp[i][j]=true 那么dp[i][j]由哪一个推导出来?

    时间：1ms击败 99.50%使用 Java 的用户
    内存：39.22MB击败 31.88%使用 Java 的用户
    */
    public boolean isMatch(String s, String p) {
        //TODO: 这里加1，是为了让dp[0][0]表示s和p都是空字符串，如果没有这个，就会漏掉空字符串比较，比如b和c*a*b；如果不加1  dp[0][4]依赖于空字符串和c*a*
        // 此时会发现，不加1的话，dp[0-1]就成了负数了，因此我们dp数组需要进行加1，dp[0][0]表示空字符串进行比较！
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        //1.初始化,两个空字符串当然匹配
        dp[0][0] = true;
        //2.p字符是空字符串，s字符不断增加，由于s字符全是字母，因此，这样的组合一定是false
        for (int i = 1;i<dp.length;i++) {
            dp[i][0] = false;
        }
        //3.s字符是空字符串，p字符不断增加，由于p可能存在*，因此即使字符长度不同，也可能会匹配
        for (int j = 1;j<dp[0].length;j++) {
            //解释: pc[j-1]=='*'表示对应p字符当前位置是*(注意这里j=1表示p字符下标的0)，dp[0][j-2]表示*前2位和s空字符串匹配
            if (pc[j-1]=='*' && j-2>=0 && dp[0][j-2]) {
                dp[0][j] = true;
            }
        }

        //2.开始进行状态转移方程: dp[0][2]
        for (int i = 1;i<=s.length();i++) {
             int si = i - 1;
            for (int j = 1;j<=p.length();j++) {
                int pi = j - 1;
                if (pc[pi] == '.') {
                    dp[i][j] = dp[i-1][j-1]; //为pc[j]"."，只需要pc前j-1个字符和sc的前i-1个字符匹配即可
                }else if (pc[pi] == '*') { //2和4
                    //(1).*重复零次,说明p的*和*前面字母与sc[i]不匹配!
                    boolean t1 = false;
                    if (j-2>=0) t1 = dp[i][j-2];
                    //(2).重复1~n，此时s的字符和p的*字符前1个字符匹配，说明除s[i]之外，前面的字符串也必须与p[1..j]匹配，可以这样理解
                    //由于p的*字符前1个字符与sc[i]匹配了，最少*代表出现一次。那么前面s[1...i-1]字符肯定要与p[1...j]字符串匹配，才行，因为
                    //就算s[i-1]!=s[i] p的*对于s[i-1]比较也没有影响，因为*可以出现零次。如果s[i-1]==s[i]，那么更是匹配了！
                    boolean t2 = (sc[si]==pc[pi-1] || pc[pi-1]=='.') && dp[i-1][j];
                    dp[i][j] = t1 || t2;
                }else { //字母
                    dp[i][j] = dp[i-1][j-1] && sc[si]==pc[pi];
                }
            }
        } //aa 和 c*a*
        return dp[s.length()][p.length()];
    }


}
