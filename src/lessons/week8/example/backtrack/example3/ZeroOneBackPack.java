package lessons.week8.example.backtrack.example3;

/**
 * @version 1.0 0-1背包问题
 * @Description: 假设有一个背部，背包可承载的最大重量是Wkg。现在有n个物品，每个物品的重量不等，并且不可分割。
 *  我们期望选择几件物品，装到背包中。在不超过背包最大重量限制的前提下，求背包中物品的最大重量。
 * @author: bingyu
 * @date: 2023/1/17
 */
public class ZeroOneBackPack {

    public static void main(String[] args) {
        ZeroOneBackPack pack = new ZeroOneBackPack();
        int[] items = {54,32,200,43,767,32,21};
        pack.bage(items,1000);
        System.out.println(maxW);
    }

    public static int maxW = Integer.MIN_VALUE; //用来存储背包中物品的最大重量

    /**
     *
     * @param items 表示n个不同重量的石块，用数组存储表示
     * @param w 背包能存储的最大重量
     * @return
     */
    public int bage(int[] items,int w) {
        backtrack(items,0,0,w);
        return maxW;
    }

    /**TODO:这题很难，需要重点理解
     * 争哥解题思路: 因为有n个石块，因此我们每次可以选择n个石头中的一个，然后在此基础上有2种选择，放入或者不放入；
     * 列出所有可能性后，再将所有可能性的重量加起来；取其中最大值但不大于背包重量的那一个解
     * 第一次全部不选，第二次只选最后一个石块放入；第三次只选倒数2个石块放入；......直到所有石块都放入
     * @param items 选择列表，表示石头放还是不放,有n个
     * @param k  阶段，表示第几次选择
     * @param cw 路径，记录已选择物品的总重量
     * @param w 表示终止条件
     */
    private void backtrack(int[] items, int k, int cw, int w) {
        if (cw == w || k == items.length) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        //开始做选择
        backtrack(items,k+1,cw,w);//不装当前石头，跳过当前阶段，直接到下一个石头，不断执行最终到最后一个石头后返回
        //执行到这里说明前面所有石头都没装，现在开始装倒数第一个石头，然后继续递归不断装倒数第2个，第3个......
        if (cw + items[k] <= w) { //不能大于背包重量
            backtrack(items,k+1,cw+items[k],w); //装当前石块
        }
    }

}
