package lessons.week5.pratice.binarysearch.pratice11;

/**
 * @version 1.0 有效的完全平方根
 * @Description: 给定一个正整数num，编写一个函数，如果num是一个完全平方数，则返回true，否则返回false。
 *
 * 进阶：不要使用任何内置的库函数，如sqrt。
 *
 * 示例 1：
 * 输入：num = 16
 * 输出：true
 *
 * 示例 2：
 * 输入：num = 14
 * 输出：false
 *
 * 提示：
 * 1 <= num <= 2^31 - 1
 *
 * @author: bingyu
 * @date: 2022/7/29
 */
public class IsPerfectSquare {

    public static void main(String[] args) {
        int num = 2147483647;
        boolean perfectSquare = isPerfectSquare(num);
        System.out.println(perfectSquare);
    }

    /*
    我的思路: 使用之前争哥的思路，在[0,num]区间中找满足k^2等于num的
    [0,num]
    */
    public static boolean isPerfectSquare(int num) {
        int low = 1;
        int high = num;
        while (low <= high) {
            int mid = low + (high - low)/2;
            long result = (long)mid * mid; //TODO: 注意这里很容易越界，需要转成long类型
            if (result == num) {
                return true;
            }else if (result < num) {
                long i = (long)(mid+1) * (mid+1);
                if (i<=num) { //mid+1小于等于num
                    low = mid + 1;
                }else { //mid+1的平方大于num,但是mid的平方小于num，因此没有对应的平方值
                    return false;
                }
            }else { //result>num
                high = mid - 1;
            }
        }
        return false;
    }


}
