package lessons.week9.example.dfsbfs.example2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @version 1.0  打开转盘锁 ----重复练习
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
 * @author: bingyu
 * @date: 2023/4/12
 */
public class RpOpenLock {

    public static void main(String[] args) {
        RpOpenLock rp = new RpOpenLock();
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        int lessTimes = rp.openLock(deadends, target);
        System.out.println(lessTimes);
    }


    private int count = 0; //用来记录拨动的次数

    /*
     一次只能拨动一个轮中的一个数字，由于要求最少拨动次数，想到最短路径，应该是用BFS来解决，
     每个轮可以拨动9次，9*9*9*9次就能覆盖所有数字组合的可能性，再对数字进行替换
     我的思路是因为有3个拨轮，我们对每个拨轮进行BFS找到指定的字符
    */
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadSet = new HashSet<>();
        for (String deadend : deadends) {
            deadSet.add(deadend);
        }
        int[] direction = {1,-1}; //拨弄的方向 1表示向前 -1表示向后
        String str = "0000"; //从4个0开始
        Set<String> visited = new HashSet<>(); //用来记录遇到的重复组合，因为第一次可能有0001或者0010这样的选择，第2次选择，
        // 则可能都会变成0011，这样2者就重复了
        visited.add(str);
        if (deadSet.contains(str)) return -1; //死亡列表包含
        Queue<String> queue = new LinkedList<>();
        queue.add(str);
        boolean found = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0;i < size;i++) { //遍历当前层元素,i是当前层元素的个数,即每个组合
                String poll = queue.poll();
                if (target.equals(poll)) { //TODO: 必须要放在前面，因为如果初始值刚好就是目标值，放在后面就会错过了
                    return count;
                }
                char[] arr = poll.toCharArray();
                for(int j = 0;j < arr.length;j++) { //j表示每个拨轮，一共4个
                    char origin = arr[j];
                    for (int k = 0;k < direction.length;k++) { //向前或者向后拨弄
                        if (arr[j] == '9' && direction[k] == 1) { //拨轮为9，并且向前拨弄，数字变为0
                            arr[j] = '0';
                        } else if (arr[j] == '0' && direction[k] == -1) { //拨轮为，并且向后拨弄，数字变为9
                            arr[j] = '9';
                        }else { //其它情况直接加就行
                            arr[j] = (char) (arr[j] - 0 + direction[k]);
                        }
                        String newStr = new String(arr); //得到新的字符串
                        if (!deadSet.contains(newStr) && !visited.contains(newStr)) {
                            queue.add(newStr); //不在死亡列表，就放入队列中
                            visited.add(newStr);
                        }
                        arr[j] = origin;
                    }
                }
            }
            //执行到这里说明一层的元素全部处理完毕
            count++;
        }
        return -1;
    }

    /*
                0          0          0        0
               / \       /   \      /   \    /   \
             9000 1000  0900 0100 0090 0010 0009 0001 -----1次
     */
}
