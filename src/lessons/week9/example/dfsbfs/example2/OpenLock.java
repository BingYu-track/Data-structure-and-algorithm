package lessons.week9.example.dfsbfs.example2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**TODO 题型2--最短路径(BFS)
 * @version 1.0  打开转盘锁
 * @Description: 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 * 示例 1:
 *
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 *
 * 示例 2:
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：把最后一位反向旋转一次即可 "0000" -> "0009"。
 *
 *
 * 示例 3:
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：无法旋转到目标数字且不被锁定。
 *
 * 提示：
 *
 * 1 <=deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target 不在 deadends 之中
 * target 和 deadends[i] 仅由若干位数字组成
 *
 * @author: bingyu
 * @date: 2023/2/24
 */
public class OpenLock {

    //TODO 找到到达目标数字的最小旋转次数
    public static void main(String[] args) {
        OpenLock l = new OpenLock();
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        int lessTimes = l.openLock(deadends, target);
        System.out.println(lessTimes);
    }

    /*
      执行用时：87 ms, 在所有 Java 提交中击败了29.37%的用户
      内存消耗：49.4 MB, 在所有 Java 提交中击败了6.26%的用户
      由于是求最小旋转次数，因此可以抽象成求最短路径的问题，很明显就要使用BFS逐层遍历来解决问题
    */
    public int openLock(String[] deadends, String target) {
        HashSet<String> set = new HashSet<>();
        HashSet<String> visited = new HashSet<>();
        for (String deadend : deadends) {
            set.add(deadend);
        }
        if (set.contains("0000")) return -1; //初始值就是死亡数字
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");
        visited.add("0000");
        int depth = 0; //最终的层数,就是我们要求的最短路径
        while (!queue.isEmpty()) {
            int size = queue.size(); //当前层节点个数
            for (int k = 0;k<size;k++) { //1.当前层所有节点执行完后才能到下一层
                String str = queue.poll();
                if (str.equals(target)) return depth;
                 char[] chars = str.toCharArray();
                for (int i = 0;i<chars.length;i++) { //2.当前四个拨轮
                    char temp = chars[i];
                    int num = chars[i] - '0'; //当前字符对应的数字
                    for (int j = 0;j<2;j++) { //3.每个拨轮有2种拨法
                        if (j==0) { //向前拨
                            int fowardNum = num - 1;
                            if (num == 0) {
                                fowardNum = 9; //等于数字'0'时向前拨
                            }
                            char f = (fowardNum + "").charAt(0);
                            chars[i] = f;
                        }else { //向后拨
                            int lastNum = num + 1;
                            if (num == 9) {
                                lastNum = 0; //等于数字'9'时向后拨
                            }
                            char l = (lastNum + "").charAt(0);
                            chars[i] = l;
                        }
                        String s = String.valueOf(chars);
                        if (set.contains(s) || visited.contains(s)) { //一旦遇到死亡数字或者之前已经访问过，直接跳过探测下一个拨法
                            chars[i] = temp;
                            continue;
                        }
                        //TODO: times++; 如果我是用这个表示最短路径次数，那么就不对了，因为这样遍历每个点都会加一下，相当与深度遍历中的所有点个数了
                        visited.add(s);
                        queue.add(s);
                    }
                    //TODO: 执行到这里说明轮到相邻的拨轮了，之前修改的字符需要复原
                    chars[i] = temp;

                }
            }
            depth++;
        }
        return -1;
    }

    /*终止条件:
       一共四个位置，初始位置都是0，因此，每个位置只能向前或者向后拨动一次，即每个位置有2种选择，4个位置总共就有8种选择
               [0][0][0][0]
           /     |      |
          /      |
         /       |
       [1]000  [9]000  0[1]00   0[9]00...............
       /         |
      /          |
               [0]000
                |
                |
              0[1]00
     []--
       TODO: 会出现环吗?
         会，因为如果不加以限制，有可能会向前拨，但是这样就会导致重复访问，因此也需要记录已访问的数字
    */

}
