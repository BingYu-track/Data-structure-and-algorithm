package lessons.week1.pratice.part1.pratice10;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2021/12/30
 */
public class Test {

    public static void main(String[] args) {
        t();
    }

    public static void t() {
        long j = 0;
        int i = 10;
        while (i > 0) {
            i = i+1;
            j++;
            if (i==10) {
                break;
            }
        }
        System.out.println(j);
    }
}
