package lessons.week8.example.backtrackin;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  17. 电话号码的字母组合
 * @Description: 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *   2=“abc” 3="def"
 *   4="ghi"  5="jkl" 6="mno"
 *   7="pqrs" 8="tuv" 9="wxyz"
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 *
 * @author: bingyu
 * @date: 2023/1/16
 */
public class LetterCombinations {

    public static void main(String[] args) {
        String digits = "";
        LetterCombinations obj = new LetterCombinations();
        List<String> result = obj.letterCombinations(digits);
        System.out.println(result);
    }

    private List<String> result = new ArrayList<String>();

    /*
      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：39.9 MB, 在所有 Java 提交中击败了84.55%的用户
     */
    public List<String> letterCombinations(String digits) {
        char[] chars = digits.toCharArray();
        List<String> list = new ArrayList<>();
        //将数字转换成对应的字符串并存入list中
        for (char c : chars) {
            String s = transform(c);
            list.add(s);
        }
        int sumk = list.size();
        if (sumk == 0) return result; //没有字符串，直接返回空集合
        char[] path = new char[sumk]; //初始化路径
        //得到数字代表的字母字符串，存放在path数组中
        backtrack(list,sumk,0,path);
        return result;
    }

    /**
     *
     * @param list
     * @param sumk 总阶段-即终止条件
     * @param k 阶段--从0开始
     * @param path
     */
    private void backtrack(List<String> list,int sumk,int k, char[] path) {
        if (k == sumk) {
            String s = String.valueOf(path);
            result.add(s);
            return;
        }
        for (int i = k;i<list.size();i++) {
            if (i!=k) break; //这里是重点，只对第1个字符串进行下面的组合处理即可
            String s = list.get(i);
            char[] chars = s.toCharArray();
            for (int j=0;j<chars.length;j++) {
                path[k] = chars[j];
                backtrack(list,sumk,k+1,path);
                path[k] = '\u0000'; //撤销之前的路径
            }
        }

    }

    public String transform(char c) {
        String str = "";
        if (c=='2') {
            str = "abc";
        }else if (c == '3') {
            str = "def";
        }else if (c == '4') {
            str = "ghi";
        }else if (c == '5') {
            str = "jkl";
        }else if (c == '6') {
            str = "mno";
        }else if (c == '7') {
            str = "pqrs";
        }else if (c == '8') {
            str = "tuv";
        }else if (c == '9') {
            str = "wxyz";
        }
        return str;
    }



    /*
       可选列表: 数字所代表的字母的个数，可能不同的数字不一样
       阶段: k--digits的长度，也是树的深度
       路径: 存储
       决策树: digits="23" -> "abc,def"

                    {}
                   / | \   --->在数字2中选一个字母
                  a  b  c
                 /|\ ....  ---->在数字3中选一个字母
               ad ae af
    */
}
