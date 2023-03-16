package lessons.week9.pratice.dfsbfs.pratice4;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0  207. 课程表
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
 *
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对互不相同
 *
 * @author: bingyu
 * @date: 2023/3/8
 */
public class CanFinish {

    public static void main(String[] args) {
        CanFinish finish = new CanFinish();
        int[][] prerequisites = new int[][]{
                {0,10}, {3,18}, {5,5},{6,11},{11,14},{13,1},{15,1},{17,4}
        };
        boolean result = finish.canFinish(20, prerequisites);
        System.out.println(result);
        /*
         注意:有20个顶点，但是题目给边只有8个，画出来的图是下面这样，这种都是分开的图，该如何处理?
                  10--->0

                  18--->3

                  14--->11--->6

                  17<---4

                  13<---1--->15

                  5<--->5
 */

    }

    private LinkedList<Integer>[] adj; //邻接表，用来构建有向图
    private boolean isCicyle = false;

    /*
     要将题目中的问题进行抽象，给出的numCourses就是我们要选修的课程个数，给出的prerequisites数组就相当于一个有向图的边，只要
     其所有边构成的图没有环，就说明我们可以读完所有的课程
     执行用时：2 ms, 在所有 Java 提交中击败了99.63%的用户
      内存消耗：41.6 MB, 在所有 Java 提交中击败了66.40%的用户
    */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adj = new LinkedList[numCourses];
        for (int i = 0;i < numCourses;i++) {
            adj[i] = new LinkedList<Integer>();
        }
        for (int i = 0;i<prerequisites.length;i++) { //添加边
            int[] prerequisite = prerequisites[i];
            adj[prerequisite[1]].add(prerequisite[0]); //注意题目中的依赖关系
        }

        //在校验完有向图后，开始检测是否有环
        int[] color = new int[numCourses]; //0表示未访问 1表示在当前路径中  2表示已退出当前路径
        for (int i = 0;i<numCourses;i++) { //dfs所有未访问的节点
            if (color[i] == 0) {
                color[i] = 1;
                dfs(i,color);
            }
        }
        return !isCicyle;
    }

    /**
     *
     * @param k dfs起始点
     * @param color 记录每个点的状态
     */
    private void dfs(int k, int[] color) {
        for (int i = 0;i<adj[k].size();i++) {
            int w = adj[k].get(i);
            if (color[w] == 0) { //未访问的节点，继续dfs
                color[w] = 1;
                dfs(w,color);
            }else if (color[w] == 1) { //访问到了之前已访问过的节点，说明有环
                isCicyle = true;
                return;
            }
        }
        color[k] = 2; //撤销k节点，说明K节点下面关联的点全部处理完毕
    }


}

