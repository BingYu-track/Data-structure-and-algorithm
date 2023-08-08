package lessons.week12.slidingwindow.pratice.pratice4;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0  最小覆盖子串
 * @Description: 给你一个字符串s、一个字符串t。返回s中涵盖t所有字符的最小子串。如果s中不存在涵盖t所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 *
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 *
 * @author: bingyu
 * @date: 2023/7/26
 */
public class MinWindow {

    public static void main(String[] args) {
        MinWindow mw = new MinWindow();
        String s = "ADOBECODEBANC";
        String t = "ABC";
//        String s = "aa";
//        String t = "aa";
        String res = mw.minWindow(s, t);
        System.out.println(res);
    }



    /*争哥的思路和我是一样的
     在s中找包含t所有字符的最小子串,注意只要包含所有字符就行，不一定是连续的，无需和t一模一样，
     我们先构建一个和t长度一样的滑动窗口，然后判断是否包含t中的所有字符。
     1.不包含，q向后移动一位直到包含t中的所有字符
     2.包含，记录此时的字符串，然后p向后移动一位，减少字符长度，再看是否包含t中的所有字符
     3.注意字母的大小写，测试用例中，两个字符串可以是不同的大小写，这个要注意，因为要区分大小写，因此不能使用int数组来存储字符
     4.不仅要记录字符，还要记录字符出现的个数，因此还是要使用hashMap


     执行用时：255 ms, 在所有 Java 提交中击败了5.53%的用户
     内存消耗：43.5 MB, 在所有 Java 提交中击败了10.09%的用户
    */
    public String minWindow(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n < m) return "";
        String minStr = ""; //用来记录最小子串
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> matched = new HashMap<>();
        for (int i = 0;i<m;i++) { //记录t字符串中每个字符出现的次数
            char c = t.charAt(i);
            if (!need.containsKey(c)) {
                need.put(c,1);
            }else {
                need.put(c,need.get(c) + 1);
            }
        }
        int p = 0;
        int q = 0;
        matched.put(s.charAt(q),1);
        while (q < n) { //p可能等于q，因此不能用p<q这个作为循环条件，q滑动了一步说明前面不包含t子串了，因此q>=n就是循环的结束条件
            boolean flag = isAllContains(matched,need);
            if (flag) { //包含说明[p,q]区间字符包含子串t
                //第一次p，q包含t所有字符； 或者p,q字符长度小于minStr，就需要截取该区间的字符作为最小子串
                if (minStr.length() == 0 || (minStr.length()!=0 && (minStr.length() > q - p + 1))) { //比minStr大
                    minStr = s.substring(p, q+1);
                }
                matched.put(s.charAt(p),matched.get(s.charAt(p)) - 1); //然后滑动窗口p指针向后移动一位
                p++;
            }else { //不包含
                q++;
                if (q < n) matched.put(s.charAt(q) , (matched.get(s.charAt(q)) == null ? 0 : matched.get(s.charAt(q))) + 1);
            }
        }
        return minStr;
    }

    //判断matched是否包含need，matched存储的是主串s中各字符出现的个数，need是子串t中各字符出现的个数
    private boolean isAllContains(Map<Character, Integer> matched, Map<Character, Integer> need) {
        //还会出现一个问题，就是a和aa，list只要存储了a后，和aa匹配都会成功，尽管List里只有一个a，因此需要修改
        boolean flag = false;
        for (Character c : need.keySet()) { //遍历need
            Integer numNeed = need.get(c);
            Integer numMatch = matched.get(c);
            if (numMatch == null) {
                flag = false;
                break;
            }
            if (numNeed <= numMatch) {
                flag = true;
            }else {
                flag = false;
                break;
            }
        }
        //执行到这里如果flag为true，说明need里的所有字符出现的个数都小于等于matched
        return flag;
    }


}
