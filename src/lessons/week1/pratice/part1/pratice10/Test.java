package lessons.week1.pratice.part1.pratice10;

/**
 * @version 1.0
 * @Description: 把字符串转换成整数 争哥的解法
 * @author: bingyu
 * @date: 2021/12/30
 */
public class Test {

    public static void main(String[] args) {
        String s = "-2147483649";
        int i = strToInt(s);
        System.out.println(i);
    }


    //推荐
    public static int strToInt(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;

        //处理空
        if (n == 0) return 0;

        //处理前置空格
        int i = 0;
        while (i<n && chars[i] == ' ') {
            i++;
        }

        //全为空格
        if (i == n) return 0;

        //处理符合
        int sign = 1;
        char c = chars[i];
        if (c == '-') {
            sign = -1;
            i++;
        }else if (c == '+') {
            sign = 1;
            i++;
        }

        //真正处理数字
        int intAbsHigh = 214748364;
        int result = 0;
        while (i < n && chars[i] >= '0' && chars[i] <= '9') { //保证都是在数字下处理
            int d = chars[i] - '0';
            //判断再乘以10，加d之后，是否越界
            if (result > intAbsHigh) {
                if (sign==1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }
            if (result == intAbsHigh) {
                if ((sign == 1) && (d > 7)) return Integer.MAX_VALUE;
                if ((sign == -1) && (d > 8)) return Integer.MIN_VALUE;
            }

            //正常逻辑
            result = result * 10 + d;
            i++;
        }

        return sign * result;
    }
}
