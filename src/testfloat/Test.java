package testfloat;

import com.sun.tools.javac.util.Bits;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 浮点数在计算机的表示机制
 * @author: bingyu
 * @date: 2021/12/6
 */
public class Test {


    public static void main(String[] args) {
        float f = 0.75f;
        String computeFloat = getComputeFloat(f);
        System.out.println("浮点数" + f + " 在计算机底层的表示:" +computeFloat);
        //0.75   0-01111110-10000000000000000000000
        //0.125  0-01111100-00000000000000000000000
        //0.1 0-01111011-10011001100110011001101

    }

    /**
     * 想要得到十进制小数在计算机中底层的单精度浮点数形式步骤：
     * 1.先将十进制小数转为二进制小数(这里的二进制小数是纸面上的二进制数的形式，计算机内部是无法使用的)
     * 2.符号位，如果是正数，为0，负数为1
     * 3.计算指数部分：因为浮点数指数部分只有8位，最大值为11111111=255,取一半将其作为0，即01111111=127作为指数0的标准，大于127的是正，小于127的是负
     * 4.计算尾数部分:前面得到的二进制小数将其左移或右移，使其二进制整数部分为1，第2位以及以上的数位均为0即可(这样做是为了消除第2位以上的数位)，
     * 然后省略第1位的1不保存，余下的数值就是尾数部分(由于第1位必须是1，因此省略该部分后就节省了一个数据位)
     *
     * 示例：十进制0.5转换成计算机中底层的单精度浮点数形式
     * 1. 十进制0.125转换成二进制为0.001
     * 2. 0.125是正数所以符号位数值是0
     * 3. 0.001要右移3位，变成1.0000,因此指数是-3，对应指数位是-3=124=01111100
     * 4. 1.0000去掉整数部分就是000000......
     * 得到0-01111100-00000000000000000000000
     */

    /**
     * 将单精度浮点数转换为计算机底层数据返回
     * @param number 单精度浮点数
     * @return
     */
    private static String getComputeFloat(float number) {
        Unsafe unsafe = getUnsafe();
        //分配内存
        long buff = unsafe.allocateMemory(64); //分配64位的内存，返回内存地址
        long dataAdress = unsafe.allocateMemory(64); //分配64位的内存，返回内存地址
        char [] s = new char[34]; //单精度是32位，加上2个分隔符就是34
        try {
            //将0.75以单精度浮点数的形式存储在dataAdress地址中
            unsafe.putFloat(dataAdress,number);
            int i;

            //把dataAdress地址里的数据，复制到4字节(32位)长度到整数变量buff中，以逐个提取出每一位
            //参数1:待复制的数据地址  参数2:目标地址   参数3:要复制的比特数
            unsafe.copyMemory(dataAdress,buff,32);
            //再根据buff的内存地址获取当前值
            int buffInt = unsafe.getInt(buff);
            float buffFloat = unsafe.getFloat(buff);
            long buffValue = unsafe.getLong(buff);
            System.out.println("buffInt: " + buffInt);
            System.out.println("buffFloat: " + buffFloat);
            System.out.println("buffValue: " + buffValue);

            // 逐一提取出每一位
            for (i = 33; i >= 0; i--) {
                //因为我们要在第1位后面和第9位后面的加上破折号来区分三部分，因此要在遇到i=1和i=10这个位置赋值破折号区分
                if(i == 1 || i == 10) { //
                    // 加入破折号来区分符号部分、指数部分和尾数部分。
                    s[i] = '-';
                } else {
                    // 为各个字节赋值'0' 或者'1'。
                    if (buffValue % 2 == 1) { //TODO:这里不太理解
                        s[i] = '1';
                    } else {
                        s[i] = '0';
                    }
                    buffValue /= 2; //自除2 TODO:这里不太理解
                }
            }
            s[34] = '\0';

        }catch(Exception e){

        }finally {
        }
        //0.75对应的二进制是0.1100;尾数部分使用"将小数点前面的值固定为1"的正则表达式得到1.10000000
        StringBuilder sb = new StringBuilder();
        for (char c : s) {
            sb.append(c);
        }
        return sb.toString(); //0-01111110-10000000000000000000000
    }

    private static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }

}
