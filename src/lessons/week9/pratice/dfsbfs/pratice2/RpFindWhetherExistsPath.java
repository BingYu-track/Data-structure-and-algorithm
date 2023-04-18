package lessons.week9.pratice.dfsbfs.pratice2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @version 1.0  面试题 04.01. 节点间通路 --- 重复练习
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
 * @author: bingyu
 * @date: 2023/4/18
 */
public class RpFindWhetherExistsPath {

    public static void main(String[] args) {
        RpFindWhetherExistsPath rp = new RpFindWhetherExistsPath();
        int n = 4;
        int[][] graph = new int[][]{{0, 1},{1, 0},{2, 0},{3,0}}; //会出现死循环
        //查看是否有从0到2的路径
        boolean existsPath = rp.findWhetherExistsPath(n, graph, 0, 3);
        System.out.println(existsPath);
    }

    private LinkedList<Integer>[] adj; //邻接表

    /**
     * 先构建有向图
     * @param n 顶点个数
     * @param graph 边
     * @param start
     * @param target
     * @return
     */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        adj = new LinkedList[n];
        //构建图
        for (int i = 0;i<adj.length;i++) {
            adj[i] = new LinkedList<Integer>();
        }
        for (int i = 0;i<graph.length;i++) {
            int k1 = graph[i][0];
            int k2 = graph[i][1];
            if (!adj[k1].contains(k2)) {
                adj[k1].add(k2);
            }
        }
        //判断是否有路径(可能存在环)，我们使用bfs来判断是否存在路径
        boolean[] visited = new boolean[n]; //0表示未访问  1表示在当前路径  2表示当前路径已退出
        return bfs(adj,start,target,visited);
    }

    private boolean bfs(LinkedList<Integer>[] adj, int star, int end, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(star);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0;i<size;i++) {
                int poll = queue.poll();
                if (poll == end) return true;
                if (visited[poll]) continue; //说明存在环，防止死循环
                visited[poll] = true;
                LinkedList<Integer> list = adj[poll];
                for (int j = 0;j<list.size();j++) {
                    queue.add(list.get(j));
                }
            }
        }
        return false;
    }

}
