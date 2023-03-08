package lessons.week9.pratice.dfsbfs.pratice2;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @version 1.0  面试题 04.01. 节点间通路
 * @Description: 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 * 示例1:  有3个点，分别是0,1,2；判断0和2之间是否存在路径
 * 输入：n = 3, graph = [
 *                      [0, 1],
 *                      [0, 2],
 *                      [1, 2],
 *                      [1, 2]
 *                    ],
 *     start = 0, target = 2
 * 输出：true
 *
 * 示例2:
 * 输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]], start = 0, target = 4
 * 输出 true
 *
 * 提示：
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 *
 * @author: bingyu
 * @date: 2023/3/2
 */
public class FindWhetherExistsPath {

    public static void main(String[] args) {
        FindWhetherExistsPath f = new FindWhetherExistsPath();
        int n = 3;
        int[][] graph = new int[][]{{0, 1},{1, 2},{0, 2},{1, 2}};
        //查看是否有从0到2的路径
        boolean existsPath = f.findWhetherExistsPath(n, graph, 0, 2);
        System.out.println(existsPath);
    }

    private boolean found = false;

    /*
     注意: 该题目给的grah是一组边，但是里面有重复边，需要先进行去重，如何去除graph中重复的边?
          可以将2个点构成一个位数，只要遇到同样的位数，说明是重复的边

     解题思路: 如何判断两个点是否有路径，就是要找出两点之间的一条路径出来,仅通过边，我们是无法确定一条点到点的路径的，需要我们把全部的边构成图串起来才行!
     1.先将边进行去重
     2.将这些边构成一个有向图，具体用邻接表实现
     3.在邻接表中我们就能得到各个顶点之间的连续关系，这样我们就能在邻接表中使用DFS来寻找点与点之间的路径了

     执行用时：38 ms, 在所有 Java 提交中击败了19.39%的用户
     内存消耗：84.8 MB, 在所有 Java 提交中击败了44.21%的用户
    */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        HashSet<Integer> set = new HashSet<>(); //用来进行去重
        Graph graphs = new Graph(n); //创建一个有向图
        for (int i = 0;i<graph.length;i++) { //遍历所有的边
            int num = getNums(graph[i][0],graph[i][1]); //两边顶点构造成一个位数
            if (!set.contains(num)) {
                //这到这说明当前边没有重复
                set.add(num);
                graphs.addEdge(graph[i][0],graph[i][1]);
            }
        }
        //执行到这里构造有向图成功
        graphs.foundPath(start,target);
        return found;
    }

    /** 注意:取个位数是用取余，num%10
     * 组装成数位和  [10,12]  [20,2]
     * @param i
     * @param j
     * @return
     */
    private int getNums(int i, int j) {
        int kj = 0; //表示数字j的位数
        int temp = j;
        while (temp!=0) { //计算数字j的位数
            temp = temp/10;
            kj++;
        }
        while (kj!=0) { //将i数字增加kj个位数
            i *= 10;
            kj--;
        }
        return i + j; //将i和j两个数字组合起来
    }

    /*
     邻接表有向图
    */
    public class Graph {
        private int v; //顶点的个数
        private LinkedList<Integer>[] adj; //链表数组。数组每个链表表示顶点，链表后面的节点表示当前顶点相连

        Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0;i<adj.length;i++) {
                adj[i] = new LinkedList<>();
            }
        }

        //增加边s->t
        public void addEdge(int s,int t) {
            adj[s].add(t);
        }

        public void foundPath(int start, int target) {
            boolean[] visited = new boolean[v];
            dfs(start,target,visited);
        }

        private void dfs(int start, int target, boolean[] visited) {
            if (found) return;
            visited[start] = true;//标记当前点已访问
            if (start == target) { //如果当前点和目标点值一样，说明可以到达目标点，有合法路径
                found = true;
                return;
            }
            for (int i = 0;i<adj[start].size();i++) { //遍历当前节点下面的节点
                int w = adj[start].get(i);
                if (!visited[w]) { //w点没有被访问过，继续向下遍历
                    dfs(w,target,visited);
                }
            }
        }

    }

}
