package lessons.week9.example.dfsbfs.example4;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import lessons.week9.graph.DiGraph;
import lessons.week9.graph.UndiGraph;

import java.util.List;

/**
 * @version 1.0
 * @Description: 题型5，检测环
 * @author: bingyu
 * @date: 2023/3/1
 */
public class CheckCircle {


    /*
     求序列是否有循环依赖，比如给出[[A,B],[B,C],[C,A]]两顶点关系， A-->B-->C-->A就有循环依赖(字节面试题)

     解决思路: 首先根据给出的两两关系构建出一个邻接表，用hashMap记录上面两个关系，遇到重复的就加1，例如
     [A,B]是A->B，那么A在hashMap中的value是0，B是1，后面[B,C]B->C,B仍然是1，C的value是1；到[C,A]后就是
     A的value变成1了，那么A,B,C的入度都不为0，那么肯定是有环的！

     TODO： 1.无向图判断是否有环
             方法1（数学方法）： 图的顶点数为n，边数为m，若n>=m+1，则无环;否则有环。
             方法2：使用并查集进行判断。
             方法3：DFS。使用visited数组辅助判断是否访问过。


     TODO： 2.有向图判断是否有环
             方法一：每次从有向图中取出一个入度为 0 的节点删除，同时删除该节点及所有以他为起点的边，若最终图为空则证明无环，最终非空则证明有环
            为什么呢？
             我们来单独考虑一个单环，那么环中每一个点都是入度为 1，出度也为 1，即不可能入度为 0。按上面删环的描述过程，
             如果环存在，这个环中的的每个点都无法有机会变成入度为 0 的点，因此就证明了环的存在。
            方法二: DFS。使用一个color数组表示节点类型，color[i]=0表示该节点未被访问，color[i]=1表示该节点正在当前访问的路径中或该节点
            存在于环路中，color[i]=2表示该节点是安全不存在于环中的。

            用一个布尔数组记录DFS遍历的路径。每处理一个节点时，将其加入到路径数组，当所有子树处理完了的时候，再清空路径数组。
     */

    public static void main(String[] args) {
        UndiGraph undiGraph = new UndiGraph(5); //创建无向图
        DiGraph graph = new DiGraph(7); //创建有向图
        graph.addEdge('g'-'a','b'-'a'); //添加g-->b的边
        graph.addEdge('g'-'a','c'-'a'); //添加g-->c的边
        graph.addEdge('b'-'a','c'-'a'); //添加b-->c的边
        graph.addEdge('b'-'a','d'-'a'); //添加b-->d的边
        graph.addEdge('b'-'a','e'-'a'); //添加b-->e的边
        graph.addEdge('d'-'a','f'-'a'); //添加d-->f的边
        graph.addEdge('e'-'a','f'-'a'); //添加e-->f的边
        graph.addEdge('f'-'a','a'-'a'); //添加f-->g的边
        graph.addEdge('a'-'a','e'-'a'); //添加a-->e的边
        boolean cicyle = graph.checkCicyle(); //检测环,注意这种方法检测环会修改图的结构，不推荐这种方法
        System.out.println("有向连通图检测环(有环): "+cicyle);
        boolean cicyleByDfs = graph.checkCicyleByDfs(); //通过DFS来检测环
        System.out.println("有向连通图DFS检测环: "+cicyleByDfs);

        /*
                   有向连通图:
                           s顶点
                            (g)
                          /     \
                         |       |
                         V       V
                        (b)---->(c)
                       /  \
                      V    V
                     (d)  (e)<--(a)  t顶点
                      \    |    ↑
                       \   |   /
                        V  V  /
                        (  f  )
        */
        System.out.println();

        DiGraph graph3 = new DiGraph(10); //创建一个有向非连通图
        graph3.addEdge('a'-'a','b'-'a'); //添加a-->b的边
        graph3.addEdge('a'-'a','e'-'a'); //添加a-->e的边
        graph3.addEdge('b'-'a','c'-'a'); //添加b-->c的边
        graph3.addEdge('b'-'a','d'-'a'); //添加b-->d的边
        graph3.addEdge('d'-'a','e'-'a'); //添加d-->e的边
        graph3.addEdge('g'-'a','h'-'a'); //添加g-->h的边
        graph3.addEdge('h'-'a','i'-'a'); //添加h-->i的边
        graph3.addEdge('h'-'a','j'-'a'); //添加h-->j的边
        graph3.addEdge('i'-'a','g'-'a'); //添加i-->g的边
        boolean checkCicyle = graph3.checkCicyle();
        System.out.println("有向非连通图检测环: " + checkCicyle);
        boolean checkCicyleByDfs = graph3.checkCicyleByDfs();
        System.out.println("有向非连通图DFS检测环: " + checkCicyleByDfs);
        if (!checkCicyle) {
            List<String> toposortByKahn3 = graph3.topoSortByKahn();
            System.out.println("有向非连通图Kahn算法拓扑排序: " + toposortByKahn3); //[a, f, g, b, h, c, d, i, j, e]
        }
        if (!checkCicyleByDfs) {
            List<String> topoSortByDFS = graph3.topoSortByDFS();
            System.out.println("有向非连通图DFS算法拓扑排序: " + topoSortByDFS); //[g, h, j, i, f, a, b, d, e, c]
        }



    }

    /*
                    有向非连通图:
                 c<---b<-----a
                      |      |
                      |      |
                      V      V
                      d----> e

                      g--->h--->j
                           |
                           v
                           i
     */
}
