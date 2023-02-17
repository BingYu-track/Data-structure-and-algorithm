package lessons.week7.pratice.trie.pratice7;

import java.util.List;

/**
 * @version 1.0  单词搜索 II
 * @Description: 给定一个m x n 二维字符网格board和一个单词（字符串）列表 words，返回所有二维网格上的单词。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。---这里意思是不同单词中，同一个单元格内的子没有可以重复使用!
 * TODO: 该题目的意思是在二维数组中寻找能和words里的字符串匹配的字符，且只能在水平相邻个垂直相邻的单元格内寻找组合，注意它
 *     这个相邻是指当前字符所在水平的一整排和纵向一整列的所有字符
 * 示例 1：
 *
 * 输入：board = [["o","a","a","n"],
 *               ["e","t","a","e"],
 *               ["i","h","k","r"],
 *               ["i","f","l","v"]], words = ["oath","pea","eat","rain"]
 * 输出：["eat","oath"]
 *
 * 示例 2：
 *
 * 输入：board = [["a","b"],
 *              ["c","d"]], words = ["abcb"]
 * 输出：[]
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 *
 * @author: bingyu
 * @date: 2022/12/12
 */
public class FindWords {

    public static void main(String[] args) {
        char[][]  board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = {"oath","pea","eat","rain"};
        List<String> result = findWords(board, words);
        System.out.println(result);
    }

    /*
     我的思路:用字典树存储words，然后用行列组成一个主串到字典树进行多模式匹配，如果找到了，就在指定的位置
     */
    public static List<String> findWords(char[][] board, String[] words) {
        int m = board.length; //数组个数
        int n = board[0].length; //数组里的元素个数
        String str = "";
        for (int i = 0;i < m;i++) {
            for (int j=0;j < n;j++) {
                char c = board[i][j];

            }
        }
        return null;
    }



}
