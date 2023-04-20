package lessons.week9.pratice.dfsbfs.pratice4;

import java.util.LinkedList;

/**
 * @version 1.0  207. 课程表 ----重复练习
 * @Description: 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites给出，其中prerequisites[i] = [ai, bi]，表示如果要学习课程ai则必须先学习课程bi 。
 * 例如，先修课程对[0, 1] 表示：想要学习课程0，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]] 0--->1
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程1之前，你需要先完成课程0；并且学习课程 0 之前，你还应先完成课程 1 ，这是不可能的。
 *
 *
 * 提示：
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对互不相同
 *
 * @author: bingyu
 * @date: 2023/4/19
 */
public class RpCanFinish {

    public static void main(String[] args) {
        RpCanFinish rp = new RpCanFinish();
        int[][] prerequisites = new int[][]{
                {0,10}, {3,18}, {5,5},{6,11},{11,14},{13,1},{15,1},{17,4}
        };
        boolean result = rp.canFinish(20, prerequisites);
        System.out.println(result);
        /*
         0--->10

         3---->18

         6---->11--->14

         17---->4

         13---->1<-----15

         5<--->5
        */

    }

    private LinkedList<Integer>[] adj;
    private boolean hasCicycle = false; //用来记录是否有环

    /*
     这个题目，很明显就是判断是否存在拓扑排序，需要我们先构建一个有向图，然后dfs中如果出现环说明不可能完成所有课程；
     如果没有环，那么肯定会有一个拓扑排序
    */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adj = new LinkedList[numCourses];
        for (int i = 0;i<adj.length;i++) {
            adj[i] = new LinkedList<>();
        }
        for (int i = 0;i<prerequisites.length;i++) {
            int sc = prerequisites[i][1]; //起始课程
            int ec = prerequisites[i][0]; //结束课程
            adj[sc].add(ec);
        }
        int[] visited = new int[numCourses]; //0表示未访问  1表示当前路径已访问  2表示已退出当前路径
        LinkedList<Integer> topoSort = new LinkedList<>();
        for (int i = 0;i<adj.length;i++) {
            if (visited[i] == 0) {
                dfs(i,visited,topoSort);
            }
        }
        return !hasCicycle;
    }


    private void dfs(int k, int[] visited,LinkedList<Integer> topoSort) {
        visited[k] = 1; //保证进入这里的元素都是未访问的
        LinkedList<Integer> list = adj[k];
        for (int i = 0;i<list.size();i++) {
            int num = list.get(i);
            if (visited[num] == 1) {
                hasCicycle = true; //遇到之前已访问的，说明存在环，直接返回
                return;
            }else if (visited[num] == 0) {
                dfs(num,visited,topoSort);
            }
        }
        topoSort.addFirst(k);
        visited[k] = 2;
    }
}
