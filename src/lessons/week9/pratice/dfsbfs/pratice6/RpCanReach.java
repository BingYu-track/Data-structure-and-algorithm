package lessons.week9.pratice.dfsbfs.pratice6;

/**
 * @version 1.0  1306. 跳跃游戏 III----重复练习
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
 * @date: 2023/4/20
 */
public class RpCanReach {

    public static void main(String[] args) {
        RpCanReach rp = new RpCanReach();
        int[] arr = {4,2,3,0,3,1,2};
        boolean canReach = rp.canReach(arr, 4);
        System.out.println(canReach);
    }


    /*
    执行用时：2 ms, 在所有 Java 提交中击败了98.42%的用户
    内存消耗：53.4 MB, 在所有 Java 提交中击败了14.74%的用户
    */
    private boolean canReach = false;

    /*
    按题意，就是从start位置是否能跳跃到元素为0的位置，遍历只有2个分支，往前和往后，注意死循环,
    如何避免死循环?每次到达一个位置时就进行标记已访问，如果后面再次遇到已访问的位置，说明存在死循环
    */
    public boolean canReach(int[] arr, int start) {
        int[] direction = {-1,1};
        boolean[] visited = new boolean[arr.length];
        dfs(start,arr,direction,visited);
        return canReach;
    }

    private void dfs(int start, int[] arr, int[] direction, boolean[] visited) {
        if (canReach) return;
        visited[start] = true;
        if (arr[start] == 0) {
            canReach = true;
            return;
        }
        for (int i = 0;i<direction.length;i++) {
            int index = start + direction[i] * arr[start]; //向前或者向后移动后的位置
            if (index >= 0 && index < arr.length) { //防止越界
                if (visited[index]) { //发现访问到了之前访问过的元素，说明存在死循环
                    continue;
                }
                //还未访问，继续向下遍历
                dfs(index,arr,direction,visited);
            }
        }
    }

}
