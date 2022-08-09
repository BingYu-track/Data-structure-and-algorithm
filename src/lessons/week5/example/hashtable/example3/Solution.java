package lessons.week5.example.hashtable.example3;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @version 1.0 移出两个集合中的相同元素
 * @Description: 给定a1和a2两个数组，将a1中出现在a2中的数字去掉(腾讯22届暑期实习)
 * @author: bingyu
 * @date: 2022/8/8
 */
public class Solution {

    public static void main(String[] args) {
        int[] a1 = {9,8,4,3,4,6,2};
        int[] a2 = {3,4,5,7,2};
        int i = removeDupZgSolved(a1, a2);
        int[] ints = Arrays.copyOfRange(a1, 0, i);
        System.out.println(Arrays.toString(ints));
    }


    //返回数组a1新的长度
    /**
     * 我的思路: 就是通过hash表来判断，然后将a1里包含a2的元素移动到a1数组末尾
     */
    public static int removeDup(int[] a1,int[] a2) {
        HashSet<Integer> hashTable = new HashSet<>();
        for (int i=0;i<a2.length;i++) {
            hashTable.add(a2[i]);
        }
        int length = a1.length;
        int k = 0;
        for (int j=0;j<length - k;) {
            if (hashTable.contains(a1[j])) { //
                int temp = a1[j];
                a1[j] = a1[length - 1 - k];
                a1[length - 1 - k] = temp;
                k++;
            }else {
                j++;
            }
        }
        return length - k;
    }

    //争哥解法
    public static int removeDupZgSolved(int[] a1,int[] a2) {
        HashSet<Integer> hashTable = new HashSet<>();
        int n1 = a1.length;
        int n2 = a2.length;
        for (int i = 0;i < n2;i++) {
            hashTable.add(a2[i]);
        }
        int k = 0;
        for (int i = 0;i<n1;i++) {
            if (!hashTable.contains(a1[i])) { //TODO: 哈希表遇到不包含的a1元素，就将对应的元素挪移到前面重复的位置
                a1[k] = a1[i];
                k++;
            }
        }
        return k;
    }

}
