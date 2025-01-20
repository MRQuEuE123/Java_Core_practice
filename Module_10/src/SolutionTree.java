public class SolutionTree {
    static int answer = 0;

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        boolean leftSum = hasPathSum(root.left, targetSum - root.val);
        boolean rightSum = hasPathSum(root.right, targetSum - root.val);

        return leftSum || rightSum;
    }

    public static int maxPathSum(TreeNode root) {
        helper(root);
        return answer;
    }

    static int helper(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int maxLeftPath = Math.max(helper(node.left), 0);
        int maxRightPath = Math.max(helper(node.right), 0);
        answer = Math.max(answer, maxLeftPath + maxRightPath + node.val);
        return Math.max(maxLeftPath, maxRightPath) + node.val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(-20);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);
        int result = maxPathSum(root);
        int targetSum = 18;
        System.out.println("Максимальный путь в дереве: " + result);
        System.out.println("есть ли путь от корня до листа равный  " + targetSum + " :" + hasPathSum(root, targetSum));

    }

}
