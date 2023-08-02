package lessons.week12.bitwise;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 位运算总结
 * @author: bingyu
 * @date: 2023/7/12
 */
public class Bitwise {


    /*
     在计算机中，所有数据底层都是用二进制表示的，操作二进制位的方法叫做位运算，
     基础的位运算包含: 与、或、异或、取反、左移、右移。
     除此之外，还有两个常用操作: 十进制转二进制，二进制转十进制

     1、与(&): 判断某位是否为1、设置某位为0、判断奇偶
     2、或(|): 设置某位为1
     3、异或(^): 反转位，a^a=0 ，两位不同就是1，相同是0 (寻找出现一次的数字)
     4、取反(~): 按位取反
     5、左移(<<): 乘以2
     6、右移(>>): 除以2 (二分查找)
     7、十进制 --> 二进制
     8、二进制 --> 十进制

     byte类型占8位； 1个字节 8位
     short类型: 2个字节  16位
     int 类型: 4 个字节  32位
     long 类型: 8个字节  64位
     float 类型: 4个字节 32位
     double 类型: 8个字节 64位
     boolean类型: 1个字节 8位
     char 类型: 2个字节 16位
    */


    public static void main(String[] args) {
        int a = 242;
        Bitwise bw = new Bitwise();
        int[] res = bw.decimalToBinary2(a);
        System.out.println(Arrays.toString(res));
        int num = bw.binaryToDecimal2(res);
        System.out.println(num);
        //[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0]
    }

    /*
     十进制数转二进制数组
     TODO： 由于java的int类型是32位，因此需要创建长度为32的数组，然后我们将十进制数的每个位和1进行与操作，这样的
      目的是为了得到该十进制在二进制下每位的数，例如: 5的二进制是101，101 & 001得到1； 101 & 010得到0；101 & 100得到1，这样
      我们就能从10进制数得到10进制在二进制中每位的数字了！
    */

    //方法一: 目标数不断右移，使其二进制每位数能和1进行"与"运算，从而得到目标数每位的二进制数
    public int[] decimalToBinary(int num) {
        int[] bits = new int[32];
        int k = bits.length - 1;
        while (num!=0) {
            bits[k] = num & 1;
            k--; //移动到num在二进制的上一位
            num = num >> 1; //num进行右移达到的效果是一样的，使上一位的数字移动到和1同样的位置，从而方便进行"与"操作
        }
        return bits;
    }

    //方法二: 将1不断进行左移，使其能和目标数的每个位进行"与"运算得到目标数每位的二进制数

    //错误代码：
    public int[] decimalToBinary2ERROR(int num) {
        int[] bits = new int[32];
        int cons = 1; //用来作为工具数字的1,构造从2^0 ~ 2^31
        for (int i = bits.length - 1;i>=0;i--) {
            //注意这里与计算后得到的不是某位的数，而是好几位的数，因此不能直接赋值到bits数组里，例如110 & 010 = 10 得到的k是2，而不是第2位的1
            int k = num & cons;
            bits[i] = k;
            cons <<= 1; //将1左移
        }
        return bits;
    }

    //正确代码:
    public int[] decimalToBinary2(int num) {
        int[] bits = new int[32];
        int cons = 1; //用来作为工具数字的1,构造从2^0 ~ 2^31
        for (int i = 31;i>=0;i--) {
            /*
            TODO：
             由于110 & 010 = 10 不能直接得到1，我们可以这样理解，因为是目标元素每一位和同位的1做比较，
             例如10 & 10 = 10  110 & 100    1010 & 1000，可以发现与的都是当前位为1的数字，其它都会成为0，这样的话只要
             与计算不为0，就说明目标数字的当前二进制位为1，也就是对应下面这个if代码
            */
            if((num & cons) != 0) {
                bits[i] = 1;
            }
            cons <<= 1;
        }
        return bits;
    }


    /*
     二进制数组转为十进制数:
        TODO： 就是使用二进制转十进制的公式，例如--110对应的十进制：1*2^2 + 1*2^1 + 0*2^0 = 6
    */

    //方法1:
    public int binaryToDecimal(int[] bits) {
        int b = 0;
        int mask = 1; //从2^0 到 2^31
        for (int i = bits.length - 1;i >=0;i--) {
            b += bits[i] * mask; //就是用的公式:  110 对应的十进制：1*2^2 + 1*2^1 + 0*2^0 = 6
            mask <<= 1; //1不断向左移动一位，即乘以2
        }
        return b;
    }

    //方法2: 从高位开始，不断加低位
    public int binaryToDecimal2(int[] bits) {
        int c = 0;
        int length = bits.length;
        for (int i = 0;i<length;i++) { //11110010  这里数组开始部分是高位，尾部是低位
            //TODO： 每次乘以2，再加上后面的。然后整体结构再乘以2，再加上后面的，这样得到的就是对应的十进制数，
            // 可以这样理解，将一个字符串"576"转为数字576，那么取高位5*10+7得到57，后面再用57*10+6得到数字576，
            // 同样下面的也是这个思想，2进制的2就相当于上面的10，用"当前位数*进制数+后面一位数"不断执行累加就能得到整个数了
            c <<= 1; //左移1位，以242的二进制数11110010为例，从高位开始这里会一直为0，直到遇到二进制数的第一个1
            c += bits[i]; //(2+1) * 2
        }
        return c;
    }


}
