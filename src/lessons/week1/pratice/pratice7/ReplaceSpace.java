package lessons.week1.pratice.pratice7;

/**
 * @version 1.0 替换空格
 * @Description: 请实现一个函数，把字符串 s 中的每个空格替换成%20
 *
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 限制：
 * 0 <= s 的长度 <= 10000
 *
 * @author: bingyu
 * @date: 2021/12/24
 */
public class ReplaceSpace {


    public static void main(String[] args) {
        String str = "We are happy.";
        String s = replaceSpace(str);
        System.out.println(s);
    }


    //测试例子:""、" "
    public static String replaceSpace(String s) {
        if (s.length() >= 10000 || s.length() < 0) return null;
        int length = s.length();
        int blankNums = 0; //记录空格数
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') {
                blankNums ++;
            }
        }
        char[] newChars = new char[length + 2 * blankNums]; //创建新的数组，并指定长度
        int j = 0;
        String replace = "%20";
        char[] rep = replace.toCharArray();
        for (int i = 0;i<length;i++) {
            if (chars[i] == ' ') {
                for (char c : rep) {
                    newChars[j] = c;
                    j++;
                }
            }else {
                newChars[j] = chars[i];
                j++;
            }
        }
        return String.valueOf(newChars);
    }



}
