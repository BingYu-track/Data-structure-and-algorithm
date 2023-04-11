package lessons.week10.dp.example.example6;

/**
 * @version 1.0  剑指 Offer 19. 正则表达式匹配----leetcode题解
 * @Description: 请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 *   在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 * @author: bingyu
 * @date: 2023/2/1
 */
public class Solved {

    public static void main(String[] args) {
        Solved match = new Solved();
        boolean result = match.isMatch("mississippi", "mis*is*p*.");
        System.out.println(result);
    }

    private boolean match = false;

    /** "mississippi", "mis*is*p*."
     * @param s 字符串s
     * @param p 字符规律p，即正则模式串
     * @return
     */
    public boolean isMatch(String s, String p) {
        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        backtrack(text,pattern,0,0);
        return match;
    }

    private void backtrack(char[] text, char[] pattern, int ti, int pi) {

    }
}
