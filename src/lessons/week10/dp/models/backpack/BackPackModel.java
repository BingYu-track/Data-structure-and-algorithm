package lessons.week10.dp.models.backpack;

/**
 * @version 1.0
 * @Description: 动态规划经典背包模型
 * @author: bingyu
 * @date: 2023/5/10
 */
public class BackPackModel {

    /*
     要求解的问题:
     a).背包可装物品总重量的最大值是多少?
     b).是否能装满整个背包?
     c).正好装满背包最少需要多少物品?
     d).装满背包有多少种装法?

     1).0-1背包问题(就是要么放入1个物品，要么不放入)
     有n个物品，重量分别为weight[i](i=0~n-1)每个物品只有一个，选择其中一些物品装入背包，在不超过背包总
     重量w的前提下...
         a).背包可装物品总重量的最大值是多少?(代码见example1)
        TODO:
          使用booelan[][] dp来存储状态，状态转移方程: dp[i][j] = dp[i-1][j] || dp[i-1][j-weight[i]]
          然后再最后一行从后往前遍历，遇到的第一个为true的就是背包可装物品总重量的最大值(只能从最后一行，必须等所有阶段决策完后，才能知道哪些重量是可达的)

         b). 是否能装满整个背包?(代码见example3)
         TODO:
          使用booelan[][] dp来存储状态，状态转移方程: dp[i][j] = dp[i-1][j] || dp[i-1][j-weight[i]]
          然后直接看最后一行的最后一列是否为true即可，因为只要前面行能装满，那么这个重量必定继承到下面所有行的位置，因为只要下面行不放入物品，
          重量都一直在

         c).正好装满背包最少需要多少物品?(代码见example4)
         TODO：
          这个就需要我们用int[][] dp来存储状态，dp[i][j]表示在考察第i个物品时，到达重量j放了多少个物品
          状态转移方程为dp[i][j] = dp[i-1][j]
                     dp[i][j] = dp[i-1][j-weight[i]] + 1；
          dp[i][j]只可能来自这两个途径，因此从这两者中选择一个最少的得到: dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-weight[i]] + 1)

          d).装满背包有多少种装法?(代码见example4)
          TODO:
           这个就需要我们用int[][] dp来存储状态，dp[i][j]表示在考察第i个物品时，到达重量j有多少种装法
            状态转移方程为dp[i][j] = dp[i-1][j] + dp[i-1][j-weight[i]]；
            因为dp[i][j]只可能来自这两个途径，如果从dp[i-1][j]进来，也就是当前没放物品，此时dp[i][j]的装法就等于dp[i-1][j]，
            如果从dp[i-1][j-weight[i]]进来，也就是当前放入了物品到达了现在的重量，此时dp[i][j]的装法就等于dp[i-1][j-weight[i]]的装法,
            为什么不加上这次的放法即dp[i-1][j-weight[i]]+1？因为只有到达当前重量j的装法才算数，dp[i-1][j-weight[i]]的重量还不到j呢，
            但是在到达j之前就已经有dp[i-1][j-weight[i]]个装法，因此从dp[i-1][j-weight[i]]进到dp[i][j]的装法就是dp[i-1][j-weight[i]]；
            由于有两种途径，因此dp[i][j]的总装法就是dp[i-1][j] + dp[i-1][j-weight[i]]

     2).完全背包问题(这里不再是一次选择一个或者0个物品，而是一次可以选择放入k个物品，但是这个k有一个限制，就是不能超过背包承受的最大重量)
     有n个物品，重量分别为weight[i](i=0~n-1)，每个物品有无限多个，选择一些物品装入背包，在不超过背包
     总重量w的前提下... TODO： 完全背包问题代码见fullbackpack包
        a).背包可装物品总重量的最大值是多少?
        TODO：
         使用booelan[][] dp来存储状态，状态转移方程: 每次放入的物品数目可以从0放到k，直到背包装满为止
         状态转移方程: dp[i][j] = dp[i-1][j - k * weight[i]](0<=j - k * weight[i]<=w)放入第i个物品时，可以放0~k个物品，只要
         0~k中有一个满足，则说明dp[i][j]可达；所有物品考察完后，找最大值时，只需要从最后一行从后往前找到第一个为true的即可

        b).是否能装满整个背包?
        TODO：
          同样是使用booelan[][] dp来存储状态，状态转移方程: dp[i][j] = dp[i-1][j - k * weight[i]]
          只要最后一行的最后一列位置为true，说明能装满整个背包

        c).正好装满背包最少需要多少物品?
        TODO：
           使用int[][] dp来存储状态，初始化用Integer.MAX_VALUE
           状态转移方程: dp[i][j] = Math.min(dp[i][j] , dp[i-1][j - k * weight[i]] + k)

        d).装满背包有多少种装法?
        TODO：
           使用int[][] dp来存储状态
           状态转移方程: dp[i][j] = Sum(dp[i-1][j - k * weight[i]]) 为dp[i-1][j - k * weight[i]]求和

     3).多重背包问题(一次可以选择放入k个物品，这里有2个限制，限制1--不能超过背包承受的最大重量;限制2--1次放入的物品个数不能超过count[i])
     有n个物品，重量分别为weight[i](i=0~n-1)，每个物品有有限多个，个数分别为count[i](i=0~n-1),选择
     一些物品装入背包，在补偿过背包重量w的前提下...

     d).装满背包有多少种装法?
     TODO: 多重背包问题就是在完全背包问题的基础上多加了一个个数限制
        同样使用int[][] dp来存储状态
        状态转移方程: dp[i][j] = Sum(dp[i-1][j - k * weight[i]]) 为dp[i-1][j - k * weight[i]]求和
        ，唯一不同的是多了一个放入物品个数的限制

     4).二维费用(前面已学习过)
     有n个物品，重量分别为weight[i](i=0~n-1)，价值分别为value[i]((i=0~n-1)，在不超过背包重量的前提下，
     装入背包物品的最大价值是多少?



    TODO： 如果题目是要把所有组合都罗列出来，即使存在重复子问题，也是不能用dp解决的，dp只适合求最优、计数、可行这类统计型的问题，
     至于罗列,穷举所有解的问题，只能用回溯来解决！


    */
}
