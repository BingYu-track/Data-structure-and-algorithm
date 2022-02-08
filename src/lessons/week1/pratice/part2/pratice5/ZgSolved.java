package lessons.week1.pratice.part2.pratice5;

import java.util.Arrays;

/**
 * @version 1.0
 * @Description: 珠玑妙算
 * @author: bingyu
 * @date: 2022/1/12
 */
public class ZgSolved {

    public static void main(String[] args) {
        String solution = "BRBB";
        String guess = "RBGY";
        int[] nums = masterMind(solution, guess);
        System.out.println(Arrays.toString(nums));
    }


    //争哥思路解法:
    public static int[] masterMind(String solution, String guess) {
        int length = solution.length();
        boolean[] used = new boolean[length]; //用来记录哪些字符已经被匹配用掉的(包括猜中和伪猜中的都记录在里面)
        int hitCount = 0; //记录猜中的
        int fakeHitCount = 0; //记录伪猜中的
        //统计猜中次数
        for (int i=0;i<length;i++) {
            if (solution.charAt(i) == guess.charAt(i)) {
                used[i] = true; //标记猜中的
                hitCount++;
            }
        }

        //统计伪猜中次数
        for (int i=0;i<guess.length();i++) { //遍历guess
            if (solution.charAt(i) == guess.charAt(i)) {
                continue; //因为前面猜中的已经统计了，不需要再进行统计
            }
            for (int j=0;j<solution.length();j++) { //再拿guess的每个字符和solution的每个字符进行比较
                if (solution.charAt(i) == guess.charAt(j) && !used[j]) { //如果字符相同，且solution对应位置字符也没有被使用过
                    used[j] = true; //TODO: 这里我不太理解，为什么只需要标记solution的字符？ 因为我们是先遍历guess字符，再遍历solution字符的
                    fakeHitCount++;
                    break;//一旦遇到伪猜中，中断此循环，直接跳到guess的下一个字符位置开始遍历比较
                }
            }
        }
        return new int[]{hitCount,fakeHitCount};
    }

}
