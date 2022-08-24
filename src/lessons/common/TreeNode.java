package lessons.common;

/**
 * @version 1.0
 * @Description:
 * @author: bingyu
 * @date: 2022/8/24
 */
public class TreeNode {
     public int val;
     public TreeNode left;
     public TreeNode right;
    public TreeNode() {

     }
    public TreeNode(int val) {
         this.val = val;
     }
     public TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }

 }