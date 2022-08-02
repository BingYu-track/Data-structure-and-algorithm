package lessons.week5.pratice.binarysearch.pratice6;

/**
 * @version 1.0 面试题 10.05. 稀疏数组搜索
 * @Description: 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 *
 * 示例1:
 *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 *  输出：-1
 *  说明: 不存在返回-1。
 *
 * 示例2:
 *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 *  输出：4
 *
 * 提示:
 * words的长度在[1, 1000000]之间
 *
 * @author: bingyu
 * @date: 2022/7/26
 */
public class FindString {

    public static void main(String[] args) {
        String[] words = {"at", "at", "", "ball", "ball", "", "", "", "", "", "", "", ""};
        String s = "ball";
        //words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""};
        //s = "at";
        int index = findString(words, s);
        System.out.println(index);
    }

    /**TODO 推荐该方法，比较难，需要重点多次练习
     * 我的思路: 根据目标字符串的每个字符去数组里二分寻找，如果遇到空字符串，则缩小范围，如果遇到相同的首字母，则确定范围
     * TODO 注意字符串之间可以直接用compareTo方法比较，该比较算法就是用的字典顺序，比较对应字符的大小（ASCII码顺序），如果第一个字符和参数的
     *      第一个字符不等，结束比较，返回他们之间的长度差值，如果第一个字符和参数的第一个字符相等，则以第二个字符和参数的第二个字符做比较，
     *      以此类推，直至比较的字符或被比较的字符有一方结束。
     *
     * 执行结果:
     *      执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     *      内存消耗：41.2 MB, 在所有 Java 提交中击败了69.46%的用户
     * @param words
     * @param s
     * @return
     */
    public static int findString(String[] words, String s) {
        if (s.equals("")) return -1;
        int length = words.length;
        int low = 0;
        int high = length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (words[mid].equals("")) {
                //TODO 当mid遇到空字符串时，就往左或者往右探查，直到遇到非空字符串就继续和target比较大小进行二分
                // 遇到空字符串为什么要左右探测？这里有点迷？，因为遇到空字符串无法确定目标值在哪个范围，只能选择向左或者向右探测到对应字符串，然后比较
                // 大小确定目标值的范围，例如: nums=["","a","","",""]，假设后面的都是空字符串，第一次mid就是在中间的位置，也是空字符串，后面都
                // 向右探测，虽然探测都是空字符串，但是随着low++，low会遇到最终的目标值
                if (!words[low].equals(s)) { //在缩小范围前判断当前指针是否是目标值，不是就移动，是就返回
                    low++;
                }else {
                    return low;
                }
            }else {
                //非空字符串
                //当遇到字母是相同时，不一定就是当前位置，因为前面可能有相同的字符串(题目是要取最前面的)，因此要不断的向前移动进行判断
                if (words[mid].equals(s)) {
                    if (mid==0 || !words[mid - 1].equals(s)) {
                        return mid;
                    }else { //mid!=0 && words[mid - 1].equals(s)
                        high = mid - 1;
                    }
                }else if (words[mid].compareTo(s) < 0) { //遇到的字符串小于目标字符串
                    low = mid + 1;
                }else {
                    high = mid - 1;
                }
            }

        }
        return -1;
    }



}
