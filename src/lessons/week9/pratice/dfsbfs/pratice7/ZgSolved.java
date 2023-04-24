package lessons.week9.pratice.dfsbfs.pratice7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Description: 面试题 17.22. 单词转换----争哥解法
 * @author: bingyu
 * @date: 2023/3/17
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved ladders = new ZgSolved();
        String beginWord = "hit";
        String endWord = "cog";
        String[] arr = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(arr);
        List<String> result = ladders.findLadders(beginWord, endWord, wordList);
        System.out.println(result);
    }

    /*争哥解法:
        执行用时：560 ms, 在所有 Java 提交中击败了15.61%的用户
        内存消耗：42.5 MB, 在所有 Java 提交中击败了61.27%的用户
     */
    private Set<String> visited = new HashSet<>();
    private List<String> resultPath = new ArrayList<>();
    private boolean found = false;

    public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
        dfs(beginWord, endWord, new ArrayList<>(), wordList);
        return resultPath;
    }

    private void dfs(String curWord, String endWord, List<String> path, List<String> wordList) {
        if (found) return;
        path.add(curWord);
        visited.add(curWord);
        if (curWord.equals(endWord)) {
            resultPath.addAll(path);
            found = true;
            return;
        }
        //没找到，就继续扩展其相邻的节点
        for (int i = 0; i < wordList.size(); ++i) {
            String nextWord = wordList.get(i);
            //没有访问过并且是否只改变了一个字符
            if (visited.contains(nextWord) || !isValidChange(curWord, nextWord)) {
                continue;
            }
            dfs(nextWord, endWord, path, wordList);
        }
        path.remove(path.size()-1); //为何是在这里才进行回溯?那是因为path.add的时机是在dfs里，也就是当前层的开头，而我的代码path.add
        //是在上一层add的，所以remove也是在上一层进行remove
    }


    private boolean isValidChange(String word1, String word2) {
        int diff = 0;
        for (int i = 0; i < word1.length(); ++i) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }

}
