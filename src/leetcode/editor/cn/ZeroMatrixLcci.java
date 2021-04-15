//编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。 
//
// 
//
// 示例 1： 
//
// 输入：
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出：
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
// 
//
// 示例 2： 
//
// 输入：
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出：
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//]
// 
// Related Topics 数组 
// 👍 29 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

//Java：零矩阵
public class ZeroMatrixLcci{
    public static void main(String[] args) {
        Solution solution = new ZeroMatrixLcci().new Solution();
        Solution2 solution2 = new ZeroMatrixLcci().new Solution2();
        // TO TEST
        int [] arr1 = {0,2,3,5};
        int [] arr2 = {4,3,1,4};
        int [] arr3 = {1,1,1,4};
        int [] arr4 = {1,2,1,3};
        int [] arr5 = {4,0,1,1};
        int [][] arr = {arr1,arr2,arr3,arr4,arr5};
        //solution.setZeroes(arr);  //我的解法  执行耗时:5 ms,击败了6.74% 的Java用户   内存消耗:40.3 MB,击败了5.62% 的Java用户
        solution2.setZeroes(arr);  //其他人的优秀解法
        for (int[] a :arr) {
            System.out.println(Arrays.toString(a));
        }
    }

class Solution2 {
    //其它人的解法
    //弄2个布尔类型的数组来记录哪些行和列需要置0
    public void setZeroes(int[][] matrix) {
        int m = matrix.length; //矩阵的行数
        if (m==0) {
            return;
        }
        int n = matrix[0].length; //矩阵的列数
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];
        for (int i=0;i<matrix.length;i++) {
            for (int j=0;j<matrix[i].length;j++) {
                if (matrix[i][j] == 0) { //遇到为0的元素就将当前的行和列记录到两个布尔数组中
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        //这段代码是比较难理解
        for (int i=0;i<rows.length;i++) {
            for (int j=0;j<cols.length;j++) {
                if (rows[i] || cols[j]) { //可以这样理解，如果只有rows[i]是true，那么后面列循环就是把这行的所有元素置为0，
                    matrix[i][j] = 0; //i是行，j是列，只要涉及到有i或者是j是要置0的，不管是[i][...]还是[...][j]都必须置0
                }
            }
        }


    }
}
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public void setZeroes(int[][] matrix) {
        Position[] poss = new Position[matrix.length * matrix[0].length]; //记录元素为0的位置
        //寻找0元素位置并记录
        findZeroPosition(matrix,poss);
        //置0
        setAllZeroes(matrix,poss);
    }

    /**
     *
     * @param matrix 二维数组
     * @param poss 记录0元素的数组
     */
    private void setAllZeroes(int[][] matrix, Position[] poss) {
        //开始置0操作
        for(int i=0;i<poss.length;i++){
            Position pos = poss[i];
            if (pos!=null) {
                int x = pos.getX();
                int y = pos.getY();
                for (int j=0;j<matrix[x].length;j++) { //将0元素所在位置的一维数组都置为0
                    matrix[x][j] = 0;
                }
                for(int j=0;j<matrix.length;j++) {
                    for (int k=0;k<matrix[j].length;k++){
                        if (k == y) {
                            matrix[j][k] = 0;
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * @param matrix 二维数组
     * @param poss 记录0元素的数组
     */
    public void findZeroPosition(int[][] matrix,Position[] poss) {
        int count = 0;
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[i].length;j++){
                if (matrix[i][j] == 0) {
                    Position pos = new Position(i,j);
                    poss[count] = pos;
                    count++;
                }
            }
        }
    }



    class Position {
        private int x; //0元素所在一维数组的位置
        private int y; //0元素所在二维数组的位置

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}