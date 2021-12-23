package lessons.week1.pratice.pratice3;

import lessons.util.Util;

import java.util.Arrays;

/**
 * @version 1.0 翻转单词顺序
 * @Description: 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，
 * 标点符号和普通字母一样处理。例如输入字符串"I am a student. "则输出"student. a am I"。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: " hello world! "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * @author: bingyu
 * @date: 2021/12/20
 */
public class ReverseWords {

    public static void main(String[] args) {
        String str = "  hello world!  ";
        str = "the sky is blue";
        str = " 1";
        String s = reverseWords2(str);
        System.out.println(s);
    }
    //要求
    //1.单词内字符顺序不变，标点符号和字母一样处理
    //2.如果有前后导空格，反转后需要去除
    //3.单词间的空格减少到只含一个空格
    //TODO:我的解法
    public static String reverseWords(String s) {
        if (s == null || s=="") return s;
        //1.先去除前后导空格
        s = s.trim();
        if (s.length() == 0) return s;

        //2.再将多空格替换成1个空格
        char[] chars = s.toCharArray();
        char[] res = new char[chars.length];
        for (int i = 0,j=0;i < chars.length;i++) {
            if (chars[i] ==' ') { //遇到空格
                if (i - 1 >=0 && chars[i-1] != ' ') { //如果是在中间遇到的第一个空格，则把第一个空格设置进新数组中
                    res[j] = chars[i];
                    j++;
                }
                //判断空格后面是否还有空格
                while (i < chars.length && chars[i] ==' ') {
                    i++;
                }
                //执行到这里说明后面没空格了
                res[j] = chars[i];
                j++;
            }else {
                res[j] = chars[i];
                j++;
            }

        }
        String str = String.valueOf(res).trim(); //注意: 因为res数组后面有2个空位，所以转为字符串会出现空白符
        //3.在字符串中提取出空格和单词组装成字符串数组
        String[] words = str.split(" ");
        String[] newWords = new String[words.length + words.length - 1]; //包含单词和空格

        //4.再进行字符串数组翻转
        reverseString(words);
        //System.out.println(Arrays.toString(words));

        //5.再在指定位置加入空格
        for (int i = 0,j=0;i<words.length;i++) {
            newWords[j] = words[i];
            j++;
            if (i < words.length - 1) { //只要不是最后一个元素，每一个元素后面都要加空格
                newWords[j] = " ";
                j++;
            }
        }

        //6.最后进行字符串拼接
        StringBuilder sb = new StringBuilder();
        for (String newWord : newWords) {
            sb.append(newWord);
        }
        return sb.toString();
    }

    public static void reverseString(String[] s) {
        String temp = "";
        for (int i = 0; i<(s.length/2);i++) { //执行到1半即停止
            swap(i,s.length - 1 - i,s,temp); //首位互换
        }
    }

    public static void swap(int f,int l,String[] s,String temp) {
        temp = s[f];
        s[f] = s[l];
        s[l] = temp;
    }

    //TODO:O(1)空间复杂度解法(很难)
    //思路：以"the sky is blue"为例，
    //1.先将字符串整个进行翻转，变成"eulb si yks eht"
    //2.去除前后导空格，还要处理中间的多个空格(注意：这个需要自己实现，库里只有去除前后导空格的方法，没有去除中间空格的方法)
    //3.再将其中的每个单词进行翻转即可
    public static String reverseWords2(String s) {
        char[] chars = s.toCharArray();
        //1.核心逻辑--处理前后空格，和中间空格
        int n = trim(chars);
        if (n==0) return "";

        //2.再将字符串整个进行翻转
        Util.reversePart(chars,0,n - 1);


        //3.核心逻辑--将其中的每个单词进行翻转
        int last = 0;  //用来记录单词最后一个字母所在的位置
        for (int first = 0; first < n; last++,first = last) {
            while (last < n && chars[last]!=' ') { //[f, ,e,d, ,c, ,b,a, , , , , ]
                last++;
            }
            //执行到这里说明last已经是在一个单词的末尾的空格位置了,因此下面单词每次翻转完成后，last都需要加1，
            //使last移动到下一个单词的首字母位置，同时也要把该位置赋值给first
            Util.reversePart(chars,first,last - 1); //翻转单词
        }
        char[] result = new char[n];
        for (int i=0;i<n;i++){
            result[i] = chars[i];
        }
        return new String(result);
    }

    /**
     * 原地删除前置空格和后置空格，以及内部多于的空格，返回新字符串⻓度
     * @param arr 待处理的char数组
     * @return
     */
    //思路:指定2个游标，1个跳过前导空格，另一个跳过后导空格，然后处理中间空格
    public static int trim(char[] arr) { //[, ,f, ,e,d, , ,c, ,b,a, ,]
        int length = arr.length;
        int i = 0; //首游标
        int j = length - 1; //尾游标
        //1.跳过前导空格
        while (i<arr.length && arr[i] == ' ') {
            i++;
        }
        //2.跳过后导空格
        while (j>=0 && arr[j] == ' ') {
            j--;
        }
        //执行到这里arr仍为[, ,f, ,e,d, , ,c, ,b,a, ,] 现在i指向f,j指向a
        //3.处理中间空格，处理思路:
        //以"  ab c  de f  "为例，中间的元素移动到右边，如果遇到字母，直接移动，如果遇到空格，再判断是否
        //循环次数就是i和j的长度,这里不能用k<=(j-i+1)，因为k是不断递增的
        int k ;
        for(k = 0;i<=j; i++) { //i=6
            if (arr[i] == ' ') { //遇到空格
                if(i - 1 >=0 && arr[i-1] != ' ') { //如果空格前面的元素不是空格，则该空格移动，否则不移动
                    arr[k] = arr[i];
                    k++;   //将i对应数据移动到前面的位置后，k才能再增加
                }
            }else {
                //执行到这里说明是非空格
                arr[k] = arr[i];
                k++;
            }
        }
        //执行到这里说明已经执行完，k这个位置就是处理完字符串空格后的末尾位置(这个k也就是新字符串的长度)，
        return k;
    }




}
