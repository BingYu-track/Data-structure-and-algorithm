package lessons.week1.example.example2;

/**
 * @version 1.0
 * @Description: IP地址无效化
 * 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 示例 1：  输入：address = "1.1.1.1"  输出："1[.]1[.]1[.]1"
 示例 2：  输入：address = "255.100.50.0"  输出："255[.]100[.]50[.]0"
  提示：给出的 address 是一个有效的 IPv4 地址
 （注意：面试时不能用replace()函数，这题考察的就是你如何实现replace函数，如果直接使用api里的，就失去该题面试的意义了）
  要揣测出题者的考察意图 + 多沟通
 * @author: bingyu
 * @date: 2021/12/17
 */
public class DefangingAnIpAddress {

    public static void main(String[] args) {
        String ip = "255.100.50.0";
        String defang = defang(ip);
        System.out.println(defang);
    }

    public static String defang(String ip) {
        char[] chars = ip.toCharArray();
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
