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
public class ZgSolved {


    public static void main(String[] args) {
        //int[] nums = {5,9,3,2,1,0,2,3,3,1,0,0};
        int[] nums = {2,3,1,1,4};
        boolean b = canJump(nums);
        System.out.println(b);
    }

    //思路: 取最长的位置，不断累加，直到长度大于末尾，说明第一个数字可以达到末尾
    public static boolean canJump(int[] nums) {
        int length = nums.length;
        //如果数组长度就是1，直接返回
        if (length == 1) {
            return true;
        }
        int farthestDistance = 0;//用来表示处于i下标可以累积走到的最远距离/位置
        //TODO: 核心逻辑在i<=step这里，因为i在不断变化，step也可能会不断变化，一旦step
        for (int i=0;i<=farthestDistance;i++) { //遍历i位置可能走到的所有位置，并比较其每个位置所能到达的最远位置，选出其最远的作为实际跳跃点
            //int tempStep = nums[i]; //获取当前i位置可走的最远步数.注意这样是有问题的，因为这样的话获取的是能走的步数，忽略了本身元素所处的位置，因此还需要i+num[i]才是表示能达到的最远位置
            int tempDistance = i + nums[i]; //获取当前i位置可走的最远步数
            if(tempDistance > farthestDistance) {
                farthestDistance = tempDistance;
            }
            if (farthestDistance >= length - 1) return true;
        }
        //return farthestDistance >= length - 1; //注意这里不能在循环外面，否则上面循环会导致由于farthestDistance大于数组长度导致数组越界
        return false;

    }


}
