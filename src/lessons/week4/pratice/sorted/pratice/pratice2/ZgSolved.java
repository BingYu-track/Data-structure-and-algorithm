package lessons.week4.pratice.sorted.pratice.pratice2;

import java.util.Arrays;

/**
 * @version 1.0 有效的字母异位词
 * @Description: 争哥解法
 * @author: bingyu
 * @date: 2022/6/17
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "anagram"; //3+1+1+1+1=7
        String t = "nagaram"; //1+2+1+1+2=7
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }


    /*
    争哥思路: 直接将两个字符串安装字母顺序排序，然后遍历比较
    */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        for (int i=0;i< str1.length;i++) {
            if (str1[i] != str2[i]) return false;
        }
        return true;
    }

}
