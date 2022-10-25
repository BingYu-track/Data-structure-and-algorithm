package lessons.week6.pratice.btree.part1.pratice17;

import lessons.common.TreeNode;

/**
 * @version 1.0
 * @Description: 验证二叉搜索树--重复练习
 * @author: bingyu
 * @date: 2022/9/21
 */
public class RepeatPratice {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(32);
        TreeNode node1 = new TreeNode(26);
        TreeNode node2 = new TreeNode(47);
        TreeNode node3 = new TreeNode(19);
        TreeNode node4 = new TreeNode(56);
        TreeNode node5 = new TreeNode(27);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        node3.right = node5;
        boolean validBST = isValidBST2(root);
        System.out.println(validBST);
    }


    /*
    TODO: 推荐该方法，一次性解决
     校验是否是搜索二叉树，就是在任意一个节点中都是其左子树的最大值小于根节点；右子树的最小值大于根节点
     执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     内存消耗：41 MB, 在所有 Java 提交中击败了58.62%的用户
    */
    private static boolean isValidBST2(TreeNode root) {
        dfs(root);
        return isValid;
    }

    private static boolean isValid = true;

    //用数组来记录当前子树的最大值和最小值,数组中的第一个元素记录树的最小值，第二个元素记录树的最大值
    private static int[] dfs(TreeNode root) {
        if (root == null) return null;
        int[] result = null; //执行到这里说明root不为null

        int[] dfsl = dfs(root.left);
        if (dfsl!=null && dfsl[1] >= root.val) { //左子树最大值大于等于根节点，不是二叉搜索树，直接返回
            isValid = false;
            return null;
        }

        int[] dfsr = dfs(root.right);
        if (dfsr!=null && dfsr[0] <= root.val) { //右子树最小值小于等于根节点，不是二叉搜索树，直接返回
            isValid = false;
            return null;
        }

        if (dfsl != null && dfsr != null) { //左右子树均不为null
            result = new int[2];
            int min = Math.min(dfsl[0], dfsr[0]);
            int max = Math.max(dfsl[1], dfsr[1]);
            result[0] = min;
            result[1] = max;
        }else if (dfsl == null && dfsr != null) { //左子树为null，右子树不为null，将result里的最小值和根节点比较
            result = dfsr;
            int min = Math.min(dfsr[0], root.val);
            result[0] = min;
        }else if (dfsl!=null) { //右子树为null，左子树不为null,将result里的最大值和根节点比较
            result = dfsl;
            int max = Math.max(dfsl[1], root.val);
            dfsl[1] = max;
        }else {
            //执行到这里说明左右子树均为null，就返回唯一存在的根节点
            result = new int[2];
            result[0] = root.val;
            result[1] = root.val;
        }
        //执行到这里得到了root的下面的最大值和最小值
        return result;
    }








    private static boolean isValidBST(TreeNode root) {
        if (root == null) return true; //如果是空节点，返回true
        int[] result = verifBST(root);
        return result[0] == 1;
    }

    //[0]表示树是否是二叉树,1表示是二叉搜索树，-1表示不是二叉搜索树
    //[1]表示树的最大值,[2]表示树的最小值;
    //TODO: 我独自写的代码，如何将该代码简化?
    private static int[] verifBST(TreeNode root) {
        if (root == null) return null; //到叶子节点
        int[] left = verifBST(root.left); //校验左子树，并返回其最大值和最小值
        if (left != null && left[0] == -1) return left; //校验左子树是否是二叉搜索树

        int[] right = verifBST(root.right); //校验右子树，并返回其最大值和最小值
        if (right != null && right[0] == -1) return right; //校验右子树是否是二叉搜索树
        int rootValue = root.val;

        int max = rootValue;
        int min = rootValue;
        //执行到这里，开始去左右子树的最大值和最小值
        if (left == null && right!=null) { //左子树为空,比较右子树和root的值
            int rightMax = right[1];
            int rightMin = right[2];
            if (rootValue >= rightMin) return new int[] {-1,0,0}; //root值比右子树的最小值还大，说明不是二叉搜索树
            max = rightMax; //右子树的最大值作为树的最大值
            min = rootValue; //因为没有左子树，因此最小值就是root值
        }else if (right == null && left!=null) { //右子树为空，比较左子树和root的值
            int leftMax = left[1];
            int leftMin = left[2];
            if (rootValue <= leftMax) return new int[] {-1,0,0}; //root值比左子树的最大值还小，说明不是二叉搜索树
            max = rootValue;//因为没有右子树，因此最大值就是root值
            min = leftMin; //最小值是左子树的最小值
        }else if (left!=null && right!=null) { //左右子树均不为空
            if (rootValue <= left[1] || rootValue >= right[2]) {
                //执行到这里说明不是二叉搜索树
                return new int[] {-1,0,0};
            }
            max = right[1]; //右子树的最大值作为树的最大值
            min = left[2]; //左子树的最小值作为树的最小值

        }
        //TODO left个right都为空的话，那么max.min直接取root值
        return new int[] {1,max,min};
    }






}
