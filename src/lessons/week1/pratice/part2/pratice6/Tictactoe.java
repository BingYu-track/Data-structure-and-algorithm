package lessons.week1.pratice.part2.pratice6;

/**
 * @version 1.0 井字游戏
 * @Description: 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 * 以下是井字游戏的规则：玩家轮流将字符放入空位（" "）中。 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。 "X"和"O"只允许放置在空位中，
 * 不允许对已放有字符的位置进行填充。 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。 当所有位置非空时，
 * 也算为游戏结束。 如果游戏结束，玩家不允许再放置字符。 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；如果游戏以平局结束，
 * 则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 *
 * 示例 2：
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 *
 * 示例 3：
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 *
 * 提示：
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 * @author: bingyu
 * @date: 2022/1/7
 */
public class Tictactoe {

    /**
     ["OXXOX",
      "XXOXO",
      "OOXOO",
      "OXXXX",
      "OXOOO"
     ]

     */
    public static void main(String[] args) {
        String[] board = {"OXXOX","XXOXO","OOXOO","OXXXX","OXOOO"};
        String tictactoe = tictactoe(board);
        System.out.println(tictactoe);
    }

    //成功条件: 1.一个列相同字符  2.一行相同字符  3.对角线相同字符
    //我的解法思路:
    //1.就是判断数组中是否有"列相同字符"、"行相同字符"、"对角线相同字符"，一旦有就返回对应字符
    //2.如果没有空位，即没空格字符，而且，行、列、对角线全部都没有相同字符，则说明平局
    //3.如果有空位，即有空格字符，并且行、列、对角线全部都没有相同字符，则说明游戏还没结束
    //TODO:推荐我自己的写法更好
    public static String tictactoe(String[] board) {
        int length = board.length;
        for (String s : board) {
            if (s.length()!=length) return ""; //长度不一致，说明不是N*N
        }

        //1.判断是否还有空格
        boolean hasBlank = hasBlank(board);

        //2.如果只有1行
        if (length == 1) {
            if (hasBlank) return "Pending";
            else {
                //没有空位，肯定是第一个字符，因为它是个n*n的矩阵，长度为1的话，就只能有一个元素
                char[] chars = board[0].toCharArray();
                return String.valueOf(chars[0]);
            }
        }

        //3.判断行、列、对角线字符的任何一个是否相同，如果有相同，返回相同的字符，如果还没有返回空字符串""
        String result = judge(board);

        if (!result.equals("")) { //不为空，说明已经产生赢家
            return result;
        }

        //执行到这里说明没有产生赢家
        //4.有空格，说明当前游戏还在继续
        if (hasBlank) {
            result = "Pending";
        } else {
            //没有空格说明平局
            result = "Draw";
        }

        return result;
    }

    /**
     ["   ",
      " XO",
      "X O"]

     ["OOXX",
      "XXOX",
      "OXOX",
      "OXOX"
     ]

     */


    public static boolean hasBlank(String[] board) {
        boolean flag = false;
        for (int i = 0;i<board.length;i++) {
            char[] chars = board[i].toCharArray();
            for (int j = 0;j<chars.length;j++) {
                if (chars[j] == ' ') {
                    flag = true;
                    return flag;
                }
            }
        }
        return flag;
    }

    /**
     * 判断行、列、对角线字符的任何一个是否相同，如果有相同，返回相同的字符，如果还没有返回空字符串""
     * @param board
     * @return
     */
    public static String judge(String[] board) {
        String result = "";
        int rowLen = board.length; //获取有多少行
        int colLen = board[0].length(); //获取有多少列

        //1.先判断每行是否有相同的字符
        for (int i = 0;i<rowLen;i++) {
            char[] chars = board[i].toCharArray(); //获得当前行的数组
            boolean flag = false;
            int j = 0;
            char c = chars[j]; //获取第一列的字符
            for (;j+1 < colLen;j++) {
                flag = false;
                if (chars[j+1] == c && c!=' ') { //只要当前行的所有字符和第一个字符相同
                    flag = true;
                }else {
                    break; //一旦这行有不同的字符，或者有空格，直接跳过，去到下一行检查
                }
            }
            //执行到这里一行已经判断完了,为true说明有一行全相同
            if (flag) return String.valueOf(c);
        }

        //TODO:重难点----2.判断每列是否有相同的字符
        for (int i = 0;i<colLen;i++) { //i为列
            int j = 0; //j为行
            char[] chars = board[j].toCharArray();
            //执行到这里说明当前元素不为空格
            char c = chars[i]; //在列中第一个不为空格的字符
            boolean flag = false;
            //再将这个第一个不为空格的字符依次与下面的字符比较，看是否有相同
            for (;j+1<rowLen;j++) {
                chars = board[j+1].toCharArray(); //获取每行的数组
                flag = false;
                if (c == chars[i] && chars[i]!=' ') { //从每行数组获取当前列元素
                    flag = true;
                }else {
                    break;
                }
            }
            //执行到这里说明一列结束，开始下一列
            if (flag) return String.valueOf(c); //为true说明有一列全相同
        }

        //3.判断2个对角线是否有相同的字符
        /**
         左对角线:
         board[0][0]
         board[i][i]
         board[...][...]
         board[length - 1][length - 1]

         右对角线:
         board[0][length - 1]
         board[i][length - 1-i]
         board[...][...]
         board[length - 1][0]
         */
        //(1).判断左对角线(行列同时递增)
        int i = 0;
        char[] chars = board[i].toCharArray();
        //执行到这说明当对角线当前位置的元素是非空的
        char c = chars[i];
        boolean flag = false;
        for (i = i+1;i<rowLen;i++) {
            flag = false;
            chars = board[i].toCharArray();
            if (c == chars[i] && chars[i]!=' ') {
                flag = true;
            }else {
                break;
            }
        }
        if (flag) return String.valueOf(c);

        //(2).判断右对角线(行增，列减)
        int j = 0;
        int k = rowLen - 1 - j;
        chars = board[j].toCharArray();
        c = chars[k];
        flag = false;
        for (j = j+1,k = k-1;j<rowLen && k>=0;j++,k--) {
            flag = false;
            chars = board[j].toCharArray();
            if (c==chars[k] && chars[k]!=' ') {
                flag = true;
            }else {
                break;
            }
        }
        if (flag) return String.valueOf(c);
        return result;
    }

}
