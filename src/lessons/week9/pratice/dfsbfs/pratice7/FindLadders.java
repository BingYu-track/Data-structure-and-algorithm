package lessons.week9.pratice.dfsbfs.pratice7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0  面试题 17.22. 单词转换
 * @Description: 给定字典中的两个词，长度相等。写一个方法，把一个词转换成另一个词， 但是一次只能改变一个字符。每一步得到的新词都必须能在字典中找到。
 * 编写一个程序，返回一个可能的转换序列。如有多个可能的转换序列，你可以返回任何一个。
 *
 * 示例 1:
 * 输入: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出: ["hit","hot","dot","lot","log","cog"]
 *
 * 示例 2:
 * 输入:beginWord = "hit" , endWord = "cog" , wordList = ["hot","dot","dog","lot","log"]
 * 输出: []
 *
 * 解释:endWord "cog" 不在字典中，所以不存在符合要求的转换序列。
 *
 * @author: bingyu
 * @date: 2023/3/17
 */
public class FindLadders {

    public static void main(String[] args) {
        FindLadders ladders = new FindLadders();
        String beginWord = "hit";
        String endWord = "cog";
//        String[] arr = {"frye","heat","tree","thee","game","free","hell","fame","faye"};
        String[] arr = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(arr);
        List<String> result = ladders.findLadders(beginWord, endWord, wordList);
        System.out.println(result);
    }




    private List<String> result = new ArrayList<>();

    private boolean iscicycle = false;

    /*TODO: 我开始的思路，但是错了!
    我刚开始的解法思路，就是用dfs回溯的思想解决，结果发现超时了!可能是因为测试案例带有环导致的，因此我们还要对有环的加一个判断。
    总体思路是: beginWord看作起点，endWord看作终点，给到的wordList看作图的每个顶点，顶点和顶点之间是否有连接就是判断
     单词之间是否只有一个字符不同，这样就把问题转换成在图中找起点和终点的路径了

     执行用时：528 ms, 在所有 Java 提交中击败了17.92%的用户
      内存消耗：42.4 MB, 在所有 Java 提交中击败了63.59%的用户
      是哪里执行效率慢了?可以进行优化?
    */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> path = new ArrayList<>();
        if (!wordList.contains(endWord)) { //不包含目标单词，直接返回
            return result;
        }
        path.add(beginWord);
        int[] visited = new int[wordList.size()]; //用来记录被用过的单词 0表示未访问  1表示已访问并在当前路径  2表示已回溯
        dfs(beginWord,endWord,wordList,visited,path);
        return result;
    }

    /**
     * 终止条件: 所有的单词都访问过、遇到目标单词
     * @param beginWord 起始单词
     * @param endWord 目标单词
     * @param wordList 转换的单词列表
     * @param visited 存储各个单词是否被访问
     * @param path 路径
     */
    private void dfs(String beginWord, String endWord, List<String> wordList, int[] visited, List<String> path) {
        if (!result.isEmpty()) return; //1.防止后面出现第2种转换路径从而覆盖第一个结果  2.如果出现环，也之间返回
        if (transform(beginWord,endWord)) { //发现能和目标字符转换
            result = new ArrayList<String>(path);
            result.add(endWord);
            return;
        }
        for (int i = 0;i < wordList.size();i++) { //将当前的单词和其它的所有单词进行比较，看是否可以转换
            //执行到这里说明beginWord和endWord无法转换，顺序取wordList种的单词转换
            String word = wordList.get(i);
            if (word.equals(endWord) || word.equals(beginWord)) continue; //如果取到了目标单词跳过
            //判断beginWord和当前单词能否转换
            boolean flag = transform(beginWord,word);
            if (flag && visited[i]==0) { //能转换，而且是之前没转换过的单词，就用这个转换的单词继续执行
                visited[i] = 1;
                path.add(word);
                dfs(word,endWord,wordList,visited,path); //这里不传到达了第几层，因为每当转换成新的单词后，该单词需要和非自己的所有单词进行比较才行
                path.remove(path.size()-1); //撤销
                visited[i] = 2;
            }else if (flag && visited[i] == 1) { //说明是之前已访问的，直接跳过
                continue;
            }

        }
        //
    }

    /**
     * 判断beginWord和word能否进行转换，即是否存在只有一个字符不同，其它均相同
     * @param beginWord
     * @param word
     * @return
     */
    private boolean transform(String beginWord, String word) {
        int count = 0;
        for (int i = 0;i<word.length();i++) {
            char bw = beginWord.charAt(i);
            char w = word.charAt(i);
             if (bw != w) {
                 count++;
             }
             if (count > 1) return false;
        }
        return true;
    }

}
