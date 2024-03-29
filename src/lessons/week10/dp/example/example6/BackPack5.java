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
     TODO： 我对dp的空间优化(二维数组只保留2行)
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
      TODO： 争哥对dp的空间优化(二维数组只保留2行)
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
    TODO: 推荐该方法使用一维的滚动数组

     上面优化空间都是用的二维数组，其实一维数组也可以进行，具体操作时将上一行结果填入数组，此时我们的下标位置到达了数组的末尾，
     然后我们再从数组末尾从后往前即可，下面是具体代码

             0 1 2 3 4 5 6 7 8 9    物品重量
        0   [1,0,1,0,0,0,0,0,0,0]    2
        1   [1,0,1,1,0,1,0,0,0,0]    3
        2   [1,0,1,1,1,1,0,1,0,0]    2
        3   [1,0,1,1,1,1,1,1,1,1]    4
        4   [1,0,1,1,1,1,1,1,1,1]    6
        5   [1,0,1,1,1,1,1,1,1,1]    3

     TODO:
        1.注意用一维数组来存储状态，从上一阶段推导下一阶段不能从前往后，只能从后往前，因为如果从前往后推导的话，由于放入物品
        可达的重量会增加，这样会导致该行后面会存储当前状态，但是本行处理的本来是上一阶段的，就会导致一维数组同一行会处理多个阶段的数据，导致错误！
        举例： 我们在上面的表格，从前往后处理，在第0行推导第1行时，当在dp[8]这个重量放入物品3时，本来上个阶段dp[5]是不可达的，但是由于在
        当前阶段前面dp[5]这个位置可以从上一个阶段的dp[2]放入3达到，从而记录当前阶段dp[5]可达了，这就与当前阶段dp[8]时，这个重量放入物品3时上个
        阶段的dp[5]不可达冲突了，所以我们要从后往前遍历，才能避免这样上一阶段状态和当前阶段状态在一维数组的冲突！但是如果我们是从后往前进行推导，
        随着重量的增加，会放入后面的位置，但是后面是我们已经处理过的，就不受其影响了！

        2.使用一维滚动数组需要注意正确的遍历顺序:  一维dp数组的背包在遍历顺序上和二维是有很大差异的，
     */
    private boolean backPack3(int[] weight, int n, int w) {
        boolean[] dp = new boolean[w+1];
        //初始化第0行
        dp[0] = true;
        if (weight[0] <= w) {
            dp[weight[0]] = true;
        }

//        //从第1行开始
//        for (int i = 1;i < n;i++) { //1.先遍历物品
//            //用一维数组，必须从后往前来进行推导
//            for (int j = w;j>=0;j--) { //2.再遍历背包容量
//                if (dp[j] || (j-weight[i]>=0 && dp[j-weight[i]])) {
//                    if(dp[j]) {
//                        System.out.print(j + "->" + j + " "); //打印dp
//                    }
//                    if (j-weight[i]>=0 && dp[j-weight[i]]) {
//                        System.out.print(j + "->" + (j-weight[i]) + " "); //打印dp
//                    }
//                    dp[j] = true;
//                }
//            }
//            System.out.println();
//        }
        /*
                  上面是"先遍历物品，再遍历背包容量"打印的dp,打印结果如下:
                 第1行: 5->2 3->0 2->2 0->0
                 第2行: 7->5 5->5 5->3 4->2 3->3 2->2 2->0 0->0
                 第3行: 9->5 8->4 7->7 7->3 6->2 5->5 4->4 4->0 3->3 2->2 0->0
                 第4行: 9->9 9->3 8->8 8->2 7->7 6->6 6->0 5->5 4->4 3->3 2->2 0->0
                 第5行: 9->9 9->6 8->8 8->5 7->7 7->4 6->6 6->3 5->5 5->2 4->4 3->3 3->0 2->2 0->0
                  可以看到当前dp[i]都是来自它上面或者左上的位置
                 */

        for (int j = w;j>=0;j--) { //先遍历背包容量
            for (int i = 1;i < n;i++) { //再遍历物品
                if (dp[j] || (j-weight[i]>=0 && dp[j-weight[i]])) { //从最右列开始，至上而下进行处理
                    if(dp[j]) {
                        System.out.print(j + "->" + j + " "); //打印dp
                    }
                    if (j-weight[i]>=0 && dp[j-weight[i]]) {
                        System.out.print(j + "->" + (j-weight[i]) + " "); //打印dp
                    }
                    dp[j] = true;
                }
            }
            System.out.println();
        }

        /*
        TODO： 问: 如果我先遍历背包容量，再遍历物品呢? 发现都是依赖前面的元素
          看"先遍历背包容量，再遍历物品"打印的dp，打印结果如下:
           第9列: xxxx......
           第8列: 8->2 8->8
           第7列: xxxx......
           第6列: 6->2 6->6 6->0 6->6
           第5列: 5->2 5->5 5->5 5->5 5->5 5->2
           第4列: 4->2 4->4 4->0 4->4 4->4
           第3列: 3->0 3->3 3->3 3->3 3->3 3->0
           第2列: 2->2 2->2 2->0 2->2 2->2 2->2
           第1列: xxxx......
           第0列: 0->0 0->0 0->0 0->0 0->0
           为什么这里的一维滚动数组dp遍历顺序不能颠倒?

          因为一维数组我们是从后往前的列开始处理的，此时其左上都还没有任何可达的位置，每次在指定的列中，即固定的容量，9、8、7......这些位置，只能
          放入每行的一个物品，导致这一列都不可达，造成的原因就是因为一开始就没有获取到可达的数据，如下所示
                            这是"先遍历背包容量，再遍历物品"在表格的遍历方向
                                          |
                  0 1 2 3 4 5 6 7 8 9     |    物品重量
             0   [1,0,1,0,0,0,0,0,0,0]    |     2
             1   [0,0,0,0,0,0,0,0,0,0]    |     3
             2   [0,0,0,0,0,0,0,0,0,0]    |     2
             3   [0,0,0,0,0,0,0,0,0,0]    |     4
             4   [0,0,0,0,0,0,0,0,0,0]    |     6
             5   [0,0,0,0,0,0,0,0,0,0]    V     3

            从dp[1][9]开始向下进行遍历，发现都不满足,因为9减去所有的weight[i]都不满足j=0或者j=2，因此一维数组的该列dp[9]全不可达，同理，前面几列
            8、7、6、5、4...列都是这样不可达，只有前面的列0、2可达。因此 TODO "从后往前先遍历背包容量，再遍历物品" 由于一开始没有数据积累，导致其dp是错误的！
            而二维dp数组之所以可以"先遍历背包容量，再遍历物品"是因为他是从前往后一列列的进行遍历，因为初始化第0行前面几列就是可达的，因此一开始就有可达数据，
            这样后面随着一列列的推导和积累就能逐渐满足第9列位置的可达，当然 TODO "如果二维dp数组也是从后往前先遍历背包容量，再遍历物品" 也会出现没有数据积累导致dp错误的情况！

            TODO 总结: "从后往前先遍历背包容量，再遍历物品"会导致一开始无可达数据积累导致dp错误；这样的遍历方式只能从前往后遍历，这样才会有数据积累，dp才
             能进行正确的推导，而一维数组dp由于只能从后往前遍历，因此就不能用"先遍历背包容量，再遍历物品"的方式进行遍历！只能用"先遍历物品，再遍历背包的方式"
             进行遍历，而这种遍历方式，因为它的可达数据是从行开始的，因此无论是从前往后，还是从后往前，都能获取到一开始的可达数据，后面dp的数据推导也就有了数据
             支持！
         */

        return dp[w];
    }


}
