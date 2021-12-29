package lessons.util;

/**
 * @version 1.0
 * @Description: 算法工具类
 * @author: bingyu
 * @date: 2021/12/22
 */
public class Util {


    /**
     * 翻转指定的数组
     * @param arr
     * @return
     */
    public static void reverse(char[] arr) {
        int length = arr.length;
        for (int i = 0;i<length/2;i++) {
            char temp = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }
    }

    /**
     * 翻转指定数组的部分
     * @param arr char数组
     * @param first 指定开始翻转的位置
     * @param last 指定翻转的结束位置
     */
    public static void reversePart(char[] arr,int first,int last) {
        if (last < first) return;
        int length = last - first + 1; //得到指定要翻转的子段的长度
        //从first开始，互换first + length/2次，
        for (int i = first,j = 0; i<(first + length/2);i++,j++) {
            char temp = arr[i];
            arr[i] = arr[first + length - 1 - j];
            arr[first + length - 1 - j] = temp;
        }
    }


}
