package lessons.week8.example.backtrackin.example5;

/**
 * @version 1.0 正则表达式
 * @Description: 假设正则表达是中只包含"*"和"?"这两种通配符，并且对两个通配符的语义稍微做些改变。其中，"*"匹配任意多个(大于等于0个)
 *  任意字符，"?"匹配零个或者一个任意字符。编程判断一个给定的文本，能否跟给定的正则表达式匹配?
 * @author: bingyu
 * @date: 2023/1/17
 */
public class RegularExpression {

    public static void main(String[] args) {
        RegularExpression expression = new RegularExpression();
        boolean result = expression.match("a", "?a*");
        System.out.println(result);
    }


    private boolean result = false;

    /**解题思路:
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

    /**
     * 如何判断其不匹配
     * @param text 文本串
     * @param pattern 模式串
     * @param ti 文本串下标位置
     * @param pi 模式串下标位置
     *   adcabef a*b?f
     */
    private void backtrack(char[] text, char[] pattern, int ti, int pi) {
        if (ti == text.length && pi == pattern.length) { //TODO： 终止条件是两个字符串下标都顺利到达末尾，而不是到达最后一个字符的下标
            result = true;
            return;
        }
        if (pattern[pi] == '*') {  //Step1.匹配任意多个，包含0;有多种选择
            for (int i = ti;i<=text.length;i++) {  //TODO: 这里i<text.length会导致text字符串为空时不会执行for循环里面的代码导致返回false
                backtrack(text,pattern,i,pi+1); //TODO: 这里pi+1后就会到下一层Step3的代码进行比较
            }
        }else if (pattern[pi] == '?') { //Step2.匹配0个或者1个任意字符,有2种选择
            backtrack(text,pattern,ti,pi+1); //?匹配0个
            if (ti < text.length) {
                backtrack(text,pattern,ti+1,pi+1); //?匹配一个，那么此时是必定能匹配成功的，这里ti可能会过界
            }
        }else {
            //Step3.执行到这说明就是一个具体的字符
            if (ti < text.length && text[ti] == pattern[pi]) { //TODO:这里要加上ti < text.length，比如text是空字符串时，这里就会出现问题
                backtrack(text,pattern,ti+1,pi+1);
            }
        }
    }


    /* 从下面画的决策树，我们就知道了，
     阶段: 跟模式串的字符个数
     可选列表: 取决于模式串的每个字符
     路径:
    决策树  adcabef  a*b?f
            0       0
            0       1
                        a
                        | ---模式串第一个字符就是a，只有一个选择
                        a
                 /  /   |    \  -----第2个字符是*，因此有多个选择
                /  /    |     \
               a  ad   adc    adcb
              /  /      |       \
             x  x       |        x  ----第3个字符是b，因此看字符串后面位置是否匹配，不匹配的全部剪枝
                       adcb
                    /       \     ------第4个字符是?，只能表示0个或者1个字符，因此有2种选择
                  adcb     adcbe
                   |         |
                   x         |    -----第5个字符是f，直接进行匹配，不匹配的剪枝
                   |        adcbef
                 adcbe
    */
}
