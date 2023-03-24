package lessons.week9.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    //添加顶点s到t的边
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
            //将入度为0的顶点放入集合中
            //TODO: 如果所有顶点的入度都能成为0，说明这个图没有环，有环的话，不断去掉关联顶点，入度不可能全是0，
            // 另外如果是非连通图的话，没有环，每个子图肯定也会有入度为0的点，因此该算法也适合非连通图
            sorted.add(String.valueOf((char)(poll + 'a')));
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
      b. DFS算法 遍历有向图实际上就是进行拓扑排序！
        如果我们对每个节点进行DFS会发现(假设无环),会一直向下执行直到后面没有节点才会返回，我们在回溯时把顶点放入集合时，会发现，其点
        在集合的的顺序和路径的顺序刚好是相反的，因此我们只需要每次在回溯放入时addFirst,放到集合的头部，这样我们集合中存储的点的顺序就是
        拓扑顺序了!

       问题: 上面是遍历的连通图，那如果我们遍历的是非连通图呢?
        答: 对于非连通图，DFS遍历过程执行之后，图中一定还有顶点未被访问，需要从图中另选一个未被访问的顶点作为起始点，再重复DFS，直到图中所有顶点
        均被访问过为止!
    */
    public List<String> topoSortByDFS() {
        boolean[] visited = new boolean[v];
        LinkedList<String> sorted = new LinkedList<>();
        visited[0] = true;
        dfs(0,sorted,visited);
        //TODO: 遍历所有未访问过的顶点，之所以这样做，就是为了考虑非连通图，这样的话，子图中dfs不到的点，也会被作为起始点进行DFS,
        // 如果只是连通图，我们只需要dfs(i,sorted,visited);这一个代码即可!
        for (int i = 0;i < v;i++) {
            if(!visited[i]) {
                visited[i] = true;
                dfs(i,sorted,visited);
            }
        }
        return sorted;
    }

    /**
     * 先把当前顶点k所依赖的点全部放入集合，最后再将当前顶点放入集合
     * @param k 初始顶点
     * @param sorted
     * @param visited 标记是否被访问过
     */
    private void dfs(int k,LinkedList<String> sorted,boolean[] visited) {
        for (int i = 0;i < adj[k].size();i++) { //遍历k下面的点
            int w = adj[k].get(i);
            //System.out.print((char)(w + 'a')+","); TODO 不能这样，因为这样就成了横向遍历了，而不是沿着一条方向的路径
            if (!visited[w]) { //如果当前依赖的w点未访问过，就继续遍历w所依赖的点
                visited[w] = true;
                dfs(w,sorted,visited); //不断深度遍历k点所依赖的所有顶点
            }
        }
        //执行到这里说明k下面的点已全部处理完毕，将当前顶点放入集合的头部
        sorted.addFirst((char)(k + 'a')+"");
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
           TODO:  Kahn算法输出:[a, c, f, d, e, b, g]；DFS拓扑排序输出: [c, a, f, e, d, b, g]
            注意2个都是正确的拓扑排序，因为a和c都没有任何依赖，因此任何一个位置都可以，e,d只依赖f，因此e和d只要
            在f的后面任意一个位置都可以！
    */


  /* TODO： c.检测图中是否有环
      方法1: 统计出有向图中入度为0的节点并放入队列，每次遍历这些入度为0的节点时，将其关联的节点入度减1，如果有新的入度为0的节点，就放入
      队列，我们用zeroDegreeCount记录队列遍历中的所有入度为0的节点个数，遍历完后，如果这个节点个数等于这个图节点总个数说明没有环，否则有环;
      因为我们每次从有向图中取出一个入度为 0 的节点，同时删除该节点及所有以他为起点的边，若最终图为空则证明无环，最终非空则证明有环，
      可以这里理解: 一个单环，那么环中每一个点都是入度为1，出度也为 1，即不可能入度为 0。按上面取节点的描述过程，如果环存在，这个环中的的每个点都无法
      有机会变成入度为0的点，即zeroDegreeCount小于总节点个数，因此就证明了环的存在。
     */
    public boolean checkCicyle() {
        //先统计出有向图中每个节点的入度
        int[] inDegrees = new int[v]; //用来存储每个顶点的入度
        LinkedList<Integer> zeroDegree = new LinkedList<>(); //用来统计入度为0的顶点
        for (int i = 0;i < v;i++) {
            for (int j = 0;j < adj[i].size();j++) { //adj[i].get(j)点依赖于i点，因此其入度加1
                inDegrees[adj[i].get(j)]++;
            }
        }
        for (int i = 0;i<inDegrees.length;i++) {
            if (inDegrees[i]==0) {
                zeroDegree.add(i);
            }
        }
        int zeroDegreeCount = 0; //TODO: 这里是重点，记录入度为0的节点个数
        while (!zeroDegree.isEmpty()) {
            int p = zeroDegree.poll();
            zeroDegreeCount++;
            for (int i = 0;i<adj[p].size();i++) { //删除和p顶点相连的边，并且其关联的顶点入度减1
                inDegrees[adj[p].get(i)]--;
                if (inDegrees[adj[p].get(i)] == 0) zeroDegree.add(adj[p].get(i));
            }
        }
        return !(zeroDegreeCount == v); //入度为0的节点个数等于总节点个数，说明无环
    }

    private boolean isCicyle = false;

    /*推荐该方法!
     思路2(这是我一开始的思路): 使用dfs检测环，先从入度为0的节点开始dfs，每个个节点在初始状态为白色，访问该节点时染成红色，并在退出时染成黑色。如果DFS遍历中发现了
       红色节点，则说明有环。先选取任意一个未访问过的点开始遍历
   TODO: 我上面的思路方法不适用于非连通图，因为我只找一个入度为0的点，然后进行dfs遍历，如果是非连通图的话，会有多个入度为0的点，
    这样可能就错过遍历另一个子图了，如果刚好这个子图里有环，那这就会判断错误;还有一种可能就是子图里形成了一个环，子图里所有的顶点入度
    都不为0.我这种dfs根本就无法遍历到这个子图上，也会出错! 所以在使用dfs时需要先遍历多余节点，对每个未访问的节点都要进行DFS才行！
    */
    public boolean checkCicyleByDfs() {
        int[] color = new int[v]; //0表示未访问 1表示节点正在被访问的路径中  2表示节点已经退出了当前路径 (如果后续遍历发现了1，说明有环)
        LinkedList<Integer> list = new LinkedList<>(); //用来存储回溯时的节点
        for (int i = 0;i < v;i++) { //需要对每个节点进行DFS，如果是连通图，这个循环代码就不需要了
            if (color[i] == 0) { //如果没访问该节点，就从这个未访问的节点开始进行DFS
                color[i] = 1; //标记为已访问，并在当前路径中
                dfs(i,color,list);
            }
        }
        return isCicyle;
    }

    /**
     *
     * @param k 起始点
     * @param color 用来记录每个点的状态
     * @param list 存储的拓扑排序集合
     */
    private void dfs(int k, int[] color, LinkedList<Integer> list) {
        for (int i = 0;i<adj[k].size();i++) { //向下遍历k连接的所有顶点
            int w = adj[k].get(i);
            if (color[w] == 0) {
                color[w] = 1;
                dfs(w,color,list);
            }else if (color[w] == 1) { //如果遍历过程中遇到前面路径中访问过的节点，说明存在环
                isCicyle = true;
                return;
            }
        }
        //TODO: 执行到这里说明k下面的节点已经全部执行完,k节点需要返回到上一层了，撤销
        list.addFirst(k);
        color[k] = 2;
    }


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
        List<String> sortByDFS = graph.topoSortByDFS();
        System.out.println("原图DFS算法拓扑排序: " + toposortByKahn); //[g, b, c, d, e, f, a]
        System.out.println();

        DiGraph graph2 = new DiGraph(7); //将上面的有向图变成依赖关系图
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
        System.out.println("依赖图DFS拓扑排序: " + topoSortByDFS); //[c, a, f, e, d, b, g]



    }

/*
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

    */
}
