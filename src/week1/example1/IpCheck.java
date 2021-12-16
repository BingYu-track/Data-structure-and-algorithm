package week1.example1;

/**
 * @version 1.0
 * @Description: IP地址解析(拼多多面试题)
 * 给定一个字符串表示的地址，比如"123.92.2.34"，判断其是否合法。合法IP地址的规则如下：
 *   a. 除了空格、数字和.之外，不得包含其他字符。
 *   b. IP地址由四个数字构成，由.分隔，每个.隔开的数字大小在0~255之间。
 *   c. 数字前后可以有空格，但中间不能有空格。比如" 123 .92.2.34"合法，"12 3.92.2. 34"非法。
 * @author: bingyu
 * @date: 2021/12/16
 */
public class IpCheck {


    public static void main(String[] args) {
        IpCheck ipCheck = new IpCheck();
        String ip = "12 3.24.1 31.11";
        boolean check = ipCheck.check(ip);
        System.out.println(check);
    }

    public boolean check(String ip) {
        if (ip == null) return false;
        //将ip分割为子段:123.2.31.11
        String[] ipSeqments = ip.split("\\.");
        //验证是否满足子段个数为4
        if (ipSeqments.length != 4) {
            return false;
        }
        //验证每段是否合法
        for (int i = 0; i < 4; ++i) {
            boolean valid = checkSeqment(ipSeqments[i]);
            if (!valid) return false;
        }
        return true;
    }

    private boolean checkSeqment(String ipSeqment) {
        int n = ipSeqment.length();
        int i = 0;
        //跳过前导空格
        while (i < n && ipSeqment.charAt(i) == ' ') {
            //TODO:是空格的话，会i++，直接跳过当前空格的元素，如果第一个元素不是空格，则永远不会进while循环
            i++;
        }
        if (i == n) { //说明字符串全是空格
            return false;
        }
        //处理数字(将字符串转化成数字)
        int digit = 0;
        while (i < n && ipSeqment.charAt(i) != ' ') { //如果处理数字过程中遇到空格，会退出while循环
            char c = ipSeqment.charAt(i);
            if (c < '0' || c > '9') { //非数字字符，如果遇到非数字，直接返回false
                return false;
            }
            // c = '1' -> 1

            //TODO: 这个字符转化为整数的思路很精妙 c-'0'是ascii码的相减
            //1234 digit = 第一次循环: 0*10 + 1 = 1; 第二次循环: 1*10 +2 =12; 第三次循环: 12*10+3=123; 第四次循环:123*10 + 4=1234
            digit = digit*10 + (c - '0');  //执行到这里说明当前字符是数字，将字符转化为整数
            if (digit > 255) { //数字超过255
                return false;
            }
            i++;
        }
        //处理后置空格，例如："123 " or "12 3"
        while (i < n) { //i<n说明后面有空格，因为n是子段的长度，而前面在处理数字时如果遇到空格会跳出while循环，因此只要后面有空格，i必定就会小于长度n
            char c = ipSeqment.charAt(i);
            if (c != ' ') { //后面居然又有非空格字符，这里是判断空格后面是否是有数字，如果是数字说明空格是在数字中间，非法!
                return false;
            }
            i++;
        }
        return true;
    }


}
