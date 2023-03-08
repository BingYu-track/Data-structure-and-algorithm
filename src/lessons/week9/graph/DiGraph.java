package lessons.week9.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @version 1.0
 * @Description: 有向图
 * @author: bingyu
 * @date: 2023/3/6
 */
public class DiGraph {

    private int v;
    private LinkedList<Integer>[] adj;
    private boolean found;
    private List<String> result = new ArrayList<>();

    public DiGraph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0;i < v;i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t) {
        adj[s].add(t);
    }

    //TODO 1.查找点s到点t是否有路径
    public boolean foundPath(char s,char t) {
        int star = s - 'a';
        int end = t - 'a';
        boolean[] visited = new boolean[v]; //用以记录是否已经访问过
        dfs(star,end,visited);
        return found;
    }

    /*
      解题思路: 不断向下遍历，直到遇到end，就是普通的深度遍历
     */
    private void dfs(int star, int end, boolean[] visited) {
        if (found) return; //已经找到就不在执行
        if(star==end) {
            found = true; //注意必须使用成员变量
            return;
        }
        visited[star] = true;
        for (int i = 0;i<adj[star].size();i++) {
            int peek = adj[star].get(i);
            if (visited[peek]) continue;
            dfs(peek,end,visited);
        }
    }

    //TODO 2.记录图中点s到点t的路径
    public List<String> getPath(char s,char t) {
        List<String> path = new ArrayList<>();
        int star = s - 'a';
        int end = t - 'a';
        boolean[] visited = new boolean[v];
        path.add(String.valueOf(s));
        dfs(star,end,visited,path);
        return result; //必须用成员变量
    }

    /*
      使用dfs解决遍历解决，其实就是回溯
     */
    private void dfs(int star, int end, boolean[] visited,List<String> path) {
        visited[star] = true;
        if (star == end) {
            result = new ArrayList<>(path);
            return;
        }
        for (int i = 0;i<adj[star].size();i++) {
            int k = adj[star].get(i);
            if (visited[k]) continue;
            path.add((char)(k + 'a') + ""); //将当前顶点放入路径中
            dfs(k,end,visited,path);
            path.remove(path.size()-1); //撤销
        }
    }


    //TODO 3.拓补排序

    /*
      a.Kahn算法
      解题思路: 先统计每个顶点的入度并存储起来，然后再从中统计入度为0的顶点放入队列中，再从入度为0的顶点开始处理,
        每次处理后，需要将与其关联的顶点入度减1，里面可能会形成新的入度为0的顶点，再将其新形成的入度为0的顶点放入
        队列中，不断执行直到所有元素都执行完毕。
    */
    public List<String> topoSortByKahn() {
        List<String> sorted = new ArrayList<>();
        int[] inDegrees = new int[v]; //用来存储每个顶点的入度
        LinkedList<Integer> zeroDegree = new LinkedList<>(); //用来统计入度为0的顶点
        for (int i = 0;i < v;i++) { //从顶点i开始
            for (int j = 0;j<adj[i].size();j++) { //i-->j，因此j顶点位置的入度加1
                inDegrees[adj[i].get(j)]++;
            }
        }
        for (int i = 0;i<inDegrees.length;i++) {
            if (inDegrees[i] == 0) {
                zeroDegree.add(i);
            }
        }
        //取入度为0的元素
        while (!zeroDegree.isEmpty()) {
            int poll = zeroDegree.poll(); //入度为0的顶点
            sorted.add(String.valueOf((char)(poll + 'a'))); //将入度为0的顶点放入序列中
            for (int i = 0;i < adj[poll].size();i++) {
                inDegrees[adj[poll].get(i)]--; //依赖与poll顶点的点入度都需要减1
                if (inDegrees[adj[poll].get(i)] == 0) {
                    zeroDegree.add(adj[poll].get(i)); //将新的入度为0的顶点放入
                }
            }
        }
        return sorted;
    }

    /*
    TODO: 这个比较难理解，需要重点掌握!
      b.DFS算法
        使用DFS算法有个前提，就是创建图时需要按照依赖关系来创建，例如a-->b 一般创建a到b的边是graph.addEdge(a,b)，
        如果用DFS，我们就要用b-->a来表示，即graph.addEdge(b,a);
       解题思路:  正常我们走的路径排序是[a, b, c, d, e, f, g]，如果按照依赖关系，构造的图按照之前的算法得到的就是[g,f,e,d,c,b,a]，
        我们通过DFS递归可以将其序列顺序进行反转。
    */
    public List<String> topoSortByDFS() {
        boolean[] visited = new boolean[v];
        List<String> sorted = new ArrayList<>();
        for (int i = 0;i<v;i++) { //遍历多余顶点
            if(!visited[i]) {
                visited[i] = true;
                dfs(i,sorted,visited);
            }
        }
        return sorted;
    }

    /* 先找入度为0，且
      先把当前顶点k所依赖的点全部放入集合，最后再将当前顶点放入集合
     */
    private void dfs(int k,List<String> sorted,boolean[] visited) {
        for (int i = 0;i < adj[k].size();i++) {
            int w = adj[k].get(i);  //依赖的顶点
            if (!visited[w]) { //如果当前依赖的w点未访问过，就继续遍历w所依赖的点
                visited[w] = true;
                dfs(w,sorted,visited); //不断深度遍历k点所依赖的所有顶点
            }
        }
        //执行到这里说明顶点k所依赖的点已全部处理完了
        sorted.add((char)(k + 'a')+"");
    }



    /*
                            图示
                           s顶点
                            (g)
                          /     \
                         |       |
                         V       V
                        (b)---->(c)
                       /  \
                      V    V
                     (d)  (e)  (a)  t顶点
                      \    |    ↑
                       \   |   /
                        V  V  /
                        (  f  )

       TODO  注意该图: 拓扑排序是[g, b, c, d, e, f, a]，也可以是[g, b, c, e, d, f, a]，
             但不能是[g, c, b, d, e, f, a]，因为c既依赖a，也依赖b，有2个入度，b只有一个入度,因此c必须等a和c都执行完后才能执行c!
             拓扑排序不一定是唯一的，还有，如果一个图中有多个入度为0的顶点，那么起始点我们从这些点随机取一个即可！有环的图是不存在拓扑序列的的！



                 将上图改为依赖关系得到以下有向图:
                          (a)
                           |
                           V
                          (f)
                        /     \
                       |       |
                       V       V
                      (d)     (e)
                        \     /
                         V   V
                         ( b )<---(c)
                           \      /
                            V    V
                             ( g )
           TODO DFS输出:[g, b, d, e, f, a, c]，注意这也是正确的拓扑排序，因为c只依赖g和b。因此c排到b后面的任意一个位置都可以！
    */


    public static void main(String[] args) {
        DiGraph graph = new DiGraph(7); //创建7个顶点的有向图
        graph.addEdge('g'-'a','b'-'a'); //添加a-->b的边
        graph.addEdge('g'-'a','c'-'a'); //添加a-->c的边
        graph.addEdge('b'-'a','c'-'a'); //添加b-->c的边
        graph.addEdge('b'-'a','d'-'a'); //添加b-->d的边
        graph.addEdge('b'-'a','e'-'a'); //添加b-->e的边
        graph.addEdge('d'-'a','f'-'a'); //添加d-->f的边
        graph.addEdge('e'-'a','f'-'a'); //添加e-->f的边
        graph.addEdge('f'-'a','a'-'a'); //添加f-->g的边
        boolean foundPath = graph.foundPath('b', 'f');
        System.out.println(foundPath);

        if (foundPath) {
            List<String> path = graph.getPath('b', 'f');
            System.out.println("b到f的路径: " + path);
        }

        List<String> toposortByKahn = graph.topoSortByKahn();
        System.out.println("原图Kahn算法拓扑排序: " + toposortByKahn); //[g, b, c, d, e, f, a]

        DiGraph graph2 = new DiGraph(7);
        graph2.addEdge('b'-'a','g'-'a'); //添加b-->a的边
        graph2.addEdge('c'-'a','g'-'a'); //添加c-->a的边
        graph2.addEdge('c'-'a','b'-'a'); //添加c-->b的边
        graph2.addEdge('d'-'a','b'-'a'); //添加d-->b的边
        graph2.addEdge('e'-'a','b'-'a'); //添加e-->b的边
        graph2.addEdge('f'-'a','d'-'a'); //添加f-->d的边
        graph2.addEdge('f'-'a','e'-'a'); //添加f-->e的边
        graph2.addEdge('a'-'a','f'-'a'); //添加g-->f的边
        List<String> toposortByKahn2 = graph2.topoSortByKahn();
        System.out.println("依赖图Kahn算法拓扑排序: " + toposortByKahn2); //[a, c, f, d, e, b, g]
        List<String> topoSortByDFS = graph2.topoSortByDFS();
        System.out.println("依赖图DFS拓扑排序: " + topoSortByDFS); //[g, b, d, e, f, a, c]
        //
    }
}
