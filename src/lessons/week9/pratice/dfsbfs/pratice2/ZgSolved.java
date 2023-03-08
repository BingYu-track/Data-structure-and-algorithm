package lessons.week9.pratice.dfsbfs.pratice2;

import java.util.HashSet;

/**
 * @version 1.0
 * @Description: 面试题 04.01. 节点间通路---争哥解法
 * @author: bingyu
 * @date: 2023/3/7
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zgSolved = new ZgSolved();
        int n = 5;
        int[][] graph = new int[][]{{0, 2},{1, 3},{1, 4},{1, 3},{2,3},{3,4}};
        //查看是否有从0到2的路径
        boolean existsPath = zgSolved.findWhetherExistsPath(n, graph, 0, 4);
        System.out.println(existsPath);
    }

    /*
                      0---->2
                            |
                            V
                      1---> 3
                      |     |
                       \   /
                         4
    */


    private boolean[] visited;
    private HashSet<Integer>[] adj;
    private boolean found = false;

    /*
     推荐该思路
     争哥解法思路:
     执行用时：20 ms, 在所有 Java 提交中击败了65.52%的用户
     内存消耗：86.6 MB, 在所有 Java 提交中击败了30.80%的用户
    */
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        visited = new boolean[n];
        adj = new HashSet[n];
        for (int i = 0; i < n; i++) { //TODO 每个顶点创建一个HashSet，用hashset来维护与其顶点关联的其它点，因为可以去重
            adj[i] = new HashSet<Integer>();
        }
        //graph[i][0]表示当前要处理的顶点，graph[i][1]表示与其关联的顶点
        for (int i = 0; i < graph.length; i++) { //遍历所有顶点并按照题目给的数组信息关联其顶点
            if (!adj[graph[i][0]].contains(graph[i][1])) {
                adj[graph[i][0]].add(graph[i][1]);
            }
        }
        dfs(start, target);
        return found;
    }

    private void dfs(int cur, int target) {
        if (found) return;
        if (cur == target) {
            found = true;
            return;
        }
        visited[cur] = true;
        for (Integer next : adj[cur]) {
            if (!visited[next]) {
                dfs(next, target);
            }
        }
    }


}
