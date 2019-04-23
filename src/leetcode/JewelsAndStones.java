package leetcode;

/**
 * @version 1.0
 * @Description: 宝石与石头
 * @author: bingyu
 * @date: 2019/4/23 20:10
 */
public class JewelsAndStones {

    //从字符串j得到不同字符  执行用时：26ms  内存消耗;36.5MB
    public int numJewelsInStones(String J, String S) {
        int count = 0;
        int index;
        while (J.length() != 0){
            String s = J.substring(0, 1); //获取第一个字符
            J = J.replaceAll(s, ""); //去除所有当前字符
            String temp = S;
            while (!"".equals(temp)){
                index = temp.indexOf(s);
                if(index == -1){
                    break;
                }
                temp = temp.substring(index+1, temp.length());
                count ++;
            }
        }
        return count;
    }

    //执行用时 : 4 ms, 内存消耗 : 34 MB
    public int numJewelsInStones2(String J, String S) {
        int max = 0;
        for (int i = 0; i < S.length(); i++) {
            if (J.contains(String.valueOf(S.charAt(i)))) {
                max += 1;
            }
        }
        return max;
    }


    public static void main(String[] args){
        JewelsAndStones stones = new JewelsAndStones();
        int i = stones.numJewelsInStones("cdefa", "bklfkljfhsfdasadasdasdsdrtadw");
        System.out.println(i);
    }
}
