package lessons.week10.dp.example.example6;

/**
 * @version 1.0  背包可行性问题(对空间进行优化)
 * @Description: 有n个物品，选择其中一些物品装入背包，能不能正好装满背包?(可行)
 * @author: bingyu
 * @date: 2023/5/10
 */
public class BackPack5 {

    public static void main(String[] args) {
        BackPack5 bp = new BackPack5();
        int[] weight = {2,3,2,4,6,3};
        boolean res = bp.backPack3(weight, weight.length, 9);
        System.out.println(res);
    }

    /*
     TODO： 我对dp的空间优化
    */
    private boolean backPack(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[2][w+1]; //只保留2行即可
        //第0行初始化
        dp[0][0] = true;
        dp[0][weight[0]] = true;

        int k = 0; //用来记录考察第几个物品
        while (k < n) {
            for (int i = 0;i<=w;i++) {
                //不放
                if (dp[0][i]) {
                    dp[1][i] = true;
                }
                //放入
                if (i-weight[k]>=0 && dp[0][i-weight[k]]) {
                    dp[1][i] = true;
                }
            }
            //当前物品考察完后将其所有元素放入上一行，当前行在空出来用以下一阶段的状态存储
            for (int i = 0;i<=w;i++) {
                dp[0][i] = dp[1][i];
                dp[1][i] = false;
            }
            k++;
        }
        return dp[0][w];
    }

    /*
      TODO： 争哥对dp的空间优化
        使用滚动二维数组，更巧妙
     */
    private boolean backPack2(int[] weight, int n, int w) {
        boolean[][] dp = new boolean[2][w+1]; //只保留2行即可
        dp[0][0] = true;
        if (weight[0] <= w) {
            dp[0][weight[0]] = true;
        }

        int turn = 1; //该填充第turn行了，turn表示要填哪一行，turn不是0就是1，所以用(turn+1)%2，turn为奇数时，(turn+1)%2得到0，为偶数时就是1
        for (int i = 1;i < n;i++) { //动态规划状态转移
            for (int j = 0;j<=w;j++) {
                //这里如果turn要处理的是第1行，(turn+1)%2就是第0行，也就是上一行；如果turn要处理的是第0行，(turn+1)%2就是第1行，在这里处理第0行，
                //说明第1行是上一个阶段的
                if (dp[(turn+1)%2][j] == true ||
                        (j-weight[i]>=0 && dp[(turn+1)%2][j-weight[i]] == true)) {
                    dp[turn][j] = true;
                }
            }
            turn = (turn + 1)%2; //0变为1，或者1变为0
        }
        return dp[(turn + 1)%2][w]; //因为turn永远指向的是将要处理的行，因此考察完后，我们需要到上一行去寻找结果状态
    }

    /*
     上面优化空间都是用的二维数组，其实一维数组也可以进行，具体操作时将上一行结果填入数组，此时我们的下标位置到达了数组的末尾，
     然后我们再从数组末尾从后往前即可，下面是具体代码

             0 1 2 3 4 5 6 7 8 9    物品重量
        0   [1,0,1,0,0,0,0,0,0,0]    2
        1   [1,0,1,1,0,1,0,0,0,0]    3
        2   [1,0,1,1,1,1,0,1,0,0]    2
        3   [1,0,1,1,1,1,1,1,1,1]    4
        4   [1,0,1,1,1,1,1,1,1,1]    6
        5   [1,0,1,1,1,1,1,1,1,1]    3

     TODO:  注意用一维数组来存储状态，从上一阶段推导下一阶段不能从前往后，只能从后往前，因为如果从前往后推导的话，由于放入物品
        可达的重量会增加，这样会导致该行后面会存储当前状态，但是本行处理的本来是上一阶段的，就会导致错误！
        举例： 我们在上面的表格，在第0行推导第1行，当在0这个重量放入物品3时，就会在第0行的第3列填入，后续处理又会遇到该位置，这样就影响到了
        正确结果，但是如果我们是从后往前进行推导，随着重量的增加，会放入后面的位置，但是后面是我们已经处理过的，就不受其影响了！
     */
    private boolean backPack3(int[] weight, int n, int w) {
        boolean[] dp = new boolean[w+1];
        //初始化第0行
        dp[0] = true;
        if (weight[0] <= w) {
            dp[weight[0]] = true;
        }

        //从第1行开始
        for (int i = 1;i < n;i++) {
            //用一维数组，必须从后往前来进行推导
            for (int j = w;j>=0;j--) {
                if (dp[j] || (j-weight[i]>=0 && dp[j-weight[i]])) {
                    dp[j] = true;
                }
            }
        }
        return dp[w];
    }


}
