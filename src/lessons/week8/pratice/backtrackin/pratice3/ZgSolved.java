package lessons.week8.pratice.backtrackin.pratice3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version 1.0  17. 电话号码的字母组合 ----争哥解法
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
 * @date: 2023/2/2
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved solved = new ZgSolved();
        String digits = "467";
        List<String> list = solved.letterCombinations(digits);
        System.out.println(list);
    }


    private List<String> result = new ArrayList<>();

    /*
     争哥解法: 推荐
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：40.2 MB, 在所有 Java 提交中击败了48.84%的用户
    */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return Collections.emptyList();
        String[] mappings = new String[10];
        mappings[2] = "abc";
        mappings[3] = "def";
        mappings[4] = "ghi";
        mappings[5] = "jkl";
        mappings[6] = "mno";
        mappings[7] = "pqrs";
        mappings[8] = "tuv";
        mappings[9] = "wxyz";
        char[] path = new char[digits.length()];
        backtrack(mappings, digits, 0, path);
        return result;
    }



    // k表示阶段
    // path路径
    // digits[k]+mappings确定当前阶段的可选列表
    private void backtrack(String[] mappings, String digits, int k, char[] path) {
        if (k == digits.length()) {
            result.add(new String(path));
            return;
        }
        String mapping = mappings[digits.charAt(k)-'0']; //这里相当于是我的那个外层的循环，争哥是用空间换取了时间
        for (int i = 0; i < mapping.length(); ++i) {
            path[k] = mapping.charAt(i); //因为这里可以覆盖，所以后面不需要进行撤销
            backtrack(mappings, digits, k+1, path);
        }
    }

}
