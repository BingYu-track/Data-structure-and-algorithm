package lessons.week1.pratice.part2.pratice3;

/**
 * @version 1.0
 * @Description: 跳水板
 * @author: bingyu
 * @date: 2022/1/12
 */
public class ZgSolved {

    public static void main(String[] args) {

    }

    //争哥的解法思路:小争哥的解法是简单的穷举
    public static int[] divingBoard(int shorter, int longer, int k) {
        //特殊情况处理
        if (k == 0) return new int[0];
        if (shorter == longer) return new int[] {k*shorter};

        int[] result = new int[k+1];
        //i代表长板的个数,k-i就是短板的个数，k就是总共需要使用木板的个数
        for (int i = 0;i<=k;++i) {
            result[i] = i*longer + (k-i)*shorter;
        }
        return result;
    }
}
