package lessons.week5.pratice.binarysearch.pratice3;

/**
 * @version 1.0 寻找比目标字母大的最小字母
 * @Description: 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的。举个例子：如果目标字母 target = 'z' 并且字符列表为letters = ['a', 'b']，则答案返回'a'
 *
 * 示例 1：
 * 输入: letters = ["c", "f", "j"]，target = "a"
 * 输出: "c"
 *
 * 示例 2:
 * 输入: letters = ["c","f","j"], target = "c"
 * 输出: "f"
 *
 * 示例 3:
 * 输入: letters = ["c","f","j"], target = "d"
 * 输出: "f"
 *
 * 提示：
 *
 * 2 <= letters.length <= 10^4
 * letters[i]是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 *
 *
 * @author: bingyu
 * @date: 2022/7/21
 */
public class NextGreatestLetter {

    public static void main(String[] args) {
        char[] letters = {'c','f','j'};
        char target = 'j';
        char c = nextGreatestLetter(letters, target);
        System.out.println(c);
    }

    /*
     我的思路: 首先寻找比目标元素大的字母 如何找比目标字母大的最小字母?
     TODO：有点迷，需要重点理解

      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
      内存消耗：42.1 MB, 在所有 Java 提交中击败了5.07%的用户
      这就过了？有点迷呀
    */
    public static char nextGreatestLetter(char[] letters, char target) {
        int length = letters.length;
        int low = 0;
        int high = length - 1;
        int mid = 0;
        while (low <= high) {
            if (letters[low] > target) { //如果low都大于目标值，那么说明low指向的就是大于target的最小值，因为根据二分法
                // 得到[low,high]区间是排除了小于等于target的范围，因此low此时就是我们要取的值
                return letters[low];
            } else { //low + high
                mid = (low + high) / 2;
                if (letters[mid] <= target) { //遇到小于等于target的，更新低位指针的位置
                    low = mid + 1;
                } else { //比target大，更新高位指针的位置
                    high = mid - 1;
                }
            }
        }
        //执行到这里，说明low大于high，且之前letters[low]小于target
        if (low>=length) { //如果目标值比数组里的最大字符都大，那么从头开始取
            low = low - length;
        }
        return letters[low];
    }
}
