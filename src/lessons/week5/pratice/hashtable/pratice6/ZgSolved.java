package lessons.week5.pratice.hashtable.pratice6;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Description: 单词频率--争哥解法
 * @author: bingyu
 * @date: 2022/8/11
 */
public class ZgSolved {

    public static void main(String[] args) {

    }

    private static Map<String, Integer> map = new HashMap<>();

    //和我思路一样
    public ZgSolved(String[] book) {
        for (String word : book) {
            int count = 1;
            if (map.containsKey(word)) {
                count += map.get(word);
            }
            map.put(word, count);
        }
    }

    public static int get(String word) {
        if (!map.containsKey(word)) {
            return 0;
        }
        return map.get(word);
    }

}
