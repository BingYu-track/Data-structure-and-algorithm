package lessons.week8.pratice.backtrackin.pratice13;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  复原 IP 地址
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
 *
 * @author: bingyu
 * @date: 2023/2/13
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {
        String s = "25525511135";
        RestoreIpAddresses ip = new RestoreIpAddresses();
        List<String> list = ip.restoreIpAddresses(s);
        System.out.println(list);
    }

    private List<String> result = new ArrayList<>();

    /* TODO:Character[]数组无法直接转化为char[]，因此，我们不能使用list，只能使用char数组来尝试解决
            如何将该问题抽象成多阶段模型?
     1. 0到255
     2. 四个整数
     3. 整数之间用 '.' 分隔
     4. 不能含有前导0
     5. 元素顺序不能发生变化
    */
    public List<String> restoreIpAddresses(String s) {
        if (s.length()<=12) { //最大值是255.255.255.255,；合法ip的最长字符也就是4个255，即12个数字字符，如果大于12.说明肯定不是合法ip
            StringBuilder path = new StringBuilder();
            backtrack(0,0,4,s,path);
        }
        return result;
    }

    /**
     *  TODO: 完全靠自己独立完成!推荐该方法！
     *  执行用时：1 ms, 在所有 Java 提交中击败了96.46%的用户
     *  内存消耗：40.2 MB, 在所有 Java 提交中击败了89.48%的用户
     *
     * @param startIndex 用来控制横向遍历的起始位置
     * @param step 记录阶段，表示当前处于决策树的第几层
     * @param k 总阶段数为4，是个固定值
     * @param s 原始字符串
     * @param path 路径
     */
    private void backtrack(int startIndex,int step, int k, String s, StringBuilder path) {
        //TODO: 不仅深度要处理到4，还要使字符串中所有字符都能遍历搜索到，不然可能出现10.10.2这样的ip
        if (step == k && startIndex==s.length()) {
            result.add(path.toString());
            return;
        }
        if (step>=k) return;
        //TODO: 从决策树可以看到横向遍历只有3次，每3次都是从起始位置开始依次增加一个字符，因此是i<=startIndex + 2；又因为可能出现越界，因此加上i<s.length()
        for (int i = startIndex;i<=startIndex + 2 && i<s.length();i++) {
            char c = s.charAt(startIndex);
            int len = i+1-startIndex; //子串的长度
            if (c=='0' && len>1) return; //判断子串的第一个字符是不是0，如果是0，就没必要继续横向遍历处理了，直接返回上一层
            if (len == 3 && check(s,startIndex,i,len)) { //如果字符个数等于3，则需要判断这3个字符构成的数字是否大于255，大于255直接跳过
                continue;
            }
            path.append(s.substring(startIndex,i+1)); //将指定下标的字符串放入路径中，因为，每放入一个，那么下次放入分隔符就要从后面开始
            if (step<3) path.append(".");
            backtrack(startIndex+len,step+1,k,s,path); //下一层横向遍历需要从前面字符长度结尾下标开始
            if (step<3) path.deleteCharAt(path.length()-1); //删除最后一个字符'.'
            path.delete(path.length()-len,path.length()); //删除倒数len个字符
        }
    }

    /**
     * 检验是否大于255
     * @param s
     * @param startIndex
     * @param end
     * @return
     */
    private boolean check(String s, int startIndex, int end,int len) {
        int num = 0;
        int t = 1;
        for (int i = end;i>=startIndex;i--) {
            int n = s.charAt(i) - '0';
            num = num + n * t;
            t = t*10;
        }
        if (num >255) return true;
        return false;
    }


    /*输入: s = "101023"
      输出: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
                            [ ]
               /             |            \
              1.            10.            101.  -----放入第1个分隔符，因为分隔符最多只能分隔3个字符，所以一层只有3种选择
         /    |   \      /   |      \
       1.0. 1.0        10.1. 10.10. 10.102.       --------------放入第2个分隔符
       .................
       从上面的决策树模型可以看出，每个分隔符实际上只有3种选择。直到3个点都有效放入后，整个过程结束
       如何记录
    */
}
