package rain.test.study20191201;

/**
 * 给定一个不为空的二叉搜索树和一个目标值 target，请在该二叉搜索树中找到最接近目标值 target 的数值。
 *
 * 注意：
 *
 * 给定的目标值 target 是一个浮点数
 * 题目保证在该二叉搜索树中只会存在一个最接近目标值的数
 * 示例：
 *
 * 输入: root = [4,2,5,1,3]，目标值 target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * 输出: 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/closest-binary-search-tree-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *  题解 二叉搜搜索树
 *  又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
 *  若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
 *  若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
 *  它的左、右子树也分别为二叉排序树。
 *
 */


public class ClosestValue {
    public int closestValue(TreeNode root, double target) {
        int ans = root.val;
        int level =0;

        if (root.val > target ){
            if(root.left  == null){
                return  root.val;
            }
            ans = closestValue(  root.left,   target);
            level++;
            System.out.println(ans+" ->>>>"+level+"  node value :"+root.left.val);

        }else{
            if(root.right  == null){
                return  root.val;
            }
            ans = closestValue(  root.right,   target);
            level++;
            System.out.println(ans+" ->>>>"+level+"  node value :"+root.right.val);

        }
        System.out.println("------>>>>>>>");
        ans = Math.abs(root.val -target) <Math.abs(ans-target) ? root.val:ans;//精髓
        //二分搜索树的特点(左<根<右)来快速定位，由于根节点是中间值 ,由于跟节点是中间值




        return  ans;

    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(4);

        TreeNode root1 = new TreeNode(2);
        TreeNode root2 = new TreeNode(5);
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(3);

        root.left =root1;
        root.right = root2;

        root1.left=root3;
        root1.right=root4;
        new ClosestValue().closestValue(root,3.714286);


    }

}
