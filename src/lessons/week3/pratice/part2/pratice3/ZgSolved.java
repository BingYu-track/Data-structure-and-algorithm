package lessons.week3.pratice.part2.pratice3;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @version 1.0
 * @Description: 删除字符串中的所有相邻重复项 争哥解法
 * @author: bingyu
 * @date: 2022/4/20
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "abbdddca";
        String s1 = removeDuplicates(s);
        System.out.println(s1);
    }

    //争哥用的双端队列来解决的
    public static String removeDuplicates(String s) {
        Deque<Character> deque = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (deque.isEmpty() || deque.peekLast() != c) { //队尾元素不和当前字符相同，就放进队列!
                deque.addLast(c);
            } else {
                deque.pollLast();
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }
        return sb.toString();
    }

}
