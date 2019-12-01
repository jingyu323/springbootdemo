package rain.test.study20191201;

/**
 * 给你一棵二叉搜索树（BST）、它的根结点 root 以及目标值 V。
 *
 * 请将该树按要求拆分为两个子树：其中一个子树结点的值都必须小于等于给定的目标值 V；另一个子树结点的值都必须大于目标值 V；树中并非一定要存在值为 V 的结点。
 *
 * 除此之外，树中大部分结构都需要保留，也就是说原始树中父节点 P 的任意子节点 C，假如拆分后它们仍在同一个子树中，那么结点 P 应仍为 C 的子结点。
 *
 * 你需要返回拆分后两个子树的根结点 TreeNode，顺序随意。
 *
 * 示例 1：
 *
 * 输入：root = [4,2,6,1,3,5,7], V = 2
 * 输出：[[2,1],[4,3,6,null,null,5,7]]
 * 解释：
 * 注意根结点 output[0] 和 output[1] 都是 TreeNode 对象，不是数组。
 *
 * 给定的树 [4,2,6,1,3,5,7] 可化为如下示意图：
 *
 *           4
 *         /   \
 *       2      6
 *      / \    / \
 *     1   3  5   7
 *
 * 输出的示意图如下：
 *
 *           4
 *         /   \
 *       3      6       和    2
 *             / \           /
 *            5   7         1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 */
public class SplitBST2 {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if(root == null){
            return new TreeNode[]{null,null};
        }else if(root.val <= V){

            TreeNode[] bns = splitBST(root.right, V);
            root.right = bns[0];
            bns[0] = root;
            System.out.println("res --->>> "+bns+" ,  root right -->>>"+root.right);
            return bns;
        } else {
            TreeNode[] bns = splitBST(root.left, V);
            System.out.println("res --->>> "+bns+" ,  root right -->>>"+root.left);
            root.left = bns[1];
            bns[1] = root;
            return bns;
        }


    }

    public static void main(String[] args) {

        //root = [4,2,6,1,3,5,7], V = 2
        TreeNode root = new TreeNode(4);

        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(5);
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(3);
        TreeNode root5 = new TreeNode(7);
        TreeNode root6 = new TreeNode(6);

        root.left=root1;
        root.right=root6;

        root1.left=root3;
        root1.right = root4;

        root6.left=root2;
        root6.right = root5;


        new SplitBST2().splitBST(root,2);


    }

}
