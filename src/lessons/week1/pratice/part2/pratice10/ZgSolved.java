package lessons.week1.pratice.part2.pratice10;

/**
 * @version 1.0
 * @Description: 搜索二维矩阵，争哥解法
 *
 * 示例 1：20没在这里
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
     思路: 从左上角顶点或者右下角点作为起点(为什么不用其它2个点，因为其它2个点是最小值和最大值，再，再和目标值进行比较大概率不会得到限制的范围)

     */
    public static boolean searchMatrix(int[][] matrix, int target) {

        return false;
    }
}
