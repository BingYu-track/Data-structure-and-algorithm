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

    //审题: 1.猜中某个槽的颜色就算猜中一次。 2.颜色猜对，槽位不对算一次伪猜中 3.一旦猜中了或者伪猜中，使用的字母后面都不能继续使用了(先猜中，再伪猜中)
    //我的思路:使用3个数组，flags数组用来标记猜中的位置，solutionflags数组标记solution字符串伪猜中字符所在的位置
    //guessflags数组标记guess字符串伪猜中字符所在的位置。然后拿guess的每个字符和soultion的每个字符比较，注意要
    //跳过之前猜中的位置和伪猜中的位置
    public static int[] masterMind(String solution, String guess) {
        char[] soluChars = solution.toCharArray();
        char[] guessChars = guess.toCharArray();
        boolean[] flags = new boolean[4]; //用来存储猜中字符和solution字符串中伪猜中所在位置
        int[] arr = new int[2];
        int guessCount = 0; //猜中的次数
        int count = 0; //伪猜中的次数

        //1.猜中一次后需要去掉对应的位置
        for (int i=0;i<soluChars.length;i++) {
            if (soluChars[i] == guessChars[i]) {
                flags[i] = true; //标记猜中的位置
                guessCount++;
            }
        }
        //遍历guess字符
        for (int i=0;i<guessChars.length;i++) {
//            if (flags[i]) { 1.这里不能这样写，这段代码并不能表示"guess字符跳过之前猜中的位置"
//                continue;
//            }
            //因为循环执行，flags数组也会对solution字符的伪猜中进行记录，这样的话除猜中的位置会跳过，
            //guess字符会将soluChars伪猜中对应的位置作为guess伪猜中的位置进行跳过，所以错误

            if (guessChars[i] == soluChars[i]) continue; //这样才能跳过猜中的位置，而不是用上面注释掉的代码
            //TODO:开始遍历solution字符，将guess的每个字符依次和solution字符进行比较，因为guess字符和solution的字符比较时，两者字符的下标不一定相同的
            for(int j=0;j<soluChars.length;j++) { //遍历
                //当字符相同，并且在flag数组中的位置是false-----既不是猜中的位置，也不是伪猜中的位置
                if (guessChars[i] == soluChars[j] && !flags[j]) { //伪猜中
                    flags[j] = true; //遇到伪猜中，需要标记solution对应字符的位置即可
                    count++;
                    break; //一旦遇到伪猜中，中断此循环，直接跳到guess的下一个字符位置开始遍历比较
                }
            }
        }
        arr[0] = guessCount;
        arr[1] = count;
        return arr;
    }

}
