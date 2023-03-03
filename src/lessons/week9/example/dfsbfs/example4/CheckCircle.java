package lessons.week9.example.dfsbfs.example4;

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
     */
}
