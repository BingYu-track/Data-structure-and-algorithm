package lessons.week7.pratice.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: trie树的应用
 * @author: bingyu
 * @date: 2023/1/11
 */
public class Trie {

    public class TrieNode {
        public char data;
        public TrieNode[] children = new TrieNode[26]; //a-z 26个字母：一个节点下最多有26个子节点，所以是26叉树
        public boolean isEndingChar = false; //判断是是结尾字符
        public TrieNode(char data) {
            this.data = data;
        }
    }

    private TrieNode root = new TrieNode('/'); //根节点

    //TODO：往Trie树中插入一堆字符串，其插入字符串的时间复杂度为O(n)，n表示字符串的长度
    public void insert(char[] text) { //text为字符串数组
        TrieNode p = root;
        for (int i = 0;i < text.length;i++) {
            int index = text[i] - 'a'; //得到字符将要在trie树节点指针数组的所在位置
            if (p.children[index] == null) { //如果指针对应的index下标为空，则用该字符创建一个trie树节点，并放进父节点p的指针中
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];//然后指向下一个指针
        }
        p.isEndingChar = true; //字符串插入完成后，p节点就是末尾节点，打上结束标记
    }

    /*
     TODO: 应用场景1--在一堆字符串里查找一个指定字符串，时间复杂度为O(n)

     在字典树里查找字符串(完全匹配)，target为要查找的指定字符串
    */
    public boolean find(char[] target) {
        TrieNode p = root;
        for (char c : target) {
            int index = c - 'a';
            if (p.children[index] == null) { //指针为null直接返回
                return false; //不存在target
            }
            //执行到这说明p后面指针不为空，说明字典树里有该字符
            TrieNode child = p.children[index];
            p = child;
        }
        if (p.isEndingChar == false) return false; //不能完全匹配，字典树里只能匹配要查找字符串的前缀，即前面部分
        return true;
    }

    /*
      TODO: 应用场景2--在主串里查找多个模式子串，并得到每个模式串在主串所在位置
      在"abdhellosayhiok"主串中查找{"am","hello","hi", "how", "here", "her","ok","say","ell"}集合中每个子串的位置
      就是将多个模式串构建成trie树，然后每次遍历主串的一个字符，用该字符到字典树里去查找，找到该字符后就到下一个指针，直到遇到结束节点
      说明该子串有匹配；如果还没到结束节点，下个节点就已经是空了，说明子串没匹配上;从主串下一个字符继续到字典树里查找
        abdhellosayhiok -->第1次到trie树里查找
         bdhellosayhiok -->第2次到trie树里查找
          dhellosayhiok -->第3次到trie树里查找
          ...........
     */
      public void match(char[] mainStr) {
          int length = mainStr.length;
          int startIndex = -1; //匹配字符串开始下标
          int endIndex = -1; //匹配字符串结束下标
          for (int i = 0;i < length;i++) { //TODO: i是主串字符所在的下标，用来控制查找的起始位置
              TrieNode p = root;
              //这里之所以要嵌套一个循环，是因为一个子串查找完成后，不能从子串末尾开始，而是要重新从子串开始字符的后一位开始
              for (int j = i;j < length;j++) { //TODO: j也是主串的下标，用来控制主串从起始位置到末尾位置
                  startIndex = i;
                  int index = mainStr[j] - 'a';
                  if (p.children[index] != null) { //
                      //在trie树找到了该字符，主串需要移动下一个位置，此时去其子节点查找;如果当前主串字符在字典树没找到，主串位置同样需要移动到下一个字符
                      p = p.children[index];
                  }else {
                      //说明没有找到该字符,打断循环直接从主串的下个字符开始
                      break;
                  }
                  if (p.isEndingChar) { //为true说明此时j为模式子串的结束字符
                      endIndex = j;
                      //输出主串中包含的模式串所在下标范围
                      System.out.println("startIndex: " + startIndex + " endIndex:" + endIndex);
                      break;
                  }
              }
          }
      }


    /*
     TODO: 应用场景3--字符串前缀匹配
     根据输入的字符寻找，在一堆字符串中寻找预期前缀匹配的字符串
     先遍历输入的字符，去trie树里寻找
    */
    public List<String> prefixMatch(char[] prefix) {
        TrieNode p = root;
        List<String> list = new ArrayList<>(); //用来存储匹配到前缀的字符串
        StringBuilder sb = new StringBuilder(); //用来添加字符来一步步构建字符串
        for (int i = 0;i < prefix.length;i++) { //遍历前缀字符
            int index = prefix[i] - 'a'; //前缀字符所在下标
            if (p.children[index] == null) { //只要有一个前缀字符没有，就直接返回
                return list;
            }
            //执行到这里说明有该字符
            p = p.children[index];
            sb.append(prefix[i]);
        }
        /*
         TODO 执行到这里说明前缀字符串遍历完毕,此时p是前缀末尾字符，p的子节点下所有的形成的字符串都是我们要的,
           因此我们需要遍历p节点下的所有节点，将其组装成字符串!
        */
        //开始遍历从p节点开始的字典树，并将单词字符串存入list集合
        traversal(p,sb,list);
        return list;
    }


    /**
     * 将p节点下的所有字符进行组装
     * @param p 字典树开始节点
     * @param sb 传入的前缀字符串，为了后面拼接出匹配的字符串
     * @param list
     */
    private void traversal(TrieNode p, StringBuilder sb, List<String> list) {
        if (p.children == null) return;
        TrieNode[] children = p.children;
        for (int i = 0;i < children.length;i++) { //p节点下trie树的横向遍历
            TrieNode child = children[i];
            if (child != null) { //不为null，说明有字符,将其字符追加到StringBuilder，然后继续向下遍历，直到单词结束
                StringBuilder s = new StringBuilder().append(sb).append(child.data);
                if (child.isEndingChar) { //TODO 如果此时节点是某个单词的末尾，则将其放入集合里
                    list.add(s.toString());
                }
                traversal(child,s,list); //trie树纵向遍历
            }
        }
    }







    //这个代码没看懂！
    private void travelTree(TrieNode p, char[] prefix, List<Character> path) {
        if (p.isEndingChar) {
            StringBuilder resultString = new StringBuilder();
            resultString.append(prefix);
            resultString.append(path);
            System.out.println(resultString.toString());
        }
        path.add(p.data);
        for (int i = 0;i < p.children.length;i++) {
            if (p.children[i] != null) {
                travelTree(p.children[i],prefix,path);
            }
        }
        path.remove(path.size()-1);
    }

}
