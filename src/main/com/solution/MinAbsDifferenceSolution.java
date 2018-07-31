package com.solution;

public class MinAbsDifferenceSolution {

    private int minDiff = Integer.MAX_VALUE;
    Solution.TreeNode prev;


    private int minAbsoluteDifference(Solution.TreeNode root) {
        traverseInOrder(root);
        return minDiff;
    }

    private void traverseInOrder(Solution.TreeNode node) {
        if (node == null) {
            return;
        }
        traverseInOrder(node.left);
        minDiff = (prev == null) ? minDiff : Math.min(minDiff, Math.abs(node.val - prev.val));
        prev = node;
        traverseInOrder(node.right);
    }

    public static void main(String[] args) {
        Solution.TreeNode root = new Solution.TreeNode(15);

        root.left = new Solution.TreeNode(8);
        root.right = new Solution.TreeNode(35);

        root.left.left = new Solution.TreeNode(5);
        root.left.right = new Solution.TreeNode(9);

        root.left.left.left = new Solution.TreeNode(1);
        root.left.left.right = new Solution.TreeNode(7);

        root.right.left = new Solution.TreeNode(25);
        root.right.right = new Solution.TreeNode(38);

        MinAbsDifferenceSolution solution = new MinAbsDifferenceSolution();

        System.out.println("Min difference is: " + solution.minAbsoluteDifference(root));
    }


}
