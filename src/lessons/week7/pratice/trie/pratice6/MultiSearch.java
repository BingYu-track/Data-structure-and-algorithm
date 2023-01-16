package lessons.week7.pratice.trie.pratice6;

import lessons.week7.pratice.trie.pratice5.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0 面试题 17.17. 多次搜索
 * @Description: 给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，
 * 对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
 * 示例：
 * 输入：
 * big = "mississippi"
 * smalls = ["is","ppi","hi","sis","i","ssippi"]
 * 输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
 * 提示：
 * 0 <= len(big) <= 1000
 * 0 <= len(smalls[i]) <= 1000
 * smalls的总字符数不会超过 100000。
 * 你可以认为smalls中没有重复字符串。
 * 所有出现的字符均为英文小写字母。
 *
 * @author: bingyu
 * @date: 2022/12/12
 */
public class MultiSearch {

    public static void main(String[] args) {
        String big = "mississippi";
        String[] smalls = {"is","ppi","hi","sis","i","ssippi"};
        MultiSearch ms = new MultiSearch();
        int[][] result = ms.multiSearch(big, smalls);
        System.out.println(Arrays.toString(result));
    }


    /*
     解题思路: 使用字典树，多模式匹配多子串，并记录其子串在长字符串的的起始位置,问题是这里如何构建trie树?
     */
    public int[][] multiSearch(String big, String[] smalls) {
        for (String str : smalls) {
            insert(str);
        }
        int[][] result = match(big, smalls);
        return result;
    }

    //插入要搜索的单词
    public void insert(String str) {
        char[] chars = str.toCharArray();
        TrieNode p = root;
        for (int i=0;i<chars.length;i++) {
            int index = chars[i] - 'a';
            if (p.children[index] == null) {
                p.children[index] = new TrieNode(chars[i]);
            }
            //执行到这里说明存在字符
            p = p.children[index];
        }
        //执行到这里说明一个单词全部插入完成，将该字符串放入当前节点变量word
        p.word = str;
    }

    /*
    TODO 多模式字符串匹配,这里处理还需要用到hash映射，重点是需要在字典树每个单词末尾存入当前字符串
    执行用时：30 ms, 在所有 Java 提交中击败了98.64%的用户
    内存消耗：62 MB, 在所有 Java 提交中击败了25.01%的用户
     */
    public int[][] match(String mainStr,String[] smalls) {
        char[] chars = mainStr.toCharArray();
        Map<String, List<Integer>> map = new HashMap<>(); //用于记录每个单词在主串出现的次数和其下标
        for (String small : smalls) {
            map.put(small,new ArrayList<Integer>());
        }
        int[][] result = new int[smalls.length][];
        //遍历主串字符
        for (int i = 0;i<chars.length;i++) {
            TrieNode p = root;
            for (int j = i;j<chars.length;j++) {
                int index = chars[j] - 'a';
                if (p.children[index] == null) { //没有字符，直接打断内层循环，重新从前面i下标后面位置开始查询
                    break;
                }
                //执行到这说明字典树查到了，继续差下一个
                p = p.children[index];
                if (p.word!=null) {
                    //1.执行进入到这里说明，匹配到了i到j的字符串word;如果没有进入说明只是前缀匹配
                    //2.这里难点是如何判断会有2个相同的单词，放在结果数组的同一位置?
                    List<Integer> list = map.get(p.word);
                    list.add(i); //将单词开始下标位置记录
                }
            }

        }
        //然后按照smalls的数组顺序构造二维数据
        for (int k = 0;k<smalls.length;k++) {
            List<Integer> list = map.get(smalls[k]);
            result[k] = new int[list.size()];
            for (int r=0;r<list.size();r++) {
                result[k][r] = list.get(r);
            }
        }
        return result;
    }



    private TrieNode root = new TrieNode('/'); //字典树根节点

    public class TrieNode {

        public char data;
        public TrieNode[] children;
        public String word; //TODO； 到了末尾用来记录单词字符串这是核心，这样处理会节省很多空间和时间
        public int count = 0; //记录下相同单词末尾查询到的次数

        public TrieNode(char data) {
            this.data = data;
            children = new TrieNode[26];
        }

        public TrieNode() {
            children = new TrieNode[26];
        }



    }



}
