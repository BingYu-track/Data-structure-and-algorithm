package lessons.week1.pratice.part2.pratice5;

import java.util.Arrays;

/**
 * @version 1.0 珠玑妙算
 * @Description: 珠玑妙算游戏（the game of master mind）的玩法如下。
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。例如，计算机可能有RGGB 4种
 * (槽1为红色，槽2、3为绿色，槽4为蓝色）。作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。要是猜对某个槽的颜色，
 * 则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。给定一种颜色组合solution
 * 和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 *
 *示例：
 * 输入： solution="RGBY",guess="GGRR"
 * 输出： [1,1]
 * 解释： 猜中1次，伪猜中1次。
 *
 * 提示：
 * len(solution) = len(guess) = 4
 * solution和guess仅包含"R","G","B","Y"这4种字符
 *
 * @author: bingyu
 * @date: 2022/1/5
 */
public class MasterMind {

    public static void main(String[] args) {
        String solution = "BRBB";
        String guess = "RBGY";
        int[] nums = masterMind(solution, guess);
        System.out.println(Arrays.toString(nums));
        /**
         B R BB
         R B GY
         */
        //猜中:0
        //伪猜中:2
    }

    //审题: 1.猜中某个槽的颜色就算猜中一次。 2.颜色猜对，槽位不对算一次伪猜中
    //我的思路:使用3个数组，flags数组用来标记猜中的位置，solutionflags数组标记solution字符串伪猜中字符所在的位置
    //guessflags数组标记guess字符串伪猜中字符所在的位置。然后拿guess的每个字符和soultion的每个字符比较，注意要
    //跳过之前猜中的位置和伪猜中的位置
    public static int[] masterMind(String solution, String guess) {
        char[] soluChars = solution.toCharArray();
        char[] guessChars = guess.toCharArray();
        boolean[] flags = new boolean[4]; //用来存储猜中字符所在位置
        boolean[] solutionflags = new boolean[4]; //用来存储solution字符串伪猜中字符所在的位置
        boolean[] guessflags = new boolean[4]; //用来存储guess字符串伪猜中字符所在的位置
        int[] arr = new int[2];
        int guessCount = 0; //猜中的次数
        int count = 0; //为猜中的次数

        //1.猜中一次后需要去掉对应的位置
        for (int i=0;i<soluChars.length;i++) {
            if (soluChars[i] == guessChars[i]) {
                flags[i] = true;
                guessCount++;
            }
        }
        //遍历guess字符
        for (int i=0;i<flags.length;i++) {
            if (flags[i] || guessflags[i]) { //1.guess字符跳过之前猜中的位置，或者遇到伪猜中的位置也直接跳过
                continue;
            }
            //开始将guess的每个字符依次和solution字符进行比较
            for(int j=0;j<flags.length;j++) {
                //guessFlags[j]表示之前猜中的位置，flags[j]表示伪猜中的位置
                if (solutionflags[j] || flags[j]) { //如果solution字符移动到之前猜中的位置直接跳过，如果期间发生了伪猜中，遇到伪猜中的位置也必须跳过
                    continue;
                }
                //当既不是猜中的位置，也不是伪猜中的位置，就可以进行比较
                //在while循环,guess字符串的i固定,solution字符串的j是在不断变化
                if (guessChars[i] == soluChars[j]) { //伪猜中
                    guessflags[i] = true; //遇到伪猜中，需要标记guess对应字符的位置
                    solutionflags[j] = true; //遇到伪猜中，需要标记solution对应字符的位置
                    count++;
                    break; //一旦遇到伪猜中，直接跳到guess的下一个字符位置开始遍历比较
                }
            }
        }
        arr[0] = guessCount;
        arr[1] = count;
        return arr;
    }

}
