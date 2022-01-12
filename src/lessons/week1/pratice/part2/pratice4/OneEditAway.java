package lessons.week1.pratice.part2.pratice4;

/**
 * @version 1.0 一次编辑
 * @Description: 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。\
 *
 * 示例1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *
 * 示例2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 *
 * @author: bingyu
 * @date: 2022/1/4
 */
public class OneEditAway {

    public static void main(String[] args) {
        String first = "islander";
        String second = "slander";
        boolean b = oneEditAway(first, second);
        System.out.println(b);
    }

    //TODO：我的解法思路:
    //1.首先比较2个字符串是否相等，相等的话，零次编辑，直接返回true
    //2.相同长度比较不同字符的数量，不同字符小于等于1，满足一次编辑可完成
    //3.不同长度，相差大于1肯定不能一次编辑；如果相差等于1，短字符串里的所有字符都被长字符串包含并且短字符的顺序在长字符的顺序一致，说明可以一次编辑完成
    public static boolean oneEditAway(String first, String second) {
        //1、2个完全相同的字符串直接返回true
        if (first.equals(second)) return true;
        int fl = first.length();
        int sl = second.length();
        int diff = fl - sl;
        //2、只要2个字符串的长度差相差大于1，肯定不可能一次编辑就能完成
        if (diff < -1 || diff > 1) return false;


        //3.相同的长度，得到2个字符串之间不同字符的数量，如果不同字符数量大于1，肯定不可能一次编辑就能完成
        if (diff == 0) {
            int count = 0;
            char[] firstChars = first.toCharArray();
            char[] secondChars = second.toCharArray();
            for (int i=0;i<fl;i++) { //TODO:核心逻辑1--将first和second字符串按下标对应进行比对，记录不同字符的个数
                if (firstChars[i] != secondChars[i]) {
                    count++;
                }
            }
            if (count <= 1) return true; //不同字符数量小于等于1，就满足条件
            /**
             first = "pale"
             second = "pele"
             输出: True

             first = "pale"
             second = "plae"
             输出: false
             //长度相同，字符至少有3个是相同的而且字符顺序是一致的
             */
        }

        //4.不同长度，但是只相差1个长度
        if (diff == 1 || diff == -1) {
            if (first.equals("") || second.equals("")) return true; //如果是空字符串，则肯定为true
            //这时，只要短字符串里的所有字符,在first都包含，而且字符顺序在2个字符串都是一样的，此时就可以保证只需要一次操作就可以
            if (diff > 0) return orderSame(first,second);
            if (diff < 0) return orderSame(second,first);
        }
        return false;
    }

    //判断短的字符串里的字符是否都被长的字符串包含，且顺序一致
    public static boolean orderSame(String longer,String shoter) {
        boolean flag = false;
        char[] longerChars = longer.toCharArray();
        char[] shortChars = shoter.toCharArray();
        //1.遍历短的字符串
        int ll = longer.length();
        int sl = shoter.length();

        for (int i = 0;i<sl;i++) {
            flag = false;
            //2.判断在长字符串中是否包含短字符串的每个字符
            for (int j = 0;j<ll;j++) {
                if (longerChars[j] == shortChars[i]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return flag; //执行到这里说明short的一个字符在longer字符串比较完了，为false的话，说明不包含，直接返回结果
        }

        //3.TODO:核心逻辑2(这里是最难的)--再判断短字符串里字符的顺序和长字符串里的顺序是否是一致的
        int countFirst = 0; //记录2字符串的字符从首部开始的连续次数
        int countLast = 0; //记录2字符串的字符从尾部开始的连续次数
        //先从首部开始比较，并获取其连续次数
        for (int i = 0;i<sl;i++) {
            if (shortChars[i] == longerChars[i]) {
                countFirst++;
            }else {
                break;
            }
        }
        //再从尾部开始一一比较，并获取其连续次数
        for (int i = sl - 1,j = ll-1;i>=0 && j>=0;i--,j--) {
            if (shortChars[i] == longerChars[j]) {
                countLast++;
            }else {
                break;
            }
        }
        //首尾连续次数相加如果等于短字符串的长度，说明顺序是没问题的
        if ((countFirst + countLast) == sl && flag) return true;

        /**
         first = "teacher"
         second = "treacher"
         输出: True

         //长度相同，字符至少有3个是相同的而且字符顺序是一致的

         first = "teacher";
         second = "traecher";
         输出: false
         */
        return false;
    }
    /**
     first = "pale" 023
     second = "ple" 012
     输出: True

     first = "pale" 023
     second = "pel"012

     */

}
