package lessons.week5.pratice.binarysearch.pratice2;

/**
 * @version 1.0
 * @Description: 猜数字大小--争哥解法
 * @author: bingyu
 * @date: 2022/7/8
 */
public class ZgSolved {

    public static void main(String[] args) {
        int i = guessNumber(1705930310);
        System.out.println(i);
    }

    /*
     争哥解法: 就是普通的二分查找

    */
    public static int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low<=high) {
            int mid = low + (high - low)/2;
            int ress = guess(mid);
            if (ress == 0) {
                return mid;
            }else if (ress == -1) { //mid大于pick
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int guess(int mid) {
        return 0;
    }
}
