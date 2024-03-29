package lessons.week4.pratice.recursion.pratice.pratice7;

/**
 * @version 1.0 数值的整数次方
 * @Description: 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题(指不考虑溢出)。
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/2^2 = 1/4 = 0.25
 *
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 *
 * @author: bingyu
 * @date: 2022/5/25
 */
public class MyPow {

    public static void main(String[] args) {
        double result = myPow(2,-2);
        System.out.println(result);
    }

    /*
       n是偶数时 f(x,n) = f(x,n/2) * f(x,n/2)
       n是奇数时 f(x,n) = f(x,n/2) * f(x,n/2) * x
     */
    public static double myPow(double x, int n) {
        if (n>=0) { //正数次幂
            return pow(x,n);
        }else { //负数次幂
            return 1 / (pow(x,(-n)-1) * x); //负数次幂的话，将其转为正数，得到结果后取倒数即可，但是要注意当n=-2^31会数值越界，需要进行特殊处理下
        }
    }

    private static double pow(double x, int n) {
        if (n == 0) return 1;
        double half = pow(x, n / 2);
        if (n % 2 == 1) { //n是奇数
            return half * half * x;
        }else {
            return half * half;
        }
    }

    /*
    递归解法:
      x^n = x*x
    */
    public static int time = 0; //用来保存执行的次数
    public static double myPow2(double x, int n) { //n表示n次冥
        if (n == 0 || x == 1) return 1; //次幂是0或者数字是1，直接返回1
        double result = x; //存储计算结果
        if (n > 0) { //如果次幂大于0，从1开始计数，否则从-1开始计数
            time = 1;
        }else {
            time = -1;
        }
        result = pow(x, n, result); //开始计算次方
        while (n>0 && (time>n || time<=0)) {
            result = result / x;
            time--;
        }
        while (n<0 && (time<n || time>=0)) {
            result = result / x;
            time++;
        }
        if (n < 0) result = 1 / result; //如果n次幂是负数，那么就是1/result
        return result;
    }


    private static double pow(double x, int n, double result) {
        //这里n>0而且time<=0
        if ((n>0 && (time>=n || time<=0)) || (n<0 && (time<=n || time>=0))) { //终止条件:一旦执行的次幂超过n就终止，后面需要进行返回处理
            return result;
        }
        result = result * result;
        time += time;
        return pow(x,n,result);
    }


    /*
    我自己的思路
     迭代法: 注意n取int范围的最小值，除的时候就会导致超出int边界范围;另外因为数据规模是n次幂，因此该方法
     的时间消耗也是指数级的，我们需要想办法降低执行的次数，否则会超时,其难点再与处理越界问题！
     TODO: 注意 -1^(-10) = 1/(-1^10),-1的负10次幂就是-1除以自己10次，此时幂是偶数，结果就成了正数
           核心思路是 通过自己乘以自己快速逼近结果值
    */
    public static double myPow3(double x, int n) {
        if (n == 0 || x == 1) return 1;
        double result = x; //结果
        double time = n; //要执行的次数
        int count = 0;  //用来控制最高遍历的次数，因为int的范围是-2^31 ~ 2^31-1，我们最多遍历不超过31次，就可以避免越界问题
        if (n < 0) { //负次幂
            int i = -1;
            for (;i>time && count<31;i+=i) {
                result = result * result; //通过自己乘以自己快速逼近结果值，而不是1个1个相乘
                count++;
            }
            while (i<time || i>0) { //i>0说明越界，超过了int的最小范围值
                result = result / x;
                i++;
            }
        }else { //正次幂
            int i = 1;//表示当前执行的次数
            for (;i<time && count<31;i+=i) { //i加加后，后面会越界变为负数
                result = result * result;
                count++;
            }
            while (i>time || i<0) { //i<0说明越界，超过了int的最大范围值，赶快除回来并减掉多余的幂次数
                result = result / x;
                i--;
            }
        }
        //执行到这里i有可能因为越界变成负数
        if (n < 0) result = 1 / result;
        return result;
    }

}
