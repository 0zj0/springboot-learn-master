package com.algorithm.algorithm.tree.binary;

import lombok.ToString;

import java.util.Stack;

/**
 * 二叉查找树定义：又称为是二叉排序树（Binary Sort Tree）或二叉搜索树。二叉排序树或者是一棵空树，或者是具有下列性质的二叉树：
 * 　　1) 若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 * 　　2) 若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
 * 　　3) 左、右子树也分别为二叉排序树；
 * 　　4) 没有键值相等的节点。
 * @author zhangjie
 * @date 2019/3/13 11:05
 */
@ToString
public class BinaryTree {

    private int value;  //节点值
    private BinaryTree leftBinaryTree;  //左节点
    private BinaryTree rightBinaryTree; //右节点

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTree getLeftBinaryTree() {
        return leftBinaryTree;
    }

    public void setLeftBinaryTree(BinaryTree leftBinaryTree) {
        this.leftBinaryTree = leftBinaryTree;
    }

    public BinaryTree getRightBinaryTree() {
        return rightBinaryTree;
    }

    public void setRightBinaryTree(BinaryTree rightBinaryTree) {
        this.rightBinaryTree = rightBinaryTree;
    }

    //初始化构造方法
    public BinaryTree(int value){
        this.value = value;
        this.leftBinaryTree = null;
        this.leftBinaryTree = null;
    }

    private static BinaryTree root = null;

    //插入
    public static void insert(int value){
        BinaryTree insertTree = new BinaryTree(value);
        BinaryTree curr = root;
        if(root == null || root.getValue() == 0){
            root = insertTree;
            return;
        }
        while (true){
            if(value < curr.getValue()){
                if(curr.getLeftBinaryTree() == null){
                    curr.setLeftBinaryTree(insertTree);
                    return;
                }else{
                    curr = curr.getLeftBinaryTree();
                }
            }else if(value > curr.getValue()){
                if(curr.getRightBinaryTree() == null){
                    curr.setRightBinaryTree(insertTree);
                    return;
                }else{
                    curr = curr.getRightBinaryTree();
                }
            }else{
                return;
            }
        }
    }


    /**
     * 前序(先序)遍历:根->左->右（递归实现）
     * 输出某个文件夹下所有文件名称(可以有子文件夹)---用先序遍历实现：
     *      如果是文件夹，先输出文件夹名，然后再依次输出该文件夹下的所有文件(包括子文件夹)，如果有子文件夹，
     * 则再进入该子文件夹，输出该子文件夹下的所有文件名。
     * @param tree
     */
    public static void frontOrder_Recursion(BinaryTree tree){
        if(tree != null){
            System.out.print(tree.getValue()+"\t");
            frontOrder_Recursion(tree.getLeftBinaryTree());
            frontOrder_Recursion(tree.getRightBinaryTree());
        }
    }

    //先序遍历（栈实现）
    public static void frontOrder_Stack(BinaryTree tree){
        // 栈 ： 后近先出
        Stack<BinaryTree> stack = new Stack<BinaryTree>();
        BinaryTree curr = tree;
        if(curr != null){
            stack.push(curr);
            while (!stack.isEmpty()){
                curr = stack.pop();
                System.out.print(curr.getValue()+"\t");
                if(curr.getRightBinaryTree() != null){
                    stack.push(curr.getRightBinaryTree());
                }
                if(curr.getLeftBinaryTree() != null){
                    stack.push(curr.getLeftBinaryTree());
                }
            }
        }
    }

    /**
     * 中序遍历：左->根->右
     *  从小到大输出数据---中序遍历实现
     * @param tree
     */
    public static void MidenOrder_Recursion(BinaryTree tree){
        if(tree != null){
            MidenOrder_Recursion(tree.getLeftBinaryTree());
            System.out.print(tree.getValue()+"\t");
            MidenOrder_Recursion(tree.getRightBinaryTree());
        }
    }

    /**
     * 中序遍历(栈实现)：
     * @param tree
     */
    public static void MidenOrder_Stack(BinaryTree tree){
        // 栈 ： 后近先出
        Stack<BinaryTree> stack = new Stack<BinaryTree>();
        BinaryTree curr = tree;
        while (!stack.isEmpty() || curr != null){
            if(curr != null){
                stack.push(curr);
                curr = curr.getLeftBinaryTree();    //左节点
            }else{
                curr = stack.pop();                 //出栈访问
                System.out.print(curr.getValue()+"\t");
                curr = curr.getRightBinaryTree();   //右节点
            }
        }
    }


    /**
     * 后序遍历（递归实现）：左->右->根
     * @param tree
     */
    public static void traversalOrder_Recursion(BinaryTree tree){
        if(tree != null){
            traversalOrder_Recursion(tree.getLeftBinaryTree());
            traversalOrder_Recursion(tree.getRightBinaryTree());
            System.out.print(tree.getValue()+"\t");
        }
    }

    /**
     * 后序遍历(双栈实现)：
     * @param tree
     */
    public static void traversalOrder_DoubleStack(BinaryTree tree){
        // 栈 ： 后近先出
        Stack<BinaryTree> stack = new Stack<BinaryTree>();
        Stack<BinaryTree> outStack = new Stack<BinaryTree>();
        BinaryTree curr = tree;
        while (!stack.isEmpty() || curr != null ){
            if(curr != null){
                outStack.push(curr);
                stack.push(curr);
                curr = curr.getRightBinaryTree();
            }else{
                curr = stack.pop();
                curr = curr.getLeftBinaryTree();
            }
        }
        while (outStack.size() > 0){
            curr = outStack.pop();
            System.out.print(curr.getValue()+"\t");
        }
    }

    //搜索
    public static BinaryTree search(BinaryTree tree,int value){
        if(tree == null){
            return null;
        }
        if(tree.getValue() == value){
            return tree;
        }
        if(value < tree.getValue()){
            return search(tree.getLeftBinaryTree(),value);
        }
        if(value > tree.getValue()){
            return search(tree.getRightBinaryTree(),value);
        }
        return null;
    }

    public static void main(String[] args) {
        //8-3-1-6-4-7-10-14-13
        insert(8);
        insert(3);
        insert(1);
        insert(10);
        insert(6);
        insert(7);
        insert(4);
        insert(14);
        insert(15);
        insert(13);
        //System.out.println(root.toString());
        System.out.print("先序遍历（递归实现）：");
        frontOrder_Recursion(root);
        System.out.println();
        System.out.print("先序遍历（栈实现）：");
        frontOrder_Stack(root);
        System.out.println();

        System.out.print("中序遍历（递归实现）：");
        MidenOrder_Recursion(root);
        System.out.println();
        System.out.print("中序遍历（栈实现）：");
        MidenOrder_Stack(root);
        System.out.println();

        System.out.print("后序遍历（递归实现）：");
        traversalOrder_Recursion(root);
        System.out.println();
        System.out.print("后序遍历（双栈实现）：");
        traversalOrder_DoubleStack(root);
        System.out.println();

        BinaryTree searchTree = search(root,3);
        if(searchTree == null){
            System.out.println("searchTree:null");
        }else{
            System.out.println("searchTree:"+searchTree.toString());
        }

    }

}
