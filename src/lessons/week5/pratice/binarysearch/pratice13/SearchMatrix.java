package lessons.week5.pratice.binarysearch.pratice13;

/**
 * @version 1.0 搜索二维矩阵
 * @Description: 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 示例 1：
 * 1  3   5  7
 * 10 11 16 20
 * 23 30 34 60
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 1  3   5  7
 * 10 11 16 20
 * 23 30 34 60
 * 输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * 输出：false
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 *
 * @author: bingyu
 * @date: 2022/7/31
 */
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        boolean result = searchMatrix(matrix, target);
        System.out.println(result);
    }

    /**         0  1  2  3   4  5  6  7  8  9  10 11
     * 我的思路: 00 01 02 03、10 11 12 13、20 21 22 23 将二维数组所有的元素按从小到大排列，按照普通二分去查询，重点是将mid转换成二维数组的下标
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了59.61%的用户
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int lines = matrix.length; //行数
        int cols = matrix[0].length; //列数
        int size = lines * cols;
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            //TODO 重点是将mid转换成对应的二维数组的下标
            int lx = mid / cols; //获取mid所在的行数
            int cx = mid - lx * cols; //获取mid所在的列数
            if (matrix[lx][cx] == target) {
                return true;
            }else if (matrix[lx][cx] < target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return false;
    }


}
