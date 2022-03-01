package lessons.week1.pratice.part2.pratice2;

/**
 * @version 1.0 扑克牌中的顺子
 * @Description: 从若干副扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 示例1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 *
 * 示例2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 * 限制：
 * 数组长度为 5
 * 数组的数取值为 [0, 13] .
 *
 * @author: bingyu
 * @date: 2022/1/3
 */
public class ShunZi {


    public static void main(String[] args) {
        int[] nums = {0,0,1,2,5};
        boolean straight = isStraightSelf(nums);
        System.out.println(straight);
    }

    /**
     * 1.首先判断数字是否重复(数字0除外),一旦非0数字重复，说明不连续
     * 2.得到非0数字的最大值和最小值，以及数字0的个数
     * 3.在1,2条件满足后只要"非0数字的最大值和最小值差小于5就是连续的"(这个条件是最难理解的！)
     * @param nums
     * @return
     */
    public static boolean isStraightSelf(int[] nums) {
        int length = nums.length;
        if (length!=5) return false;
        int min = 13;
        int max = -1;
        boolean[] flag = new boolean[14]; //0~13
        int zeroCount = 0; //记录有多少个0
        //判断数字是否有重复
        for (int i=0;i<length;i++) {
            int num = nums[i]; //获取当前扑克牌对应的数字
            if (flag[num] && num!=0) { //如果为true说明数字重复了，直接返回false
                return false;
            }
            flag[num] = true;
            if (num == 0) {
                zeroCount++;
            }else {
                //得到最大值和最小值
                if (num > max) {
                    max = num;
                }
                if (num < min) {
                    min = num;
                }
            }
        }
        //执行到这里说明数字不重复,只要不重复，那么数字是否连续就只取决于最大和最小值的差了
        return (max - min ) <= 4; //这里小于等于4就行了，因为如果没有数字0，最大值和最小值的差必须等于4，而如果有数字0，数字0可以表示
        //任何一个数字


        //1,2,3,4,5
        //1,2,0,4,5
    }


    //自己的思路和解法:1.先进行从小到大排序
    //2.然后从尾部开始对首部数值进行差比较
    //TODO:不推荐，而且我的算法有错误
    public static boolean isStraight(int[] nums) {
        boolean flag = false;
        int length = nums.length;
        if (length != 5) return false;
        //1.从小到大排序
        quickSort(nums,0,nums.length-1);
        int i = 0;
        //2.跳过0
        while (i < length && nums[i] == 0) {
            i++;
        }
        //3.将非0数字依次和后面的数值相减，来判断是否满足连续条件
        int count = 0;
        for (int k=length-1;k>i;k--) {
            if ((k - i) == (nums[k] - nums[i])) { //如果当前比较的2个元素满足连续条件，就记录依次
                count++;
            }
        }

        //在判断当前count是否与i元素与后面比较的次数一致，如果一致说明是连续的
        if ((length - 1 - i) == count) flag = true;
        return flag;
    }

    public static void quickSort(int[] nums,int low,int high) {
        if (low < high) {
             //划分获得分界点所在的下标位置
            int pivot = partition(nums,low,high);
            quickSort(nums,low,pivot-1);
            quickSort(nums,pivot+1,high);
        }
    }

    //1.用low指向的位置为分界点 2.开始比较分界点的值pivotValue和low的值，直到pivotValue小于low的值，交换位置，然后pivotValue和high指向的值进行比较
    public static int partition(int[] nums, int low, int high) {
        int pivotValue = nums[low];
        while (low < high) { //必须先保证low < high
            while (low < high && pivotValue <= nums[high]) {
                high--;
            }
            //执行到这里说明pivotValue大于high指向的值，把high的值放到分界点的左边去
            swap(nums,low,high);

            while (low < high && pivotValue >= nums[low]) {
                low++;
            }
            //执行到这里才能说明pivotValue小于low指向的值，把low的值放到分界点的右边去
            swap(nums,low,high);
        }

        return low;
    }


    public static void swap(int[] nums,int i,int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
