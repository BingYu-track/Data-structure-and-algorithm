package lessons.week5.pratice.hashtable;

/**
 * @version 1.0
 * @Description: 哈希表总结
 * @author: bingyu
 * @date: 2022/8/7
 */
public class Summary {

    public static void main(String[] args) {
        BitMap bitMap = new BitMap(64); //创建一个能存储64位的位图
        System.out.println("是否包含37:"+bitMap.contains(37));
        bitMap.add(37);
        System.out.println("是否包含37:"+bitMap.contains(37));
        System.out.println("是否包含38:"+bitMap.contains(38));
        bitMap.add(38);
        System.out.println("是否包含38:"+bitMap.contains(38));
    }


    /**
     一.位图
     有1千万个整数，范围0~3千万，如何快速判定某个数据是否出现在这1千万个数据中?
     1.基于数组(循环遍历即可)
     2.基于有序数组(使用二分查找)
     3.基于哈希表
     4.基于位图(下标与数据一一映射)
     TODO: 位图，就是用每一位来存放某种状态，适用于大规模数据，但数据状态又不是很多的情况。通常是用来判断某个数据存不存在的。
     位图其实是用数组实现的，数组的每一个元素的每一个二进制位都可以表示一个数据在或者不在，0表示数据存在，1表示数据不存在。
     因为比特位只有两种状态，要么是0，要么就是1，所以位图其实就是一种直接定址法的哈希，只不过位图只能表示这个值在或者不在。

     顺序扫描数据集合，对于集合中的x，我们标记: arr[x]=true

     */
    public class Mapping{
        private int maxValue = 30000000;
        private boolean arr[] = new boolean[maxValue+1]; //初始化数组 TODO: 该数组就构成了一个位图，但问题是这样很浪费空间

        public void add(int data) { //添加元素时只需将元素对应的数组下标标记为true，表示这个元素已存在
            arr[data] = true;
        }

        public boolean contains(int data) { //查询时同样通过元素对应的数组下标判断是否存在
            return arr[data];
        }
    }

    /*
     TODO: 位图基础知识重要，需要掌握
     假设存储数字范围0~63的数据的位图: 需64个二进制位。boolean arr[64]很浪费空间(因为java的布尔类型是占1个字节，即8位)，
     因此，使用char[4];每一个char是2个字节，即包含16个二进制位，可以表示16个数据。
     自己实现一个位图
    */
    public static class BitMap {
        private char[] arr;
        private int nbits; //总共要存储的位数
        /*
          0      15  16     31  32     47 48      63
          |______|   |______|   |______|  |_______|
            arr[0]    arr[1]     arr[2]    arr[3]
         */

        public BitMap(int nbits) {
            this.nbits = nbits;
            this.arr = new char[(nbits-1)/16+1];
        }

        //设值
        public void add(int data) { //假设data=37  2
            if (data > nbits) return; //大于位数，直接返回
            int i = data / 16; //计算出data对应落下的数组是第几段(也就是对应数组的下标);假设data=37，那么就是落在arr[2]这段
            int bitIndex = data % 16; //计算出data对应的比特位上;假设data=37，那么就是落在arr[2]这段，第5位上
            //把对应比特位置设置为1
            arr[i] |= (1 << bitIndex);
            //TODO: 先将1左移5位，相当于0000 0000 0000 0001 => 0000 0000 0010 0000，然后再让arr[2]与0000 0000 0010 0000进行或运算
            // 就可以将arr[2]中的第5位从0设置为1
        }

        //设置为0
        public void reset(int data) {
            int i = data / 16; //
            int bitIndex = data % 16;
            arr[i] &= ~(1 << bitIndex); //把1左移余数位，再取反，然后和对应位置进行按位与
        }
        /*
            0000 0000 0010 0000 //存在
            1111 1111 1101 1111 //把1左移余数位，再取反
            _______________________
            0000 0000 0000 0000 //按位与，对应比特位变为0
        */

        //判断是否有值
        public boolean contains(int data) {
            if (data > nbits) return false;
            int i = data / 16; //
            int bitIndex = data % 16;
            //TODO: 数组对应位置的二进制和进行'与运算'，如果不为0，说明存在，为0说明不存在
            int result = arr[i] & (1 << bitIndex);
            if (result!=0) return true;
            return false;
        }
        /*
        0000 0000 0010 0000                           0000 0000 0000 0000 //对应位置不存在
        0000 0000 0010 0000                           0000 0000 0010 0000
        ______________________                       ______________________
        0000 0000 0010 0000                           0000 0000 0000 0000
        按位与的结果不为0，说明对应位置存在                 按位与的结果为0，说明对应位置不存在
         */


    }


    /*
     二: 布隆过滤器

       有1千万个数据，范围0~10亿，如何快速判定某个数据是否出现在这1千万个数据中?
       设置位图大小为4千万，通过哈希函数，让哈希值落在4千万范围内，比如:f(x)=x%n。其中，x表示
       数据，n表示位图的大小(n=4千万)

       减少存储空间带来的问题:
       哈希冲突

       哈希冲突导致误判:
       1.判定为存在，但是实际可能不存在该数据
        例如: 3123和1123哈希值相同,存储了3123，没有存储1123,查询1123也会返回true
       2.判定为不存在的，肯定是不存在(这个不会误判)

       TODO: 把这种存在冲突和误判的位图，叫做布隆过滤器
            比如在访问数据库查询数据前，先访问内存中的位图，如果经过位图判定数据不存在，就不需要继续访问数据库，这样就能减少数据库操作。
            布隆过滤器降低冲突概率的方法:
           用多个二进制位表示一个数据，a、b经过3个哈希函数得到的值都相等的概率肯定要小很多
    */

    /*
     三、哈希表相关的题型套路总结
        纯粹考察哈希表的题目不难，也不多。大部分情况下，哈希表只不过是一个小配角，配合解决其他算法类型的题目。用到
        哈希表的场景也比较明确，就是为了提高查找的效率，让查找的时间复杂度降为O(1)。
        布隆过滤器、位图作为哈希表的延伸，往往用在大数据处理中，如果面试中考到，大部分都是讲讲思路就够了，不会让候选人动手编程。
    */

}
