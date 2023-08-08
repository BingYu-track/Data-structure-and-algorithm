package lessons.week12.slidingwindow.pratice.pratice3;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  438. 找到字符串中所有字母异位词
 * @Description: 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 * 1 <= s.length, p.length <= 3 * 10^4
 * s和p仅包含小写字母
 *
 * @author: bingyu
 * @date: 2023/7/25
 */
public class FindAnagrams {

    public static void main(String[] args) {
        FindAnagrams f = new FindAnagrams();
//        String s = "cbaebabacd";
//        String p = "abc";
//        String s = "abab";
//        String p = "ab";
        String s = "acdcaeccde";
        String p = "c";
        List<Integer> result = f.findAnagrams(s, p);
        System.out.println(result);
    }

    /**
     * 思路: 建立一个子串长度相等的滑动窗口，再建立一个一一映射的数组，滑动窗口不断向后移动，根据滑动窗口的开始和末尾2个字符可以推导出下一步的一一
     * 映射数组没这样每次滑动时毕竟一一映射数组和最开始p对应的映射数组，如果一致说明就是异位词
     * @param s 主串
     * @param p 子串
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (p.length() > s.length()) return result;
        int[] need = new int[26]; //保存p子串个字符出现的次数
        for (int i = 0;i<p.length();i++) {
            need[p.charAt(i) - 'a']++;
        }
        int[] matched = new int[26];
        int start = 0;
        int end = p.length() - 1;
        //在主串从0下标开始记录与p相同长度字符出现的个数
        for (int i = start;i<p.length();i++) {
            matched[s.charAt(i) - 'a']++;
        }
        while (start <= end && end < s.length()) { //这里要用<=，因为p可能就是1个字符，如果用<符合，直接就进不了循环里了
            boolean same = isSame(need, matched);
            if (same) {
                result.add(start);
            }
            //滑动窗口向后移动
            matched[s.charAt(start) - 'a']--;
            start++;
            end++;
            if (end < s.length()) { //这里以防止end+=后越界
                matched[s.charAt(end) - 'a']++;
            }
        }
        return result;
    }

    /*
    TODO: 这题有3种解决方法:
          1.排序
          2.hash表(hash表一节里有判断异位词 lessons.week5.pratice.hashtable.pratice9.IsAnagram)
          3.数组一一映射，也就是我们下面这个方法(因为题目提到只包含小写字母，如果还包含大写字母和其它符合，就不能用该方法了)

     异位词 指由相同字母重排列形成的字符串（包括相同的字符串），即字符个数一样，不同的字符数目也是一样的，仅仅是排列不一样
     ,  由于字符个数必须一样，并且字符种类数目也必须一样
        cbaebabacd
        abc
      思路:核心思路滑动窗口，根据前面一个一一映射数组来构建下一个一一映射数组，只需要处理两个字符
       我们需要在字符串s寻找字符串p的异位词。因为字符串p的异位词的长度一定与字符串p的长度相同，所以我们可以在字符串s 中构造一个长度为与字符串
       p的长度相同的滑动窗口，并在滑动中维护窗口中每种字母的数量；当窗口中每种字母的数量与字符串p中每种字母的数量相同时，则说明当前窗口为字符串
       p的异位词

       执行用时：7 ms, 在所有 Java 提交中击败了89.26%的用户
       内存消耗：42.8 MB, 在所有 Java 提交中击败了48.42%的用户
    */
    public List<Integer> findAnagrams2(String s, String p) { //在字符串s中寻找和p字符串的异位词
        int n = s.length();
        int m = p.length();
        List<Integer> list = new ArrayList<>();
        if (m > n) return new ArrayList<>(); //说明p字符个数比s字符个数都多，不可能满足条件，直接返回空集合
        int[] needs = new int[26]; //使用数组记录26个字母在p字符串中出现的个数
        for (int i = 0; i < m; ++i) { //记录字符串p里所有字符出现的次数
            needs[p.charAt(i)-'a']++;
        }
        int[] matched = new int[26]; //
        int start = 0;
        int end = 0;
        while (end < m) { //使end往后移动，使其两指针长度和p字符串长度一样，并记录s字符串在0~m-1区间字符出现的个数到matched数组里
            matched[s.charAt(end)-'a']++;
            end++;
        }
         //TODO: 错误写法,这些写会漏掉最后一个[2,4]指针指向的情况，因为end=4后，在while条件循环就直接结束了，此时还没有比较这个区间的字符
          while (start < end && end < n) { //
            boolean flag = isSame(needs,matched); //判断matchd里和needs里的字符出现个数是不是完全一样，一样说明是异位，否则不是
              //滑动窗口向户滑动一位
              matched[s.charAt(start) -'a']--; //移除首元素后，需要减去之前首字符出现的次数
              matched[s.charAt(end) -'a']++; //++，向后移动元素，因此需要加上末尾字符出现的次数
            if (flag) { //说明是异位,放入集合中，然后为了保持后续区间字符串长度与p一样,两指针都需要向后移动
                list.add(start);
                start++;
                end++;
            }else { //说明不是异位
                start++;
                end++;
            }
          }
          if (isSame(needs,matched)) { //最后一个区间在进行一次匹配
              list.add(start);
          }
        return list;
    }

    private boolean isSame(int[] needs, int[] matched) {
        boolean flag = false;
        for (int i = 0;i<matched.length;i++) {
            if (matched[i] == needs[i]) {
                flag = true;
            }else {
                flag = false;
                break;
            }
        }
        return flag;
    }

}
