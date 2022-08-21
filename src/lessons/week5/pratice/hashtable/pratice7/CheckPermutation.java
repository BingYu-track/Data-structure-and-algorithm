package lessons.week5.pratice.hashtable.pratice7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0 面试题 01.02. 判定是否互为字符重排
 * @Description: 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 *
 * 示例 2：
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 *
 * 说明：
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * @author: bingyu
 * @date: 2022/8/11
 */
public class CheckPermutation {

    public static void main(String[] args) {
        String s1 = "bba";
        String s2 = "baa";
        String s3 = "bad";
        boolean f = CheckPermutation(s1, s2);
        System.out.println(f);
    }

    //TODO: 有排序、哈希表、位图方式解决 这题稍微有点难，需要多次练习
    /*
    我的思路:2个字符串的长度相等，并且每个字符都是相同的，仅顺序不同，这样就可以
      方法一: 排序    时间复杂度:O(n)  空间复杂度: O(1)
      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：39.2 MB, 在所有 Java 提交中击败了54.38%的用户
    */
    public static boolean checkPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i=0;i<s1.length();i++) {
            if (chars1[i]!=chars2[i]) {
                return false;
            }
        }
        return true;
    }

    //方法二: 哈希表
    /*  时间复杂度:O(M+N)m和n代表两链表的长度  空间复杂度:O(n)
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：39 MB, 在所有 Java 提交中击败了81.26%的用户
     */
    public static boolean checkPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        HashMap<Character,Integer> hashMap1 = new HashMap<>();
        HashMap<Character,Integer> hashMap2 = new HashMap<>();
        for (int i=0;i<s1.length();i++) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            int k1 = 0;
            int k2 = 0;
            if (hashMap1.get(c1) != null) {
                k1 = hashMap1.get(c1);
            }
            if (hashMap2.get(c2) != null) {
                k2 = hashMap2.get(c2);
            }
            hashMap1.put(c1,++k1);
            hashMap2.put(c2,++k2);
        }
        for (Map.Entry<Character, Integer> entry : hashMap1.entrySet()) {
            char key = entry.getKey();
            Integer value = entry.getValue();
            Integer time = hashMap2.get(key);
            if (!value.equals(time)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 哈希表leetcode官方题解
     */
    public static boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s1.length(); i++) { //记录s1中个字母出现的次数
            table[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) { //
            table[s2.charAt(i) - 'a']--; //遇到字母就减1
            if (table[s2.charAt(i) - 'a'] < 0) {
                //减去频次 如果出现table[i]<0，则说明s2包含一个不在s1中的额外字符，返回false 即可。
                return false;
            }
        }
        return true;
    }

}
