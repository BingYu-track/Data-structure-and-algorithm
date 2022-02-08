package lessons.week1.pratice.part2.pratice1;

/**
 * @version 1.0 零矩阵
 * @Description: 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * 示例 1：
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 示例 2：
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *  多举例，总结规律
 * @author: bingyu
 * @date: 2021/12/31
 */
public class SetZeroes {

    public static void main(String[] args) {
        //二维数组快速初始化
        int[][] arr = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        print(arr);
        System.out.println("--------------");
        setZeroes(arr);
        print(arr);
    }

    //我的解法思路:1.当二维数组中的一维只要有0，该一维数组必须全部置为0
    //2.用2个布尔的数组记录m和n有0的位置，然后分别根据该位置将其对应的元素置为0
    //缺点:行和列会重复扫描
    public static void setZeroes(int[][] matrix){
        int m = matrix.length; //代表m行
        int n = matrix[0].length; //代表n列
        boolean[] mb = new boolean[m]; //存储0在第几行的位置
        boolean[] nb = new boolean[n]; //存储0在第几列的位置
        for (int i =0;i<matrix.length;i++) {
            for (int j=0;j<matrix[i].length;j++) {
                if (matrix[i][j] == 0) {
                    mb[i] = true; //0所在行置为true
                    nb[j] = true; //0所在列置为true
                }
            }
        }

        //将对应的行全设为0
        for (int i =0;i<mb.length;i++) {
            if (mb[i]) {
                for (int j = 0;j<matrix[i].length;j++) {
                    if (matrix[i][j] !=0) matrix[i][j] = 0;
                }
            }
        }

        //将对应的列全设为0
        for (int i =0;i<nb.length;i++) {
            if (nb[i]) {
                for (int j =0;j<matrix.length;j++) {
                    if (matrix[j][i] !=0) matrix[j][i] = 0;
                }
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
