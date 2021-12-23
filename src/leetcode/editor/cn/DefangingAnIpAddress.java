//给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。 
//
// 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。 
//
// 
//
// 示例 1： 
//
// 输入：address = "1.1.1.1"
//输出："1[.]1[.]1[.]1"
// 
//
// 示例 2： 
//
// 输入：address = "255.100.50.0"
//输出："255[.]100[.]50[.]0"
// 
//
// 
//
// 提示： 
//
// 
// 给出的 address 是一个有效的 IPv4 地址 
// 
// Related Topics 字符串 👍 73 👎 0

package leetcode.editor.cn;
//Java：IP 地址无效化
public class DefangingAnIpAddress{
    public static void main(String[] args) {
        Solution solution = new DefangingAnIpAddress().new Solution();
        // TO TEST
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String defangIPaddr(String address) {
        char[] chars = address.toCharArray();
        int oldLength = chars.length;
        char[] s = new char[oldLength + 3*2];
        int newLength = s.length;
        int j = 0;
        for (int i = 0; i<oldLength;i++) {
            if (chars[i] == '.') {
                s[j++] = '[';
                s[j++] = chars[i];
                s[j++] = ']';
            } else {
                s[j] = chars[i];
                j ++;
            }
        }
        return String.valueOf(s);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}