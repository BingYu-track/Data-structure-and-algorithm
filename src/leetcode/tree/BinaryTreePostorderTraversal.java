package leetcode.tree;

import java.util.*;

/**
 * @version 1.0
 * @Description: 145.非递归后序遍历(困难)
 * @author: bingyu
 * @date: 2019/11/22 22:09
 */
public class BinaryTreePostorderTraversal {


    //自己单独完成的解法：在中序遍历的基础上，用set集合存储已遍历完左子树的结点
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> set = new HashSet<>(); //用来存储是否左子树已遍历完的根结点
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null && !set.contains(cur)) { //这里set.contains(cur)是为了防止结点重复的放入
                stack.push(cur);
                cur = cur.left;
            }
            if (stack.size() == 0) {
                break;
            }
            cur = stack.peek();
            if (!set.contains(cur)) { //如果是第一次遇到该根结点，说明是左子树遍历完成；否则就是右子树遍历完成
                set.add(cur);
            }else {
                cur = stack.pop(); //执行到这里说明右子树遍历完成
                list.add(cur.val);
            }
            cur = cur.right;
        }
        return list;
    }

    //方法一(推荐)：用变量存储当前节点的上一次遍历的结点，并且将其与当前结点的右结点比较，相同则说明是从右结点过来的，否则是从左结点过来的
    public static List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode last = null; //用来记录上一次遍历的结点，如果当前节点的右节点和上一次遍历的节点相同，那就表明当前是从右节点过来的了
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode temp = stack.peek();
            //cur = stack.peek();
            //是否变到右子树
            if (temp.right != null && temp.right != last) { //这里如果当前节点有右子树并且右结点不等于上一次遍历的结点，则说明该结点是从其左结点过来的
                cur = temp.right;
            } else {
                list.add(temp.val); //如果当前结点没有右结点，也可以直接添加
                last = temp;
                stack.pop();
            }
        }
        return list;
    }

    //方法二: 只需要把每个节点 push 两次，然后判断当前 pop 节点和栈顶节点是否相同。
    //相同的话，就意味着是从左子树到的根节点。
    //不同的话，就意味着是从右子树到的根节点，此时就可以把节点加入到 list 中。
    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur == null) {
                continue;
            }
            if (!stack.isEmpty() && cur == stack.peek()) {
                stack.push(cur.right);
                stack.push(cur.right);
                stack.push(cur.left);
                stack.push(cur.left);
            } else {
                list.add(cur.val);
            }
        }
        return list;
    }

    //方法三(推荐)：另一种思路是反过来，从后往前开始添加；但这种解题方式不是严格的后序遍历解法
    public static List<Integer> postorderTraversal3(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        if(root == null) return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            root = stack.pop(); //弹出结点
            list.addFirst(root.val); //将结点放入头部位置
            if(root.left != null) { //先将左子树放入栈中，再将右子树放入栈中
                stack.push(root.left);
            }
            if(root.right != null) {
                stack.push(root.right);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(9);
        root.left = node1; root.right = node2;
        node1.left = node3; node1.right = node4;
        node2.left = node5; node2.right = node6;
        List<Integer> list = postorderTraversal2(root);
        System.out.println(list);
    }
}
