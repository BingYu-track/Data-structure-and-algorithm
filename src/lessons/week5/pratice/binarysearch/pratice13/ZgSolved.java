package lessons.week5.pratice.binarysearch.pratice13;

/**
 * @version 1.0
 * @Description: 搜索二维矩阵--争哥解法
 * @author: bingyu
 * @date: 2022/8/1
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int target = 3;
        boolean result = searchMatrix(matrix, target);
        System.out.println(result);
    }


    /*
    争哥思路: 和我思路是一样的
    */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int low = 0, high = m * n - 1;
        int mid, midValue;
        while (low <= high) {
            mid = (low + high) / 2;
            midValue = matrix[mid/n][mid%n]; //通过mid计算得到对应二位数组的行下标和列下标
            if (target == midValue) {
                return true;
            } else if (target < midValue) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

}
