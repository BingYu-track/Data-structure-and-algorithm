package lessons.week1.pratice.part1;

/**
 * @version 1.0
 * @Description: 刷题技巧总结
 * @author: bingyu
 * @date: 2022/1/3
 */
public class Summary {


    public static void main(String[] args) {
        trim("12  345");
        //System.out.println(i);
    }


    //TODO: ASCII码--必须记住0是48, A是65, a是97

    //TODO: 技巧1.翻转字符串：
    public static void reverse(String str) {
        char[] arr = str.toCharArray();
        int length = arr.length;
        for (int i = 0;i<length/2;i++) {
            char temp = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }
    }


    //TODO: 技巧2:去除前置空格和后置空格
    //TODO: 技巧3: 处理中间空格--将中间多个空格处理成一个空格
    public static void trim(String str) {
        char[] arr = str.toCharArray();
        int length = arr.length;
        int i = 0;
        int j = length - 1;
        //1.跳过前导空格
        while (i<arr.length && arr[i] == ' ') {
            i++;
        }

        //2.跳过后导空格
        while (j>=0 && arr[j] == ' ') {
            j--;
        }

        //3.处理中间空格
        int k ;
        for(k = 0;i<=j; i++) {
            if (arr[i] == ' ') { //遇到空格
                //如果当前i多对应的空格前面的元素不是空格，则k向后移动一位;否则k直接跳过，但是i仍然会向后移动一位，下次i遇到非空格时,
                // k仍然是指向空格，直接赋值arr[k] = arr[i]就达到了空格被后面的字符填满
                if(i - 1 >=0 && arr[i-1] != ' ') {
                    arr[k] = arr[i];
                    k++;
                }
            }else {
                //执行到这里说明是非空格
                arr[k] = arr[i];
                k++;
            }
        }

        //执行到这里
    }


    //TODO: 技巧4:数字字符串转化成数字
    public static int strToInt(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int i= 0;
        int result = 0;
        while (i < length) {
            int digits = chars[i] - '0'; //将字符转为纯数字
            result = 10 * result + digits; //重点
            i++;
        }
        return result;
    }





}
