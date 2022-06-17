package lessons.week4.pratice.sorted.pratice.pratice2;

/**
 * @version 1.0 有效的字母异位词
 * @Description: 给定两个字符串s和t，编写一个函数来判断 t是否是 s 的字母异位词。
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
 * 1 <= s.length, t.length <= 5 * 10^4
 * s和t仅包含小写字母
 *
 * @author: bingyu
 * @date: 2022/6/16
 */
public class IsAnagram {

    public static void main(String[] args) {
        String s = "anagram"; //3+1+1+1+1=7
        String t = "nagaram"; //1+2+1+1+2=7
        boolean anagram = isAnagram(s, t);
        System.out.println(anagram);
    }

    /*
    TODO 推荐该方法
     我的思路2: 英文字母就26个，直接创建26个数组然后记录其2个字符串中的个数,然后遍历比较即可！
     时间复杂度为O(n)
    */
    public static boolean isAnagram(String s, String t) {
        int[] ss = new int[26];
        int[] ts = new int[26];
        for (int i = 0;i<s.length();i++) {
            int index = s.charAt(i) - 'a';
            ss[index]++;
        }
        for (int i = 0;i<t.length();i++) {
            int index = t.charAt(i) - 'a';
            ts[index]++;
        }
        boolean flag = true;
        for (int i = 0;i<ss.length;i++) {
            if (ss[i] != ts[i]) {
                flag = false;
            }
        }
        return flag;
    }


    /*
        我的思路1: 按照字母顺序排序,排序完成后，如果一样，则说明是字母异位词，但是容易超时
    */
    public static boolean isAnagram1(String s, String t) {
//        s = bubbleSortLetters(s);
//        t = bubbleSortLetters(t);
        s = insertSortLetters(s);
        t = insertSortLetters(t);
        return s.equals(t);
    }


    /*
     使用插入排序对字母进行排序，将后面的与前面有序区间元素进行比较，找到对应的位置插入，这里核心是变移动边插入，会比较快
      5,4,3,2,1
      通过，但是时间还是很长
     */
    public static String insertSortLetters(String str) {
        char[] chars = str.toCharArray();
        for (int i = 1;i<chars.length;i++) { //从第1个元素开始作为有序区间
            char e = chars[i]; //无序区间的元素
            int j = i; //无序区间开始元素的位置
            for (;j>0 && e < chars[j-1];j--) { //比较后面的无序区间的元素，只有小于有序区间的最大元素才会进入循环
                chars[j] = chars[j-1]; //无序元素小于有序元素，将无序元素向后移动，这里chars[j]是无序元素，chars[j-1]是有序元素
            }
            chars[j] = e;
        }
        return String.valueOf(chars);
    }

    /** 5 4 3 2 1
     * 使用冒泡排序对字符串进行字母排序 ,此时这题的时间复杂度取决于这里的排序，如果用的是冒泡、插入、选择排序，则时间复杂度是O(n^2)，用的是希尔排序，则是O(n^2)
     * @param str anagram
     */
    public static String bubbleSortLetters(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0;i<chars.length - 1;i++) {
            boolean flag = false;
            for (int j = 0;j<chars.length - i - 1;j++) {
                if (chars[j] > chars[j+1]) {
                    swap(j,j+1,chars);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        return String.valueOf(chars);
    }

    private static void swap(int i, int j, char[] chars) {
        char temp = 0;
        temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


}
