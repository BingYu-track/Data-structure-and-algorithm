package lessons.week7.pratice.trie.pratice5;

/**
 * @version 1.0  实现 Trie (前缀树)
 * @Description: Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 *
 * 请你实现 Trie 类：
 *
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 *
 * 示例：
 *
 * 输入
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 *
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 *
 * 提示：
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 10^4 次
 *
 * @author: bingyu
 * @date: 2022/12/9
 */
public class Trie {


    public static void main(String[] args) {
        String[] strs = {"am","hello","hi", "how", "here", "her","ok","say","ell"};
        Trie trie = new Trie();
        for (String str : strs) {
            trie.insert(str);
        }
        System.out.println(trie.search("am"));
        System.out.println(trie.startsWith("sa"));
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode('/');
    }


    /*
    执行用时：34 ms, 在所有 Java 提交中击败了56.29%的用户
    内存消耗：50.7 MB, 在所有 Java 提交中击败了36.90%的用户
    插入字典树
    */
    public void insert(String word) {
        TrieNode p = root;
        char[] chars = word.toCharArray();
        for (int i = 0;i<chars.length;i++) {
            int index = chars[i] - 'a';
            if (p.children[index] == null) { //没有该字符，插入字典树;如果有的话直接到下一个节点
                p.children[index] = new TrieNode(chars[i]);
            }
            p = p.children[index];
        }
        //执行到这里说明word到了末尾
        p.isEnd = true;
    }

    /*
    完全匹配
    */
    public boolean search(String word) {
        TrieNode p = root;
        char[] chars = word.toCharArray();
        for (int i = 0;i < chars.length;i++) {
            int index = chars[i] - 'a';
            if (p.children[index] == null) { //没有，直接返回false
                return false;
            }
            //执行到这说有，到下一个节点继续查找
            p = p.children[index];
        }
        if (!p.isEnd) return false; //说明word只有前缀匹配到字典树里的字符串
        return true;
    }

    /*
     前缀匹配
    */
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        char[] chars = prefix.toCharArray();
        for (int i = 0;i < chars.length;i++) {
            int index = chars[i] - 'a';
            if (p.children[index] == null) return false;
            //执行到这里说明匹配了一个字符
            p = p.children[index];
        }
        //执行到这里说明前缀字符正常遍历完了
        return true;
    }

    public class TrieNode {

        public char data;
        public TrieNode[] children;
        public boolean isEnd;

        public TrieNode() {
            children = new TrieNode[26];
        }


        public TrieNode(char c) {
            data = c;
            children = new TrieNode[26];
        }
    }

}
