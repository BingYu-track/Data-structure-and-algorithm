package lessons.week10.dp.example.example1;

/**
 * @version 1.0 0-1背包问题 (使用动态规划)
 * @Description: 假设有一个背部，背包可承载的最大重量是Wkg。现在有n个物品，每个物品的重量不等，并且不可分割。
 *  我们期望选择几件物品，装到背包中。在不超过背包最大重量限制的前提下，求背包中物品的最大重量。
 *  物品重量: {2,2,4,6,3}
 *  背包最大承重: 9
 * @author: bingyu
 * @date: 2023/4/25
 */
public class ZeroOneBackPack {

    public static void main(String[] args) {
        ZeroOneBackPack pack = new ZeroOneBackPack();
        int[] items = {2,2,4,6,3};
        pack.bage(items,items.length,9);
    }


    /* 定义一个函数f(a,b) a表示处理的几个石头的下标，b表示放入背包里石头的总重量，以{2,2,4,6,3}数据为例

                      0 f(0,0)--从0开始
                   /           \
              f(1,0)--不放入    f(1,2)--选择放入了第1个石头，背包重量为2
              /     \          /    \
           f(2,0)  f(2,2)   f(2,2) f(2,4)--选择放入了第2个石头，背包重量为4
          /    \
        f(3,0) f(3,4).................
       TODO: 从上面可以看到存在重复的状态f(2,2)，这2个相同的状态后面的分支肯定也是一摸一样的，因此我们
        需要将这种每层重复的状态合并，只记录不同的状态，然后基于上一层的状态集合，来推导下一层的状态集合
        (i,cw)表示一个状态，表示第i个物品决策完之后背包重量为cw。cw的取值为0~W(W表示背包的承载重量)。
        通过合并每一层重复的状态，这样就能保证每一层状态个数不会超过W+1个(W表示背包的承载重量)，因为
        每一层只记录不同的状态，最多的状态只有W+1个。于是，我们就可以避免回溯算法递归树中每层状态个
        数的指数级增长。可以用哈希表来去重，常规来说都是用一一映射的数组来表示如下:

        物品总重量不会超过w，我们用boolean类型的二维数组dp[n][w+1]来记录每层可以达到的不同状态。
        第0个物品的重量是2，要么装入背包，要么不装入背包，决策完之后，对应背包的状态有两种:
        背包中物品的总重量0或者2。我们用dp[0][0]=true和dp[0][2]=true来表示这两种状态。
        第1个物品的重量也是2，在第0个物品决策完之后，背包中物品的重量有两种情况: 0或者2，是基于第0个物品决策完之后的状态；在第1
        个物品决策完之后，对应背包中物品总重量分别又三种情况: 0(0+0)、2(0+2 or 2+0)、4(2+2)。我们用dp[1][0]=true，dp[1][2]=true
        dp[1][4]=true来表示这三种状态。以此类推，基于第i个物品决策完之后的状态来推导第i+1个物品决策完之后的状态。

           0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |
         |---|---|---|---|---|---|---|---|---|---|
        0| 1 | 0 | 1 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |  下标为0的物品重量为2
        -|---|---|---|---|---|---|---|---|---|---|
        1| 1 | 0 | 1 | 0 | 1 | 0 | 0 | 0 | 0 | 0 |  下标为1的物品重量为2
        -|---|---|---|---|---|---|---|---|---|---|
        2| 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 |  下标为2的物品重量为4
        -|---|---|---|---|---|---|---|---|---|---|
        3| 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 | 1 | 0 |  下标为3的物品重量为6
        -|---|---|---|---|---|---|---|---|---|---|
        4| 1 | 0 | 1 | 1 | 1 | 1 | 1 | 1 | 1 | 1 |  下标为4的物品重量为3
        -|---|---|---|---|---|---|---|---|---|---|
        上面填的表格就是通过上一层的状态来确定下一层的状态
     */

    /**
     * 动态规划
     * @param weight 物品重量的二维数组
     * @param n 物品的数量
     * @param w 背包承载的最大重量
     * @return
     */
    public int bage(int[] weight,int n, int w) {
        boolean[][] dp = new boolean[n][w+1]; //默认值false，这里n就相当于上面表格的行的数量，w+1就是列的数量
        //第0行上面没有依赖，所以需要我们进行初始化
        dp[0][0] = true;  //表示下标为0的物品没有放入
        if (weight[0] <= w) {
            dp[0][weight[0]] = true; //表示下标为0的物品放入
        }
        for (int i = 1;i < n;i++) { //开始处理下标为1到后面的所有物品，这里就是相当于上面表格的行数
            for (int j = 0;j <= w;j++) { //j是w+1个，相当于上面表格的列数，表示重量
                //遍历第i-1个阶段的所有可达状态，这里i-1就是上一层，我们需要根据上一层来得到当前层的状态
                if (dp[i-1][j] == true) { //可达
                    dp[i][j] = true; //由于dp[i-1][j]可达，那么下一层同样的列肯定可达，因此将dp[i][j]设为true
                    if (j + weight[i] <= w) { //这里j是上一层对应的重量，如过上面对应重量加上当前层物品的重量不超过背包最大承重即j+weight[i]，则可达
                        dp[i][j + weight[i]] = true; //推导第i层状态
                    }
                }
            }
        }
        for (int i = w;i >= 0;i--) { //这里i是表格中的列，表示重量
            if (dp[n-1][i] == true) return i; //从最后一层的最后一个重量开始遍历，如果可达，那么这个位置就是最大的重量
        }
        return 0;
    }

}
