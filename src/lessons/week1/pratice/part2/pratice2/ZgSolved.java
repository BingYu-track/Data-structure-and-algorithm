package lessons.week1.pratice.part2.pratice2;

/**
 * @version 1.0 扑克牌中的顺子
 * @Description: 争哥的解法
 * @author: bingyu
 * @date: 2022/1/4
 */
public class ZgSolved {

    public static void main(String[] args) {

    }

    //TODO: 推荐--核心思路:获取最大值和最小值，只要最大值-最小值小于6，且没有数字重复，说明就是顺子，注意要排除0，因为0可以作为任何数字
    public static boolean isStraight(int[] nums) {
        if (nums.length != 5) return false;
        boolean flag[] = new boolean[14]; //记录是否有数字重复
        int max = -1;
        int min = 15;
        for (int i = 0;i<nums.length;i++) {
            if (nums[i] != 0) {
                if (flag[nums[i]]) { //如果是true说明数字重复了
                    return false;
                }else {
                    flag[nums[i]] = true; //记录当前数字
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
