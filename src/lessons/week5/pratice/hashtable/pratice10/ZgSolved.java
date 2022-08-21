package lessons.week5.pratice.hashtable.pratice10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 字母异位词分组--争哥解法
 * @author: bingyu
 * @date: 2022/8/15
 */
public class ZgSolved {

    public static void main(String[] args) {
        String[] str = {"eat","tea","tan","ate","nat","bat"};
        List<List<String>> lists = groupAnagrams(str);
        System.out.println(lists);
    }

    /*TODO :推荐该解法
    争哥解法:
    */
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>()); //找不到返回默认值new ArrayList<String>()
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

}
