package lessons.week1.pratice.pratice3;

/**
 * @version 1.0
 * @Description: 争哥的解法
 * @author: bingyu
 * @date: 2021/12/22
 */
public class Test {

    public static void main(String[] args) {
        String str = "  hello world!  ";
        String s = reverseWords(str);
        System.out.println(s);
    }

    public static String reverseWords(String s) {
        // 这⾥只是因为Java中的String不可变
        char[] str = s.toCharArray();
        //1.先处理空格并返回处理后的字符串长度
        int n = trim(str); //⾃⼰实现的
        if (n == 0) return ""; //如果长度为0，返回""

        //2.翻转所有字符
        reverse(str, 0, n-1);

        //3.翻转每个单词
        int p = 0;
        while (p < n) {
            int r = p;
            while (r < n && str[r] != ' ') {
                r++;
            }
            //执行到这里说明r位置是空格，则需要翻转p到n-1位置的单词，然后p从r+1位置开始作为起始点
            reverse(str, p, r-1);
            p = r+1;
        }

        // 这⾥只是为了配合输出
        char[] newStr = new char[n];
        for (int i = 0; i < n; ++i) {
            newStr[i] = str[i];
        }
        return new String(newStr);
    }

    public static int trim(char[] str) {
        int n = str.length;
        // 跳过⾸空格
        int i = 0;
        while (i < n && str[i] == ' ') {
            i++;
        }
        // 跳过尾空格
        int j = n-1;
        while (j >= 0 && str[j] == ' ') {
            j--;
        }
        //清除内部多于空格，并且把i~j之间的字符搬移到数组最前⾯
        //处理思路: 将i和j之间的元素一个个移动到右边，如果遇到字母，直接移动，如果遇到空格，如果只有一个空格就移动，如果有多个空格
        int k = 0;
        while (i <= j) { //因为要将i和j之间的元素一个个移动到右边，所以需要移动j-i次
            if (str[i] == ' ') { //因为执行到这里的i和j之间是没有首位空格的，i和j中间如果再遇到空格，就是单词中间的空格
                if (i+1<=j && str[i+1] != ' ') { //如果遇到了空格，但是后面位置不是空格就把该位置的空格移动到右变，如果后面还有空格就跳过
                    str[k++] = ' ';
                }
            } else {
                str[k++] = str[i];
            }
            i++;
        }
        return k;
    }

    public static void reverse(char[] arr,int first,int last) {
        if (last < first) return;
        int length = last - first + 1; //得到指定要翻转的子段的长度
        //从first开始，互换first + length/2次，
        for (int i = first,j = 0; i<(first + length/2);i++,j++) {
            char temp = arr[i];
            arr[i] = arr[first + length - 1 - j];
            arr[first + length - 1 - j] = temp;
        }
    }
}
