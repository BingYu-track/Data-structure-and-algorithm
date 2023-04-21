package lessons.week9.graph;

import lessons.week9.Summary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 无向图
 * @author: bingyu
 * @date: 2023/3/6
 */
public class UndiGraph {

    private int v; //顶点总个数

    private LinkedList<Integer>[] adj; //用来存储各个顶点与其它顶点之间的关系
    private List<Integer> result = new ArrayList<>();
    private List<List<Integer>> allResult = new ArrayList<>();

    public UndiGraph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0;i < v;i++) {
            adj[i] = new LinkedList<>();
        }
    }

    //增加s点到t的边，由于是无向图，所以方向是双向的
    public void addEdge(int s,int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    //TODO: 1.获取从s点到点t最短路径经过了几个节点，也就是有几层
    public int getLevel(int s,int t) {
        boolean[] visited = new boolean[v];
        int[] levels = new int[v]; //记录每个节点的所在层数
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        levels[s] = 1; //起始节点作为第一层
        visited[s] = true;
        while (!queue.isEmpty()) {
            int q = queue.poll();
            if (q == t) {
                return levels[q];
            }
            for (int i = 0;i<adj[q].size();i++) {
                int w = adj[q].get(i); //
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    levels[w] = levels[q] + 1;
                }
            }
        }
        return 0;
    }


    //TODO： 2.获取最短路径并用集合形式返回
    public List<Integer> getShortPath(int s,int t) {
        boolean[] visited = new boolean[v];
        int[] prev = new int[v]; //用来记录每个节点是从哪个节点来的
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        prev[s] = -1; //起始节点因为没有前置节点，赋值为-1
        while (!queue.isEmpty()) {
            int q = queue.poll();
            if (q == t) {
                break;
            }
            for (int i = 0;i<adj[q].size();i++) {
                int w = adj[q].get(i);
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    prev[w] = q;
                }
            }
        }
        //当得到了一个前驱序列后
        reverse(prev,s,t);
        return result;
    }

    /*
     prev数组已经存了s->t的所有路径，但是要注意，此时prev存的是所有顶点的前置顶点，因此我们只能从结束顶点t开始反向打印，为什么要从结束顶点反向打印呢？
     因为prev存储了每个顶点的前置点，那么我们就能从尾节点推算出它是从哪个节点到尾节点的，反之，如果我们想从起始节点推算出会走后面哪个节点是不行的，
     因为有多种可能！例如[-1,0,0,1,1,2,3]6尾节点是从3过来的，3是从1过来的，1是从0过来的；而我们看起始点，它有2个后置节点，1和2，因此只能从结束顶点往前推导!

     由于我们只能从后往前推导，得到的会是个我们要求的路径的逆序，因此我们使用递归将该路径进行逆序就得到了我们要的路径了!
     */
    private void reverse(int[] prev, int s, int t) {
        if (t!=s) {
            reverse(prev,s,prev[t]); //不断缩小范围到[s,prev[t]]
        }
        //执行到这里说明t==s，即尾节点递归到了起始节点
        result.add(t);
    }

    private HashSet<Integer> set = new HashSet<>();

    /*
      TODO： 3.获取所有可能的最短路径并用集合形式返回(使用BFS得到最短路径的层数，再使用DFS得筛选出所有的最短路径)
       但是我这样的方法，一旦数据规模很大，深度很深，分支很多，执行时间就会很慢了，还需要进行优化!
     */
    public List<List<Integer>> getAllShortPath(int s,int t) {
        List<Integer> path = new ArrayList<>(); //
        boolean[] visited = new boolean[v];
        int level = getLevel(s, t); //得到最短路径个数
        //然后使用dfs
        dfs(0,s,t,path,level,visited);
        return allResult;
    }

    private void dfs(int k, int s, int t, List<Integer> path, int level, boolean[] visited) {
        visited[s] = true;
        path.add(s);
        if (k < level && s == t) {
            allResult.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i<adj[s].size();i++) {
            int w = adj[s].get(i); //s节点联系的点
            if (!visited[w] && k+1<level) { //加上!set.contains(w)发现后面多个分支就无法执行了，
                dfs(k+1,w,t,path,level,visited);
                path.remove(path.size()-1);
                visited[w] = false; //这里也需要撤销，因为当前
            }
        }
    }

    /*
      TODO： 4.获取所有可能的最短路径并用集合形式返回,核心思路，使用BFS记录下每个节点最短路径所在层数并维护成一个映射顶点---level，再进行DFS，
       在DFS期间对每个节点进行判断，只将当前该节点所在层数等于我们维护的映射中的层数，才将该节点放入path，否则当前节点所在路径肯定不是最短的路径
       注意这个是在已构建图的基础上
    */
    public List<List<Integer>> getAllShortPathOptimize(int s,int t) {
        List<Integer> path = new ArrayList<>(); //
        HashMap<Integer,Integer> map = new HashMap<>(); //节点---level映射
        bfs(s,t,map);//将每个顶点所在最短路径的层数找出来并放入map中维护
        dfs(0,s,t,map,path);
        return allResult;
    }

    //广度优先遍历，并对每个节点维护其所在的最短路径层数(需要掌握!)
    private void bfs(int s, int t, HashMap<Integer, Integer> map) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int depth = 0; //用来记录层数
        map.put(s,depth); //起始节点层数设为0
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0;i < size;i++) { //按层遍历
                int q = queue.poll();
                for (int j = 0;j < adj[q].size();j++) { //遍历q节点下面的所有节点，由于是无向图可能存在重复节点，我们通过map可以顺便去重
                    Integer w = adj[q].get(j);
                    if (!map.containsKey(w)) {
                        map.put(w,depth);  //TODO： 维护最短路径中每个节点和其所在的层数
                        queue.add(w); //将下一层的节点放入队列
                        if (w == t) { //找到了目标节点,直接返回，因为这个无向图在BFS之前就已经构建好了
                            return;
                        }
                    }
                }
            }
        }
    }

    private void dfs(int k, int s, int t, HashMap<Integer, Integer> map, List<Integer> path) {
        path.add(s);
        if (s == t) {
            allResult.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i<adj[s].size();i++) {
            int w = adj[s].get(i);
            Integer shortLevel = map.get(w);
            if (shortLevel!=null && k+1 == shortLevel.intValue()) { //如果下一层刚好是w节点的最短路径所在层数，那么才能继续dfs
                dfs(k+1,w,t,map,path);
                path.remove(path.size()-1);
            }
        }
    }

     /*
                     图
                    (0)
                  /     \
                (1)-----(2)
                /  \   /   \
              (3)   (4)    (5)
                \    |    /
                 \   |   /
                    (6)
     */

    public static void main(String[] args) {
        UndiGraph graph = new UndiGraph(7);
        graph.addEdge(0,1); //增加0和1的边
        graph.addEdge(0,2); //增加0和2的边
        graph.addEdge(1,2); //增加1和2的变
        graph.addEdge(1,3);
        graph.addEdge(1,4);
        graph.addEdge(2,4);
        graph.addEdge(2,5);
        graph.addEdge(3,6);
        graph.addEdge(4,6);
        graph.addEdge(5,6);
        int level = graph.getLevel(0, 6);
        System.out.println("level: " + level);
        List<Integer> shortPath = graph.getShortPath(0, 6);
        System.out.println(shortPath);
//        List<List<Integer>> allShortPath = graph.getAllShortPath(0, 6);
//        System.out.println("allShortPath: " + allShortPath); //allShortPath: [[0, 1, 3, 6], [0, 1, 4, 6], [0, 2, 4, 6], [0, 2, 5, 6]]
        List<List<Integer>> allShortPathOptimize = graph.getAllShortPathOptimize(0, 6);
        System.out.println("allShortPathOptimize: " + allShortPathOptimize);
    }

}
