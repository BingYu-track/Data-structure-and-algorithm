package lessons.week1.pratice.part1.pratice4;

/**
 * @version 1.0 验证回文串
 * @Description: 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *  说明：本题中，我们将空字符串定义为有效的回文串
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 *
 * 提示：
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 *
 * @author: bingyu
 * @date: 2021/12/20
 */
public class PalindromeString {

    public static void main(String[] args) {
        String s = ".,";
//        boolean palindrome = isPalindrome(s);
//        System.out.println(palindrome);
        boolean palindrome2 = isPalindrome2(s);
        System.out.println(palindrome2);
    }

    //只用数字和字母，忽略标点符号和大小写
    //必须记住所有数字和字母在ascii表里的位置
    //TODO: ASCII码--只要记住0是48, A是65, a是97它们就行
    //自己的解法:
    public static boolean isPalindrome(String s) {
        int length = s.length();
        if (length < 1 || length > 2 * 100000) {
            return false;
        }
        s = s.toLowerCase();

        //1.过滤掉所有非数字和小写字母的字符
        char[] chars = s.toCharArray();   //0~9是48~57  A~Z 65~90  a~z 97~122
        char[] cs = new char[chars.length]; //用来存储新的字符串
        for (int i=0,j=0;i<chars.length;i++) {
            if((chars[i] >= '0' && chars[i]<='9') || (chars[i]>='a' && chars[i]<='z')) {
                cs[j] = chars[i];
                j++;
            }
        }
        String str = String.valueOf(cs).trim(); //得到过滤后的新字符串
        System.out.println("oldStr: "+str);
        if (str == "") return true; //如果是空字符串

        //2.再将过滤后的字符串进行翻转，如果翻转后的结果一样，则为回文串
        char[] charArray = str.toCharArray();
        for (int i=0; i<charArray.length/2;i++) {
            swap(i,charArray.length-1-i,charArray); //翻转互换
        }
        String newStr = String.valueOf(charArray);
        System.out.println("newStr: "+newStr);
        if (str.equals(newStr)) return true;
        return false;
    }

    public static void swap(int i,int j,char[] chars) {
        char temp = 'a';
        temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }


    //小争哥的解法：在原来的数组上处理，不开辟新的数组空间，而且只需要扫描一遍
    //TODO: 重要---比较回文串的方法:0和i比较，1和i-1比较，依次下去.....所有比较都相等才是回文串，否则只要一次不相等就不是回文串
    //在比较的过程中进行小写后比较，然后当遇到非字母和非数字的时候跳过，
    public static boolean isPalindrome2(String s) { //0是48 、A是65、a是97
        char[] chars = s.toCharArray();
        int length = chars.length;
        int first = 0;
        int last = length - 1;
        for (;first < last;first++,last--){ //注意这里first必须小于last，不能用小于等于，否则，如果字符串为" "，last--会导致下标越界
            //先判断首位指针所在位置是否是字母或数字
            while (first < last && !isNumberOrLetter(chars[first])) { //如果不合法，向后跳过
                first ++;
            }
            while (first < last && !isNumberOrLetter(chars[last])) { //如果不合法，向前跳过
                //注意这里last判断条件必须是first < last否则，这里循环last可能跑到first前面去，导致后面对比不正确，例如当字符串是".,"时
                //last对应的字符就是'.'，而first对应的字符成了','对比为false
                last --;
            }
            //执行到这里，说明2指针所在的位置对应的值都是合法的
            char lowFirst = toLow(chars[first]);
            char lowLast = toLow(chars[last]);
            if (lowFirst != lowLast) return false;
        }
        return true;
    }

    //判断字符是否是数字或字母
    public static boolean isNumberOrLetter(char c) {
        if (c < '0' || (c > '9' && c < 'A') || (c > 'Z' && c < 'a') || c > 'z') return false;
        return true;
    }

    //将字符转为小写
    public static char toLow(char c) {
        if (c >='0' && c <='9') return c; //是数字直接返回
        if (c >='a' && c <= 'z') return c; //是小写字母直接返回
        if (c >= 'A' && c <='Z') { //如果是大写
            int i = c + ('a' - 'A');
            c = (char) i;
        }
        return c;
    }

}
