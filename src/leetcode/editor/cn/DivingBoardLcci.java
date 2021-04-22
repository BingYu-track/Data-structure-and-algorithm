//你正在使用一堆木板建造跳水板。有两种类型的木板，其中长度较短的木板长度为shorter，长度较长的木板长度为longer。你必须正好使用k块木板。编写一个方
//法，生成跳水板所有可能的长度。 
//
// 返回的长度需要从小到大排列。 
//
// 示例 1 
//
// 输入：
//shorter = 1
//longer = 2
//k = 3
//输出： [3,4,5,6]
//解释：
//可以使用 3 次 shorter，得到结果 3；使用 2 次 shorter 和 1 次 longer，得到结果 4 。以此类推，得到最终结果。 
//
// 提示： 
//
// 
// 0 < shorter <= longer 
// 0 <= k <= 100000 
// 
// Related Topics 递归 记忆化 
// 👍 84 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//Java：跳水板
public class DivingBoardLcci{
    public static void main(String[] args) {
        Solution solution = new DivingBoardLcci().new Solution();
        // TO TEST
        int[] ints = solution.divingBoard(1, 1, 0);
        System.out.println(Arrays.toString(ints));
    }

    //这个题目比较简单，我们可以举例子来查找规律
    //假设shorter=2,longer=3 k=1就有[2,3]; k=2就有[4,5,6]; k=3就有[6,7,8,9]
    //假设shorter=2,longer=4 k=1就有[2,4]; k=2就有[4,6,8]; k=3就有[6,8,10,12]
    //从上面我们很容易找到规律,k个木板最多能构成k+1种不同长度的木板;我们可以确定最短木板和最长的木板，
    //在所有可能的木板构成一个等差数列，这个公差就是longer与shorter的差；当然有一个例外的情况
    // 如果longer=shorter，那么就只会有一种可能长度的木板
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] divingBoard(int shorter, int longer, int k) {
        int[] arr = null;
        if (shorter > longer || shorter < 0 || k>100000 || k < 0 || k==0) {
            arr = new int[0];
            return arr;
        }
        if (shorter == longer) {
            arr = new int[1];
            arr[0] = k * shorter;
            return arr;
        }
        int min = k * shorter; //最短木板
        int max = k * longer; //最长木板
        int diff = longer - shorter; //longer和shorter的差值
        arr = new int[k+1];
        int count = 0;
        //从最短木板开始依次加上公差，直到最长木板为止
        for (int i=min;i<=max;i=i+diff) {
            arr[count] = i;
            count++;
        }
        return arr;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}