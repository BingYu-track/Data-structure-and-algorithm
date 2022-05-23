package lessons.week4.pratice.recursion.example.example1;

/**
 * @version 1.0 例题1. 细胞分裂
 * @Description: 1个细胞的生命周期是3小时，1小时分裂一次。求n小时后，容器内有多少净细胞(分裂完、死亡之后)？细胞会在每个小时的开始分裂、死亡，并且
 *               先分裂后死亡
 * @author: bingyu
 * @date: 2022/5/20
 */
public class CellDivision {

    public static void main(String[] args) {
        int cells = f(4);
        System.out.println(cells);
    }

    /*  TODO 注意题目中的含义，"细胞会在每个小时的开始分裂和死亡"，因此第一个小时的开始细胞个数应该是2个，而不是1个,我们用f(1)=2来表示
        递推公式: 0: 1
                1: 2
                2: 2+2 = 4
                3: 4+4 = 8
                4: 8+8 - 2 = 14
  递推公式:f(n) = 2f(n-1) - g(n-3)
        第n个小时后的细胞数量 = 第n-1小时后的细胞数加上其在第n小时开始时分裂数即2f(n-1) - 在第n-3个时间节点"分裂的细胞数量"
        而在第n-3个时间节点分裂的细胞数量 = 取决于第n-4个小时的细胞数量。
        因此得到最终的递推公式是:f(n) = 2f(n-1) - f(n-4)
        终止条件:  f(1)=2、f(2)=4、f(3)=8

        时间复杂度:O(2^n)--就是求递归树的总节点
        空间复杂度:O(n)--就是求递归树的深度
    */
    public static int f(int n) {
        if (n == 0) return 1;
        if (n == 1) return 2;
        if (n == 2) return 4;
        if (n == 3) return 8;
        return 2*f(n-1) - f(n-4);
    }

}
