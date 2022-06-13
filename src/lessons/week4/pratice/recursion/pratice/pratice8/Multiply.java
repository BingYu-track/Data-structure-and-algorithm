package lessons.week4.pratice.recursion.pratice.pratice8;


/**
 * @version 1.0 递归乘法
 * @Description: 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 *
 * 示例1:
 *
 *  输入：A = 1, B = 10
 *  输出：10
 *
 * 示例2:
 *  输入：A = 3, B = 4
 *  输出：12
 *
 * 提示:
 * 保证乘法范围不会溢出
 *
 * @author: bingyu
 * @date: 2022/5/25
 */
public class Multiply {

    public static void main(String[] args) {
        int result = multiply(101, 2);
        System.out.println(result);
    }

    /*
       递归解法:
       递推公式: 就是将乘法转化为加法，递归的加，直到只剩1个返回，加法结果用成员变量res保存
       终止条件:被乘数被减去1
       执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
        内存消耗：38.5 MB, 在所有 Java 提交中击败了5.47%的用户
    */
    public static int res = 0;
    public static int multiply(int A, int B) {
        if (A==1) return B;
        if (B==1) return A;
        if(A > B) { //用大的值作为乘数，小的值作为被乘数，这样执行次数会相对少一些
            res = A;
            mul(A,B);
        }else {
            res = B;
            mul(B,A);
        }
        return res;
    }

    /*
    非递归,注意题目是正整数
    4*3
    A表示要计算的数字，B表示要加的次数
    */
    public static int mul(int A, int B) {
        if (B == 1) return A; //直到加法次数到1时直接返回
        res = res + A; //将乘法转化为加法
        B = B - 1; //前面加一次后，要加的次数需要减少
        return mul(A,B);
    }

}
