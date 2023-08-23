package leetcode.dp.WildcardMatching;

/**
 * @version 1.0  44. 通配符匹配
 * @Description: 给你一个输入字符串 (s) 和一个字符模式 (p) ，请你实现一个支持 '?' 和 '*' 匹配规则的通配符匹配：
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符序列（包括空字符序列）。
 * 判定匹配成功的充要条件是：字符模式必须能够 完全匹配 输入字符串（而不是部分匹配）。
 *
 *  abccd  a*
 * 示例 1：
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2：
 * 输入：s = "aa", p = "*"
 * 输出：true
 * 解释：'*' 可以匹配任意字符串。
 *
 * 示例 3：
 * 输入：s = "cb", p = "?a"
 * 输出：false
 * 解释：'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 *
 *
 * 提示：
 * 0 <= s.length, p.length <= 2000
 * s 仅由小写英文字母组成
 * p 仅由小写英文字母、'?' 或 '*' 组成
 * @author: bingyu
 * @date: 2023/8/22
 */
public class IsMatch {

    public static void main(String[] args) {
        IsMatch im = new IsMatch();
        boolean res = im.isMatch("aa", "a");
        System.out.println(res);
    }


    /*
     '?' 可以匹配任何单个字符。
     '*' 可以匹配任意字符序列（包括空字符序列）。
      1. p是字母，dp[i][j] = dp[i-1][j-1] && sc[i]==pc[j]
      2. p是?，dp[i][j] = dp[i-1][j-1]
      3. p是*，要想保证匹配就必须使*前面字符和s字符有匹配
        (1). *匹配空字符串
             dp[i][j] = dp[i][j-1]
        (2). *匹配s里1~n个字符
        sc[1...i-1]和pc[1....j]必须匹配
         dp[i][j] = dp[i-1][j]   abc ab*
         TODO：这里一直搞得不是很明白，为何是dp[i-1][j]而不是dp[i-1][j-1]? 可以这样理解因为abc和a* dp[3][2]=dp[2][2],即ab和a*也是匹配的
          k表示*可以表示s中字符的个数，既然s字符串中s[1...i-(0...k)]和p[1....j]匹配，那么说明前面没有被*代替的字符和p*前面的字符也是匹配的
          意思就是既然ab和a*匹配的，那么p字符串a*中*前面的字符a肯定是和abc字符串中没有被*代替的a字符串匹配

       如果上面的这种说法难以理解，也可以用下面这个等式来理解
       TODO:
        dp[i][j]=dp[i][j-1] || dp[i-1][j-1] || dp[i-2][j-1]       ||   dp[i-3][j-1] || dp[i-4][j-1] || ... || dp[i-k][j-1],直到k=i.
                 *表示空字符串    *表示s[i]一个字符  *表示s[i]和s[i-1]2个字符  ....................................  *表示s[0...i]所有字符，
          此时只要p[0...j-1]和s空字符串匹配即可，这些只要某个dp[i-k][j-1]==true，那么dp[i][j]=true，这个推导没毛病
        然后按照上面的公式，将i=i-1带入其中得到
        而dp[i-1][j] = dp[i-1][j-1] || dp[i-2][j-1] || dp[i-3][j-1] || dp[i-4][j-1] ||... || dp[i-k][j-1],直到k=i
        对比dp[i][j]与dp[i-1][j]，发现dp[i][j]比dp[i-1][j]多出的一个dp[i][j-1]，其他后面的一串都是一样的。
        因此得到：dp[i][j] = dp[i][j-1] || dp[i-1][j]
      时间14ms 击败 81.94%使用 Java 的用户
      内存42.33MB 击败 76.08%使用 Java 的用户
    */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length()+1];
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        //1.初始化
        dp[0][0] = true;
        //2.dp[i][0]既p是空字符串，肯定是false
        for (int i = 1;i<dp.length;i++) {
            dp[i][0] = false;
        }
        //3.dp[0][j],即s是空字符串，p从一个字符开始递增，只要*前面的s匹配，即dp[1...j-1]和空字符串s匹配，那么自然就能推导出 dp[0][j] = true;
        for (int j = 1;j<dp[0].length;j++) {
            if (pc[j-1]=='*' && dp[0][j-1]) {
                dp[0][j] = true;
            }
        }
        for (int i = 1;i<dp.length;i++) {
            int si = i-1;
            for (int j = 1;j<dp[0].length;j++) {
                int pi = j-1;
                if (pc[pi]=='?') {
                    dp[i][j] = dp[i-1][j-1];
                }else if (pc[pi]=='*') {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }else { //pc[pi]为普通字母
                    dp[i][j] = dp[i-1][j-1] && sc[si]==pc[pi];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /*
    设dp[i][j]表示s.substr(0,i)与p.substr(0,j)是匹配的



    */

}
