package lessons.week1.pratice.part2.pratice6;

/**
 * @version 1.0
 * @Description: 井字游戏 争哥解法
 * @author: bingyu
 * @date: 2022/3/1
 */
public class ZgSolved {

    public static void main(String[] args) {
        String[] board = {"OXXOX","XXOXO","OOXOO","OXXXX","OXOOO"};
        String tictactoe = tictactoe(board);
        System.out.println(tictactoe);
    }

    public static String tictactoe(String[] board) {
        int n = board.length;
        char[][] boards = new char[n][n]; //空间复杂度太高(没我的好)
        for (int i = 0; i < n; ++i) {
            boards[i] = board[i].toCharArray();
        }
        boolean determined = false; //表示是否已经发现有⼈赢了

        // 检查⾏
        for (int i = 0; i < n; ++i) {
            if (boards[i][0] == ' ') continue;
            determined = true;
            for (int j = 1; j < n; ++j) {
                if (boards[i][j] != boards[i][0]) {
                    determined = false;
                    break;
                }
            }
            if (determined) return "" + boards[i][0];
        }

        // 检查列
        for (int j = 0; j < n; ++j) {
            if (boards[0][j] == ' ') continue;
            determined = true;
            for (int i = 1; i < n; ++i) {
                if (boards[i][j] != boards[0][j]) {
                    determined = false;
                }
            }
            if (determined) return "" + boards[0][j];
        }

        // 检查对⻆线:左上->右下
        if (boards[0][0] != ' ') {
            int i = 1;
            int j = 1;
            determined = true;
            while (i < n && j < n) {
                if (boards[i][j] != boards[0][0]) {
                    determined = false;
                    break;
                }
                i++;
                j++;
            }
            if (determined) return boards[0][0] + "";
        }

        // 检查对⻆线：左下->右上
        if (boards[n-1][0] != ' ') {
            int i = n-2;
            int j = 1;
            determined = true;
            while (i >= 0 && j < n) {
                if (boards[i][j] != boards[n-1][0]) {
                    determined = false;
                    break;
                }
                i--;
                j++;
            }
            if (determined) return "" + boards[n-1][0];
        }

        // 上⾯没有找到哪⽅赢，判定游戏是否还能继续玩，即还有没有空格
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (boards[i][j] == ' ') return "Pending";
            }
        }
        // 游戏结束了，平局
        return "Draw";
    }

}
