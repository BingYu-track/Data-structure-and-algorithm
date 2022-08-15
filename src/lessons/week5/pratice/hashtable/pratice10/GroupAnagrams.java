package lessons.week5.pratice.hashtable.pratice10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @version 1.0 字母异位词分组
 * @Description: 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 *
 * 提示：
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i]仅包含小写字母
 *
 * @author: bingyu
 * @date: 2022/8/12
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] str = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = groupAnagrams(str);
        System.out.println(lists);
    }

    /*
     * 核心思路: 将字符串进行排序，如果是异位词的，排序后的字符串应该是一样的，然后再以排序的字符串作为key存到hash里，value则是存排序前各个字符串的列表
     执行用时：6 ms, 在所有 Java 提交中击败了76.02%的用户
     内存消耗：44.8 MB, 在所有 Java 提交中击败了22.47%的用户
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        int n = strs.length;
        List<List<String>> results = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0;i < n;i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String s = String.valueOf(chars);
            List<String> strings = null;
            if (!map.containsKey(s)) {
                strings = new ArrayList<>();
                strings.add(strs[i]);
                map.put(s,strings);
            }else {
                strings = map.get(s);
                strings.add(strs[i]);
                map.put(s,strings);
            }
        }
        for (List<String> value : map.values()) {
            results.add(value);
        }
        return results;
    }


}
