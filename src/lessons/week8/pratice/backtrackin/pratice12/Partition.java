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
 *
 * @author: bingyu
 * @date: 2023/2/9
 */
public class Partition {

    public static void main(String[] args) {
        String s = "aab";
        Partition p = new Partition();
        List<List<String>> lists = p.partition(s);
        System.out.println(lists);
    }

    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> partition(String s) {
        char[] chars = s.toCharArray();
        List<String> path = new ArrayList<>();
        backtrack(0,path,chars);
        return result;
    }

    /** TODO：需要理解
     * 解题思路: 从第一个字符开始不断和后面字符组合成子串，再判断子串是否是回文串，并放入路径中
     * @param k 阶段--作为子串的起始下标
     * @param path
     * @param cs
     */
    private void backtrack(int k, List<String> path, char[] cs) {
        if (k == cs.length) {
            result.add(new ArrayList<String>(path));
            return;
        }
        for (int i = k;i<cs.length;i++) { //从第一个字符开始不断和后面字符组合成子串，再判断子串是否是回文串
            boolean flag = isPalindrome(cs, k, i);//检测每个子串是否是回文串
            if (flag) {
                path.add(new String(cs,k,i-k+1)); //生成子串并放入路径里
                backtrack(i+1,path,cs); //这里为何额i+1而不是k+1
                path.remove(path.size()-1); //撤销
            }
        }

    }

    /*
     判断是否是回文串，使用双指针
     */
    public boolean isPalindrome(char[] chars,int i,int j) {
        if (chars.length == 1) return true;
        for (;i<=j;i++,j--) {
            char ci = chars[i];
            char cj = chars[j];
            if (ci != cj) {
                return false;
            }
        }
        return true;
    }



}
