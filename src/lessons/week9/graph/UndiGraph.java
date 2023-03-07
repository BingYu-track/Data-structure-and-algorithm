package lessons.week9.graph;

import java.util.LinkedList;

/**
 * @version 1.0
 * @Description: 无向图
 * @author: bingyu
 * @date: 2023/3/6
 */
public class UndiGraph {

    private int v; //顶点总个数

    private LinkedList<Integer>[] adj; //用来存储各个顶点与其它顶点之间的关系

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


}
