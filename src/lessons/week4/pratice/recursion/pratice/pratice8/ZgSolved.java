package lessons.week4.pratice.recursion.pratice.pratice8;

/**
 * @version 1.0
 * @Description: 递归乘法--争哥解法
 * @author: bingyu
 * @date: 2022/5/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        int result = multiply(23, 5);
        System.out.println(result);
    }


    /*
     TODO 推荐！ 争哥解法思路: 跟上一个题目思路一样，先求一半的和，后一半就不用算了，直接加
     如果B是偶数 f(A,B) = f(A,B/2) + f(A,B/2)
     如果B是奇数 f(A,B) = f(A,B/2) + f(A,B/2) + f(A,1)
     */
    public static int multiply(int A, int B) {
        if (A==1) return B;
        if (B==1) return A;
        int res = 0;
        if(A > B) { //用大的值作为乘数，小的值作为被乘数，这样执行次数会相对少一些
            res = mul(A,B);
        }else {
            res = mul(B,A);
        }
        return res;
    }

    /*
    执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
    内存消耗：38.1 MB, 在所有 Java 提交中击败了63.65%的用户
     */
    public static int mul(int A, int B) {
        if (B == 1) {
            return A;
        }
        int halfB = mul(A,B >> 1); //求一半B数目的和，>>右移符号，相当于除以2
        if (B % 2 == 1) { //如果B是奇数
            return halfB + halfB + A;
        }else {
            return halfB + halfB; //如果B是偶数
        }
    }


}
