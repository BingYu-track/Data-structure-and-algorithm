package leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

//111.求二叉树的最小深度(最小深度是从根节点到最近叶子节点的最短路径上的节点数量，注意这里不包含根节点) 简单
public class MinimumDepthofBinaryTree {
	
	//方法1：递归
	public static int minDepth(TreeNode root){
		int depth = 0;
        if (root == null) {
			return depth;
		}
        depth = 1;
		if (root.left == null) { //没有左子树时只取右子树的最小深度
			int rd = minDepth(root.right);
			depth += rd;
		}else if (root.right == null) { //没有右子树时只取左子树的最小深度
			int ld = minDepth(root.left);
			depth += ld;
		}else  { //左右子树都有，取两树中最小的深度
			int ld = minDepth(root.left);
			int rd = minDepth(root.right);
			int min = Math.min(ld, rd);
			depth += min;
		} 
		return depth;
	}
	
	//方法2：BFS迭代 (待理解)
	public static int minDepthBFS(TreeNode root){
		int depth =0;
		if (root == null) {
			return depth;
		}
		depth = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		System.out.println(queue);
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) { //
				TreeNode temp = queue.poll();
				if(temp.left == null && temp.right == null){ //一旦遇到叶子结点，就立马返回目前的深度
					return depth;
				}
				if (temp.left != null) {
					queue.offer(temp.left);
				}
				if (temp.right != null) {
					queue.offer(temp.right);
				}
				System.out.println(queue);
			}
			depth ++;
		}
		return depth;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(20);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(7);
		root.left = node1; root.right = node2;
		node2.left = node3; node2.right = node4;
		TreeNode rootf = new TreeNode(1);
		TreeNode nodef = new TreeNode(2);
		rootf.left = nodef;
		System.out.println(minDepthBFS(root));
	}
}
