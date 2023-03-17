package lessons.week9.pratice.dfsbfs.pratice6;

/**
 * @version 1.0  1306. 跳跃游戏 III
 * @Description: 这里有一个非负整数数组arr，你最开始位于该数组的起始下标start处。当你位于下标i处时，你可以跳到i + arr[i] 或者 i - arr[i]。
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一下标处。
 *
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 *
 * 示例 1：
 * 输入：arr = [4,2,3,0,3,1,2], start = 5
 * 输出：true
 * 解释：到达值为 0 的下标 3 有以下可能方案：
 * 下标 5 -> 下标 4 -> 下标 1 -> 下标 3
 * 下标 5 -> 下标 6 -> 下标 4 -> 下标 1 -> 下标 3
 *
 * 示例 2：
 * 输入：arr = [4,2,3,0,3,1,2], start = 0
 * 输出：true
 * 解释：到达值为 0 的下标 3 有以下可能方案：
 * 下标 0 -> 下标 4 -> 下标 1 -> 下标 3
 *
 * 示例 3：
 * 输入：arr = [3,0,2,1,2], start = 2
 * 输出：false
 * 解释：无法到达值为 0 的下标 1 处。
 *
 * 提示：
 *
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] <arr.length
 * 0 <= start < arr.length
 *
 * @author: bingyu
 * @date: 2023/3/17
 */
public class CanReach {

    public static void main(String[] args) {
        CanReach c = new CanReach();
        int[] arr = {3,0,2,1,2};
        boolean canReach = c.canReach(arr, 2);
        System.out.println(canReach);
    }


    private boolean reach = false;

    /*
     我的思路: 从起始位置开始左右进行dfs，如果越界，终止，还要考虑是否会有环
     执行用时：2 ms, 在所有 Java 提交中击败了98.70%的用户
     内存消耗：52.5 MB, 在所有 Java 提交中击败了45.14%的用户
    */
    public boolean canReach(int[] arr, int start) {
        int[] color = new int[arr.length]; //用以记录每个位置的状态 0表示未访问  1表示在当前路径中  2表示已访问过并退出了当前路径
        dfs(start,arr,color);
        return reach;
    }

    private void dfs(int start, int[] arr,int[] color) {
        if (reach) return;
        if (start < 0 || start > arr.length-1) return; //越界
        if (color[start] == 0) {
            color[start] = 1;
            if (arr[start] == 0) {
                reach = true;
                return;
            }
        }else if (color[start] == 1) { //说明在当前路径遇到了之前访问过的元素，说明有环
            return;
        }
        //TODO 注意: 可能会出现死循环的现象，比如[3,0,2,1,2]从第一个2的位置开始，向右移2位置，到了末尾的2，然后末尾2位置又向左移动2位置，又回到了之前的
        //位置，如此循环往复导致死循环，
        dfs(start + arr[start],arr,color);
        dfs(start - arr[start],arr,color);
        color[start] = 2; //撤销并将其设置为当前节点不在当前路径
    }

}
