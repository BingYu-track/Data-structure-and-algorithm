package lessons.week5.pratice.hashtable.pratice9;

/**
 * @version 1.0 有效的字母异位词
 * @Description: 给定两个字符串 s 和 t，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * 注意：若s和t中每个字符出现的次数都相同，则称s和t互为字母异位词。
 *
 * 示例1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 *
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 *
 * 提示:
 * 1 <= s.length, t.length <= 5 * 104
 * s 和 t仅包含小写字母
 *
 * @author: bingyu
 * @date: 2022/8/12
 */
public class IsAnagram {

    public static void main(String[] args) {
        String s = "rat";
        String t = "car";
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }

    /**
     * 我的思路: 同上题的思路一样
     * 执行用时：4 ms, 在所有 Java 提交中击败了37.35%的用户
     * 内存消耗：41.4 MB, 在所有 Java 提交中击败了72.59%的用户
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length()!=t.length()) return false;
        int[] arr = new int[26];
        for (int i = 0;i<s.length();i++) {
            arr[s.charAt(i) - 'a']++;
        }
        for (int j = 0;j<t.length();j++) {
            arr[t.charAt(j) - 'a']--;
            if (arr[t.charAt(j) - 'a'] < 0) return false; //TODO:注意不管t如何变化，如果字符出现次数不一样，肯定会出现小于0的情况
        }
        return true;
    }

}
