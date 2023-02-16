package lessons.week8.pratice.backtrackin.pratice14;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  括号生成
 * @Description: 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 *
 * @author: bingyu
 * @date: 2023/2/15
 */
public class GenerateParenthesis {

    public static void main(String[] args) {
        int n = 3;
        GenerateParenthesis g = new GenerateParenthesis();
        List<String> list = g.generateParenthesis(n);
        System.out.println(list);
    }

    private int leftCount = 0; //记录左括号出现的次数
    private int rightCount = 0; //记录右括号出现的次数
    private List<String> result = new ArrayList<>();


    public List<String> generateParenthesis(int n) {
        char[] path = new char[n * 2];
        backtrack(n,0,path);
        return result;
    }

    /** 独立完成
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.2 MB, 在所有 Java 提交中击败了91%的用户
     * @param n
     * @param startIndex
     * @param path
     */
    private void backtrack(int n,int startIndex, char[] path) {
        if (leftCount == n) { //执行到这里，说明左括号都使用完了，再把剩下的右括号放进去
            while (startIndex < path.length) {
                path[startIndex] = ')';
                startIndex++;
            }
            result.add(String.valueOf(path));
            return;
        }
        //如果右括号都使用了，再把剩下的左括号放进去,如果出现这种状况，说明就是非法组合，因此永远不可能进入这里
        //放入左括号
        path[startIndex] = '(';
        if(check(path,startIndex)) return; //校验括号组合是否非法
        leftCount += 1;
        backtrack(n,startIndex+1,path);
        path[startIndex] = '\u0000'; //撤销左括号
        leftCount -= 1;

        //放入右括号
        path[startIndex] = ')';
        if(check(path,startIndex)) return; //校验括号组合是否非法
        rightCount += 1;
        backtrack(n,startIndex+1,path);
        path[startIndex] = '\u0000'; //撤销右括号
        rightCount -= 1;
    }

    /**
     * 判断路径里的字符串是否合法，
     * TODO 思路: 重点在右括号，遍历过程中记录左右括号的个数，当碰到右括号，如果前面有左括号个数还有，就消去一个；当下次
     *      遇到右括号时，前面左括号个数已经没有了，说明该括号组合非法!
     * @param path
     * @param endIndex
     */
    private boolean check(char[] path,int endIndex) {
        int left = 0; //记录左括号个数
        int right = 0; //记录右括号个数
        for (int i = 0;i<=endIndex;i++) {
            char p = path[i];
            if (p == ')') {
                if (left > 0) { //前面有左括号，说明可以成对，跳过当前循环
                    left--;
                    continue;
                }
                if (left == 0) return true; //右括号前面没有左括号，说明当前括号组合非法
                right++;
            }
            if (p == '(') {
                left++;
            }
        }
        return false;
    }


    /* 保证  ( ) ) ( (
      输入：n = 3  3对括号，长度为6的字符串，相当于6个位置，每个位置有2种选择，选择放入或者不放入
      输出：["((()))","(()())","(())()","()(())","()()()"]
                            [  ]
                          /     \
                        (        )   --------在第一个位置选择左括号或者右括号
                       / \
                      ((  ()
                     /  \
                    (((  (()
                         /   \
                        (()(  (())
                        |      /  \
                     (()()) (())(  (()))
                              |       |
                            (())()  (()))(
        终止条件:
                1.难点在于如何判断括号组合是否合法?
                 TODO 在搜索的时候判断括号是否合法---右括号前面有无有效的左括号，没有说明是不和发组合，后面也无需再进行搜索
                2.左括号或者右括号出现n次停止。
         从上面的决策树模型中可以看出，是没有固定的k阶段的，横向遍历也只有2个分支，重点还是在于终止条件的判断!
    */
}
