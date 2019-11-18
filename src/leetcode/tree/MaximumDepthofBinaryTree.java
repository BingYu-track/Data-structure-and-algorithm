package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//104.求二叉树的最大深度(二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。)  简单
public class MaximumDepthofBinaryTree {
	
	
	//方法1：递归   耗时0ms
	public static int maxDepth(TreeNode root) {
		int depth = 0;
        if (root == null) {
        	return depth;
		}
		depth = 1;
		int ld = maxDepth(root.left); //获取左子树的最大深度
		int rd = maxDepth(root.right); //获取右子树的最大深度
		int max = Math.max(ld, rd); //根节点取左右子树中最大的那一个深度即可
		depth = depth + max;  //将跟节点的层数+子树的最大深度 = 当前树的最大深度
		return depth;
    }
	
	//方法2：是用队列进行BFS迭代 耗时1ms(广度优先，在二叉树中就是层次遍历)  思路：节点先入队，出队时得到其左右子树，再将左右子树入队，后面再出队；不断反复直到队列空为止
	public static int maxDepth2(TreeNode root) {
		if(root == null) {
	        return 0;
	    }
	    Queue<TreeNode> queue = new LinkedList<>();
	    queue.offer(root); //添加到末尾
	    int count = 0;
	    System.out.println(queue);
	    while(!queue.isEmpty()) {
	        int size = queue.size(); //保留当期层的节点数
	        while(size-- > 0) { //注意每次队列开始存储的都是一层节点，
	            TreeNode node = queue.poll(); //当前层节点出队
	            //下一层节点入队
	            if(node.left != null) {
	                queue.offer(node.left);
	            }
	            if(node.right != null) {
	                queue.offer(node.right);
	            }
	            System.out.println(queue);
	        }
	        count++; //执行到这说明队列已经将当前层的节点出对完毕，下层的所有节点已入队
	    }
	    return count;
	}
	
	//方法三： DFS迭代
	public static int maxDepth3(TreeNode root) {
		if(root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();
        stack.push(root);
        value.push(1);
        int depth = 0;
        System.out.println(stack);
        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int temp = value.pop();
            depth = Math.max(temp, depth);
            if(node.left != null) {
                stack.push(node.left);
                value.push(temp+1);
            }
            if(node.right != null) {
                stack.push(node.right);
                value.push(temp+1);
            }
            System.out.println(stack);
        }
        return depth;
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
		TreeNode rootf = new TreeNode(0);
		System.out.println(maxDepth(root));
		System.out.println("--------------------------");
		System.out.println(maxDepth3(root));
	}
}
