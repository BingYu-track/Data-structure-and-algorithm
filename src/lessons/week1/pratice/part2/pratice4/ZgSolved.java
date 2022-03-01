package lessons.week1.pratice.part2.pratice4;

/**
 * @version 1.0
 * @Description: 一次编辑
 * @author: bingyu
 * @date: 2022/1/12
 */
public class ZgSolved {

    public static void main(String[] args) {

    }

    //推荐--争哥的解法思路:
    public static boolean oneEditAway(String first, String second) {
        int n = first.length();
        int m = second.length();
        // ⻓度相差⼤于1，⽆法通过⼀次编辑匹配
        if (Math.abs(n-m)>1) return false;
        // ⻓度相等，要么完全相同，要么只有⼀个不同
        int diffCount = 0;
        if (n == m) {
            for (int i = 0; i < first.length(); ++i) {
                if (first.charAt(i) != second.charAt(i)) {
                    diffCount++;
                }
            }
            return diffCount <= 1;
        }


        // ⻓度相差1，插⼊或者删除
        diffCount = 0;
        int i = 0; //first字符串的字符下标
        int j = 0; //second字符串的字符下标
        while (i < n && j < m) {
    //TODO:核心逻辑:将2个字符串的字符一一对应进行比较,一旦遇到不同的字符，长的字符串的跳过，继续比较，如果后面还有不同，那么就无法一次操作成功
            if (first.charAt(i) == second.charAt(j)) {
                i++;
                j++;
            } else {
                diffCount++;
                if (n > m) { //如果字符串first的长度大于second，则
                    i++;
                } else {
                    j++;
                }
            }
        }
        return diffCount <= 1;
    }
}
