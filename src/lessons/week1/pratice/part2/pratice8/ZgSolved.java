package lessons.week1.pratice.part2.pratice8;

/**
 * @version 1.0
 * @Description: 旋转图像，争哥解法
 * @author: bingyu
 * @date: 2022/1/17
 */
public class ZgSolved {


    public static void main(String[] args) {

    }

    //思路1:非原矩阵(不推荐)
    /*思路2:使用翻转来实现旋转 翻转分为 1上下翻转  2左右翻转  3.对角线翻转
        a).旋转90度 = 上下翻转 + 对角线翻转
        b).旋转180度 = 上下翻转 + 左右翻转
        c).旋转270度 = 左右翻转 + 对角线翻转
        缺点:复杂度太高
    */
    //思路3:直接原地旋转(推荐)
    //争哥的大致思路是和我一样的，也是一层一层的旋转处理，不一样的是具体的处理细节，争哥的是先确定矩阵四个顶点的坐标，在
    //4个顶点坐标中选择一个顶点作为基点，随后可以通过这个基点推算出其它3个顶点的坐标;交换后，下次选择，只需要通过四个顶点进行一次移动
    //就可以得到其它四个需要交换的4个元素坐标了，依次类推进行解决
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int s1_i = 0;
        int s1_j = 0;
        while (n > 1) {
            int s2_i = s1_i;
            int s2_j = s1_j+n-1;
            int s3_i = s1_i+n-1;
            int s3_j = s1_j+n-1;
            int s4_i = s1_i+n-1;
            int s4_j = s1_j;
            for (int move = 0; move<=n-2; ++move) {
                int p1_i = s1_i + 0;
                int p1_j = s1_j + move;
                int p2_i = s2_i + move;
                int p2_j = s2_j + 0;
                int p3_i = s3_i + 0;
                int p3_j = s3_j - move;
                int p4_i = s4_i - move;
                int p4_j = s4_j + 0;
                swap4(matrix, p1_i, p1_j, p2_i, p2_j, p3_i, p3_j, p4_i, p4_j);
            }
            s1_i++;
            s1_j++;
            n-=2;
        }
    }

    private static void swap4(int[][] a, int i1, int j1, int i2, int j2, int i3, int j3, int i4, int j4) {
        int tmp = a[i1][j1];
        a[i1][j1] = a[i4][j4];
        a[i4][j4] = a[i3][j3];
        a[i3][j3] = a[i2][j2];
        a[i2][j2] = tmp;
    }

}
