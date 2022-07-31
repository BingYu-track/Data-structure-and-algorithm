package lessons.week5.pratice.binarysearch.pratice12;

/**
 * @version 1.0  x 的平方根
 * @Description: 给你一个非负整数 x ，计算并返回x的 算术平方根 。由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 *
 * 示例 1：
 * 输入：x = 4
 * 输出：2
 *
 * 示例 2：
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *
 * 提示：
 * 0 <= x <= 2^31 - 1
 *
 * @author: bingyu
 * @date: 2022/7/31
 */
public class MySqrt {

    public static void main(String[] args) {
        int i = mySqrt(9);
        System.out.println(i);
    }

    /**TODO:需要复习
     * 争哥思路: x介于[0,x]之间，那么假设k是x的平方根的结果，那么k^2肯定处于[0,x]之间；因此解题就转换成了：
     *      在[0,x]中找到最后一个k^2<=x的k值
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.68%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了42.35%的用户
     */
    public static int mySqrt(int x) {
        if (x == 0) return 0; //0的算数平方根还是0
        int low = 0; //从0开始
        int high = x;
        while (low<=high) {
            int mid = low + (high - low)/2;
            long result = (long)mid * mid; //mid的平方
            if (result <= x) {
                long i = (long)(mid + 1) * (mid + 1); //如果mid的平方小于等于x，则判断mid+1的平方是否小于等于x
                //TODO: 这里为何是i<=x，而不是i<x呢？因为如果i=x话，说明是mid+1的平方等于x,那么最后的一位就是mid+1，因此放在这里没问题
                if (i<=x) { //mid+1的平方也小于x，说明mid不是最后一个mid需要向后移动一位
                    low = mid + 1;
                }else {
                    //执行到这里说明i>x即说明mid+1的平方大于x，此时mid就是最后一个，是我们要找的
                    return mid;
                }
            }else {
                //执行到这里说明mid的平方大于x，需要mid向前移动
                high = mid - 1;
            }
        }
        return -1;
    }

}
