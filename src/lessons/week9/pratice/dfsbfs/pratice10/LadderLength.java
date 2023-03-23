package lessons.week9.pratice.dfsbfs.pratice10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @version 1.0 127. 单词接龙(需要多次练习)
 * @Description: 字典wordList中,从单词 beginWord 和 endWord 的转换序列是一个按下述规格形成的序列 beginWord -> s1-> s2-> ... -> sk：
 *   1.每一对相邻的单词只差一个字母。
 *   2.对于1 <= i <= k时，每个si都在wordList中。注意， beginWord不需要在wordList中。
 *   3.sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList，返回从beginWord 到endWord 的 最短转换序列中的单词数目 。如果不存在这样的转换序列，返回 0 。
 *
 * 示例 1：
 * 输入：beginWord = "hit",
 *      endWord = "cog",
 *      wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：5
 * 解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
 *
 * 示例 2：
 * 输入：beginWord = "hit",
 *      endWord = "cog",
 *      wordList = ["hot","dot","dog","lot","log"]
 * 输出：0
 * 解释：endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 提示：
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有字符串互不相同
 *
 * @author: bingyu
 * @date: 2023/3/21
 */
public class LadderLength {

    public static void main(String[] args) {
        LadderLength ladder = new LadderLength();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<String> list = new ArrayList<>();
        for (String s : wordList) {
            list.add(s);
        }
        int res = ladder.ladderLength(beginWord, endWord, list);
        System.out.println(res);
    }

    /*
    执行用时：52 ms, 在所有 Java 提交中击败了67.28%的用户
    内存消耗：49.6 MB, 在所有 Java 提交中击败了5.22%的用户
     */
    Map<String,Integer> map = new HashMap<String,Integer>(); //用来存储所有word以及word对应所在的位置

    //用来图的基础数据结构，里面的List<Integer>用来表示一个word所在的位置，里面包含的是与word有联系的其它单词对应的位置，这样就构成了一个邻接表
    List<List<Integer>> adj = new ArrayList<>();
    /*
     读题后注意事项:
      1.所有单词长度都相同
      2.beginWord可能不在wordList中
      3.要求的是beginWord到endWord最短转换序列的单词数目
      注意了，题目是要求求最短路径那么肯定就是要用BFS来解决这个问题了，由于题目没有给出点之间的联系，又需要我们进行判断并构建出一个图，
      然后，后面的问题就好解决了，目前难点是如何能快速构建出图?
    TODO: 改题目解题的核心思想就是--如何快速建图(来自leetcode)
     1.首先构建出一个邻接表来作为我们建图的基础，因此我们先给每一个单词标号，即给每个单词分配一个 id。创建一个由单词 word 到 id 对应的映射 wordId，
     并将 beginWord 与 wordList 中所有的单词都加入这个映射中。之后我们检查 endWord 是否在该映射内，若不存在，则输入无解。我们可以使用哈希表实现上面的映射关系。
     2.然后我们需要建图，依据一般的思路，我们可以枚举每一对单词的组合，判断它们是否恰好相差一个字符，以判断这两个单词对应的节点是否能够相连。
     但是这样效率太低，我们可以优化建图。具体地，我们可以创建虚拟节点。对于单词 hit，我们创建三个虚拟节点 *it、h*t、hi*，并让 hit向这三个虚拟节点
     分别连一条边即可。如果一个单词能够转化为 hit，那么该单词必然会连接到这三个虚拟节点之一。对于每一个单词，我们枚举它连接到的虚拟节点，把
     该单词对应的 id 与这些虚拟节点对应的 id 相连即可！

     如何理解用三个虚拟节点 *it、h*t、hi* 快速建图？
     答: 意思是每个word对其生成的3个虚拟节点都先构成一个联系，然后再由器虚拟节点去与其它单词再建立联系,例如: ["hot","dot","dog","lot","log","cog"]

                            hot
                        /    |   \
                       *ot  h*t  ho*     ----------虚节点
                       |
                       V
                    (  dot   )
                  /     |      \
                 *ot   d*t     do*
                 |              |
                 V              V
                lot            dog
            /    |   \     /    |   \
          *ot   l*t  lo*  *og   d*g  do*
                           |
                           V
                          cog
        这样我们就能看出，如果是互相能转换的单词，他们生成的虚拟节点至少会有一个是完全一样的，因此，我们每次生成虚拟节点放入map中时判断map中是否已存在
        该虚拟节点，如果存在，说明word和word之间存在联系，这样我们就能通过虚拟节点快速建立联系，而不用一对对进行遍历了,对了注意单词之前联系是双向的，
        因此构建的图应该是无向图。由于是双向的，遍历时，我们还需要校验节点再路径内是否被访问过！当用BFS得到了一个最短路径，由于该路径里是包含真实节点
        和虚节点的，我们需要根据其路径的元素个数count算出真实节点个数，由于每个单词连接是通过虚节点连接的，因此我们知道beginWord->虚节点...->endWord
        如果最短路径里的节点个数是N，那么真实节点个数应该是(N+1)/2
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) { //不包含目标单词，直接返回
            return 0;
        }
        if (!wordList.contains(beginWord)) { //不包含beginWord，就将单词数量加1
            wordList.add(beginWord);
        }
        int count = wordList.size();
        int wordId = 0; //
        for (int i = 0;i < count;i++) { //构建单词和其位置的映射
            String word = wordList.get(i);
            map.put(word,wordId); //i表示word对应的下标位置
            adj.add(new ArrayList<>()); //初始化邻接表
            wordId++; //单词id一一对应
        }
        //构建每个单词和其虚节点的联系，也就是增加点和点之间的边
        for (int i = 0;i < count;i++) {
            String word = wordList.get(i); //遍历每个单词
            char[] arr = word.toCharArray(); //转成字符数组方便生成虚拟节点
            for (int j = 0;j<arr.length;j++) {
                char temp = arr[j]; //临时存储字符
                arr[j] = '*';
                String newWord = String.valueOf(arr); //生成虚拟节点
                arr[j] = temp;
                Integer id = map.get(word);
                if (!map.containsKey(newWord)) { //映射里没有该虚拟节点就放入，因为其它word生成的虚拟节点可能会重复，所以需要判断
                    map.put(newWord,wordId);
                    adj.add(new ArrayList<>()); //给虚拟节点增加邻接表中的位置
                    adj.get(id).add(wordId);
                    adj.get(wordId).add(id);
                    wordId++; //自增，作为下一个虚拟节点的id
                }else {
                    //newWord已存在，直接添加word和其联系
                    Integer newWordId = map.get(newWord);
                    adj.get(id).add(newWordId);
                    adj.get(newWordId).add(id);
                }
            }
        }
        //TODO: 执行到这里说明图构建完成，此时构建图的时间复杂度是O(C*N)，C是单词的长度，N是单词总个数，如果用暴力一个个对比构建图，其时间复杂度就是O(N^2)
        //现在我们可以开始进行BFS遍历了
        int num = bfs(beginWord,endWord);
        return num;
    }

    private int bfs(String beginWord, String endWord) {
        //adj.size()为所有真实节点+所有虚拟节点的总和，数组visited用来标识节点是否访问过
        boolean[] visited = new boolean[adj.size()];
        int[] levels = new int[adj.size()]; //使用数组来记录每个节点所处的深度，在后面遍历过程中会不断更新
        Queue<Integer> queue = new LinkedList<>();
        Integer beginIndex = map.get(beginWord);
        levels[beginIndex] = 1; //起始节点设为第1层
        queue.add(beginIndex);
        //TODO: 现在难点是如果计算遍历的层数!
        while (!queue.isEmpty()) {
            int id = queue.poll();
            visited[id] = true;
            if (id == map.get(endWord)) {
                //执行到这里说明找到了结束单词，此时level就是 beginWord->虚节点...->endWord的总节点数，真实节点个数应该是(N+1)/2
                return (levels[id] + 1) / 2;
            }
            List<Integer> list = adj.get(id);
            for (int i = 0;i<list.size();i++) {
                int nextWordId = list.get(i); //有联系的节点的下标位置
                //如果节点未访问，才将其放入队列,这样可以避免无向图节点之间双向边的互相循环引用，
                //也可以避免2个节点引用同一节点导致重复访问
                if (!visited[nextWordId]) {
                    queue.add(nextWordId);
                    levels[nextWordId] = levels[id] + 1; //由于nextWordId是word下一层，因此nextWord的层数就是word的层数加1
                }
            }
        }
        return 0;
    }

    /*
      总结:
        1.构建图
          (1).使用map对每个点生成一个id，并建立联系，将这个id作为点的下标位置
          (2).使用集合List<List<Integer>>作为邻接表，来构建图基础数据结构
          (3).由于没有给顶点之间的联系，需要我们快速构建图，而且不能直接遍历顶点来构建联系，效率会很慢;
              可以生成虚拟节点来间接进行顶点之间的联系，这样就能快速构建无向图。
        2.BFS
          (1).广度优先遍历使用队列，要注意由于是无向图，一定要跳过重复访问过的节点，可以使用额外的数组
     TODO:(2).在计算广度遍历的深度时，我们只能通过图中达到固定的节点来计算，使用额外的数组来存储图中每个节点的所在层数，
             入队时，入队的节点就是在下一层，将该入队节点的层数加1，直到遇到最后的节点，那么这个末尾节点的层数就是我们
             要的！

    */
}
