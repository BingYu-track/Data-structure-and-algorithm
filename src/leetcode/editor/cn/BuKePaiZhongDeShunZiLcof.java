//从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任
//意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// 👍 118 👎 0

package leetcode.editor.cn;
//Java：扑克牌中的顺子
public class BuKePaiZhongDeShunZiLcof{
    public static void main(String[] args) {
        Solution solution = new BuKePaiZhongDeShunZiLcof().new Solution();
        int[] arr = {11,10,0,0,13};
        // TO TEST
        boolean straight = solution.isStraight(arr);
        System.out.println(straight);
    }


    //0表示万能数字，还有除0之外的数字不能重复，还有和排列顺序无关，必须只有5个数
    //例如：[0,0,8,5,4]可以构成顺子，因为这些抽到的牌可以排成4,5,0,0,8这样
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isStraight(int[] nums) {
        if (nums.length != 5) {
            return false;
        }
        boolean[] dup = new boolean[14]; //用来判断是否有重复的数字，之所以用14个元素数组是因为有14种数字，0~13
        int max = -1; //用来记录最大值
        int min = 15; //用来记录最小值
        for (int i=0; i<nums.length;i++) {//第一次循环是检查数字是否符合0到13之间，且是否是从小到大排列
            if (nums[i] != 0) {
                if (dup[nums[i]]) { //第一次进来的数字dup[nums[i]]肯定是false，如果是true说明之前已经遇到过该数字了，说明数字重复，不是顺子
                    return false;
                }else {
                    dup[nums[i]] = true; //第一次遇到一个非零数字，就在布尔数组里进行记录
                }
                //注意下面的最小值和最大值要在非0数字的条件下判断
                if (min > nums[i]) {
                    min = nums[i];
                }
                if (max < nums[i]) {
                    max = nums[i];
                }
            }
        }
        return (max - min) < 5; //最大值和最小值相差小于5说明就是顺子
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}