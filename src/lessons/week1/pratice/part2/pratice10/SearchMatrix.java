package lessons.week1.pratice.part2.pratice10;

/**
 * @version 1.0 搜索二维矩阵
 * @Description: 编写一个高效的算法来搜索 m x n 矩阵 matrix中的一个目标值 target 。该矩阵具有以下特性
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列.
 *
 * 示例 1：20没在这里
 * 1   4  7 11 15
 * 2   5  8 12 19
 * 3   6  9 16 22
 * 10 13 14 17 24
 * 18 21 23 26 30
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例 2：
 *
 * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * n >=1 , m <= 300
 * -10^9<= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9<= target <= 10^9
 *
 * @author: bingyu
 * @date: 2022/1/20
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
//        int target = 30;
        //int[][] matrix = {{5,6,9},{8,10,11},{11,14,18}};
        int target = 9;
        boolean b = searchMatrix(matrix, target);
        System.out.println(b);
    }

    /*
      新思路:1.从行开始比较行的最大值和行的最小值，如果目标值大于行的最大值或者小于行的最小值，将其行记录下来并排除，
              会得到一个缩小范围的矩阵。
            2.再将之前检测行得到下来的缩小矩阵中，从列开始比较列的最大值和列的最小值,如果目标值大于列的最大值或者小于列的最小值，将其对应的列排除，
            最后剩下的矩阵范围里面取遍历目标值即可
            重复上述操作直到

     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length; //m行
        int n = matrix[0].length; //n列
        //获取最小值和最大值
        int min = matrix[0][0];
        int max = matrix[m-1][n - 1];
        if (target < min) return false; //目标值小于第一行的最小值
        if (target > max) return false; //目标值大于最后一行的最大值
        int rowStart = 0; //记录矩阵起点的行下标
        int colStart = 0; //记录矩阵起点的列下标
        int rowEnd = m - 1; //记录矩阵终点的行下标
        int colEnd = n - 1; //记录矩阵终点的列下标
        while ((rowStart != rowEnd) && (colStart != colEnd)) { //m或者n
            //1.先对行进行检测并排除不满足的行
            for (int i=rowStart;i<=rowEnd;i++) {
                int rowMin = matrix[i][colStart]; //得到当前行中的最小元素值
                int rowMax = matrix[i][colEnd]; //得到当前行中的最大元素值
                if (target == rowMin || target == rowMax) return true; //如果目标值等于当前行的最大值或者最小值
                if (target > rowMax) {
                    rowStart = i + 1; //如果当前行的最大值还要比目标值小，将起点的行下标向下移动
                }
                if (target < rowMin) {
                    rowEnd = i - 1; //将终点的行下标向上移动
                    break; //如果当前行的最小值都比目标值大，那么下一行的所有值肯定都比目标值大，结束循环,那么这里就能确定目标值只可能在rowStart~rowEnd这段行的范围里
                }
            }
            if (rowStart > rowEnd) return false; //如果起点的行下标还大于终点的行下标，说明没有目标值(rowStart > rowEnd说明所有行都检查完了)

            //2.对列进行检测并排除列
            for (int j=colStart;j<=colEnd;j++) {
                int colMin = matrix[rowStart][j]; //得到当前列中的最小元素值，注意由于前面列已经检测过了，确定了目标值所在行的范围，因此这里用
                int colMax = matrix[rowEnd][j];  //得到当前列中的最大元素值
                if (colMax == target || colMin == target) return true;
                if (target > colMax) { //
                    colStart = j + 1; //如果当前列的最大值都比目标值小，那么将起点的列下标向右移动一位
                }
                if (target < colMin) {
                    colEnd = j - 1; //将终点的列下标向左移动一位
                    break; //如果当前列的最小值都比目标值大，那么下一列的所有值肯定都比目标值大，结束循环，那么到这里就能确定目标值只可能在colStart~colEnd这段列的范围里
                }
            }
            if (colStart > colEnd) return false; //colStart > colEnd说明所有列都检查完了
        }
        //执行到这里说明有列或者行处于同一个下标，开始遍历每
        if (rowStart == rowEnd) {
            for(int i=colStart;i<=colEnd;i++) {
                if (target == matrix[rowStart][i]) return true;
            }
        }
        if (colStart == colEnd) {
            for (int j =rowStart;j<=rowEnd;j++) {
                if (target == matrix[j][colStart]) return true;
            }
        }
        return false;
    }

    /**
     *
     */
    public static void  fail() {
        int[][] max = {{1,2,3},{2,3,4}};
        boolean b = searchMatrix(max, 7);
        System.out.println(b);
    }








}
