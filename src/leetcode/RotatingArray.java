package leetcode;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 旋转数组
 * @author: bingyu
 * @date: 2019/4/21 19:46
 */
public class RotatingArray {


    //这里将k分为三种情况
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        int mod = 0; //要移动的数目
        if(k < length){ //k小于length
            mod = k;
        }else if(k % length == 0){ //k是length的整数倍,顺序不变
            System.out.println(Arrays.toString(nums));
            return;
        }else { //k大于length但不是length的整数倍
            mod = k % length; //获取余数
        }
        //开始旋转
        int temp1 = 0;//临时变量
        int temp2 = 0;//临时变量
        int count = 0;
        while (count < length-1){
             if(count == 0){
                temp1 = nums[count + mod];
                nums[count + mod] = nums[count];
            }else {
                temp2 = nums[count + mod];
                nums[count + mod] = temp1;
                temp1 = temp2;
            }
            count +=mod;
        }
        //执行到这说明（写不出来了...............）
    }

    //最容易想到的方法，我却也没想出来
    public void rotate_1(int[] nums,int k){
        int length = nums.length;
        while (k > 0){
            int num = nums[length - 1]; //获取末尾数字
            for (int i = length-2;i>=0;i--){ //从倒数第2个数字开始
                nums[i+1] = nums[i];
            }
            nums[0] = num;
            k--;
        }
        System.out.println(Arrays.toString(nums));
    }

    /**
     *
     * @param nums 数组
     * @param k 移动的步数
     */
    public void rotate_2(int[] nums, int k) {
        int n = nums.length;
        k %= n; //对n取余数
        reverse(nums, 0, n - 1); //将数组先进行反转 [9, 8, 7, 6, 5, 4, 3, 2, 1]
        reverse(nums, 0, k - 1);//对前k个元素进行倒置[7, 8, 9, 6, 5, 4, 3, 2, 1]
        reverse(nums, k, n - 1);//对后n-k个元素进行倒置[7, 8, 9, 1, 2, 3, 4, 5, 6]
    }


    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
        System.out.println(Arrays.toString(nums));
    }




    public static void main(String[] args){
        int[] nums = {1,2,3,4,5,6,7,8,9};
        RotatingArray array = new RotatingArray();
        System.out.println(Arrays.toString(nums));
        array.rotate_2(nums,12);
    }
}
