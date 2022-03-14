package lessons.week1.pratice.part2.pratice10;

/**
 * @version 1.0
 * @Description: 搜索二维矩阵，争哥解法
 *
 * 示例 1：  20没在这里(m x n 矩阵)
 * 1   4  7 11 15
 * 2   5  8 12 19
 * 3   6  9 16 22
 * 10 13 14 17 24
 * 18 21 23 26 30
 *
 * @author: bingyu
 * @date: 2022/1/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 20;
        boolean b = searchMatrix(matrix, target);
        System.out.println(b);
    }


    /*
    推荐!
     思路: 从左上角顶点或者右下角点作为起点(为什么不用其它2个点？因为其它2个点是最小值和最大值，再和目标值进行比较大概率不会得到限制的范围)
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; //行
        int n = matrix[0].length; //列
        int row = 0;
        int col = n - 1;
         //取左上角的点开始比较(第1行，第n列)
        while (row < m && col >=0){
            int num = matrix[row][col];
            if (target > num) { //如果目标值大于选定的值，则行向下移动一位
                row++;
            }
            if (target < num) { //如果目标值小于选定的值，则列向前移动一位
                col--;
            }
            if (target == num) {
                return true;
            }
        }
        //执行到这里说明从while循环出来了，矩阵范围都没有找到目标值
        return false;
    }
}
