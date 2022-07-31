package lessons.week5.pratice.binarysearch.pratice2;

/**
 * @version 1.0 猜数字大小
 * @Description: 猜数字游戏的规则如下：
 * 每轮游戏，我都会从1到n 随机选择一个数字。 请你猜选出的是哪个数字。
 * 如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
 * 你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1或 0）：
 *
 * -1：我选出的数字比你猜的数字小 pick < num
 * 1：我选出的数字比你猜的数字大 pick > num
 * 0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num返回我选出的数字。
 *
 * 示例 1：
 * 输入：n = 10, pick = 6  猜的数字是10，选出的数字是6
 * 输出：6
 *
 * 示例 2：
 * 输入：n = 1, pick = 1
 * 输出：1
 *
 * 示例 3：
 * 输入：n = 2, pick = 1
 * 输出：1
 *
 * 示例 4：
 * 输入：n = 2, pick = 2
 * 输出：2
 *
 * 提示：
 * 1 <= n <= 2^31 - 1
 * 1 <= pick <= n
 *
 * @author: bingyu
 * @date: 2022/7/8
 */
public class Solution {

    public static void main(String[] args) {
        int i = guessNumber(1705930310);
        System.out.println(i);
    }

    /* TODO 推荐争哥的方法！我的方法太繁琐了！
     我的思路--先根据猜出的数字得到大致的范围，然后在确定的范围内不断二分，二分时注意不能超过int的最大范围
      如果n>pick 从[1,n-1]里查询
      如果n<pick 从[n+1,MaxInt]查询，期间每次循环要保存上一次的开始和结束区间
      先根据猜的数字确定pick所在区间的范围，然后通过二分到这个范围内去查找

      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：38.7 MB, 在所有 Java 提交中击败了5.06%的用户
    */
    public static int guessNumber(int n) { //n是我们猜的数字
        int pick = 1508243771;
        int low = 1;
        int high = Integer.MAX_VALUE;
        int guess = guess(n,pick);
        int target = -1;
        if (guess == 0) {
            return pick;
        }else if (guess == 1) { //小于选出的数字,即n<pick 从[n+1,MaxInt]里查询
            low = n + 1;
            target = search(low,high);
        }else if (guess == -1) { //n大于选出的数字,即n>pick 从 [1,n-1]查询
            high = n - 1;
            target = search(low,high);
        }
        return target;
    }

    private static int search(int low, int high) {
        int pick = 1508243771;
        while (low<=high) {
            int mid = 0;
            if (high <= Integer.MAX_VALUE/2) { //TODO 注意1: 为了防止超过int最大范围类型
                mid = (low + high)/2;
            }else {
                mid = low/2 + high/2;
                if ((low%2 + high%2)==2) { //TODO 注意2: 如果两个数对2取余后剩下的都是1，则mid计算完后需要补1
                    mid = mid + 1;
                }
            }
            if (guess(mid,pick) == 0) {
                return mid;
            }else if (guess(mid,pick) == 1) { //小于选出的数字,即mid<pick 从[mid+1,MaxInt]里查询
                low = mid + 1;
            }else if (guess(mid,pick) == -1) { //大于选出的数字,即mid>pick 从 [1,mid-1]查询
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     *
     * @param num  传入的数字
     * @param pick 选出的数字
     * @return
     */
    private static int guess(int num,int pick) {
        if (pick > num) return 1;
        if (pick < num) return -1;
        return 0;
    }
}
