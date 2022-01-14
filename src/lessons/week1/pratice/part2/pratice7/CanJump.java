package lessons.week1.pratice.part2.pratice7;

/**
 * @version 跳跃游戏
 * @Description:  给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *              判断你是否能够到达最后一个下标。
 * 示例1：
 * 输入：nums = [2,3,1,1,4]
 * 输出：true
 * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
 *
 * 示例2：
 * 输入：nums = [3,2,1,0,4]
 * 输出：false
 * 解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
 *
 *
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * 0 <= nums[i] <= 10^5
 *
 * //TODO:这题必须重新做
 * @author: bingyu
 * @date: 2022/1/10
 */
public class CanJump {

    public static void main(String[] args) {
        int[] nums = {5,9,3,2,1,0,2,3,3,1,0,0};
        boolean b = canJump2(nums);
        System.out.println(b);
    }

    //我的解题思路:使用递归解法
    /*
     特殊情况：
     1.数组长度为0。 2.数组长度为1。  3.

     */
    public static boolean canJump(int[] nums) {
        int length = nums.length;
        if (length == 1) { //长度为1，且值不为0
            return true;
        }
        boolean flag = false;

        //长度大于1的
        if (nums[0] == 0) return false; //如果第一个下标位置是0，直接返回，只有非0的数才可以跳
        int start = 0; //下标的开始位置
        int step = nums[0]; //当前能跳跃的步数
        boolean arrivedEnd = jump(start,step,nums);
        if (arrivedEnd) { //如果到达了末尾直接返回true,没有就继续执行
            return true;
        }
        return flag;
    }

    /**
     * 思路1: 挨个进行跳跃遍历，但是超时了
     * @param start 起始位置下标
     * @param step 要跳跃的步数
     * @param nums 数组
     * @return
     */
    public static boolean jump(int start, int step, int[] nums) {
        for (int i=1;i<=step;i++) { //因为每次都需要进行1 ~ step次跳跃
            if ((start + i) == nums.length - 1) return true;
            if ((start + i) < nums.length) { //小于数组长度，且步数不为0，可以继续跳跃
                //跳跃后不能直接返回，因为如果是false后面还要跳跃，只有当true时才能直接返回
                boolean flag = jump(start + i,nums[start + i],nums); //(start+1)~(start + step)
                if (flag) return true;
            }
        }
        return false;
    }

    //思路2:只取能跳跃到的所有元素集合中，最远位置的那一个
    public static int max(int start, int step, int[] nums) {
        int maxLenth = 0;
        for (int i =1;i<=step;i++) {
            int length = i + nums[start + i]; //计算如果选择跳跃i步，后面可跳跃的下标位置
            if (maxLenth < length) {
                maxLenth = length;
            }
        }
        return maxLenth;
    }


    //官方题解思路:
    //正解：
    public static boolean canJump2(int[] nums) {
        if (nums == null) {
            return false;
        }
        //前n-1个元素能够跳到的最远距离
        int k = 0;//这里k表示能跳最远的距离
        for (int i = 0; i <= k; i++) { //TODO: 这里i<=k实在是太巧妙了，这里就是限制当前位置所有可能跳的元素
            //第i个元素能够跳到的最远距离的下标
            int temp = i + nums[i]; //
            //更新最远距离
            k = Math.max(k, temp);
            //如果最远距离已经大于或等于最后一个元素的下标,则说明能跳过去,退出. 减少循环
            if (k >= nums.length - 1) {
                return true;
            }
        }
        //最远距离k不再改变,且没有到末尾元素
        return false;
    }
    /*
     如果某一个作为 起跳点 的格子可以跳跃的距离是3，那么表示后面 3 个格子都可以作为 起跳点
    可以对每一个能作为 起跳点 的格子都尝试跳一次，把能跳到最远的距离 不断更新,如果可以一直跳到最后，就成功了

     */


}
