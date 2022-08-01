package lessons.week5.pratice.binarysearch.pratice3;

/**
 * @version 1.0
 * @Description: 寻找比目标字母大的最小字母--争哥解法
 * @author: bingyu
 * @date: 2022/7/22
 */
public class ZgSolved {

    public static void main(String[] args) {
        char[] letters = {'c','f','j'};
        char target = 'j';
        char c = nextGreatestLetter(letters, target);
        System.out.println(c);
    }

    /*
     争哥解法思路: 和我的思路基本是一样的
    */
    public static char nextGreatestLetter(char[] letters, char target) {
        int low = 0;
        int length = letters.length;
        int high = length - 1;
        while (low<=high) {
            int mid = low + (high-low)/2;
            if (letters[mid] <= target) { //小于等于目标值，去后半查
                low = mid + 1;
            }else if (letters[mid] > target) { //大于目标值
                if (mid==0 || letters[mid-1] <= target) { //如果mid是第一个元素，或者前面小于等于目标值，说明当前mid就是我们要找的
                    return letters[mid];
                }else { //mid-1>target
                    high = mid - 1;
                }
            }
        }
        //执行到这里说明数组里没有
        return letters[0];
    }
}
