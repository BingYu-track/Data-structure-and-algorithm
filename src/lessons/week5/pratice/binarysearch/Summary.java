package lessons.week5.pratice.binarysearch;

/**
 * @version 1.0
 * @Description: 二分查找总结
 * @author: bingyu
 * @date: 2022/7/12
 */
public class Summary {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int i = binarySearch(nums, 5);
        System.out.println(i);
    }

    //TODO:基础知识
    /*
     二分查找前提是元素都是有序的，另外二分查找用在链表上，是很慢的，不适用与链表查找,在链表上时间复杂度是O(n)
     二分查找的实际复杂度是O(logn)，不断除2，形成一个等比数列
     */

    /*
     二分查找递归方式实现
     空间复杂度: log2n
     时间复杂度: log2n
     b(n)->b(n/2)->b(n/4)->....->b(0)
     二分查找无论递和归，执行次数都是常数，因此按照递归树，时间复杂度就是log2n
    */
    public static int binarySearchR(int[] nums,int target) {
        return binarySearchR(nums,0,nums.length-1,target);
    }

    public static int binarySearchR(int[] nums,int low,int high,int target) {
        if (low > high) return -1;
        int mid = (low+high)/2;
        if (nums[mid] == target) {
            return mid;
        }else if (nums[mid] < target) {
            return binarySearchR(nums,mid+1,high,target);
        }else {
            return binarySearchR(nums,low,mid-1,target);
        }
    }

    /*
    二分查找非递归方式实现
    */
    public static int binarySearch(int[] nums,int target) {
        int length = nums.length;
        int low = 0;
        int high = length - 1;
        while (low<=high) {
            int mid = (low + high)/2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //TODO: 题型套路
    /*
     1.大部分都是考察变形二分查找

     2.特点:代码很短，单不容易写对
      变形二分查找的难点: 确定搜索区间、循环条件、区间更新、返回值这几点，成功绕晕新手

      如果准备面试?
      这类题目没有其他花样，就是考察刚刚提到的几个易错点，即便考察原题，也很少有同学写对，所以，把布置的18道题练熟练，就超过了90%的候选人！
      这类题型就算拿下了！
     */

    //TODO: 二分查找正确的编写姿势
    /*
     1.查找区间永远是闭区间[low,high]
     2.循环条件永远是:low<=high
     3.对于low==high的情况，必要的时候特殊处理，在while内容补充退出条件
     4.返回值永远是mid,而不是low、high
     5.low、high更新永远是 low=mid+1和 high=mid-1
     6.对于非确定性查找，使用前后探测法，来确定搜索区间 (非确定性查找: a.第一个、最后一个相等的
                                                           b.第一个大于等于的、最后一个小于等于的
                                                           c.循环数组寻找最小值
                                                           d.寻找峰值)
     7.先处理命中情况，再处理左右半部分查找的情况
     TODO 解题小技巧：
      当前需要使后面计算的mid往前移动一位(怎么做呢？因为我们只能选择移动low或者high，low++相当于mid往后移动，high--相当于mid往前移动，
       因此这里我们应该用high--)
       int[] nums = {1,3,5,5,7,8,9,10};
       high--时
                [0,7] mid=3 --> [0,6] mid =3 --> [0,5] mid=2 可以看到high--会导致mid不断向前移动
       low++时
               [1,7] mid=4 --> [2,7] mid= 4 --> [3,7] mid=5 看到low++会导致mid不断向后移动
       high = mid - 1时
                [0,7] mid=3 --> [0,2] mid=1 --> [0,0] mid=0结束  看到high = mid - 1会使mid更快的向前移动，移动距离快于high--
       low = mid + 1时
                [0,7] mid=3 --> [4,7] mid=5 --> [6,7] mid=6 --> [7,7] mid=7结束 看到low = mid + 1会使mid更快的向后移动，移动快于low++
    */
}
