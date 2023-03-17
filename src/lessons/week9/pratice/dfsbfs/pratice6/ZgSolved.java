package lessons.week9.pratice.dfsbfs.pratice6;

/**
 * @version 1.0
 * @Description: 1306. 跳跃游戏 III ---争哥解法
 * @author: bingyu
 * @date: 2023/3/17
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved c = new ZgSolved();
        int[] arr = {3,0,2,1,2};
        boolean canReach = c.canReach(arr, 2);
        System.out.println(canReach);
    }

    private boolean[] visited;
    private boolean reached = false;
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        visited = new boolean[n];
        dfs(arr, start);
        return reached;
    }

    private void dfs(int[] arr, int curi) {
        if (reached) return;
        if (arr[curi]==0) {
            reached = true;
            return;
        }
        visited[curi] = true;
        int move2left = curi - arr[curi];
        //只要遇到前面已访问的元素，说明形成了环，不能继续下去，所以这里加了visited[move2left]==false，保证后面遇到的都是未被访问过的
        if (move2left>=0 && move2left<arr.length && visited[move2left]==false) {
            dfs(arr, move2left);
        }
        int move2right = curi + arr[curi];
        if (move2right>=0 && move2right<arr.length && visited[move2right]==false) {
            dfs(arr, move2right);
        }
    }

}
