package lessons.week1.pratice.part2.pratice9;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 螺旋矩阵
 * @Description: 给你一个m行, n列的矩阵matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素
 *
 * 示例 1：
 * 1->2->3
 *       |
 * 4->5  6
 * |     |
 * 7<-8<-9
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 1->2->3 ->  4
 *             |
 * 5->6->7     8
 * |           |
 * 9<-10<-11<-12
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 示例 3：
 * 输入: matrix = [[1,2,3],[4,5,6],[7,8,9],[10,11,12]]
 * 输出：[1,2,3,6,9,12,11,10,7,4,5,8]
 * 1 ->2 -> 3
 *          |
 * 4 ->5    6
 * |   |    |
 * 7   8    9
 * |        |
 * 10<-11<-12
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * m>=1, n<=10
 * -100 <= matrix[i][j] <= 100
 *
 * @author: bingyu
 * @date: 2022/1/17
 */
public class SpiralOrder {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

    //思路:发现螺旋一次，就会走遍最外一层，第二次会从对角线的第2个元素开始。发现其实就是求从对角线开始的外层元素从上->右->下->左的遍历顺序，外层遍历完后
    //继续内层遍历,要注意的是每次螺旋一层后，矩阵的高度和长度都会减少，会影响到每条边的起始位置和遍历的长度
    //TODO:推荐使用争哥的方法，争哥的代码逻辑思路比我更清晰且容易理解!
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length; //获取m行
        int n = matrix[0].length; //获取n列
        List<Integer> list = new ArrayList<>();
        if (m == n && m == 1) { //特殊情况1-如果只有1个元素，直接返回该元素
            list.add(matrix[0][0]);
            return list;
        }
        if (m == 1) { //特殊情况2-如果只有一行，直接返回该行元素
            for (int i : matrix[0]) {
                list.add(i);
            }
            return list;
        }

        //其它情况,
        int time = 0; //需要螺旋的次数(就是矩阵里套了有多少层矩阵)
        int k = m % 2; //对行数取余，如果等于0，就是偶数，否则就是奇数
        if (k==0) {
            time = m/2;
        }else {
            time = m/2 + 1;
        }
        int total = n*m;
        //开始螺旋遍历，m代表行，n代表列
        for (int i = 0;i < time;i++) {   //i表示当前是第几次螺旋
            //TODO:每次螺旋按照从上->右->下->左的顺序进行遍历
            //1.遍历当前层矩阵的顶部，每次螺旋循环后最上面的长度会发生变化，每次减少2个单位长度，因此列j从i开始到n-i结束
            for (int j = i;j < (n - i) && (list.size()< total);j++) {
                list.add(matrix[i][j]); //行(i)不变，列(j)是在变化的
            }

            //2.遍历当前层矩阵的右列，不包括上、下两边和右列重叠的元素，因为每次螺旋循环后，高度会不断变小，即行不断变小，
            // 因此行j从i+1开始到m-i-1(减1是要去除与下边重合的元素)
            for (int j = i+1;j < m-i-1 && (list.size()< total);j++) { //列不断向内推进即n-1-i
                list.add(matrix[j][n-1-i]);//列随着每层螺旋不断向内推进，因此列在遍历时是(n-1-i)固定不变的，行(j)在不断变化
            }

            //3.遍历当前层矩阵的底部,因为每次螺旋循环后，长度都会变小，因此起始的列元素也会变化，因此列j从n-i-1开始到i(注意要从后往前遍历)
            //对应的列也会向内推进因此行下标为m-1-i
            for (int j = n-i-1;j >=i && (list.size()< total);j--) {
                list.add(matrix[m-1-i][j]); //列随着每层螺旋不断向内推进，行位置在当前层遍历时是(m-i-1)不变的，列(j)在不断变化
            }

            //4.遍历当前层矩阵的左列，不包括上、下两行重叠的元素，并且从下到上遍历;列下标不变，行下标在不断变化
            //因此行从m-i-2(减2是因为一个要去除与底部重合的元素，一个因为是数组下标需要减去1)开始到i-1(因为要去除与顶部重合的元素)结束
            for (int j = m-i-2;j>i && (list.size()< total);j--) {
                list.add(matrix[j][i]); //左列随着每层螺旋不断变小，列位置在当前层遍历时就是i保持不变，行(j)在不断变化
            }
        }
        return list;
    }

    /*
    1 2 3 4 5 6 7 8
    1 2 3 4 5 6 7 8
    1 2 3 4 5 6 7 8
    1 2 3 4 5 6 7 8
    1 2 3 4 5 6 7 8

     */

}
