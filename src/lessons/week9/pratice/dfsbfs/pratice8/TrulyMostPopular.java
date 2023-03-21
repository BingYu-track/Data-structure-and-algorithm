package lessons.week9.pratice.dfsbfs.pratice8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0  面试题 17.07. 婴儿名字
 * @Description: 每年，政府都会公布一万个最常见的婴儿名字和它们出现的频率，也就是同名婴儿的数量。有些名字有多种拼法，例如，John和Jon
 * 本质上是相同的名字，但被当成了两个名字公布出来。给定两个列表，一个是名字及对应的频率，另一个是本质相同的名字对。设计一个算法打印出每个真实
 * 名字的实际频率。注意，如果John和Jon是相同的，并且 Jon 和 Johnny 相同，则 John 与 Johnny 也相同，即它们有传递和对称性。
 * 在结果列表中，选择 字典序最小 的名字作为真实名字。
 *
 * 示例：
 * 输入：names = ["John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"],
 *    synonyms = ["(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"]
 * 输出：["John(27)","Chris(36)"]
 *
 * 提示：
 * names.length <= 100000
 *
 * @author: bingyu
 * @date: 2023/3/20
 */
public class TrulyMostPopular {

    public static void main(String[] args) {
        TrulyMostPopular p = new TrulyMostPopular();
        //例1
        String[] name = {"Kris(4)","John(15)","Jon(12)","Chris(13)","Christopher(19)","Johnny(10)","KK(5)","JoJo(10)"};
        String[] synonyms = {"(Jon,John)","(John,Johnny)","(Johnny,Jon)","(Chris,Kris)","(Chris,Christopher)","(JoJo,Johnny)"};
//        String[] name = {"Kgabb(80)","Tuvzkd(85)","Kri(71)"};
//        String[] synonyms = {"(Kri,Tuvzkd)","(Kgabb,Tuvzkd)"};
//        String[] name = {"a(10)","c(13)"};
//        String[] synonyms = {"(a,b)","(c,d)","(b,c)"};
        String[] result = p.trulyMostPopular(name, synonyms); //
        System.out.println(Arrays.toString(result));
        /*
             a--->b
                  |
                  V
                  c--->d
         */
    }

    /*                    例1:
                                    JoJo(10)
                                        |
                       (12)    (15)     V
                       Jon--->John--->Johnny(10)
                        ↑               |
                        |_______________|           从该图得知，我们一定是要从入度为0的点开始出发

                       Christopher<---Chris--->Kris 这种后面路径出现的John和Johnny的次数，全部算作Jon
                       正确结果: ["Chris(36)","JoJo(47)","KK(5)"]

                        例2:
                        Kgabb(80)--->Tuvzkd(85)<---Kri(71)
                       正确结果: ["Kgabb(236)"]
     */



    /*我独自的解法
    TODO: 当对题目不清晰时，一定要多进行测试案例，根据上面的例子，发现只要有2个姓名之间有联系，无论是谁指向谁都能互相转换
     解题思路: 因此我们只需要找到当前节点的所有有联系的节点，然后将其数值进行求和即可，相当于是一个无向图，
     核心就是"把有联系的姓名都放入一个路径中"，后面才好处理!
    执行用时：69 ms, 在所有 Java 提交中击败了60.00%的用户
    内存消耗：50 MB, 在所有 Java 提交中击败了15.88%的用户
    */
    private List<String> result = new ArrayList<>();
    private HashMap<String,NamePro> map = new HashMap<>();


    public String[] trulyMostPopular(String[] names, String[] synonyms) {
        for (int i = 0;i < names.length;i++) {
            putNameTime(names[i]);
        }
        for (int i = 0;i < synonyms.length;i++) {
            String synonym = synonyms[i];
            String[] s = splitName(synonym); //分隔出2个姓名
            String name1 = s[0];
            String name2 = s[1];
            //map里如果没有对应的姓名，就创建并放入
            if (!map.containsKey(name1)) {
                map.put(name1,new NamePro(name1,0));
            }
            if (!map.containsKey(name2)) {
                map.put(name2,new NamePro(name2,0));
            }
            NamePro namePro1 = map.get(name1);
            NamePro namePro2 = map.get(name2);
            //添加联系
            namePro1.adj.add(namePro2);
            namePro2.adj.add(namePro1);
        }
        //遍历map
        for (Map.Entry<String, NamePro> entry : map.entrySet()) {
            LinkedList<String> path = new LinkedList<>();
            NamePro value = entry.getValue();
            if (value.visited != 0) continue; //已访问过就跳过
            dfs(value,path);
            Collections.sort(path); //因为题目要求选择字典序最小的名字作为真实名字，所以需要排序
            int count = 0;
            for (String s : path) {
                count += map.get(s).count; //将路径上的所有姓名值求和
            }
            String s = path.get(0);
            result.add(s + "(" + count + ")");
        }
        String[] arr = result.toArray(new String[result.size()]);
        return arr;
    }

    private void dfs(NamePro value, LinkedList<String> path) {
        value.visited = 1;
        LinkedList<NamePro> adj = value.adj;
        for (int i = 0;i < adj.size();i++) { //遍历当前姓名
            NamePro w = adj.get(i);
            if (w.visited == 0) { //遇到1的话，说明遇到重复元素，无需进行处理吗，直接这样跳过即可
                dfs(w,path);
            }
        }
        value.visited = 2; //
        path.addFirst(value.name);
    }

    //将(Jon,John)名字分开
    private String[] splitName(String synonym) {
        String[] str = new String[2];
        int index = synonym.indexOf(",");
        String name1 = synonym.substring(1, index);
        String name2 = synonym.substring(index + 1, synonym.length() - 1);
        str[0] = name1;
        str[1] = name2;
        return str;
    }

    //将字符串分成姓名和出现次数,并以姓名--数组下标为键值对的形式放入map
    private void putNameTime(String str) {
        int s = str.indexOf("(");
        String nameStr = str.substring(0, s);
        int count = Integer.valueOf(str.substring(s + 1, str.length()-1));
        NamePro namePro = new NamePro(nameStr, count);
        map.put(nameStr,namePro);
    }

    class NamePro {
         LinkedList<NamePro> adj = new LinkedList<>();
         String name; //姓名
         int count; //姓名出现的次数
         int visited; //用以记录当前姓名是否有被访问过 0表示未访问  1表示在路径中  2表示已回溯

        public NamePro(String name,int count) {
            this.name = name;
            this.count = count;
        }
    }
}
