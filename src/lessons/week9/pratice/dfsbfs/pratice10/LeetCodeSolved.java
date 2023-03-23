package lessons.week9.pratice.dfsbfs.pratice10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @version 1.0
 * @Description: 单词接龙---官方解法
 * @author: bingyu
 * @date: 2023/3/22
 */
public class LeetCodeSolved {

    public static void main(String[] args) {
        LeetCodeSolved ladder = new LeetCodeSolved();
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


    Map<String, Integer> wordId = new HashMap<String, Integer>();

    List<List<Integer>> edge = new ArrayList<List<Integer>>();

    int nodeNum = 0; //表示节点的数量

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum]; //这个是用来干什么的?用来记录图中每个节点所在的层数或者深度
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    //TODO: 这个方法是解题的核心思想，构建图的边
    public void addEdge(String word) {
        addWord(word); //先构建当前word的映射以及对应的邻接表
        int id1 = wordId.get(word); //前面构建完后，这里就能得到word所在的位置
        char[] array = word.toCharArray(); //
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array); //生成虚拟节点单词
            addWord(newWord); //将生成的虚拟节点也加入映射表，如果已包含表示word和
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2); //TODO: 将当前节点和虚拟节点构成联系，也就是在这里，如果有其它单词生成的虚拟节点和当前单词一样，那么这里就会取的联系，也就间接
                                    //    使wordlist中的单词联系在一起构成图了!
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

}
