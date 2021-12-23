package lessons.week1.example.example4;

/**
 * @version 1.0 扑克牌中的顺子
 * @Description:从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A不能视为 14。
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 * 限制：
 * 数组长度为 5
 * 数组的数取值为 [0, 13]
 * @author: bingyu
 * @date: 2021/12/19
 */
public class BuKeShunZi {


    public static void main(String[] args) {
        //下面是总结的规律
        //1.前提条件max和min都是是非0，且数字不重复
        //2.max - min >=5 肯定不是顺子
        //3.max - min <5 肯定是一个顺子
        int[] nums = {1,2,3,4,5};
        boolean straight = isStraight(nums);
        System.out.println(straight);
    }

    //0表示万能数字，还有除0之外的数字不能重复，还有和排列顺序无关，必须只有5个数
    public static boolean isStraight(int[] nums) {
        if (nums.length != 5) return false;
        boolean flag[] = new boolean[14];
        int max = -1; //注意
        int min = 15; //注意
        for (int i = 0;i<nums.length;i++) {
            if (nums[i] != 0) {
                if (flag[nums[i]]) { //如果是true说明数字重复了
                    return false;
                }else {
                    flag[nums[i]] = true;
                    if (min > nums[i]) {
                        min = nums[i];
                    }
                    if (max < nums[i]) {
                        max = nums[i];
                    }
                }
            }
        }
        return (max - min) < 5;
    }


}
