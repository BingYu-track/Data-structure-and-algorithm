package lessons.week9;

import java.util.LinkedList;

/**
 * @version 1.0
 * @Description: 拓扑排序知识和代码
 * @author: bingyu
 * @date: 2023/2/27
 */
public class TopologicalSort {


    /*
     拓扑排序: 有n个任务，已知两两之间的依赖关系，找一个任务执行序列，满足所有的依赖关系
     例如:
        B依赖A  A->B
        A依赖C  C->A
        B依赖C  C->B
        D依赖B  B->D

         A--->B--->D
          \   ↑
           \  |
              C
        可以看到构成一个有向图，满足以上所有依赖关系的一个序列就是: C->A->B->D
        要求出这种序列有2种算法

        1.Kahn算法
         定义数据结构: 如果s需要先于t执行，那就添加一条s指向t的边。所以，每个顶点的入度表示这个顶点依赖
         多少个其它顶点。如果某个顶点的入度变成了0，就表示这个顶点没有依赖的顶点了，或者说这个顶点依赖的
         顶点可以作为起始点开始执行。
         具体思路:
         我们从上图中找出一个入度为0的顶点，将其输出到拓扑排序的结果序列中，这里的输出就表示被执行。既然
         这个顶点已经被执行了，那么所有依赖它的顶点的入度都可以减1，反映到图上就是把这个顶点的可达顶点的
         入度都减1.我们循环执行上面的过程，直到所有顶点都被输出。最后的结果序列就是满足所有局部依赖关系的
         拓扑序列。
         TODO:总结就是先统计每个顶点的入度，然后找入度为0的放到结果数组中，然后将其相邻的顶点入度减1，然后不断这样循环执行

        具体代码:
    */

    //定义一个有向图
    public class Graph2 {
        private int v; //顶点个数
        private LinkedList<Integer> adj[]; //邻接表

        public Graph2(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0;i<v;i++) {
                adj[i] = new LinkedList<>();
            }
        }
        //添加s到t的边，即s->t方向的边
        public void addEdge(int s,int t) {
            adj[s].add(t); //adj[s]的顶点s对应位置的链表，然后将顶点t加到链表后面
        }

        //采用邻接表来存储图，这里是含有v个顶点从0~v-1的数字来代表每个顶点，如果每个顶点是字符就需要用hash表来存储了
        public void topoSortByKahn() {
            int[] inDegree = new int[v]; //统计每个顶点的入度
            for (int i = 0;i < v;i++) { //这个i表示从顶点i出发
                for (int j = 0;j < adj[i].size();j++) { //遍历顶点i连接的的每个顶点
                    int w = adj[i].get(j); //得到一个从i指向w的顶点i->w
                    inDegree[w]++; //w顶点入度加1
                }
            }
            LinkedList<Integer> zeroset = new LinkedList<>(); //用来存储入度为0的顶点
            for (int i = 0;i<v;i++) { //遍历inDegree，找出入度为0的顶点
                if (inDegree[i]==0) zeroset.add(i);
            }
            while (!zeroset.isEmpty()) {
                int i = zeroset.poll(); //弹出一个入度为0的顶点
                System.out.print("-->" + i);
                //再遍历当前弹出顶点相连的所有顶点，并减一个入度
                for (int j = 0;j<adj[i].size();j++) {
                    int k = adj[i].get(j); //与当前i顶点相连的k点
                    inDegree[k]--; //减少一个入度
                    if (inDegree[k]==0) zeroset.add(k);
                }
            }
        }

    }



    /*拓扑排序
     2.DFS算法--DFS算法思路和前面Kahn算法是相反的
       定义数据结构: 如果s需要先于t执行，也就是说，t依赖s，那就添加一条t执行s的边，即t-->s。
       算法处理的核心思想是递归。对于顶点，我们先输出它可达的所有顶点，也就是说，先
       把它依赖的所有的顶点输出，然后再输出自己。这个过程可以借助DFS实现。


          Kahn算法图
        A--->B--->D
          \   ↑
           \  |
              C

            DFS算法图(和Kahn算法方向相反)
         A<---B<---D
          \   |
           \  v
              C

        具体代码:
     */

    public class Graph3 {
        private int v; //顶点个数
        private LinkedList<Integer> adj[]; //邻接表
        private boolean[] visited; //TODO: 相比Kahn算法，多一个记录访问过的顶点数组

        public Graph3(int v) {
            this.v = v;
            adj = new LinkedList[v];
            visited = new boolean[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        //添加s->t
        public void addEdge(int s, int t) {
            adj[s].add(t); //adj[s]的顶点s对应位置的链表，然后将顶点t加到链表后面
        }

        //解决问题3: "DFS拓扑排序"
        public void topoSortByDFS() {
            for (int i = 0;i < v;i++) { //遍历顶点
                if (visited[i] == false) { //没有访问过该顶点，就开始处理该顶点，然后
                    visited[i] = true;
                    dfs(i); //dfs处理其所依赖的顶点
                }
            }
        }

        private void dfs(int vertex) {
            for (int i = 0;i<adj[vertex].size();i++) {
                int w = adj[vertex].get(i);
                if (visited[w] == true) continue;
                visited[w] = true;
                dfs(w);
            } //先把vertex这个顶点可达的所有顶点都打印出来之后，再打印它自己
            System.out.print("-->" +vertex);
        }

    }


    public static void main(String[] args) {
        TopologicalSort t = new TopologicalSort();
        //1.使用Kahn算法来进行拓扑排序
        Graph2 graph2 = t.new Graph2(4);
        graph2.addEdge(3,1); //添加顶点3到1的边,3先执行，然后1才能执行
        graph2.addEdge(3,0);
        graph2.addEdge(0,1);
        graph2.addEdge(1,2);
        graph2.topoSortByKahn(); //输出: 3-->0-->1-->2

        System.out.println();

        //2.使用DFS算法来进行拓扑排序
        Graph3 graph3 = t.new Graph3(4);
        graph3.addEdge(1,3); //添加顶点1到3的边，1依赖于3，和上面的添加边的方向相反
        graph3.addEdge(1,0);
        graph3.addEdge(2,1);
        graph3.addEdge(0,3);
        graph3.topoSortByDFS(); //输出: 3-->0-->1-->2
    }

    /*
        Kahn算法图--这里箭头表示"先于"，0-->1表示，0先于1执行，然后1才能执行
        0---> 1--->2
          \   ↑
           \  |
              3

          DFS算法图--这里箭头表示"依赖"，0<--->1表示，1依赖于0执行；和上面表示的都是同一个意思
         0<---1<---2
          \   |
           \  v
              3
     */
}
