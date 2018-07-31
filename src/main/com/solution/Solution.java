package com.solution;

import java.util.*;

public class Solution {


    public static void main(String[] args) {
        TreeNode root = new TreeNode(15);

        root.left = new TreeNode(8);
        root.right = new TreeNode(35);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(9);

        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(7);

        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(38);

        Solution solution = new Solution();

//        System.out.println("Printing in-order: ");
//        solution.printInOrder(root);
//
//        System.out.println();
//
//        System.out.println("Printing pre-order: ");
//        solution.printPreOrder(root);
//
//        System.out.println();
//        System.out.println("Printing post-order: ");
//        solution.printPostOrder(root);
//
//        // queue using two stacks
//        solution.queueUsingStacks(Arrays.asList(1,2,3,4,5));


//        System.out.println("Min difference is: " + solution.minAbsoluteDifference(root));
//
//        String s = "1";
//        System.out.println("val is: " + s.substring(1, s.length()));
//
//        Integer count = 1;
//        if(count != null) {
//            count = count++;
//        }
//        System.out.println("count is: " + count);

        String s1 ="";
        String s2 = "a";
        String val = (s1 == "") ? (s2 =="a")? "n" : "m" : "k";

        System.out.println("val: " + val);


    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

    }


    private int minAbsoluteDifference(TreeNode root) {
        Deque<Integer> stack = new LinkedList<>();
        return traverseInOrder(root, stack, Integer.MAX_VALUE);
    }


    private int traverseInOrder(TreeNode node, final Deque<Integer> stack, int currentMin) {
        if (node == null) {
            return currentMin;
        }
        int leftMinDiff = traverseInOrder(node.left, stack, currentMin);
        int diff = Integer.MAX_VALUE;
        if (!stack.isEmpty()) {
            int lastVal = stack.pop();
            diff = Math.abs(lastVal - node.val);
        }
        stack.push(node.val);
        int rightMinDiff = traverseInOrder(node.right, stack, currentMin);
        return Math.min(diff, Math.min(leftMinDiff, rightMinDiff));
    }


    private void printInOrder(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            // keep pushing left sub-tree to stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // current must be null here
            current = stack.pop();
            System.out.print(current.val + " ");
            current = current.right;
        }
    }

    private void printPreOrder(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            // keep pushing left sub-tree to stack
            while (current != null) {
                System.out.print(current.val + " ");
                stack.push(current);
                current = current.left;
            }

            // current must be null here
            current = stack.pop();
            current = current.right;
        }
    }

    public void printPostOrder(TreeNode root) {
        // 2 empty stacks s1 and s2
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();

        TreeNode current = root;
        stack1.push(current);

        while (!stack1.isEmpty()) {
            current = stack1.pop();
            stack2.push(current);

            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }


    public void queueUsingStacks(List<Integer> list) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        list.forEach(s1::push);

        while (!s1.isEmpty()) {
            Integer val = s1.pop();
            s2.push(val);
        }

        System.out.println();
        while (!s2.isEmpty()) {
            System.out.println(s2.pop() + " ");
        }
    }


}
