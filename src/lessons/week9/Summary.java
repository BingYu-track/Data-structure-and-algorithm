package lessons.week9;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 深度和广度优先遍历总结
 * @author: bingyu
 * @date: 2023/2/19
 */
public class Summary {


    /*
     一、图的相关概念和算法简介
       1.图的几个常用概念介绍
         顶点
         边: 2个顶点之间有边
         无向图: 边是没有方向的图
         有向图: 边是有方向的图
         有权图: 边是有权重值的图
         度: 一个顶点相连接的边的个数就是度数
         入度: 在有向图中箭头指向一个顶点的边的个数，是这个顶点的入度
         出度: 在有向图中箭头指出一个顶点的边的个数，是这个顶点的出度
            a.无向无权图
         (0)-------(2)
          |         |
          |         |
         (1)-------(3)


            b.无向有权图
         (0)---2---(2)
          |         |
          3         3
          |         |
         (1)---5---(3)

            c.有向无权图
        (0)<------(2)
         |         ↑
         |         |
         V         |
        (1)------>(3)


            d.有向有权图
         (0)<--2---(2)
          |         ↑
          3         |
          |         3
          V         |
         (1)---5-->(3)
            有向有权图

        TODO  注意: 树是图的一种特殊情况，因为树也有顶点和边

        TODO: 任意2个顶点之间都存在路径，那么称为连通图（注意是任意2顶点）。上面的图每个顶点都有路径，因此是连通图。而下面这张图中，
         顶点8和顶点2之间就不存在路径，因此下图不是一个连通图，当然该图中还有很多顶点之间不存在路径。虽然不是一个连通图，但它有多个
         连通子图：0,1,2顶点构成一个连通子图，0,1,2,3,4顶点构成的子图是连通图，6,7,8,9顶点构成的子图也是连通图，当然还有很多子图。
         我们把一个图的最大连通子图称为它的连通分量。0,1,2,3,4顶点构成的子图就是该图的最大连通子图，也就是连通分量！
         连通分量有如下特点：
            1)是子图；
            2)子图是连通的；
            3)子图含有最大顶点数。
            注意：“最大连通子图”指的是无法再扩展了，不能包含更多顶点和边的子图。0,1,2,3,4顶点构成的子图已经无法再扩展了。
            显然，对于连通图来说，它的最大连通子图就是其本身，连通分量也是其本身


                        2<----1<-----0
                              |      |
                              |      |
                              V      V
                              3----> 4

                         6---->7--->9
                               |
                               v
                               8
                 注意:这2个子图构成一个非连通图



       2.图的两种存储方式: 邻接矩阵和邻接表
          TODO  邻接矩阵 :  用二维数组的方式，来表示图

                    a.无向图邻接矩阵的存储方式
                      0   1    2   3
                   0 ['0','1','1','0']
                   1 ['1','0','0','1']
                   2 ['1','0','0','1']
                   3 ['0','1','1','0']
                  用布尔类型的二维数组boolean[][]表示,在无向图中arr[i][j]表示顶点i<-->j之间的关系，arr[i][j]=true和arr[j][i]表示
                  顶点i和顶点j之间有边，且为无向边，如上图arr[0][2]=true并且arr[2][0]=true表示顶点0和2之间有边


                b.有向有权图邻接矩阵的存储方式
                    0   1    2   3
                0 ['0','3','0','0']
                1 ['0','0','5','0']
                2 ['2','0','0','0']
                3 ['0','0','3','0']
                用int类型的二维数组int[][]表示，在有向图中arr[i][j]表示顶点i-->j之间的关系，arr[i][j]=k并且arr[j][i]=0表示从i指向j的
                一个边，权重是k。
                如上图arr[0][2]=0并且arr[2][0]=2说明顶点0和顶点2之间的边，方向是从顶点2出发指向0，边的权重是2


          TODO 邻接表: 和哈希表有点相似，假设一个顶点A和n个顶点相连，那么在这个顶点后面拉n个节点长度的链表
                  a.无向图的邻接表的存储方式

               数组
             [0]-->[1| ]-->[2| ]-->null    顶点0和1、2顶点相连，因此后面跟1,2个节点，形成一个链表
             |1]-->[0| ]-->[3| ]-->null
             [2]-->[0| ]-->[3| ]-->null
             [3]-->[1| ]-->[2| ]-->null

                 b.有向有权图的邻接表的存储方式
             [0]-->[1|3| ]-->null   顶点0和顶点1，从0出发指向1，权重是3
             |1]-->[3|5| ]-->null
             [2]-->[0|2| ]-->null
             [3]-->[2|3| ]-->null

             简单总结:
                  邻接矩阵: 存储稀疏图比较浪费空间，数据的访问效率比较高
                  邻接表: 适合存储稀疏图，访问两个顶点之间是否有边，需要遍历链表



         代码实现：
     */
        //有向无权图--邻接矩阵
        public class Graph {
            private int v; //顶点个数
            private boolean matrix[][];

            public Graph(int v) {
                this.v = v;
                matrix = new boolean[v][v]; //默认都为false
            }
            //添加s到t的边
            public void addEdge(int s,int t) {
                matrix[s][t] = true;
            }

        }

    //有向无权图--邻接表
    public class Graph2 {
        private int v; //顶点个数
        private LinkedList<Integer>[] adj; //邻接表

        public Graph2(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0;i<v;i++) {
                adj[i] = new LinkedList<>();
            }
        }
        //添加s到t的边
        public void addEdge(int s,int t) {
            adj[s].add(t); //adj[s]的顶点s对应位置的链表，然后将顶点t加到链表后面
        }

    }

    /*
      3.图上的算法
       (1).搜索or遍历
          BFS
    TODO  DFS(需要重点掌握) 注意回溯是个大类，在图上的回溯叫做DFS，树是图的一种，树上的DFS就叫做树的前中后序遍历

       (2).最短路径(简单知道即可)
          Dijkstra: 针对有权图的单源最短路径算法，并且要求没有负权边
          Bellman-Ford: 针对有权图的单源最短路径算法，允许存在负权边
          Floyd: 针对有权图的多源最短路径算法，允许存在负权边，但不允许负权环
          A*算法: 启发式搜索算法，求有权图的次优最短路径

       (3).最小生成树(简单知道即可)
          Prim算法
          Kruskal算法

       (4).最大流。二分匹配(简单知道即可)
          Ford-Fulkerson
          Edmonds-Karp

     二、广度优先搜索/遍历(这才是要学的重点)
        1.广度优先搜索/遍历算法
          树是图的一种特殊情况，二叉树的按层遍历，实际上就是广度优先搜索，从根节点开始，一层一层的从上往下遍历，先遍历与根节点近的，再逐层遍历
          与根节点远的。
          图上的广度优先搜索(或遍历)跟树上的按层遍历很像，先查找离起始顶点S最近的，然后是次近的，依次往外搜索，直到找到终止顶点t(或所有订点都遍历了一遍)。
              二叉树                         图
               ()                          ()
             /    \                      /    \
            ()    ()                    ()----()
           /  \                        /  \  /  \
          ()  ()                      ()   ()    ()
             /  \                      \   |    /
            ()  ()                      \  |   /
                                           ()

          树其实就是图的一种特殊情况，树是没有环的，图是有环的
          我们知道在按层遍历树时，需要用到队列，同理，图的广度优先搜索(或遍历)也要用到队列。除此之外，对于图的按层遍历，需要用一个visited数组，
          来记录已经遍历过的顶点，防止图中存在环，从而出现循环遍历多次的情况。

          广度优先搜索处理是无权图时，实际上通过广度优先搜索找到的源点到终点的路径也是顶点S到顶点t的最短路径。

        广度优先搜索代码实现:
     */
        //无向无权图
        public class Graph3 {
            private int v; //顶点个数
            private LinkedList<Integer>[] adj; //邻接表

            public Graph3(int v) {
                this.v = v;
                adj = new LinkedList[v];
                for (int i = 0;i<v;i++) {
                    adj[i] = new LinkedList<>();
                }
            }

            public void addEdge(int s,int t) { //添加一条从顶点s到顶点t的边
                adj[s].add(t);
                adj[t].add(s);
            }

            //从s顶点开始遍历直到找到t点
            public boolean bfs_simple(int s,int t) {
                boolean[] visited = new boolean[v]; //用来记录是否遍历过
                Queue<Integer> queue = new LinkedList<>();
                queue.add(s);
                visited[s] = true;
                while (!queue.isEmpty()) {
                    int p = queue.poll(); //第一次是返回的p就是顶点S
                    if (p == t) {  //加上这行代码就是搜索，去掉就是遍历
                        return true;
                    }
                    for (int i = 0;i < adj[p].size();i++) { //由于是邻接表，直接遍历与p点连接的所有顶点
                        int q = adj[p].get(i); //得到与p点相连接的q点
                        if (!visited[q]) {
                            visited[q] = true;
                            queue.add(q);
                        }
                    }
                }
                return false;
            }

            //将上面的方法进一步改造，使其支持能打印出从s到t的路径
            //空间复杂度分析: 因为只用到了visited、queue、prev，总空间是3v，因此空间复杂度是O(v)
            //时间复杂度分析: while里的for循环应该是执行次数最多的，先分析它，内层for循环可以看出是和顶点相连的边数成正比的，
            // 因此时间复杂度是O(E)，E表示图的边数
            public void bfs(int s,int t) {
                int[] levels = new int[v]; //TODO: 用来存储每个节点BFS所在的层数/深度
                boolean[] visited = new boolean[v]; //用来记录是否遍历过
                Queue<Integer> queue = new LinkedList<>();
                queue.add(s);
                levels[s] = 1;
                visited[s] = true;
                int[] prev = new int[v]; //这个数组用来记录每个顶点是从哪个顶点扩展出来的
                for (int i = 0;i < v;i++) {
                    prev[i] =-1; //先都初始化为-1
                }
                while (queue.size() !=0) {
                    int p = queue.poll();
                    if (p == t) {
                        print(prev,s,t);
                        System.out.println(s + "到" + t + "的最短路径节点个数: " + levels[t]);
                        return;
                    }
                    for (int i = 0;i < adj[p].size();i++) { //遍历与p相连的所有顶点
                        int q = adj[p].get(i);
                        if (!visited[q]) { //说明之前没有访问q点
                   /*TODO 记录q点的前置点，最后结果为prev = [-1,0,0,1,1,2,3]，为什么这里记录6的前置顶点只有3，而没有其它两个点4、5呢?
                         因为再遍历1下面的顶点3，后面继续向下时，已经把6遍历过一遍了，我们在visited数组记录了6节点，已被遍历过，因此后面
                         顶点4,5遍历时会跳过6这个顶点，因此，我们知道该代码只会记录顶点值最小的那一个路径
                    */
                            prev[q] = p; //设置q的前置节点为p
                            visited[q] = true;
                            queue.add(q); //将相连的顶点放入队列中
                            levels[q] = levels[p] + 1; //TODO：后面节点的层数等于上一个节点层数加1
                        }
                    }
                }
            }

            /*
                     图1
                    (0)
                  /     \
                (1)-----(2)
                /  \   /   \
              (3)   (4)    (5)
                \    |    /
                 \   |   /
                    (6)
     */

            /*TODO 该方法是重点，如何理解?
               执行到这里说明prev数组已经存了s->t的所有路径，但是要注意，此时prev存的是所有顶点的前置顶点，因此我们只能从结束顶点t
               开始反向打印，为什么要从结束顶点反向打印呢？因为prev存储了每个顶点的前置点，那么我们就能从尾节点推算出它是从哪个节点到尾节点的，
               反之，如果我们想从起始节点推算出会走后面哪个节点是不行的，因为有多种可能！例如[-1,0,0,1,1,2,3]6尾节点是从3过来的，3是从1过来的，
               1是从0过来的；而我们看起始点，它有2个后置节点，1和2，因此只能从结束顶点往前推导!

              由于我们只能从后往前推导，得到的会是个我们要求的路径的逆序，因此我们使用递归将该路径进行逆序就得到了我们要的路径了!
              null<--0<--1<--3<--6
             */
            private void print(int[] prev, int s, int t) { //prev = [-1,0,0,1,1,2,3] 顶点6从顶点3进来,顶点5从顶点2进来....，顶点0位置存的-1就是前面没有前置顶点
                //结束递归的条件是: 到达了起始顶点，或者遍历过程中，当前顶点t没有了前置顶点，说明到了路径的起始点
                if (prev[t]!=-1 && t!=s) { //t!=s表示没有到达起始顶点 ，prev[t]!=-1表示当前t顶点还有前置点
                    print(prev,s,prev[t]); //
                }
                //第一次执行到这里说明t==s，已经到达了起始出发点，开始打印起始点
                System.out.println(t + " ");
            }

    }

    //使用BFS查找0-->6的最短路径
    public static void main(String[] args) {
        Summary s = new Summary();
        //创建图1
        Graph3 graph3 = s.new Graph3(7);
        graph3.addEdge(0,1); //增加0和1的边
        graph3.addEdge(0,2); //增加0和2的边
        graph3.addEdge(1,2); //增加1和2的变
        graph3.addEdge(1,3);
        graph3.addEdge(1,4);
        graph3.addEdge(2,4);
        graph3.addEdge(2,5);
        graph3.addEdge(3,6);
        graph3.addEdge(4,6);
        graph3.addEdge(5,6);
        graph3.bfs(0,6); //打印从顶点0到6的最短路径
    }


    /*
     三、深度优先搜索/遍历算法
       广度优先搜索是一种"地毯式的搜索策略"，那么深度优先搜索(DFS)就是一种"不撞南墙不回头"的搜索策略。
       "DFS实际就是图上的回溯算法"。沿着一条路一股脑往前走，当走到无路可走是，再回退到一个岔路口，选择另
       一条路继续前进。

       树的前中后序遍历就是深度优先遍历。前中后序的区别仅仅在于处理节点的时机不同。
       换句话说: 树上的深度优先遍历又分为三类，前中后序遍历
       TODO: 实际上，DFS也是一种回溯算法，也可以看做多阶段决策模型，用回溯模板解决。
           1.每个阶段都是基于当前顶点移动到下一个顶点。
           2.可选列表是: 相邻切没有被访问过的顶点。
           3.因为每个顶点相邻的顶点不同，因此每个阶段会做出不同的选择。
           4.回溯结束条件: 所有节点都已经访问完成或找到了终止顶点。
           5.在回溯过程中，我们用visited数组,记录已经遍历过的顶点，以免循环重复遍历。
    */

    //无向图
    public class Graph4 {
        private int v; //顶点个数
        private LinkedList<Integer>[] adj; //邻接表

        public Graph4(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0;i<v;i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s,int t) { //添加一条从顶点s到顶点t的边
            adj[s].add(t);
            adj[t].add(s);
        }
        private boolean found = false; //标识是否找到
        private boolean[] visited = new boolean[v]; //顶点个数
        private List<Integer> resultPath = new ArrayList<>();

        //TODO 解决问题1: "判断从S到t的路径是否存在"
        public boolean dfs_simple(int s,int t) {
            dfs_simple_r(s,t);
            return found;
        }

        //查找s到t的点是否存在路径
        private void dfs_simple_r(int s, int t) {
            if (found) return; //找到直接返回
            visited[s] = true;
            if (s == t) { //找到t点，返回
                found = true;
                return;
            }
            //横向循环遍历s点相连的所有顶点
            for (int i = 0;i < adj[s].size();i++) {
                int q = adj[s].get(i);
                if (!visited[q]) { //还没有访问过的点，继续向下搜索，不过就是从新的点开始了
                    dfs_simple_r(q,t);
                }
            }
        }

        //TODO 解决问题2: "记录图中从顶点s到顶点t的路径节点"
        /*
          空间复杂度分析:O(n)，顶点的个数有关
          时间复杂度分析: 里面所有顶点横向遍历综合好像也是和顶点的个数有关O(E),E为图的边数，因为一个顶点，会探测其关联的所有边，
          但不一定会递归执行下去，因为一些顶点可能已经访问过了！
         */
        public List<Integer> dfs(int s,int t) {
            List<Integer> path = new ArrayList<>();
            dfs_r(s,t,path);
            return resultPath;
        }

        private void dfs_r(int s, int t, List<Integer> path) {
            if (s == t) { //找到路径末尾节点就返回
                resultPath = new ArrayList<>(path);
                return;
            }
            visited[s] = true; //表示当前顶点已经遍历过
            path.add(s); //将当前顶点放入路径中
            for (int i = 0;i < adj[s].size();i++) { //2+2+1+1+1 总共加起来
                int q = adj[s].get(i); //当前s点可能下面的节点
                if (!visited[q]) {
                    dfs_r(q,t,path);
                }
                /*
                TODO:
                 撤销返回，因为执行到这个代码说明是需要回退到原来的节点，再往下走是走不到t节点，因此此时q节点就不是我们需要的路径节点
                 a-->b-->d---->f---->e---->f--->g 这是顶点s到顶点t的路径可以发现，到达e点后，无路可走，只能从e点回退到f点，
                 此时e点不是a->c->d->f->g的路径点，需要删除
                 */
                path.remove(path.size()-1);
            }
        }
        /*
                           图示
                             s顶点
                            (a)
                          /     \
                        (b)-----(c)
                       /  \
                     (d)  (e)   (g)  t顶点
                      \    |    /
                       \   |   /
                        (  f  )

         */
    }



    /*
      DFS题型详解:
       题型1、二维矩阵搜索或遍历
       题型2、最短路径(BFS) 主要采用BFS来解决
       题型3、连通分量/连通性
       题型4、拓扑排序 TODO: 见example4 通常给的是一组边，需要我们将这些边组成一个图，然后再进行处理，因为仅
                           通过边，我们是无法确定一条点到点的路径的，需要我们全部的边构成图串起来才行!
       题型5、检测环


       TODO: 感觉"在图中查询两点的路径是否存在"问题和"拓扑排序"问题解决的似乎是同一个问题?，有点混淆
            答: 是不一样的，"查找路径问题"只需要查找指定两点，不需要输出全部的顶点，只需要查找其中的一条路径就行，
                而"拓扑排序"不一样，拓扑排序需要将所有顶点按照"入度等于0"的依赖关系一个一个进行排序，需要查找所有顶点，路径
                问题不需要，而且只有有向图才存在拓扑排序。

     */

}
