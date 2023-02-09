package lessons.week8.pratice.backtrackin.pratice4;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0  77. 组合
 * @Description: 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 *
 * 示例 1：
 *
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 *
 * 示例 2：
 *
 * 输入：n = 1, k = 1
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * @author: bingyu
 * @date: 2023/1/16
 */
public class Combine {

    public static void main(String[] args) {
        Combine combine = new Combine();
        List<List<Integer>> result = combine.combine(4, 2);
        System.out.println(result);
    }

    private List<List<Integer>> result = new ArrayList<List<Integer>>(); //leetcode成员变量别用静态

    /*

    */
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
//        backtrack(n,k,0,path);
        backtrack2(n,k,0,path);
        return result;
    }

    /** 方法一
     * 这题的可变参数就是4，选择和不选择
     * @param n 可变参数
     * @param size 是路径里的规定的元素个数,也是总阶段深度
     * @param k 当前阶段从0开始
     * @param path 路径
     *   执行用时：154 ms, 在所有 Java 提交中击败了5.02%的用户
     *   内存消耗：43 MB, 在所有 Java 提交中击败了23.09%的用户
     */
    private void backtrack(int n, int size, int k,List<Integer> path) {
        if (path.size() == size) {
            result.add(new ArrayList<>(path));
            return;
        }
        if(k > n) return;
        for (int i = 1;i<=n;i++) {
            if (path.size()>0 && path.get(path.size()-1)>=i) continue; //跳过路径之前走过的，并且比自己小的都跳过
            path.add(i);
            backtrack(n,size,k+1,path);
            path.remove(path.size()-1);
        }

    }

    /* n=4,k=2
    从决策树可以看到，第一层有4种选择，第2层只有3种选择了，可选列表随着深度的增加而减小
                        []
         /          /             \      \
       1           2               3      4
    /  | \       / |  \
  1,2 1,3 1,4
    */


    /* 方法二:
     执行用时：19 ms, 在所有 Java 提交中击败了16.78%的用户
     内存消耗：43.2 MB, 在所有 Java 提交中击败了18.15%的用户
     * 这题的可变参数就是2，选择和不选择
     * @param n 是阶段总数-->决定递归的深度,从1开始
     * @param size 是路径里的规定的元素个数
     * @param k 当前阶段从0开始
     * @param path 路径
     */
    private void backtrack2(int n, int size, int k,List<Integer> path) {
        if (k == n && path.size() == size) { //TODO： 因为是中途就可以得到可行解
            result.add(new ArrayList<>(path));
            return;
        }
        if(k >= n) return; //这里是防止出现一直不放的情况下，会超出n，因为前面必须是path ==size才能返回，因此如果一直不放，会一直进行下去，所以这里需要进行限制
        //不选择
        backtrack2(n,size,k+1,path);
        path.add(k+1);
        backtrack2(n,size,k+1,path);
        path.remove(path.size()-1); //撤销
    }


    /* 画出决策树 1,2,3,4  n=4 k=2。可以看出，我们要的结果都在决策树的底部
                                  {}
                        /                         \   -----k=0，选择放入1还是不放入
                      {}                           {1}
               /              \                /            \ -----k=1 选择放入2还是不放入
              {}              {2}             {1}          {1,2}
             /   \          /     \            /   \        /     \  ----k=2 选择放入3还是不放入
           {}    {3}       {2}   {2,3}        {1} {1,3}   {1,2} {1,2,3} ---到递归到底部时进行返回，并放入结果数组里
          / \    / \      / \    /   \         /\                                                     -----k=3 选择放入4还是不放
         {} {4} {3}{3,4}{2}{2,4}{2,3} {2,3,4} {} {1,4}


            分为2个格子，1个格子有n种选择
   */

}
