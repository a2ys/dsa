class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {}

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Diameter {

    int height(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        int midDiameter = height(root.left) + height(root.right);

        return Math.max(leftDiameter, Math.max(rightDiameter, midDiameter));
    }
}
