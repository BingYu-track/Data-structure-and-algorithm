package lessons.week9.pratice.dfsbfs.pratice10;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0 单词接龙
 * @Description: 双向BFS解题(优先推荐)
 * @author: bingyu
 * @date: 2023/4/4
 */
public class DoubleDirectBFS {

    public static void main(String[] args) {
        DoubleDirectBFS t = new DoubleDirectBFS();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
//        String beginWord = "cet";
//        String endWord = "ism";
//        String[] wordList = {"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes",
//                "ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan",
//                "cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark",
//                "has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box",
//                "ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit",
//                "tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye",
//                "oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age",
//                "fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex",
//                "yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag",
//                "amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen",
//                "paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob",
//                "gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev",
//                "jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye",
//                "lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe",
//                "ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy",
//                "rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo",
//                "hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix",
//                "ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex",
//                "via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who",
//                "bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia",
//                "ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut",
//                "ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay",
//                "poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog",
//                "pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim",
//                "pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem",
//                "off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil",
//                "jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut",
//                "bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop",
//                "tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err",
//                "him","all","pad","hah","hie","aim"};
        List<String> list = new ArrayList<>();
        for (String s : wordList) {
            list.add(s);
        }
        int i = t.ladderLength(beginWord, endWord, list);
        System.out.println(i);

    }

    /*
    TODO：
     为什么双向BFS比单向的更快了?
     见图ddbfs.png，可以看到双向BFS扫描的元素要比单向扫描的元素数量少一半的面积，因此在知道起始点和终止点的基础上，使用双向BFS是最快的!
     核心思想就是这样，具体实现就是用2个哈希表来代替之前队列的功能，从小的开始进行bfs，当一边BFS到下层后，当前哈希表的元素就会多余另外一个哈希表，
     接着就是另一个哈希表进行BFS，就这样双向BFS，直到遇到共同的节点，执行结束!
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 第 1 步：先将 wordList 放到哈希表里，便于判断某个单词是否在 wordList 里
        Set<String> wordSet = new HashSet<>(wordList);
        if (wordSet.size() == 0 || !wordSet.contains(endWord)) {
            return 0;
        }

        // 第 2 步：已经访问过的 word 添加到 visited 哈希表里
        Set<String> visited = new HashSet<>();
        // 分别用左边和右边扩散的哈希表代替单向 BFS 里的队列，它们在双向 BFS 的过程中交替使用
        Set<String> beginVisited = new HashSet<>(); //起始节点开始的队列
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>(); //终止节点开始的队列
        endVisited.add(endWord);

        // 第 3 步：执行双向 BFS，左右交替扩散的步数之和为所求
        int step = 1;
        while (!beginVisited.isEmpty() && !endVisited.isEmpty()) {
            // 优先选择小的哈希表进行扩散，考虑到的情况更少
            if (beginVisited.size() > endVisited.size()) { //如果终止节点开始的元素更少就从终止节点先开始BFS，这里是将终止节点作为起始，开始节点作为终止
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }

            // 逻辑到这里，保证 beginVisited 是相对较小的集合，nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                if (changeWordEveryOneLetter(word, endVisited, visited, wordSet, nextLevelVisited)) {
                    return step + 1;
                }
            }

            // 原来的 beginVisited 废弃，从 nextLevelVisited 开始新的双向 BFS，这个代码就类似于queue.poll下一层节点后，再将下一层节点重新放入队列
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }


    /**
     * 尝试对 word 修改每一个字符，看看是不是能落在 endVisited 中，扩展得到的新的 word 添加到 nextLevelVisited 里
     *
     * @param word 起始节点集合中的单词
     * @param endVisited 从终止节点开始的哈希表
     * @param visited 用来记录已经访问过的单词的集合
     * @param wordSet 里面包含所有单词的哈希表用，相当于一个字典，用以判断生成的新的单词是否在该字典中
     * @param nextLevelVisited 用一保存下一层所有单词的集合
     * @return
     */
    private boolean changeWordEveryOneLetter(String word, Set<String> endVisited, Set<String> visited, Set<String> wordSet,
                                             Set<String> nextLevelVisited) {
        char[] charArray = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char originChar = charArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (originChar == c) { //剔除原始字符
                    continue;
                }
                charArray[i] = c;
                String nextWord = String.valueOf(charArray); //得到新字符串
                if (wordSet.contains(nextWord)) {
                    //如果新生成的单词在字典里，先判断是否在endVisited里有，如果有，说明在双向BFS找到了共同的节点，
                    //那么这个共同的节点就说明双向BFS已经完成了
                    if (endVisited.contains(nextWord)) {
                        return true;
                    }
                    //如果没找到共同的节点，并且还是未访问的，则说明是下一层的节点，放入nextLevelVisited集合中
                    if (!visited.contains(nextWord)) {
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            // 恢复，下次再用
            charArray[i] = originChar;
        }
        return false;
    }


}
