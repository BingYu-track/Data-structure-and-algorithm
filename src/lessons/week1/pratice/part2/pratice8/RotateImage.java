package lessons.week1.pratice.part2.pratice8;

/**
 * @version 1.0 旋转图像
 * @Description: 给定一个 n×n 的二维矩阵matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 2：
 *
 *
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：[[1]]
 *
 * 示例 4：
 * 输入：matrix = [[1,2],[3,4]]
 * 1 2
 * 3 4
 * 输出：[[3,1],[4,2]]
 * 3 1
 * 4 2
 *
 *
 * 提示：
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 * @author: bingyu
 * @date: 2022/1/12
 */
public class RotateImage {

    public static void main(String[] args) {
        int[][] arr1 = {{1,2},{3,4}};
        /*
        5  1  9  11
        2  4  8  10
        13 3  6   7
        15 14 12 16
         */
        int[][] arr2 = {{3,1},{4,2}};
        /*
        15 13  2  5
        14  3  4  1
        12  6  8  9
        16  7 10 11
         */
        print(arr1);
        System.out.println();
        print(arr2);
        System.out.println();
        rotate(arr1);
        print(arr1);
    }

    //思路:观察旋转的规律，从第一行开始，将第一行的每个元素进行选择90度的两两交换，交换完后会发现最外的所有元素都处于正确位置
    //然后按照对角线，再从matrix[1][1]开始按照之前一样的规律处理会发现内层的元素也处于正确位置，直到所有元素处理完成
    public static void rotate(int[][] matrix) {
        int length = matrix.length;
        if (length == 1) {
            return;
        }
        int temp = 0;
        //大于1才有必要继续旋转
        for (int i=0;i<length/2;i++) {
            //从第1行开始i=0,交换完后只能对最外层的元素旋转完毕，还有内层的元素待处理，length长度共有length/2层需要处理
            for(int j=i;j<length - i - 1;j++) { //这里随着外层的元素处理完毕，内层的元素开始处理，长度也会明显减少。因此是length - i - 1
                //第一次交换
                temp = matrix[j][length - 1 - i];
                matrix[j][length - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;

                //第二次交换
                matrix[i][j] = matrix[length - 1 - j][i];
                matrix[length - 1 - j][i] = temp;

                //第三次交换
                matrix[length - 1 - j][i] = matrix[length - 1 - i][length - 1 - j];
                matrix[length - 1 - i][length - 1 - j] = temp;
            }

        }

    }


    //打印二维数组
    public static void print(int[][] matrix) {
        for (int i =0;i<matrix.length;i++) {
            for (int j=0;j<matrix[i].length;j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
