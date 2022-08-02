package lessons.week5.pratice.binarysearch.pratice6;

/**
 * @version 1.0
 * @Description: 稀疏数组搜索--争哥解法
 * @author: bingyu
 * @date: 2022/7/26
 */
public class ZgSolved {

    public static void main(String[] args) {
        String[] words = {"as", "at", "", "ball", "ball", "", "", "car", "", "", "dad", "", ""};
        String s = "at";
        //words = new String[]{"at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""};
        //s = "at";
        int index = findString(words, s);
        System.out.println(index);
    }

    /**
     * 争哥解法：当mid遇到空字符串时，就往左或者往右探查，直到遇到非空字符串
     * 但是该方法有个问题，就是不会找出第一个遇到的目标值，按照上面的测试用例，返回的是下标4，也就是第2个目标值，因此不推荐该方法
     */
    public static int findString(String[] words, String s) {
        int low = 0;
        int high = words.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (words[mid].equals(s)) { //遇到目标值
                if (mid == 0 || !words[mid - 1].equals(s)) { //判断是不是首个目标值
                    return mid;
                }else {
                    //mid!=0 && words[mid - 1].equals(s)
                    high = mid - 1;
                }
                return mid;
            }else if (words[mid].compareTo(s) > 0) { //目标值
                high = mid - 1;
            }else if (words[mid].equals("")) { //TODO 遇到空字符串，向右探测
                if (words[low].equals(s)) {    //    注意这里是用words[low]来比较
                    return low;
                }else {
                    low++;
                }
            } else {
                low = mid + 1;
            }

        }
        return -1;
    }


}
