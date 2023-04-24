package lessons.week9.pratice.dfsbfs.pratice7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0  面试题 17.22. 单词转换----重复练习
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
 * @date: 2023/4/21
 */
public class RpFindLadders {

    public static void main(String[] args) {
        RpFindLadders rp = new RpFindLadders();
        String beginWord = "hot";
        String endWord = "dog";
//        String[] arr = {"frye","heat","tree","thee","game","free","hell","fame","faye"};
//        String[] arr = {"hot","dot","dog","lot","log","cog"};
        String[] arr = {"hot","dog"};
        List<String> wordList = Arrays.asList(arr);
        List<String> result = rp.findLadders(beginWord, endWord, wordList);
        System.out.println(result);
    }

    private List<String> result = new ArrayList<>();
    private Map<String, LinkedList<String>> adj;

    /*
    执行用时：88 ms, 在所有 Java 提交中击败了68.32%的用户
    内存消耗：49.7 MB, 在所有 Java 提交中击败了5.34%的用户
     26个字母依次和word中的每个字符替换形成newWord，再判断newWord是否在字典中，每次查找只需要O(26 * wordlength)，如果一个个对比的话，每次
     查找需要O(n * wordlength)；得到新字符后，两个单词建立关系，这样就形成了一个图，然后在图中查找路径
    */
    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        adj = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (String word : wordList) {
            set.add(word);
        }
        if (!set.contains(endWord)) return result;
        if (!set.contains(beginWord)) {
            set.add(beginWord);
        }
        Map<String,Integer> visited = new HashMap<>(); //0-未访问  1-已访问还在当前路径  2-已访问并退出了当前路径
        //构建图
        for (String word : set) {
            visited.put(word,0);
            char[] originWord = word.toCharArray();
            for (int i = 0;i<originWord.length;i++) {
                char tmp = originWord[i];
                for (char j = 'a';j<='z';j++) {
                    if (tmp == j) continue; //避免生成的新单词和原单词一模一样
                    originWord[i] = j;
                    String newWord = new String(originWord); //形成新的单词
                    if (set.contains(newWord)) {
                        adj.putIfAbsent(word,new LinkedList<>());
                        adj.get(word).add(newWord);
                    }
                }
                originWord[i] = tmp;
            }
            System.out.println();
        }
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(beginWord,endWord,path,visited);
        return result;
    }

    private boolean flag = false;

    //由于单词之间是可以互相转换的，单词和单词之间双向的，因此使用visited来防止访问重复元素导致死循环
    private void dfs(String beginWord, String endWord,List<String> path, Map<String,Integer> visited) {
        visited.put(beginWord,1);
        if (flag) return;
        if (beginWord.equals(endWord)) {
            result = new ArrayList<>(path);
            flag = true;
            return;
        }
        LinkedList<String> list = adj.get(beginWord);
        if (list!=null) {
            for (String s : list) {
                int status = visited.get(s);
                if (status == 0) {
                    path.add(s);
                    dfs(s,endWord,path,visited);
                    path.remove(path.size()-1);
                }
            }
        }

    }


}
