package lessons.week12.doublepointer.pratice.pratice9;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 面试题 17.11. 单词距离
 * @Description: 有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 * words.length <= 100000
 *
 * @author: bingyu
 * @date: 2023/7/20
 */
public class FindClosest {

    public static void main(String[] args) {
        FindClosest fc = new FindClosest();
        String[] words = {"I","am","a","b","student","a","university","from","in","a","student"};
        String word1 = "a";
        String word2 = "student";
        int res = fc.findClosest2(words, word1, word2);
        System.out.println(res);
    }


    /*
     就是求指定单词下标差值的最小值，如果没有words里没有包含这两个单词的任意一个，那么最短距离就是数组的长度
     最小的是1，即两个单词紧挨着
     最长的是length，即有任意一个单词不存在
     i没有遇到word1之前，往后移动；当后面遇到word1后停顿
     j没有遇到word2之前，往后移动；当后面遇到word2时停顿

     执行用时：13 ms, 在所有 Java 提交中击败了51.12%的用户
     内存消耗：55.1 MB, 在所有 Java 提交中击败了36.57%的用户
    */
    public int findClosest(String[] words, String word1, String word2) {
        int i = 0;
        int j = 0;
        boolean w1 = false; //表示i指针是否指向word1
        boolean w2 = false; //表示j指针是否指向word2
        int min = words.length;
        while (i<words.length && j<words.length) {
            if (words[i].equals(word1)) {
                w1 = true;
            }else {
                w1 = false;
            }
            if (words[j].equals(word2)) {
                w2 = true;
            }else {
                w2 = false;
            }
            if (w1 && !w2) { //先遇到了word1,还没有遇到word2
                if (words[j].equals(word1)) { //执行到这里，i肯定是指向前面的word1,j遇到后面的word1了，需要将i位置指向j的位置
                    i = j;
                }
                j++;
            }else if (!w1 && w2) { //先遇到了word2，还没有遇到word1
                if (words[i].equals(word2)) { //同理，到这里j肯定是指向前面的word2，i遇到后面的word2了，需要将j位置指向i的位置
                    j = i;
                }
                i++;
            }else if (w1 && w2) { //都遇到了
                int diff = i - j;
                min = Math.min(min,Math.abs(diff));
                //TODO:这里是重点，如果找到了一对，我们需要将较大的那个指针向后移动一位，这样做是为了防止其后一个刚好遇到了我们要找的单词
                // 比如该例子中就是"a","b","student","a","university" 前面a student构成后，还要往后检测是否有更短的，如果我们
                // 这里同时i++,j++, i指针就执向了b,j指针就指向了a,就会错过student后面紧挨着的a，因此应该最大的指针向后移动一位，再
                // 进行处理!
                if (i>j) {
                    j = i + 1;
                }else {
                    i = j + 1;
                }
                if (min == 1) break;
            }else { //都没遇到
                i++;
                j++;
            }

        }
        return min;
    }


    /*
    TODO: 推荐该方法
     争哥解法： 遍历一遍数组，把a出现的位置个记录下来，把student出现的位置给记录下来形成两个数组，然后就相当于
     求这两个数组元素的最小差，转换成上一题的问题了,总的来说就是把两个字符串所有的位置找出来，然后求最小差

     执行用时：11 ms, 在所有 Java 提交中击败了86.57%的用户
     内存消耗：55 MB, 在所有 Java 提交中击败了39.55%的用户
    */
    public int findClosest2(String[] words, String word1, String word2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        for (int i = 0;i<words.length;i++) { //遍历数组，找到word1所在的所有位置并存储到l1；
            if (words[i].equals(word1)) {
                l1.add(i);
            }
            if (words[i].equals(word2)) { //找到word2所在的所有位置并存储到l2；
                l2.add(i);
            }
        }
        int i = 0; //存word1数组的指针
        int j = 0; //存word2数组的指针
        int min = Integer.MAX_VALUE;
        while (i < l1.size() && j < l2.size()) {
            int diff = l2.get(j) - l1.get(i); //当前位置的差值
            //将差值的绝对值和最小值进行对比,TODO 注意这里不能赋值diff，因为这个是距离永远是正数，后面判断大小需要使用原差值才行，不然激素diff永远大于0了!
            min = Math.min(Math.abs(diff),min);
            if (diff < 0) { //diff小于0，说明j太小
                j++;
            }else if (diff > 0) { //diff大于0，说明i太小
                i++;
            }else { //执行到这里说明word1和word2一样，该题不会出现这种状况

            }
        }
        return min;
    }

}
