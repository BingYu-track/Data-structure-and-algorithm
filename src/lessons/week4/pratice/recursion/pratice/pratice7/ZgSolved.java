package lessons.week4.pratice.recursion.pratice.pratice7;

/**
 * @version 1.0
 * @Description: 数值的整数次方--争哥解法 (需要复习)
 * @author: bingyu
 * @date: 2022/5/25
 */
public class ZgSolved {

    public static void main(String[] args) {
        double result = myPow(-1,2147483647);
        System.out.println(result);
    }

    /*
     TODO 争哥解法思路: 推荐!
          递推公式： n是偶数的情况下--f(x,n) = f(x,n/2) * f(x,n/2)
                   n是奇数的情况下--f(x,n) = f(x,n/2) * f(x,n/2) * x

          时间复杂度分析: O(logn)
     */
    public static double myPow(double x, int n) {
        if (n >= 0) {
            return rPow(x, n);
        }else { //n次幂小于0  -2^31~2^31-1  = -2147483648 ~ 2147483647
        //return 1 / rPow(x, -1*n); 注意不能这样写，因为当x=2,n=-2147483648 ，再转换成正数就rPow(x,2147483648),n就越界了,
       // 因此我们把rPow(x, -1*n) 凑成 rPow(x, -1*(n+1))*x  ，x的-1*(n+1))次幂再乘以x,同样等于x的-1*n次幂，但是却会避免越界
       // 此时n=-2147483648时就成了rPow(x,-1*-21474836477)*x = rPow(x,21474836477) * x，这样就不会越界了
            // x=2.00000 n=-2147483648
            return 1/(rPow(x, -1*(n+1))*x);
        }
    }

    public static double rPow(double x, int n) {
        if (n == 0) return 1; //终止条件，不断进行n/2直到n==0
        double halfPow = rPow(x, n/2);
        if (n % 2 == 1) { //如果n次幂是奇数
            return halfPow * halfPow * x;
        } else { //n次幂是偶数
            return halfPow * halfPow;
        }
    }
}
