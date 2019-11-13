package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 22.括号生成问题
 * @author: bingyu
 * @date: 2019/11/4 21:29
 */
public class GenerateParentheses {

    //解题思路：将一个个括号的位置看成一个格子，一个格子有2种分叉的可能，使用这样的思路求解

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(0,0,n,"",list);
        return list;
    }

    /**
     *  TODO 方法一：递归
     * @param left 已使用左括号的个数
     * @param right 已使用右括号的个数
     * @param n 总共提供的个数
     * @param currentStr 当前字符串
     */
    public void generate(int left, int right,int n,String currentStr,List list) {
        //1.terminator  终止条件：左右括号数量均为n
        if (left == n && right == n) {
            //添加括号时判断括号的合法性 1.左括号不能大于n。 2.添加右括号要注意前面必须要有左括号，且左括号的个数要大于右括号的个数
            list.add(currentStr);
            return;
        }
        //2.process current level   当前层逻辑加左右括号
        String s1,s2;
        if (left < n) { //添加左括号
            s1 = currentStr + "(";
            generate(left + 1,right,n,s1,list); //添加括号后，相应的已使用的个数消耗掉，需要加1
        }

        if (left > right) { //添加右括号
            s2 = currentStr + ")";
            generate(left,right + 1,n,s2,list);
        }


        //3.drill down

        //4.reverse status
    }



    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        List<String> list = gp.generateParenthesis(3);
        System.out.println(list);
    }
}
