package lessons.week12.bitwise.pratice.pratice6;

/**
 * @version 1.0  面试题 17.04. 消失的数字
 * @Description: 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 * 注意：本题相对书上原题稍作改动
 *
 * 示例 1：
 * 输入：[3,0,1]
 * 输出：2
 *
 * 示例 2：
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 *
 * @author: bingyu
 * @date: 2023/8/3
 */
public class MissingNumber {

    public static void main(String[] args) {
        MissingNumber m = new MissingNumber();
        int[] nums = {9,6,4,2,3,5,7,0,1};
        int res = m.missingNumber2(nums);
        System.out.println(res);
    }


    /*
     我的思路: 根据数组长度可知应该包含的所有数字和与当前数字和相减，就是我们缺少的那个数字
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：42.9 MB, 在所有 Java 提交中击败了55.50%的用户
    */
    public int missingNumber(int[] nums) {
        int max = nums.length; //得到数组应该包含的最大数字
        int sum = 0; //用来记录包含所有的数字的和
        int numSum = 0; //用来记录nums数组包含的数字的和
        for (int i = 0;i<=max;i++) {
            sum += i;
        }
        for (int i = 0;i<max;i++) {
            numSum += nums[i];
        }
        return sum - numSum;
    }

    /*
     如何使用位运算的思想解决?
   TODO：核心思想是使用"异或"位运算的交换律(争哥的思路)
     例如"3 7 3 6 6 7 5 2 2 给出这一组数据，其中只有一个数字不是两两配队的，请找出这个数，这种使用异或，相同的数字为0，不同为1
     这样3和3异或得到0,7和7异或得到0,6和6异或得到0....最终会得到5，注意这个和顺序无关，例如 1^2^1^2与 1^1^2^2得到的结果都是一样的为0，并不会因为
     顺序改变而改变,因为四则运算交换律，顺序交换不影响计算结果，所有的位运算都是这样。

     我们将该题转换为2个数字两两配队的问题，再利用异或运算的交换律，将所有数字进行异或，如果最终得到的是0，说明没有缺失，如果不为0，那么最终
     结果就是我们所缺失的

     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：43.1 MB, 在所有 Java 提交中击败了34.47%的用户
    */
    public int missingNumber2(int[] nums) {
        int n = nums.length;
        int res = 0; //之所以开始用0进行异或，是因为0和其它数字异或得到的结果就是该数字本身！
        for (int i = 0;i<=n;i++) { //1.将完整数组里的数字都进行异或
            res^=i;
        }
        //2.再将前面的结果为基础，和目标数组里所有的数字进行异或，这样由于两两配队，相同的数字异或全变成0，留下来的就是缺少的数字了
        for (int i = 0;i<n;i++) {
            res^=nums[i];
        }
        return res;
    }
}
