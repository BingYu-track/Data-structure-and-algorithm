package lessons.week3.pratice.part2.pratice4;

import java.util.Stack;

/**
 * @version 1.0
 * @Description: 栈的压入、弹出序列--争哥解法
 * @author: bingyu
 * @date: 2022/4/21
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1}; //4,5,3,1,2
        boolean result = validateStackSequences(pushed, popped);
        System.out.println(result);
    }

    /**
     * TODO: 推荐方法
     * 争哥的解法思路和我的比较相似: 用i和j两个游标分别记录pop和push的位置，每次都考察栈顶元素是否能安装poped来弹出，不能就只能pushed，
     *    然后不断考察直到poped里的元素全部出完为止，否则就是顺序不正确
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0; //用来表示poped游标位置
        int j = 0; //用来表示pushed游标位置
        while (i < popped.length || j < pushed.length) { //当poped和pushed都操作完，表示所有操作完成----即i和j游标都移到到末尾，
            if(stack.isEmpty() || (j < pushed.length && stack.peek() != popped[i])){ //当栈的栈顶元素无法匹配poped游标指向的元素时，将pushed中的元素压到栈里，移动pushed的游标
                stack.push(pushed[j]);
                j++;
                continue;
            }
            //执行到这里，说明要么是栈顶元素刚好匹配poped游标指向的元素，要么是pushed游标已经到了末尾执行到了这里
            if(!stack.isEmpty() && stack.peek() == popped[i]){
                stack.pop();
                i++;
                continue;
            }
            break; //执行到这说明，1.栈不为空，且栈顶元素不匹配poped 2.栈为空，正常执行完成
        }
        //当pushed和poped完后,j和i就都到了末尾，且栈为空时，说明顺序正确，否则就是某个地方卡住了
        return i == popped.length && j == pushed.length && stack.isEmpty();

    }


}
