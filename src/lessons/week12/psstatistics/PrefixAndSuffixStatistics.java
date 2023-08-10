package lessons.week12.psstatistics;

/**
 * @version 1.0
 * @Description: 前缀后缀统计
 * @author: bingyu
 * @date: 2023/7/12
 */
public class PrefixAndSuffixStatistics {

    /*
      不常考，前缀和、后缀和、前缀积、后缀积、前缀最大、后缀最大
      支持频繁+快速的区间统计，有点类似于滑动窗口，滑动窗口也是求连续的子区间

         有一组数2,3,1,5,7,8
         我们需要频繁的询问某个区间的元素和，如果用暴力方法，只需要for循环遍历指定区间里元素一个个累加即可，看似时间复杂度
         不是很高，但是还是有更好的方法，我们先预处理一下，当我们要求区间[i,j]是sum和，

         sum[i,j] = sum[0,j] - sum[0,i-1]
         举例求sum[1,4]的和，即3+1+5+7，可以通过sum[0,4] - sum[0,0] = 2+3+1+5+7-2
     */

}
