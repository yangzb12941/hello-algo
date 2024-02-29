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
        if (root == null)
            return;
        // 访问优先级：根节点 -> 左子树 -> 右子树
        TreeNode curNode = root;
       do{
           //先访问节点的左子树
           if(null != curNode.left){
               nodeStack.push(curNode);
               list.add(curNode.val);
               curNode = curNode.left;
           }else{
               //再访问节点的右子树
               if(null != curNode.right){
                   list.add(curNode.val);
                   curNode = curNode.right;
                   nodeStack.push(curNode);
               }else{
                   //如果当前节点左右子树都没有，这说明是叶子节点。
                   //需要从栈中去除其父节点。
                   list.add(curNode.val);
                   curNode = nodeStack.pop();
                   //当前节点存在右子树
                   if(null != curNode.right){
                       //当栈底的节点是根节点的时候，需要做一次栈底节点
                       //替换，替换为根节点下，右子树的节点。
                       if(curNode == root){
                           nodeStack.push(curNode.right);
                       }
                       curNode = curNode.right;
                   }else{
                       //不存在则弹出其父节点或其本身
                       curNode = nodeStack.pop();
                   }
               }
           }
       }while (!nodeStack.isEmpty());
    }

    /* 中序遍历-迭代 */
    static void inOrderIterate(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树 -> 根节点 -> 右子树
        inOrderIterate(root.left);
        list.add(root.val);
        inOrderIterate(root.right);
    }

    /* 后序遍历-迭代 */
    static void postOrderIterate(TreeNode root) {
        if (root == null)
            return;
        // 访问优先级：左子树 -> 右子树 -> 根节点
        postOrderIterate(root.left);
        postOrderIterate(root.right);
        list.add(root.val);
    }

    public static void main(String[] args) {
        /* 初始化二叉树 */
        // 这里借助了一个从数组直接生成二叉树的函数
        TreeNode root = TreeNode.listToTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
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

        /* 前序遍历-递归 */
        list.clear();
        preOrderIterate(root);
        System.out.println("\n前序遍历的节点打印序列 = " + list);
    }
}
