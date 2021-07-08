package program;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2021/4/5
 */
public class Test {


    public static boolean test(String str) {
        int n = str.length();
        int i = 0;
        int digit = 0;
        while (i < n && str.charAt(i)!=' ') {
            char c = str.charAt(i);
            digit = digit*10 + (c-'0');
            if (digit > 255) {
                return  false;
            }
            i++;
        }
        return true;
    }

    public static void main(String[] args) {
        test("256");
    }
}
