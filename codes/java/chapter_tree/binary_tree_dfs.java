/**
 * File: binary_tree_dfs.java
 * Created Time: 2022-11-25
 * Author: krahets (krahets@163.com)
 */

package chapter_tree;

import utils.*;
import java.util.*;

public class binary_tree_dfs {
    // 初始化列表，用于存储遍历序列
    static ArrayList<Integer> list = new ArrayList<>();

    /* 前序遍历-递归 */
    static void preOrderRecursion(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：根节点 -> 左子树 -> 右子树
        list.add(root.val);
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    /* 中序遍历-递归 */
    static void inOrderRecursion(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树 -> 根节点 -> 右子树
        inOrderRecursion(root.left);
        list.add(root.val);
        inOrderRecursion(root.right);
    }

    /* 后序遍历-递归 */
    static void postOrderRecursion(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树 -> 右子树 -> 根节点
        postOrderRecursion(root.left);
        postOrderRecursion(root.right);
        list.add(root.val);
    }

    /* 前序遍历-迭代 */
    static void preOrderIterate(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        while(root != null || !nodeStack.isEmpty()){
            while (root != null){
                list.add(root.val);
                nodeStack.push(root);
                root = root.left;
            }
            if (!nodeStack.isEmpty()) {
                root = nodeStack.pop();
                root = root.right;
            }
        }
    }

    /* 中序遍历-迭代 */
    static void inOrderIterate(TreeNode root) {
        Stack<TreeNode> nodeStack = new Stack<>();
        while(root != null || !nodeStack.isEmpty()){
            while (root != null){
                nodeStack.push(root);
                root = root.left;
            }
            if (!nodeStack.isEmpty()) {
                root = nodeStack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
    }

    /* 后序遍历-迭代 */
    static void postOrderIterate(TreeNode root) {
        //用于表示是从左子树返回到父节点
        int left = 1;
        //用于表示是从右子树返回到父节点
        int right = 2;
        //用于存放树节点
        Stack<TreeNode> nodeStack = new Stack<>();
        //用于存放从左子树节点还是右子树节点返回到父节点的标记
        Stack<Integer> markStack = new Stack<>();

        while(root != null || !nodeStack.isEmpty()){
            while (root!=null){
                //将节点压栈并标记为左节点
                nodeStack.push(root);
                markStack.push(left);
                root = root.left;
            }
            while(!nodeStack.isEmpty() && markStack.peek() == right){
                //如果是从右子节点返回到父节点，直接输出
                markStack.pop();
                list.add(nodeStack.pop().val);
            }
            if(!nodeStack.isEmpty() && markStack.peek() == left){
                //如果是从左子节点返回到父节点，标记为右节点，并对右节点进行操作。
                markStack.pop();
                markStack.push(right);
                root = nodeStack.peek().right;
            }
        }
    }

    /* 后序遍历-迭代 */
    static void postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;  // 用于记录上一个访问的节点

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();

            if (prev == null || prev != curr) {  // 如果prev为空或者prev与curr不是父子关系
                list.add(0, curr.val);  // 将curr的值插入到结果列表的开头
            }

            if (curr.left != null) {
                stack.push(curr.left);
            }

            if (curr.right != null) {
                stack.push(curr.right);
            }

            prev = curr;  // 更新prev为当前节点
        }
    }

    /* 层序遍历-迭代 */
    static void levelOrderIterate(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = null;//当前访问的节点
        stack.push(root);
        while (!stack.isEmpty()){
            cur = stack.pop();
            list.add(root.val);
            if(null != cur.right){
                stack.push(cur.right);
            }
            if(null != cur.left){
                stack.push(cur.left);
            }
        }
    }

    public static void main(String[] args) {
        /* 初始化二叉树 */
        // 这里借助了一个从数组直接生成二叉树的函数
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        //TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7,8));
        //TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2,1, 3, 4, 5, 6, 7,8,9,10));
        System.out.println("\n初始化二叉树\n");
        PrintUtil.printTree(root);

        /* 前序遍历-递归 */
        list.clear();
        preOrderRecursion(root);
        System.out.println("\n前序遍历的节点打印序列 = " + list);

        /* 中序遍历-递归 */
        list.clear();
        inOrderRecursion(root);
        System.out.println("\n中序遍历的节点打印序列 = " + list);

        /* 后序遍历-递归 */
        list.clear();
        postOrderRecursion(root);
        System.out.println("\n后序遍历的节点打印序列 = " + list);

        /* 前序遍历-迭代 */
        list.clear();
        preOrderIterate(root);
        System.out.println("\n前序遍历的节点打印序列 = " + list);

        /* 中序遍历-迭代 */
        list.clear();
        inOrderIterate(root);
        System.out.println("\n中序遍历的节点打印序列 = " + list);

        /* 后序遍历-迭代 */
        list.clear();
        postOrderIterate(root);
        System.out.println("\n后序遍历的节点打印序列 = " + list);

        /* 后序遍历-迭代 */
        list.clear();
        postorderTraversal(root);
        System.out.println("\n后序遍历的节点打印序列 = " + list);

        /* 层序遍历-迭代 */
        list.clear();
        levelOrderIterate(root);
        System.out.println("\n层序遍历的节点打印序列 = " + list);
    }
}
