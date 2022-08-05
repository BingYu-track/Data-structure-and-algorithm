package lessons.week5.pratice.binarysearch.time.pratice14;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Description: 找到K个最接近的元素
 * @author: bingyu
 * @date: 2022/8/5
 */
public class LeetCodeSolved {

    public static void main(String[] args) {
        int[] nums = {1,5,10};
        int k = 1;
        int x = 4;
        List<Integer> closestElements = findClosestElements(nums, k, x);
        System.out.println(closestElements);
    }


    /**
     * leetcode官方题解: 核心思路:是找到目标值插入的位置，然后按照[mid-K-1,mid+k-1]区间一步步缩小范围
     *
     */
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList()); //将给定的数组转换成List
        int n = ret.size();
        if (x <= ret.get(0)) { //如果x小于等于数组的第一个元素,就直接从0取k个元素就是我们要的
            return ret.subList(0, k);
        } else if (ret.get(n - 1) <= x) { //如果x大于等于数组的最后一个元素，就直接从n-k下标开始取k个元素，就是我们要的
            return ret.subList(n - k, n);
        } else {
            //执行到这里说明x大于数组中的最小值，小于数组中的最大值
            int index = Collections.binarySearch(ret, x); //在list中二分查找目标值x，如果每有目标值，该函数返回的是: (-(插入点下标)-1)
            if (index < 0) { //小于0说明没有找到目标值
                index = -index - 1; //将index计算回x的插入点位置 ，本例是1
            }
            /*
            TODO: 这里开始不太理解了
             可以使用二分查找来找到恰好大于x一点点的元素的索引index。然后让low等于index左边k-1个位置的索引，high等于index右边k-1个位置的索引。
             我们需要的k个数字肯定在范围[index-k-1, index+k-1]里面。所以我们可以根据以下规则缩小范围以得到答案。
             如果low小于0或者low对应的元素比high对应的元素更接近x，那么减小high索引。
             如果high大于最后一个元素的索引arr.size()-1或者它比起low对应的元素更接近x,那么增加low索引。
             当且仅当[low, high]之间恰好有k个元素，循环终止，此时范围内的数就是答案
            */

            int low = Math.max(0, index - k - 1); //在数组的开头和mid - k - 1中取最大的赋值给low (这样做的目的应该是怕mid-k-1越过了数组边界)
            int high = Math.min(ret.size() - 1, index + k - 1); //在数组的末尾和mid + k - 1中取最小的赋值给high
            while (high - low > k - 1) { //high和low之间元素等于k停止循环
                if ((x - ret.get(low)) <= (ret.get(high) - x)) { //左边的差绝对值小于等于右边的差绝对值，抛弃掉右边的，因此high--
                    high--;
                }else if ((x - ret.get(low)) > (ret.get(high) - x)) { //左边的差绝对值大于等于右边的差绝对值,就抛弃掉左边的，因此low++
                    low++;
                }else {
                    System.out.println("unhandled case: " + low + " " + high);
                }
            }
            return ret.subList(low, high + 1);
        }
    }
}
