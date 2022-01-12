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
 * @author: bingyu
 * @date: 2022/1/10
 */
public class CanJump {

    public static void main(String[] args) {
        int[] nums = {37,36,35,34,33,32,31,30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0,0};
        boolean b = canJump(nums);
        System.out.println(b);
    }

    //我的解题思路:使用递归解法，挨个进行跳跃遍历，但是超时了
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
        int step = nums[0]; //j代表当前能跳跃的步数
        boolean arrivedEnd = jump(start,step,nums);
        if (arrivedEnd) { //如果到达了末尾直接返回true,没有就继续执行
            return true;
        }
        return flag;
    }

    /**
     *
     * @param start 起始位置下标
     * @param step 要跳跃的步数
     * @param nums 数组
     * @return
     */
    public static boolean jump(int start, int step, int[] nums) {
        for (int i=1;i<=step;i++) { //因为每次都需要进行1 ~ step次跳跃
            if ((start + i) == nums.length - 1) return true;
            if ((start + i) < nums.length) { //小于数组长度，且步数不为0，可以继续跳跃
                //跳跃后不能直接返回，因为如果是false后面还要跳跃，只有当true时才能返回
                boolean flag = jump(start + i,nums[start + i],nums); //(start+1)~(start + step)
                if (flag) return true;
            }
        }
        return false;
    }


    //官方题解:
    //TODO: 官方思路: 只要一个位置的数字能到达，那么这个位置左侧所有位置都能到达(我不太理解)
//    public static boolean canJumpOfficial(int[] nums) {
//        int k = 0; //能跳跃的距离
//        for (int i = 0;i<nums.length;i++) {
//            k = max(i,i + nums[i],nums); //获取i~(i+step)里能跳的最远的位置
//
//        }
//        return false;
//    }
    /*
     如果某一个作为 起跳点 的格子可以跳跃的距离是3，那么表示后面 3 个格子都可以作为 起跳点
    可以对每一个能作为 起跳点 的格子都尝试跳一次，把能跳到最远的距离 不断更新,如果可以一直跳到最后，就成功了

     */

//    int k = 0;
//    for (int i = 0; i < nums.size(); i++) {
//        if (i > k) return false;
//        k = max(k, i + nums[i]);
//    }
//    return true;


}
