package lessons.week12.psstatistics.pratice.pratice5;

/**
 * @version 1.0 最大子数组和
 * @Description: 给你一个整数数组nums，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1]的和最大，为6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 * @author: bingyu
 * @date: 2023/7/27
 */
public class MaxSubArray {

    public static void main(String[] args) {
//        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int[] nums = {5,4,-1,7,8};
//        int[] nums = {-2,-1};
        MaxSubArray msa = new MaxSubArray();
        int res = msa.maxSubArray(nums);
        System.out.println(res);
    }

    /*
     争哥解法:
    */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; ++i) { //遍历数组
            if (sum < 0) {
                sum = 0;
            }
            sum += nums[i];
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }

    /*
     在给定的数组中找出一个，最大和的连续子数组，数字是可以为负数
    思路: 明显感觉要用到滑动窗口，但是发现不对，因为数字可能为负数，不知道什么条件下是p++还是q++，因此试着使用前缀和的思路解题
    1.先求出nums[i]前面数字的和(包含自己)
    2.求出前缀和并存入prifixSum数组后，找最大和的连续子数组，即找到前缀和数组中prifixSum[j] - prifixSum[i]最大的值
    3.遍历前缀和数组，一个记录当前遍历位置前的最小前缀和；再利用一个int记录差值最大的值
     prifixSum[1] - prifixSum[0] = -1-(-2) = 1
     prifixSum[3] - prifixSum[0] = 0 - (-2) = 2 求的是[1,3]之间的连续子数组和
    */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
       //TODO： 注意这里，由于我们前缀和是不包含自身元素的，因此会漏掉最后一种情况，以5,4,-1,7,8为例
       // 最后一个元素的前缀和是5+4-1+7+8=15，会漏掉所有元素相加的情况，即整个数组的和就是最大的，因此我们需要
       // 在前缀和数组中比元数组多一个长度才行
        int[] prefixSum = new int[n+1];
        for (int i = 0;i <= n;i++) {
            if (i == 0) {
                prefixSum[i] = 0;
            }else {
                prefixSum[i] = prefixSum[i-1] + nums[i-1];
            }
        }
        //TODO: 再利用滑动窗口的双指针思想，
        int max = Integer.MIN_VALUE; //用来记录连续子数组sum的最大值
        int min = Integer.MAX_VALUE; //记录当前元素前面的最小前缀和
        for (int i = 0;i <= n;i++) { //遍历前缀和数组，找到前缀和数组中prifixSum[j]-prifixSum[i]最大的值
            //TODO: 这两行代码比较难理解 这里好像没太理解?
            // 当前元素的前缀和 - 当前元素前面的最小前缀和 = 从0到当前元素位置连续数组的最大和(注意，这里不是指0~i的和是最大值，
            // 而是当前i位置到前面最小前缀和j位置之间的元素的和是最大值)
            max = Math.max(max, prefixSum[i] - min);
            min = Math.min(min, prefixSum[i]); //将前面一个元素的最小前缀和当前元素的前缀和作比较，看谁最小
        // 当前位置的最小前缀和 = Math.min(当前元素位置前一个元素位置的最小前缀和 , 当前元素位置的前缀和)
        }
        return max;
    }

    //TODO:  这种是错误解法
    // 如果我的前缀和是包含自身呢？注意这样不行，同样以5,4,-1,7,8为例，这样我们得到的prefixSum是[5, 9, 8, 15, 23]，
    // 这样仍然漏掉了，整个数组和是最大的情况，因此我们必须让第一个元素的前缀和为0，这样就不会漏掉这种情况了！
    public int maxSubArrayError(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] prefixSum = new int[n];
        for (int i = 0;i < n;i++) {
            if (i == 0) {
                prefixSum[i] = nums[i];
            }else {
                prefixSum[i] = prefixSum[i-1] + nums[i];
            }
        }

        int minPrefixSum = Integer.MAX_VALUE; //当前nums[i]的最小前缀和(包含nums[i]自身)
        int maxDiff = Integer.MIN_VALUE; //当前[0,i]区间中，连续子数组的最大和
        for (int i = 0;i<prefixSum.length;i++) {
            if (i == 0) { //之所以对0进行特殊处理，是因为后面prefixSum[i] - minPrefixSum可能会导致int范围越界
                maxDiff = prefixSum[i];
                minPrefixSum = prefixSum[i];
            }else {
                //当前位置前缀和 - 前面1个元素最小元素前缀和 = [0,i]区间数组的最大连续子数组的和
                maxDiff = Math.max(maxDiff , prefixSum[i] - minPrefixSum);
                minPrefixSum = Math.min(minPrefixSum,prefixSum[i]);
            }
        }
        return maxDiff;
    }




}
