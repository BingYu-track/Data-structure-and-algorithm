package lessons.week12.slidingwindow.pratice.pratice2;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 1.0  剑指 Offer 48. 最长不含重复字符的子字符串
 * @Description: 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 示例1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 *
 * 请注意，你的答案必须是子串的长度，"pwke"是一个子序列，不是子串。
 * 提示：
 * s.length <= 40000
 *
 * @author: bingyu
 * @date: 2023/7/24
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();
        String s = "abcabcbb";
        int res = lols.lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    /*
      计算出不包含连续字符的子串,i指针和j指针，注意，必须是连续的
     我的思路；
      1.当i和j之间字符没有重复的，则j继续向后移动，长度加1
      2.如果遇到重复的字符，i++，去除前面字符直到不再有重复字符为止，然后j继续向后移动
      执行用时：9 ms, 在所有 Java 提交中击败了16.77%的用户
      内存消耗：43.1 MB, 在所有 Java 提交中击败了5.01%的用户
    */
    public int lengthOfLongestSubstring(String s) {
        if (s.equals("")) return 0;
        int i = 0;
        int j = i + 1;
        int k = j;
        int maxLength = 1; //最小的长度肯定是1，就是所有字符都相同
        int length = 1;
        int len = s.length();
        while (i <= j && i<len && j<len) { //注意
            String substr = s.substring(i, j);
            String s1 = s.charAt(k) + "";
            if (!substr.contains(s1)) { //不包含,j向后移动一位
                j++;
                length++;
                maxLength = Math.max(maxLength,length);
                k = j;
            }else { //说明包含重复的字符，i指针向后移动一位直到后面重复的元素被移除，才能继续让j移动
                i++;
                length--;
            }

        }
        return maxLength;
    }

    /*
     争哥解法：使用的HashSet代替我的subStr，基本思路和我是一致的
    */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int p = 0;
        int q = 0;
        Set<Character> set = new HashSet<>(); //用来存储[p,q]之间连续的字符
        int maxLen = 0;
        while (q < n) {
            char c = s.charAt(q);
            if (!set.contains(c)) { //不包含
                set.add(c);
                q++;
                if (q-p > maxLen) maxLen = q-p;
                continue;
            }
            while (set.contains(c)) { //如果包含，就一直让前面的指针向后移动，直到去除重复的元素
                set.remove(s.charAt(p));
                p++;
            }
        }
        return maxLen;
    }
}
