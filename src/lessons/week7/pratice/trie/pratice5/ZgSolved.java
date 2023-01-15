package lessons.week7.pratice.trie.pratice5;

/**
 * @version 1.0
 * @Description: 实现 Trie (前缀树) --争哥解法(和我基本一致)
 * @author: bingyu
 * @date: 2022/12/12
 */
public class ZgSolved {



    public void insert(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (p.children[c-'a'] == null) {
                p.children[c-'a'] = new TrieNode(c);
            }
            p = p.children[c-'a'];
        }
        p.isEnding = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode p = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (p.children[c-'a'] == null) return false;
            p = p.children[c-'a'];
        }
        return p.isEnding;
    }
    public boolean startsWith(String prefix) {
        TrieNode p = root;
        for (int i = 0; i < prefix.length(); ++i) {
            char c = prefix.charAt(i);
            if (p.children[c-'a'] == null) return false;
            p = p.children[c-'a'];
        }
        return true;
    }

    private class TrieNode {
        char data;
        boolean isEnding = false;
        TrieNode[] children = new TrieNode[26];
        public TrieNode(char data) {
            this.data = data;
        }
    }

    private TrieNode root;
    /** Initialize your data structure here. */
    public ZgSolved() {
        root = new TrieNode('/');
    }
}
