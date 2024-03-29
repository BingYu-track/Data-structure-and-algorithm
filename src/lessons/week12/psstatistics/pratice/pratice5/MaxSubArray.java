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
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//        int[] nums = {5,4,-1,7,8};
//        int[] nums = {-2,-1};
//        int[] nums = {-1};
        MaxSubArray msa = new MaxSubArray();
        int res = msa.maxSubArray2(nums);
        System.out.println(res);
    }

    /*
     争哥解法1: 使用滑动窗口
     滑动窗口就是使用两指针，看什么时候start++，什么时候end++，什么时候能得到解，就能解决问题
     以-2,1,-3,4,-1,2,1,-5,4为例，因为是取sum的最大值，因此在两指针之间的sum如果是一个负数，想要增大只能后面是正数才行，因此不如让start抛弃当前负数，
     继续移动到下一个数，只要下一个数不为负数，这一个数就比之前两个数的和大，对应上面例子就是当satrt=0,end=1时，sum是个负数，因此start往后移动一位抛弃-2，从1
     开始计数，此时start和end都指向1，然后end向后移动,end指向-3，此时sum是负数，跳过重新从下一个数字开始，即: end = end + 1;  start = end;
     此时两指针都指向4，后面end继续往后移动，到达-1，sum=3为正数，然后和maxSum进行比较，然后让end继续往后移动，看能否让sum变的更大(不能让start往后移动
     ，只会变的更小)

        TODO:总结就是:
         1.sum<=0两指针从后面的元素重新开始(因为sum已经是负数，还不如从sum=0重新开始,无论如何都比sum为负数要大)，
         2.如果sum>0，则只让end向后移动，看是否能让sum变的更大，即使后面的元素可能是一个负数，只要sum为正数，就会对后面数的增加有贡献，
         3.如果sum为正数，end移动后sum又变成了负数，则回到规则1继续执行，直到end到达末尾结束

         执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
         内存消耗：56.7 MB, 在所有 Java 提交中击败了17.25%的用户
    */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n==1) return nums[0];
        int maxSum = Integer.MIN_VALUE; //用来记录sum的最大值
        int sum = 0; //因为我们只需要sum<0做一次操作，sum>0做一次操作，因此我们不需要使用start和end两指针
        for (int i = 0;i < n;i++) { //刚开始两指针都是下标0，
            sum += nums[i];
            maxSum = Math.max(maxSum,sum);//这里不能放到if语句后面，因为这样的话，如果nums全为负数，sum就一直为0了
            if (sum < 0) { //和为负数，则从下一个元素开始，并重置sum为0
                sum = 0;
            }
//            else {
//                //说明sum>=0
//            }
        }
        return maxSum;
    }

    /*解法2: 前缀后缀统计
     在给定的数组中找出一个，最大和的连续子数组，数字是可以为负数
    思路: 明显感觉要用到滑动窗口，但是发现不对，因为数字可能为负数，不知道什么条件下是p++还是q++，因此试着使用前缀和的思路解题
    1.先求出nums[i]前面数字的和(不包含自己)
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
       // 在前缀和数组中比原数组多一个长度才行
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
            // 当前元素的前缀和 - 当前元素前面的最小前缀和 = 从0到当前元素位置中连续数组的最大和(注意，这里不是指0~i的和是最大值，
            // 而是当前i位置到前面最小前缀和j位置之间的元素的和是最大值)
            max = Math.max(max, prefixSum[i] - min);
            min = Math.min(min, prefixSum[i]); //将前面一个元素的最小前缀和当前元素的前缀和作比较，看谁最小
        // 当前位置的最小前缀和 = Math.min(当前元素位置前一个元素位置的最小前缀和 , 当前元素位置的前缀和)
        }
        return max;
    }

    //TODO:  这种是错误解法
    // 如果我的前缀和是包含自身呢？注意这样不行，同样以5,4,-1,7,8为例，这样我们得到的prefixSum是[5, 9, 8, 15, 23]，
    //然后用最大前缀和 - 前面的最小前缀和 = 23-5 = 18，这样漏掉了，整个数组是最大的情况，即最大前缀和应该是23，
    //因此我们必须让第一个元素的前缀和为0，这样就不会漏掉这种情况了！
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

    /*
     争哥的前缀后缀解法: 这个比较难理解，不推荐
    */
    public int maxSubArray3(int[] nums) {
        if (nums.length == 1) return nums[0];
        int sum[] = new int[nums.length]; //用来存储各元素的前缀和，包含自己
        int max[] = new int[nums.length]; //用来存储当前元素后面的元素的最大前缀和，即sum[i]后面的最大元素值，目的是
        int cursum = 0;
        for (int i = 0; i < nums.length; ++i) {
            cursum += nums[i];
            sum[i] = cursum;
        }
        int curmax = Integer.MIN_VALUE;
        //这个循环是干什么？
        for (int i = sum.length-1; i >= 0; --i) {
            if (curmax < sum[i]) curmax = sum[i]; //
            max[i] = curmax;
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 && result < max[0]) {
                result = max[0]; //当位置处于第一个元素时，最大的和就是自己
            }
            //TODO：这个max[i] - sum[i-1]表示的是什么意思？
            if (i != 0 && result < max[i] - sum[i-1]) {
                result = max[i] - sum[i-1]; //当前i位置的最大前缀和 - (i-1位置的前缀和)
            }
            System.out.println();
        }
        return result;
    }



}
