package lessons.week5.pratice.hashtable.pratice7;

import java.util.HashMap;

/**
 * @version 1.0
 * @Description: 面试题 01.02. 判定是否互为字符重排--争哥解法
 * @author: bingyu
 * @date: 2022/8/12
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s1 = "bba";
        String s2 = "baa";
        String s3 = "bad";
        boolean f = checkPermutation(s1, s2);
        System.out.println(f);
    }

    /*

     */
    public static boolean checkPermutation(String s1, String s2) {
        HashMap<Character, Integer> s1ht = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            int count = 1;
            if (s1ht.containsKey(c)) {
                count += s1ht.get(c);
            }
            s1ht.put(c, count);
        }
        // s2去跟s1匹配
        for (int i = 0; i < s2.length(); ++i) {
            char c = s2.charAt(i);
            if (!s1ht.containsKey(c)) { //不匹配，说明c这个字符在s1里没有
                return false;
            }
            int count = s1ht.get(c);
            if (count == 0) return false; //如果从哈希取到的值为0，则说明c这个字符在S2比S1要多
            s1ht.put(c, count-1);
        }
        // 检查s1ht是否为空
        for (int i = 0; i < s1.length(); ++i) {
            char c = s1.charAt(i);
            if (s1ht.get(c) != 0) return false;
        }
        return true;
    }

}
