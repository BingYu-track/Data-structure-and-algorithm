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

    //思路:发现螺旋一次，就会走遍最外一层，第二次会从对角线的第2个元素开始。发现其实就是求从对角线开始的外层元素的顺序，
    //要注意的是每次螺旋一层后，矩阵的高度和长度都会减少，会影响到每条边的起始位置和遍历的长度
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
        int time = 0; //需要螺旋的次数
        int k = m % 2; //对行数取余，如果等于0，就是偶数，否则就是奇数
        if (k==0) {
            time = m/2;
        }else {
            time = m/2 + 1;
        }
        //开始螺旋遍历
        for (int i = 0;i < time;i++) {
            //1.遍历外层上面一行(行不变，列是在变化)，不能写list.size()< n*m，当刚好是16时，还会执行就会报错
            for (int j = i;j < (n - i) && (list.size()< n*m);j++) { //因为每次螺旋循环后最上面的长度会发生变化，每次减少2个单位长度，因此从i开始到n-i
                list.add(matrix[i][j]);
            }

            //2.遍历外层右列，不包括和上、下两行重叠的元素(列不变，行在变化)
            for (int j = i+1;j <= m-2-i && (list.size()< n*m);j++) { //因为每次螺旋循环后，高度不断变小，即行不断变小，列不断向内推进即n-1-i
                list.add(matrix[j][n-1-i]);
            }

            //3.遍历外层下面一行(行下标不变，列在变化)
            for (int j = n-1-i;j >=i && (list.size()< n*m);j--) { //因为每次螺旋循环后，长度都会变小，因此起始的行元素也会变化，因此j=n-1-i,对应的行也会向内推荐因此行下标为m-1-i
                list.add(matrix[m-1-i][j]);
            }

            //4.遍历外层左列，不包括上、下两行重叠的元素
            for (int j = m-2-i;j>i && (list.size()< n*m);j--) {//j=5-2-1=2 j>2
                list.add(matrix[j][i]);
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
