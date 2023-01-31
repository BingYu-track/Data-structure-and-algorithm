package lessons.week8.example.backtrackin.example5;

/**
 * @version 1.0 正则表达式 ---争哥解法
 * @Description: 假设正则表达是中只包含"*"和"?"这两种通配符，并且对两个通配符的语义稍微做些改变。其中，"*"匹配任意多个(大于等于0个)
 *  *  任意字符，"?"匹配零个或者一个任意字符。编程判断一个给定的文本，能否跟给定的正则表达式匹配?
 * @author: bingyu
 * @date: 2023/1/31
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved solved = new ZgSolved();
        boolean result = solved.match("", "*");
        System.out.println(result);
    }



    private boolean result = false;

    /**争哥解法
     * @param s 字符串s
     * @param p 字符规律p，即正则
     * @return
     */
    public boolean match(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        backtrack(text,pattern,0,0);
        return result;
    }

    private void backtrack(char[] text, char[] pattern, int ti, int pi) {
        if (ti == text.length && pi == pattern.length) {
            result = true;
            return;
        }
        //做选择--根据模式字符判断有多少选择
        if (pattern[pi] == '*') { //*匹配任意字符，包含0
            for (int k = 0;k <= text.length - ti;k++) {
                backtrack(text,pattern,ti+k,pi+1);
            }
        }else if (pattern[pi] == '?') { //?匹配0个或1个字符
            backtrack(text,pattern,ti,pi+1); //匹配0个
            if (ti < text.length) {
                backtrack(text,pattern,ti+1,pi+1);
            }
        }else if (ti < text.length && pattern[pi] == text[ti]) {
            backtrack(text,pattern,ti+1,pi+1);
        }
    }


}
