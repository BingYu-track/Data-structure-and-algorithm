package lessons.week6.pratice.btree.part2.pratice10;

/**
 * @version 1.0
 * @Description: 剑指 Offer 33. 二叉搜索树的后序遍历序列 --争哥解法
 * @author: bingyu
 * @date: 2022/9/21
 */
public class ZgSolved {

    public static void main(String[] args) {
        int[] postorder = {1,3,2,6,9,8,5};
        boolean result = verifyPostorder(postorder);
        System.out.println(result);
    }

    /*
     争哥解法
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：38.9 MB, 在所有 Java 提交中击败了63.89%的用户
    */
    public static boolean verifyPostorder(int[] postorder) {
        return myVerify(postorder, 0, postorder.length-1);
    }

    //TODO: 推荐该解法
    private static boolean myVerify(int[] postorder, int i, int j) {
        if (i >= j) return true;
        // postorder[j]是根节点,先分离出左⼦树[i, k-1]
        int k = i;
        while (k < j && postorder[k] < postorder[j]) { //找出第一个大于根节点的元素，
            k++;
        }
        //TODO:核心判断逻辑--执行到这里，如果是二叉搜索树的话[i,k-1]范围的所有元素都是左子树，都应该小于root值; [K,j-1]范围的都是右子树，都应该大于root值
        // 验证[k, j-1]满⾜有⼦树的要求，都⼤于postorder[j]
        int p = k;
        while (p < j) {
            if (postorder[p] < postorder[j]) { //出现右子树节点小于根节点的，说明不是二叉搜索树，返回false
                return false;
            }
            p++;
        }
        // 递归验证左右⼦树是否满⾜BST的要求
        boolean leftValid = myVerify(postorder, i, k-1); //继续向下校验左子树
        if (leftValid == false) return false;
        boolean rightValid = myVerify(postorder, k, j-1); //向下校验右子树
        return rightValid;
    }

}
