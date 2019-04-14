package bitmap;

/**
 * @version 1.0
 * @Description: 简单的位图代码实现
 * @author: hxw
 * @date: 2019/3/21 17:46
 */
public class BitMap { // Java 中 char 类型占 16bit，也即是 2 个字节
    private char[] bytes;
    private int nbits; //这里代表的是存储的位(bit)数

    public BitMap(int nbits) {
        this.nbits = nbits;
        this.bytes = new char[nbits/16+1]; //由于一个char占据16位，而位图是用bit来存储，因此一个char可以表示16个数值。
                                           //举例：当我要创建一个空间大小为10bit的位图时，一个char就足已满足需要，因此这里才会有nbits/16这个计算
    }

    //插入值
    public void set(int k) {
        if (k > nbits){
            return;
        }
        int byteIndex = k / 16; //获取k在char数组的索引位置
        int bitIndex = k % 16; //获取k在char上的一个bit位置
        bytes[byteIndex] |= (1 << bitIndex); //bitIndex向左移动1位，然后对自己取
    }

    public boolean get(int k) {
        if (k > nbits){
            return false;
        }
        int byteIndex = k / 16;
        int bitIndex = k % 16;
        return (bytes[byteIndex] & (1 << bitIndex)) != 0;
    }

    public static void main(String[] args){
        BitMap bitMap = new BitMap(10);
        for (int i=0;i<=10;i++){
            bitMap.set(i);
        }
    }
}
