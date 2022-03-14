package lessons.week1.pratice.part2.pratice9;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Description: 螺旋矩阵，争哥解法
 * @author: bingyu
 * @date: 2022/1/20
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9},{10,11,12}};
        List<Integer> list = spiralOrder(matrix);
        System.out.println(list);
    }

    //争哥的思路和我一样，也是一层一层的打印，但是争哥的代码逻辑思路会比我自己的更加清晰，推荐使用争哥的方法
    public static List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        //首先得到四条边的行号或者列号，根据行号和列号进行遍历
        int left = 0; //左边的列号
        int right = n-1; //右边的列号
        int top = 0; //顶部的行号
        int bottom = m-1; //底部的行号
        while (left<=right && top <= bottom) {
            //1.从顶部元素从左到右开始遍历，top行不变，列从left到right
            for (int j = left; j <= right; ++j) {
                result.add(matrix[top][j]);
            }
            //2.从右边元素从上到下开始遍历，right列不变，行从top+1到底部bottom，因为我们需要去除顶部和右边重复的元素
            for (int i = top+1; i <= bottom; ++i) {
                result.add(matrix[i][right]);
            }
            //3.从底部开始从右到左进行遍历，bottom行不变，列从right-1到left
            if (top != bottom) { //TODO:注意这行代码判断是为了防止只有一行的矩阵重复打印2次行元素
                for (int j = right-1; j >= left; --j) {
                    result.add(matrix[bottom][j]);
                }
            }
            //4.从左边元素从下到上进行遍历,left列不变，行从bottom-1到top-1，因为我们需要去除顶部、底部和左边重复的元素
            if (left != right) { //TODO:注意这行代码判断是为了防止只有一列的矩阵重复打印2次列元素
                for (int i = bottom-1; i > top; --i) {
                    result.add(matrix[i][left]);
                }
            }
            //执行到这说明一层矩阵执行完，开始将对应的四边的行号和列号进行修改，从下一层矩阵开始进行遍历
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }

}
