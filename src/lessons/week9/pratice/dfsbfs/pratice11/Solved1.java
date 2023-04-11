package lessons.week9.pratice.dfsbfs.pratice11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2023/3/28
 */
public class Solved1 {

    public static void main(String[] args) {
        Solved1 t = new Solved1();
        String beginWord = "cet";
        String endWord = "ism";
        String[] wordList = {"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes",
                "ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan",
                "cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark",
                "has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box",
                "ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit",
                "tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye",
                "oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age",
                "fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex",
                "yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag",
                "amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen",
                "paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob",
                "gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev",
                "jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye",
                "lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe",
                "ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy",
                "rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo",
                "hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix",
                "ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex",
                "via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who",
                "bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia",
                "ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut",
                "ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay",
                "poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog",
                "pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim",
                "pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem",
                "off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil",
                "jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut",
                "bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop",
                "tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err",
                "him","all","pad","hah","hie","aim"};
//        String beginWord = "hit";
//        String endWord = "cog";
//        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        List<String> list = new ArrayList<>();
        for (String s : wordList) {
            list.add(s);
        }
        List<List<String>> result = t.findLadders(beginWord, endWord, list);
        System.out.println(result);
    }

    int min = 0;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        //如果不含有结束单词，直接结束，不然后边会造成死循环
        if (!wordList.contains(endWord)) {
            return ans;
        }
        //用来存储单词和其所在的层数
        HashMap<String,Integer> distance = new HashMap<>();
        //1.利用 BFS 得到所有的邻居节点，但是你图没建立，怎么进行BFS?这个map就相当于一个图，作者是在BFS的过程中构建的邻接表
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        bfs(beginWord, endWord, wordList,map,distance);
        ArrayList<String> path = new ArrayList<String>();
        // temp 用来保存当前的路径
        path.add(beginWord);
        //2.进行dfs
        dfs(beginWord, endWord, map, distance,path, ans);
        return ans;
    }

    /*
     我们在BFS中，就把每个节点的所有相邻节点保存到HashMap中，就省去了DFS再去找相邻节点的时间
    */
    public void bfs(String beginWord, String endWord, List<String> wordList, HashMap<String, ArrayList<String>> map,
                    HashMap<String,Integer> distance) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distance.put(beginWord, 0); //起始节点设为第0层
        boolean isFound = false;
        int depth = 0;
        Set<String> dict = new HashSet<>(wordList); //方便后面getNeighbors方法得到相邻的节点
        while (!queue.isEmpty()) {
            int size = queue.size(); //按层遍历
            depth++;
            for (int j = 0; j < size; j++) {
                String temp = queue.poll();
                // 一次性得到所有的下一个的节点
                ArrayList<String> neighbors = getNeighbors(temp, dict);
                map.put(temp, neighbors);
                for (String neighbor : neighbors) { //遍历相邻的节点
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, depth); //得到相邻节点与其所在层数
                        queue.offer(neighbor);
                        if (neighbor.equals(endWord)) {
                            isFound = true;
                        }
                        queue.offer(neighbor);
                    }
                }
            }
            if (isFound) {
                break;
            }
        }
    }

    private void dfs(String beginWord, String endWord, HashMap<String, ArrayList<String>> map,
                                   HashMap<String,Integer> distance, ArrayList<String> path, List<List<String>> ans) {
        if (beginWord.equals(endWord)) {
            ans.add(new ArrayList<String>(path));
            return;
        }
        // 得到所有的下一个的节点
        /*
          "a"
          "c"
          ["a","b","c"]*/
        //之所以是 map.getOrDefault 而不是 get，就是上边的情况 get 会出错
        ArrayList<String> neighbors = map.getOrDefault(beginWord, new ArrayList<String>());
        for (String neighbor : neighbors) {
            //判断层数是否符合
            if (distance.get(beginWord) + 1 == distance.get(neighbor)) { //这里是比较beginWord的下一层是否是neighbor最短路径所在层
                path.add(neighbor);
                dfs(neighbor, endWord, map, distance, path, ans);
                path.remove(path.size() - 1);
            }
        }
    }


    //这个方法是干什么的?寻找出单词node关联的所有单词，也就是找其只有一个字符不一样的节点
    private ArrayList<String> getNeighbors(String node, Set<String> dict) {
        ArrayList<String> res = new ArrayList<String>();
        char chs[] = node.toCharArray(); //节点值，也就是单词转为字符数组
        for (char ch = 'a'; ch <= 'z'; ch++) { //遍历a~z
            for (int i = 0; i < chs.length; i++) { //遍历chs
                if (chs[i] == ch)
                    continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) { //不断遍历，生成新的单词，看在字典是否存在，存在说明该新的单词是node的一个邻居节点，放入集合
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }




}
