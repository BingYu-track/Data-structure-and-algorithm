package lessons.week10.dp.models.match;

/**
 * @version 1.0
 * @Description: 匹配模型
 * @author: bingyu
 * @date: 2023/7/5
 */
public class MatchModel {


    /*
     一、匹配问题模型
        一般是两个字符串做匹配

     二、多阶段决策模型:
        每一阶段考察两个字符如何匹配。
        考察s1[i]和s2[j]如何匹配，有多种决策方法(视题目而定)，决策完之后，假设i后移1位，j后移2位，
        下一个阶段为考察s1[i+1]和s2[j+2]如何匹配

     三、状态定义:
        int/boolean dp[n+1][m+1]; n、m表示两个字符串的长度
        dp[i][j]表示长度为i的子串s1[0~i-1]和长度为j的子串s2[0~j-1]匹配时的最值/可行问题(视题目而定)

     四、状态转移方程:
        到达(i,j)这个状态，那么上一步是怎么匹配的，
        假设从(i-1,j)、(i,j-1)、(i-1,j-1)转移过来，那么dp[i][j]值也是由dp[i-1][j]、dp[i][j-1]、dp[i-1][j-1]推导出来

        匹配问题：
          1143.最长公共子序列
          72.编辑距离
     */

}
