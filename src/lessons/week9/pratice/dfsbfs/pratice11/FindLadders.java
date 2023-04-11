package lessons.week9.pratice.dfsbfs.pratice11;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @version 1.0  126. 单词接龙 II
 * @Description: 按字典wordList完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的转换序列 是形式上像
 * beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
 *
 * 1.每对相邻的单词之间仅有单个字母不同。
 * 2.转换过程中的每个单词 si（1 <= i <= k）必须是字典wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
 * 3.sk == endWord
 *
 * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列 ，
 * 如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
 *
 * 示例 1：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * 解释：存在 2 种最短的转换序列：
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 *
 * 示例 2：
 * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * 输出：[]
 * 解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
 *
 * 提示：
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord、endWord 和 wordList[i] 由小写英文字母组成
 * beginWord != endWord
 * wordList 中的所有单词 互不相同
 *
 * @author: bingyu
 * @date: 2023/3/23
 */
public class FindLadders {

    public static void main(String[] args) {
        FindLadders f = new FindLadders();
//        String beginWord = "hit";
//        String endWord = "cog";
//        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        String beginWord = "aaaaa";
        String endWord = "uuuuu";
        String[] wordList = {"aaaaa","waaaa","wbaaa","xaaaa","xbaaa","bbaaa","bbwaa","bbwba","bbxaa","bbxba","bbbba","wbbba",
                "wbbbb","xbbba","xbbbb","cbbbb","cwbbb","cwcbb","cxbbb","cxcbb","cccbb","cccwb","cccwc","cccxb","cccxc","ccccc",
                "wcccc","wdccc","xcccc","xdccc","ddccc","ddwcc","ddwdc","ddxcc","ddxdc","ddddc","wdddc","wdddd","xdddc","xdddd",
                "edddd","ewddd","ewedd","exddd","exedd","eeedd","eeewd","eeewe","eeexd","eeexe","eeeee","weeee","wfeee","xeeee",
                "xfeee","ffeee","ffwee","ffwfe","ffxee","ffxfe","ffffe","wfffe","wffff","xfffe","xffff","gffff","gwfff","gwgff",
                "gxfff","gxgff","gggff","gggwf","gggwg","gggxf","gggxg","ggggg","wgggg","whggg","xgggg","xhggg","hhggg","hhwgg",
                "hhwhg","hhxgg","hhxhg","hhhhg","whhhg","whhhh","xhhhg","xhhhh","ihhhh","iwhhh","iwihh","ixhhh","ixihh","iiihh",
                "iiiwh","iiiwi","iiixh","iiixi","iiiii","wiiii","wjiii","xiiii","xjiii","jjiii","jjwii","jjwji","jjxii","jjxji",
                "jjjji","wjjji","wjjjj","xjjji","xjjjj","kjjjj","kwjjj","kwkjj","kxjjj","kxkjj","kkkjj","kkkwj","kkkwk","kkkxj",
                "kkkxk","kkkkk","wkkkk","wlkkk","xkkkk","xlkkk","llkkk","llwkk","llwlk","llxkk","llxlk","llllk","wlllk","wllll",
                "xlllk","xllll","mllll","mwlll","mwmll","mxlll","mxmll","mmmll","mmmwl","mmmwm","mmmxl","mmmxm","mmmmm","wmmmm",
                "wnmmm","xmmmm","xnmmm","nnmmm","nnwmm","nnwnm","nnxmm","nnxnm","nnnnm","wnnnm","wnnnn","xnnnm","xnnnn","onnnn",
                "ownnn","owonn","oxnnn","oxonn","ooonn","ooown","ooowo","oooxn","oooxo","ooooo","woooo","wpooo","xoooo","xpooo",
                "ppooo","ppwoo","ppwpo","ppxoo","ppxpo","ppppo","wpppo","wpppp","xpppo","xpppp","qpppp","qwppp","qwqpp","qxppp",
                "qxqpp","qqqpp","qqqwp","qqqwq","qqqxp","qqqxq","qqqqq","wqqqq","wrqqq","xqqqq","xrqqq","rrqqq","rrwqq","rrwrq",
                "rrxqq","rrxrq","rrrrq","wrrrq","wrrrr","xrrrq","xrrrr","srrrr","swrrr","swsrr","sxrrr","sxsrr","sssrr","ssswr",
                "sssws","sssxr","sssxs","sssss","wssss","wtsss","xssss","xtsss","ttsss","ttwss","ttwts","ttxss","ttxts","tttts",
                "wttts","wtttt","xttts","xtttt","utttt","uwttt","uwutt","uxttt","uxutt","uuutt","uuuwt","uuuwu","uuuxt","uuuxu",
                "uuuuu","zzzzz","zzzzy","zzzyy","zzyyy","zzyyx","zzyxx","zzxxx","zzxxw","zzxww","zzwww","zzwwv","zzwvv","zzvvv",
                "zzvvu","zzvuu","zzuuu","zzuut","zzutt","zzttt","zztts","zztss","zzsss","zzssr","zzsrr","zzrrr","zzrrq","zzrqq",
                "zzqqq","zzqqp","zzqpp","zzppp","zzppo","zzpoo","zzooo","zzoon","zzonn","zznnn","zznnm","zznmm","zzmmm","zzmml",
                "zzmll","zzlll","zzllk","zzlkk","zzkkk","zzkkj","zzkjj","zzjjj","zzjji","zzjii","zziii","zziih","zzihh","zzhhh",
                "zzhhg","zzhgg","zzggg","zzggf","zzgff","zzfff","zzffe","zzfee","zzeee","zzeed","zzedd","zzddd","zzddc","zzdcc",
                "zzccc","zzccz","azccz","aaccz","aaacz","aaaaz","uuuzu","uuzzu","uzzzu","zzzzu"};
        List<String> list = new ArrayList<>();
        for (String s : wordList) {
            list.add(s);
        }
        long l1 = System.currentTimeMillis();
        List<List<String>> result = f.findLadders3(beginWord, endWord, list);
        long l2 = System.currentTimeMillis();
        long l = l2 - l1;
        System.out.println(result);
        System.out.println("消耗时间: " + l + "毫秒");
    }

    private List<List<String>> result = new ArrayList<>(); //结果列表

    private Map<String,Integer> map = new HashMap<>(); //记录每个单词和所在位置的映射word--id映射
    private Map<Integer,String> m = new HashMap<>(); //记录每个单词所在位置和单词的映射id--word
    private List<List<Integer>> adj = new ArrayList<>(); //图的邻接表

    /* die 21-559   dis 54-559  di*
     现在题目要求得到最短路径和其节点，按照上一题的思路来解决，构建图后进行BFS遍历，
     TODO： 我开始的想法是，先进行BFS找出最短路径的个数，再根据最短路径个数DFS出所有的最短路径，但是这样超时了!因为数据规模变大后，深度增加，分支变多
      再按照之前一样进行dfs，执行时间就会非常长，因此我们需要对dfs进行优化.
      第1次优化: 优化的核心思想就是在BFS时维护节点和该节点在其最短路径的层数，然后再DFS时，我们只处理在这最短路径层数的节点即可!如果我们遇到的该节点，
      它的层数大于最短路径所在层数，我们就直接跳过，这样我们就大大减少了dfs时遍历的深度了!但是发现当前测试用例最短路径本身就很长时，dfs还是超时，还需
      要进行一次优化。
      第2次优化: 就是不再使用虚节点来连接，而是用26个字母与当前单词的每个字符替换比较形成新的单词，这样构建每个节点的时间复杂度就是O(26*wordLength)
      如果按照之前一个个比较时间负责是O(N*wordLength)；然后BFS时，我们不用维护每个节点和节点所在的最短路径，因为bfs中第一次遇到的节点才是属于最短路径
      的节点，所以每次遇到第一次访问的节点，就将其从字典里删除，保证只会访问一次，这样的话建的邻接表，在 DFS中就不需要用层数进行判断了，直接取邻居节点就可以了。
      但是这样做会导致一个问题，就是注意dict.remove后，后面的单词就无法与前面的单词建立联系了，而正好后面的单词刚好和前面的单词是能建立联系的，这样就会导致
      构建的邻接表不完整，因此发现还是要维护个每个节点和节点所在的最短路径，然后在字典dict.remove前判断要删除的节点是否在最短路径，在就放入邻接表中
      上述优化代码见findLadders2方法。
      第3次优化: 进行2次优化后发现还是超时，但是由于我们知道起始和终止节点，这样我们可以进行双向BFS，这样扫描的元素个数会比单向BFS少一倍，见findLadders3方法
    */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) { //不包含目标单词，直接返回
            return result;
        }
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        int wordId = 0;
        for (int i = 0;i<wordList.size();i++) {
            map.put(wordList.get(i),i);
            m.put(wordId,wordList.get(i));
            adj.add(new ArrayList<Integer>());
            wordId++;
        }
        for (int i = 0;i<wordList.size();i++) {
            String word = wordList.get(i);
            char[] arr = word.toCharArray();
            for (int j = 0;j < arr.length;j++) {
                char temp = arr[j];
                arr[j] = '*';
                String newWord = new String(arr); //虚拟节点
                arr[j] = temp;
                Integer id = map.get(word); //当前单词的id
                if (!map.containsKey(newWord)) {
                    map.put(newWord,wordId);
                    m.put(wordId,newWord);
                    adj.add(new ArrayList<Integer>());
                    adj.get(id).add(wordId); //将word和newWord添加联系
                    adj.get(wordId).add(id);
                    wordId++;
                }else {
                    //说明map里有newWord，只需要与其建立联系即可
                    Integer newWordId = map.get(newWord);
                    adj.get(newWordId).add(id);
                    adj.get(id).add(newWordId);
                }
            }
        }
        HashMap<Integer, Integer> wordLevel = new HashMap<>(); //TODO: 用以维护单词----最短路径所在层数
        //执行到这里说明图已构建完毕，可以开始进行BFS遍历(前面建图的时间复杂度为O(len*N))
        Integer s = map.get(beginWord); //起始顶点
        Integer t = map.get(endWord); //结束顶点
        bfs(s,t,wordLevel);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
        dfs(0,s,t,wordLevel,path);
        return result;
    }

    //bfs获取最短路径个数该时间复杂度是N*logN
    private void bfs(int s, int t,HashMap<Integer, Integer> wordLevel) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int depth = 0; //层数
        wordLevel.put(s,depth);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0;i<size;i++) {
                Integer q = queue.poll(); //TODO: 注意这里
                for (int j = 0;j<adj.get(q).size();j++) {
                    int w = adj.get(q).get(j);
                    if (!wordLevel.containsKey(w)) {
                        wordLevel.put(w,depth);
                        queue.add(w);
                        if (w == t) return;
                    }
                }
            }
        }
    }

    private void dfs(int k, int s, int t, HashMap<Integer, Integer> wordLevel, List<String> path) {
        if (s == t) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0;i<adj.get(s).size();i++) {
            int w = adj.get(s).get(i);
            Integer shortLevel = wordLevel.get(w); //获取节点w的最短路径所在层
            if (shortLevel != null && k+1 == shortLevel.intValue()) {
                String word = m.get(w);
                if (!word.contains("*")) {
                    path.add(word);
                }
                dfs(k+1,w,t,wordLevel,path);
                if (!word.contains("*")) {
                    path.remove(path.size()-1);
                }
            }
        }

    }

    /* 第2次优化后
    核心思想是如何进行剪枝

                                           hit
                                            |
                                           hot
                                         /     \
                                       dot     lot
                                   /       \     |
                                 dog       lot  log
                                /   \       |    |
                               log  cog    log  cog ----如果dict.remove了，这个cog因为出现在log的前面，导致后面log无法和cog产生联系，但我们
                                |           |           只要比较，如果当前遍历的层数和cog的最短路径层数一致，就让其产生联系即可!
                              cog          cog
    */
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return result;
        Set<String> dict = new HashSet<>(wordList); //创建一个字典
        Map<String,List<String>> adj = new HashMap<>(); //图的邻接表，单词直接与有联系的进行映射
        Map<String,Integer> levels = new HashMap<>(); //记录每个单词和所在最短路径的层数
        //对word的每个字符从a~z进行替换，看是否能在子典里查找到，如果找到了，说明替换的单词与原单词存在联系，是原word的下一层
        //在bfs搜索时顺便构建图
        bfs(beginWord,endWord,adj,levels,dict);
        List<String> path = new ArrayList<>();
        path.add(beginWord);
//        for (Map.Entry<String, List<String>> entry : adj.entrySet()) {
//            String key = entry.getKey();
//            List<String> list = entry.getValue();
//            for (String nextWord : list) {
//                sb.append(key + "->" + nextWord + "\n");
//            }
//        }
//        System.out.println(sb.toString());
// TODO: 通过打印出拓扑字符串，放到拓扑可视化工具发现该测试案例很极端，只有一个路径是正确的，
//         其它分支都是错的，导致正向寻找会导致超时，还是要进行双向遍历
        dfs(beginWord,endWord,adj,path);
        return result;
    }
    //[[aaaaa, aaaaz, aaawz, aavwz, avvwz, vvvwz, vvvww, wvvww, wwvww, wwwww, ywwww, yywww, yyyww, yyyyw, yyyyy, xyyyy, xxyyy, xxxyy, xxxxy, xxxxx, gxxxx, ggxxx, gggxx, ggggx, ggggg]]

    private StringBuilder sb = new StringBuilder();

    /*
     bfs遍历找到每个节点对应的最短路径层数，并且构建出图
    */
    private void bfs(String beginWord, String endWord, Map<String,List<String>> adj, Map<String, Integer> levels, Set<String> dict) {
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        boolean found = false;
        int depth = 0;
        levels.put(beginWord,depth);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int k = 0;k<size;k++) { //开始按层遍历
                String word = queue.poll();
                char[] chars = word.toCharArray();
                for (int i = 0;i<chars.length;i++) {
                    char tmp = chars[i];
                    for (char j = 'a';j <= 'z';j++) { //发现aaaaa和caaaa产生了环
                        chars[i] = j;
                        String nextWord = String.valueOf(chars); //扩展出的单词
                        //TODO: nextWord非第一次出现，但是在最短路径里，下面这个代码就是用来解决"dict.remove后，后面单词就无法与前面的单词建立联系的问题!"
                        if (levels.get(nextWord)!=null && depth == levels.get(nextWord)) {
                            adj.putIfAbsent(word,new ArrayList<String>());
                            adj.get(word).add(nextWord);
                        }
                        //TODO: 1.扩展出的word在字典里才是我们想要的
                        // 2.扩展出的nextword可能和之前的word重复
                        // 3.在记录word对应的最短路径层数时，只有第一次遇到的才是最短路径，后面再遇到相同的word直接跳过
                        if (dict.contains(nextWord) && !word.equals(nextWord) && !nextWord.equals(beginWord)) {
                            //注意dict.remove后，单词就无法与前面的单词建立联系了
                            adj.putIfAbsent(word,new ArrayList<String>());
                            adj.get(word).add(nextWord);
                            dict.remove(nextWord); //保证只去第一次遇到的单词放入adj中，因为bfs第一次遇到的节点才是属于最短路径的节点
                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                            levels.put(nextWord,depth);
                            queue.add(nextWord);
                        }
                    }
                    chars[i] = tmp;
                }
            }
            if (found) return;
        }
    }

    //TODO: 我正向建立图，并对图进行了剪枝，但还是超时，为何反向建立的就会快很多?
    private void dfs(String beginWord, String endWord, Map<String, List<String>> adj, List<String> path) {
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
        }
        List<String> list = adj.get(beginWord);
        if (list!=null) {
            for (int i = 0;i<adj.get(beginWord).size();i++) { //由于非最短路径的节点在bfs期间已经删除了，因此dfs时直接遍历即可
                String nextWord = adj.get(beginWord).get(i);
                path.add(nextWord); //
                dfs(nextWord,endWord,adj,path);
                path.remove(path.size()-1);
            }
        }
    }

    private int down = 0; //用来统计向下扩展的频率
    private int up = 0; //用来统计向上扩展的频率

    /*
     第3次优化: 使用2个哈希表来模拟队列，进行双向BFS，虽然快了很多，但是执行耗时3000多毫秒，仍然超时了!
     这次的测试用例最短路径的层数是80层，比之前的测试用例更深，目前超过我的能力了，先放下，建议使用官方题解
    */
    public List<List<String>> findLadders3(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>(); //结果列表
        HashMap<String,List<String>> adj = new HashMap<>(); //邻接表
        if (!dict.contains(endWord)) return result; //如果字典不包含endWord，说明没有从beginWord到endWord的路径，直接返回
        dict.remove(beginWord); //字典中可能存在beginWord，先去除，这样就不会把自己也放入邻接表，导致自己指向自己导致死循环
        Set<String> beginSet = new HashSet<>(); //起始
        Set<String> endSet = new HashSet<>(); //结束
        beginSet.add(beginWord);
        endSet.add(endWord);
        bfs(beginSet,endSet,dict,adj);
        LinkedList<String> path = new LinkedList<>();//路径
        path.add(beginWord);
        dfs(endWord,beginWord,adj,path,result);
        return result;
    }


    //双向BFS
    private void bfs(Set<String> beginSet, Set<String> endSet, Set<String> dict, HashMap<String, List<String>> adj) {
        boolean flag = false; //false表示正向遍历  true表示反向
        boolean found = false; //
        while (!beginSet.isEmpty() && !endSet.isEmpty() && !found) {
            //TODO: 1.交替进行BFS处理，先从endSet开始，再处理beginSet，依次交替执行
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
                flag = !flag; //endSet和beginSet交替的标志
            }
            if (flag) {
                up++;
            }else {
                down++;
            }
            dict.removeAll(beginSet); //TODO： 2.将已扩展的节点集中进行remove，beginSet里的元素就是已扩展的
            dict.removeAll(endSet);  //提前将endSet里的也进行删除
            Set<String> nextLevelSet = new HashSet<>(); //用以存储下一层所有的节点
            //从beginSet开始层序遍历
            for (String word : endSet) {
                char[] arr = word.toCharArray();
                for (int i = 0;i<arr.length;i++) {
                    char originChar = arr[i];
                    for (char j = 'a';j<='z';j++) {
                        if (originChar == j) { //剔除原始字符，这样就不会出现重复的
                            continue;
                        }
                        arr[i] = j;
                        String nextWord = new String(arr); //得到新的字符串
                        //TODO: 3.flag为true说明是反向遍历，要将nextWord作为key，word作为下一层节点才行,这么做是因为我们需要要判断是正向遍历
                        // 还是反向遍历，如果是反向遍历的话，我们需要将建立联系的方向要和正向遍历建立联系的方向一致才行!
                        String key = flag ? nextWord : word;
                        String value = flag ? word : nextWord;
                        //endSet也包含nextWord，说明找到了共同的节点，当前层所有元素处理完后，后面结束BFS
                        if (endSet.contains(nextWord)) {
                            //TODO: 这里是重点，找到了共同的节点，需要将其和另一个节点元素建立联系
                            found = true;
                            adj.putIfAbsent(key,new ArrayList<>());
                            adj.get(key).add(value);
                        }else if (!found && dict.contains(nextWord)) { //如果在字典中，此时是第一次遇到，将其放入邻接表
                            adj.putIfAbsent(key,new ArrayList<>()); //如果映射为空，则新建list，如果不为空，则无需新建
                            adj.get(key).add(value); //将nextWord和word建立联系
                            //TODO： dict.remove(nextWord)从字典中删除，在这里删除会导致后面的单词不能与该单词建立联系，因此导致建图不完整，
                            // 因此我们将即将要访问的元素放入前面提前进行removeAll掉
                            nextLevelSet.add(nextWord); //记录下一层的单词节点
                        }
                    }
                    arr[i] = originChar; //恢复原字符
                }
            }
            //执行到这里说明beginSet里的所有元素都处理完毕，将下一层所有元素作为beginSet，类似普通bfs中的queue.poll完后，queue.add这一步
            beginSet = nextLevelSet;
            if (found) {
                break;
            }
        }
    }

    private void dfs(String beginWord, String endWord, HashMap<String,List<String>> adj, LinkedList<String> path,
                     List<List<String>> result) {
        if (beginWord.equals(endWord)) {
            result.add(new ArrayList<>(path));
            return;
        }
        List<String> list = adj.get(beginWord);
        if (list!=null) { //这里必须校验
            for (String word : list) {
                path.add(word);
                dfs(word,endWord,adj,path,result);
                path.remove(path.size()-1);
            }
        }
    }


}
