package lessons.week8.pratice.backtrackin.pratice12;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  分割回文串
 * @Description: 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 * 回文串 是正着读和反着读都一样的字符串。
 *
 * 示例 1：
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 *
 * 示例 2：
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * 提示：
 * 1 <= s.length <= 16
 * s 仅由小写英文字母组成
 * @author: bingyu
 * @date: 2023/2/13
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "aab";
        Partition p = new Partition();
        List<List<String>> lists = p.partition(s);
        System.out.println(lists);
    }

    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0, new ArrayList<>());
        return result;
    }

    private void backtrack(String s, int k, List<String> path) {
        if (k == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int end = k; end < s.length(); ++end) { //这里end就相当于startIndex，控制横向遍历的起始位置，使形成的子串不会重复
            if (ispalindrome(s, k, end)) { //判断子串是否是回文串
                path.add(s.substring(k, end+1));
                backtrack(s, end+1, path);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean ispalindrome(String s, int p, int r) {
        int i = p;
        int j = r;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

}
