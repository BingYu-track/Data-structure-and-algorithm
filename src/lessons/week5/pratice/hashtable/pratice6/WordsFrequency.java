package lessons.week5.pratice.hashtable.pratice6;

import java.util.HashMap;

/**
 * @version 1.0 单词频率
 * @Description: 设计一个方法，找出任意指定单词在一本书中的出现频率。
 *
 * 你的实现应该支持如下操作：
 * WordsFrequency(book)构造函数，参数为字符串数组构成的一本书
 * get(word)查询指定单词在书中出现的频率
 *
 * 示例：
 * WordsFrequency wordsFrequency = new WordsFrequency({"i", "have", "an", "apple", "he", "have", "a", "pen"});
 * wordsFrequency.get("you"); //返回0，"you"没有出现过
 * wordsFrequency.get("have"); //返回2，"have"出现2次
 * wordsFrequency.get("an"); //返回1
 * wordsFrequency.get("apple"); //返回1
 * wordsFrequency.get("pen"); //返回1
 *
 * 提示：
 * book[i]中只包含小写字母
 * 1 <= book.length <= 100000
 * 1 <= book[i].length <= 10
 * get函数的调用次数不会超过100000
 *
 * @author: bingyu
 * @date: 2022/8/11
 */
public class WordsFrequency {

    public static void main(String[] args) {
        String[] strs = {"i", "have", "an", "apple", "he", "have", "a", "pen"};
        WordsFrequency wordsFrequency = new WordsFrequency(strs);
        int you = wordsFrequency.get("you");//返回0，"you"没有出现过
        int have = wordsFrequency.get("have");//返回2，"have"出现2次
        int an = wordsFrequency.get("an");//返回1
        int apple = wordsFrequency.get("apple");//返回1
        int pen = wordsFrequency.get("pen");//返回1
        System.out.println("you: " + you);
        System.out.println("have: " + have);
        System.out.println("an: " + an);
        System.out.println("apple: " + apple);
        System.out.println("pen: " + pen);
    }

    private String[] book;
    private HashMap<String,Integer> hashTable = new HashMap<>();

    public WordsFrequency(String[] book) {
        this.book = book;
        for (String s : book) {
            int k = 1;
            Integer integer = hashTable.get(s);
            if (hashTable.containsKey(s)) {
                k = hashTable.get(s);
                hashTable.put(s,++k);
            }else {
                hashTable.put(s,k);
            }
        }
    }

    public int get(String word) {
        Integer times = hashTable.get(word);
        if (times == null) return 0;
        return times;
    }

}
