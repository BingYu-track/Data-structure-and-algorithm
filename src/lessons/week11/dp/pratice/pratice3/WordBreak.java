package lessons.week11.dp.pratice.pratice3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0  单词拆分 TODO：dp解法我感觉还是有点迷糊，需要多次理解
 * @Description: 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 * 注意，你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 * @author: bingyu
 * @date: 2023/6/20
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
//        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//        String[] str = {"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};
//        String s = "aaaaaaa";
//        String[] str = {"aa","aaaa"};
        String s = "aaaaaaa";
        String[] str = {"aaa","aaaa"};
        List<String> list = Arrays.asList(str);
//        boolean res = wb.wordBreakDfs(s, list);
        boolean res = wb.wordBreak(s, list);
        System.out.println(res);
    }


    //1.dfs解题 思路，挨个扫描遍历字符，不构成单词就继续往后扫描，如果能构成单词，再从剩下的字符开始遍历，
    // 一个个扫描看能否形成单词，直到所有字符扫描完毕
    public boolean wordBreakDfs(String s, List<String> wordDict) {
        int[] visited = new int[s.length()]; //-1表示不匹配  1表示匹配  0表示未处理
        return dfs(0,s,wordDict,visited);
    }


    /*
    使用dfs解题发现超时了，这是因为其存在重复子问题,因此我们要记录所有重复子问题的结果
    例如: s= "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
         wordDict = ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
              aaaa
       0  /      |      \      \
        axxx    aaxx   aaax  aaaa
     1/   |2  \3        |        |
     axx aax aaa      a

        执行用时：5 ms, 在所有 Java 提交中击败了79.06%的用户
        内存消耗：42.4 MB, 在所有 Java 提交中击败了28.90%的用户
     */

    /**
     *
     * @param start 固定的起始字符下标
     * @param s
     * @param wordDict 字典数据
     * @param visited 用来记录访问过的下标位置值，目的是遇到重复处理过的位置时直接返回结果
     * @return
     */
    private boolean dfs(int start, String s, List<String> wordDict, int[] visited) {
        if (start == s.length()) {
            return true; //如果k能执行过掉最后一个字符，说明整体都是匹配的
        }
        StringBuilder sb = new StringBuilder();
        for (int i = start;i<s.length();i++) { //start为遍历的起始位置，i为遍历的终止位置
            //TODO： 重点1--剪枝 防止重复计算(为什么放在这里可以，放到下面for循环里就不行?因为for循环中i是结束下标，不是起始下标，我们应该从起始下标)
            if (visited[start] == 1) {
                return true;
            }
            if (visited[start] == -1) {
                return false;
            }
            sb.append(s.charAt(i));
            String str = sb.toString();
            if (wordDict.contains(str)) { //包含单词就从i+1开始作为起始位置扫描；不包含继续for循环
                boolean dfs = dfs(i+1, s, wordDict, visited);
                if (dfs) {
                    visited[start] = 1;
                    return true; //如果剩下的字符也都能匹配，直接返回true；如果是false，只能继续往后循环扫描
                }
            }
        }
        //TODO: 重点2: 执行到这里说明里面dfs返回的是false，起始位置start下面所有可能都遍历完毕，该位置开始无法进行整体匹配，继续下一个位置
        visited[start] = -1;
        return false;
    }





    /*
     判断单词是否能被字典里的单词拼接，单词不用全部使用，而且同一个单词可以重复使用，只要能凑成最终单词即可!
     暴力解法是将字典里的单词一个个到字符串s中进行比对，有匹配的，就继续后面是否匹配，直到字符串s都匹配完成，如果没有找到说明不匹配

     我的思路是，将字符串不断进行拆分成合格长度的单词看是否匹配，例如：applepenapple我先拆分为apple和penapple，发现长度比最长的单词还长，继续拆分
     TODO:
      dp[i]表示前i个字符构成的字符串是否能被字典单词匹配，dp[i] = dp[j] && check(j+1,i-1) (0<j<i)
      check(j+1,i-1)如何用dp进行表示,check(j+1,i-1)表示[j+1,i-1]范围的字符串是否存在字典中

      applepenapple wordDict = ["apple","pen"]
      dp[0] = true
      dp[1] = dp[0] && check(1 , );
      dp[13] = dp[4] && check(5,12)

        执行用时：1 ms, 在所有 Java 提交中击败了99.36%的用户
        内存消耗：39.6 MB, 在所有 Java 提交中击败了96.61%的用户
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length(); //字符串s的总长度
        boolean[] dp = new boolean[length + 1]; //dp[i]表示前i个字符组成的字符串s[0.....i-1]是否能被拆分成若干个字典中出现的单词
        //初始化值空字符可以被匹配
        dp[0] = true;
        Set<String> set = new HashSet();
        int maxw = 0; //记录字典中最长的单词长度
        for(String str : wordDict){
            set.add(str);
            maxw = Math.max(maxw, str.length());
        }
        for (int i = 1;i<dp.length;i++) { //i表示前i个字符
            for (int j = i;j >= 0 && i-j<=maxw;j--) { //j为分隔点，[j.....i-1]，用以构成不同的单词，倒叙遍历分隔点更快
                String substr = s.substring(j, i); //这里是从后往前分隔.i-j<=maxw是为了保证分隔时直到字典里的最长的单词就停止，再长字符的分割就没意义了，因为肯定不在字典里
                if (dp[j] && set.contains(substr)) { //TODO: 这里含义是在前i个字符的字符串中,只要前i-1能匹配，并且[]
                    //TODO: 1.dp[j]就是前面计算得到的前j字符是否能匹配字典单词，
                    // 2.wordDict.contains(substr)就是不断check(j+1,i-1)的过程
                    dp[i] = true; //这里为true表示在字符串s中前j个字符构成的单词能够和字典里进行匹配，记录结果后，继续根据dp[i]去推导dp[i+1]
                    break;
                }
            }
        }
        return dp[dp.length-1];
    }


}
