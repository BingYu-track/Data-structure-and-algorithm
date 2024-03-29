package lessons.week8.pratice.backtrack.pratice13;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  复原 IP 地址 ---争个解法
 * @Description: 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入'.' 来形成。你不能
 * 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
 *
 * 示例 1：
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 *
 * 示例 2：
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 *
 * 示例 3：
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 * 提示：
 * 1 <= s.length <= 20
 * s 仅由数字组成
 * @author: bingyu
 * @date: 2023/2/15
 */
public class ZgSolved {

    public static void main(String[] args) {
        String s = "25525511135";
        RestoreIpAddresses ip = new RestoreIpAddresses();
        List<String> list = ip.restoreIpAddresses(s);
        System.out.println(list);
    }

    private List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0, 0, new ArrayList<>());
        return result;
    }

    /*
     争哥解法: 比较难理解，推荐我上面自己的方法最好！
    */
    private void backtrack(String s, int k, int step, List<Integer> path) {
        if (k == s.length() && step == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; ++i) {
                sb.append(path.get(i) + ".");
            }
            sb.append(path.get(3));
            result.add(sb.toString());
            return;
        }
        if (step > 4) {
            return;
        }
        if (k == s.length()) {
            return;
        }
        int val = 0;
        // 1位数
        if (k < s.length()) {
            val = val*10+(s.charAt(k)-'0');
            path.add(val);
            backtrack(s, k+1, step+1, path);
            path.remove(path.size()-1);
        }
        if (s.charAt(k) == '0') { //前导0不⾏
            return;
        }
        // 2位数
        if (k+1 < s.length()) {
            val = val*10 + (s.charAt(k+1)-'0');
            path.add(val);
            backtrack(s, k+2, step+1, path);
            path.remove(path.size()-1);
        }
        // 3位数
        if (k+2 < s.length()) {
            val = val*10 + (s.charAt(k+2)-'0');
            if (val <= 255) {
                path.add(val);
                backtrack(s, k+3, step+1, path);
                path.remove(path.size()-1);
            }
        }
    }


}
