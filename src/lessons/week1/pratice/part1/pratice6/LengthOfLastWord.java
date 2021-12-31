package lessons.week1.pratice.part1.pratice6;

/**
 * @version 1.0 最后一个单词的长度
 * @Description: 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中最后一个单词的长度。
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 *
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 *
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 *
 * @author: bingyu
 * @date: 2021/12/21
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        String s = "   fly me   to   the moon  ";
        s = "Hello World";
        s = "   fly me   to   the moon  ";
        int length = lengthOfLastWord(s);
        System.out.println("最后一个单词的长度是: "+length);

        int k = 5;
        int l = lengthOfBackWord(s, k);
        System.out.println("倒数第"+k+"个单词的长度是: " + l);

        int l2 = lengthOfWord(s, k);
        System.out.println("正数第"+k+"个单词的长度是: " + l2);
    }

    //思路:先去除首尾空格，并且将中间多个空格进行处理成一个空格，最后再进行单词判断
    public static int lengthOfLastWord(String s) {
        char[] chars = s.toCharArray();
        //处理空格，并返回处理空格后新字符串的长度
        int n = trim(chars);
        if (n == 0) return 0;

        //遍历每个单词
        int i = n - 1;
        while (i>=0 && chars[i] != ' ') { //从新字符数组的末尾出发，向前移动，第一次遇到空格时，就说明移动到了末尾单词的前面的空格
            i--;
        }
        //执行到这里说明遇到了最后单词的首空格
        int lastWordLength = n - 1 - i;
        return lastWordLength;
    }



    public static int trim(char[] chars) {
        int length = chars.length;
        int i = 0,j=length - 1;
        //1.去除首尾空格
        while (i < length && chars[i] ==' ') {
            i++;
        }
        while (j >= 0 && chars[j] ==' ') {
            j--;
        }

        //2.处理中间空格
        int k = 0;
        while (i<=j) { //"1  2 34"  1
            if (chars[i] ==' ') { //如果遇到空格
                while (i+1<=j && chars[i+1] == ' ') { //判断后面还有没有空格
                    i++;
                }
                //执行到这说明i+1不再是空格，因此i是最后一个空格
                chars[k] = chars[i];
            }else {
                //非空格
                chars[k] = chars[i];
            }
            k++;
            i++;
        }
        return k;
    }

    //增加难度:
    //1.求倒数第k个单词的长度
    public static int lengthOfBackWord(String s,int k) {
        if (k==0) return 0;
        char[] chars = s.toCharArray();
        //处理空格，并返回处理空格后新字符串的长度
        int n = trim(chars);
        if (n == 0) return 0;
        int i = n-1;
        int count = 0; //用来记录遇到了多少次空格
        //从末尾开始,找到第k-1个空格
        while (i>=0 && count!= k-1) {
            if (chars[i] == ' ') {
                count++;
            }
            i--; //每次移动i都需要
        }

        //执行到这里说明遇到了倒数第k-1个空格，此时的i是倒数第k个单词的末尾字符
        int p = 0; //记录倒数第k个单词的长度
        while (i>=0 && chars[i]!=' ') {
            i--;
            p++;
        }
        //执行到这里说明i到了倒数第k个单词的前面的空格,此时p就是倒数第k个单词的长度
        return p;
    }


    //2.求正数第k个单词的长度
    public static int lengthOfWord(String s,int k) {
        if (k==0) return 0;
        char[] chars = s.toCharArray();
        //处理空格，并返回处理空格后新字符串的长度
        int n = trim(chars);
        if (n == 0) return 0;
        int i = 0;
        int blankCount = 0; //用来记录遇到了多少次空格
        while (i < n && blankCount!=k-1) {
            if (chars[i] == ' ') {
                //遇到空格
                blankCount++;
            }
            i++;
        }

        //执行到这里说明i到了整数第k个数字的开头字符
        int p = 0; //用来记录第k个数字的长度
        while (i < n && chars[i] !=' ') {
            i++;
            p++;
        }
        return p;
    }

}
