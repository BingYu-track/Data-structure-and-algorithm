package lessons.week12.psstatistics.pratice.pratice3;

/**
 * @version 1.0  面试题 05.03. 翻转数位 争哥解法
 * @Description:
 * @author: bingyu
 * @date: 2023/7/31
 */
public class ZgSolved {

    public static void main(String[] args) {
        ZgSolved zg = new ZgSolved();
        int num = -2;
        int res = zg.reverseBits(num);
        System.out.println(res);
    }


    public int reverseBits(int num) {
        if (num == 0) return 1; //数字为0，直接返回1
        int[] nums = new int[32];
        //1.这里开始转二进制
        for (int i = 0; i < 32; ++i) {
            nums[i] = (num & 1);
            num >>= 1; //右移
        }
        //2.统计每个0左边有多少个1
        int[] leftOneCounts = new int[32];
        int count = 0; //用来记录当前位置前面有多少个1
        for (int i = 0; i < 32; ++i) {
            leftOneCounts[i] = count; //这里count是i位置前面1的个数，不包含i
            if (nums[i] == 1) { //当前位置是1，count增加;否则当前位置是0，对应后面一位前面的1个数为0，因此count赋值为0
                count++;
            } else {
                count = 0;
            }
        }
        //3.统计每个0右边有多少个1
        int[] rightOneCounts = new int[32];
        count = 0;
        for (int i = 31; i >= 0; --i) {
            rightOneCounts[i] = count;
            if (nums[i] == 1) {
                count++;
            } else {
                count = 0;
            }
        }
        int ret = 0;
        for (int i = 0; i < 32; ++i) {
            if (ret < leftOneCounts[i] + rightOneCounts[i] + 1) {
                ret = leftOneCounts[i] + rightOneCounts[i] + 1;
            }
        }
        return ret;
    }

}
