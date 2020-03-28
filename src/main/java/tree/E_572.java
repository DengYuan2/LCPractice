package tree;

// 另一个树的子树,检验t是否是s的子树（都非空）
//要理解好题意啊，看好给的两个例子
// https://leetcode-cn.com/problems/subtree-of-another-tree/description/
public class E_572 {
    public static void main(String[] args) {
        E_572 e = new E_572();
        General g = new General();
        int[] s= {3,4,5,1,2,655,655,655,655,0};
        int[] t={4,1,2};
        TreeNode tree1 = g.buildTree(s);
        TreeNode tree2 = g.buildTree(t);
        boolean res1 = e.isSubtree1(tree1, tree2);
        System.out.println("****"+res1);
        boolean res2 = e.isSubtree2(tree1, tree2);
        System.out.println(res2);
        boolean res3 = e.isSubtree3(tree1, tree2);
        System.out.println(res3);
    }


    //我的思路：先从根节点比较是否是子树（这用到递归）；如果不是，再从左、右节点比较（又是递归）
    public boolean isSubtree1(TreeNode s, TreeNode t) {
        boolean res = helper(s, t);
        //如果根节点没找到，找左边
        if (!res){
            if (s.left!=null){
                res=isSubtree1(s.left,t);
            }
        }
        //左边也没找到的话，再找右边
        if (!res){
            if (s.right!=null){
                res=isSubtree1(s.right,t);
            }
        }

        return res;

    }

    //检查以s为根节点，t是否是s的子树【即第一个比较的是s节点和t节点】，
    public boolean helper(TreeNode s,TreeNode t){
        //单独两个节点相比时，两种情况下可以返回true
        //1、都为空节点。最开始传入的这两个值必不为空。但递归比较时，可能会有某棵树的节点为空（主要是叶子节点已经比较完了）
        if (t==null&&s==null){
            return true;
        }
        //2、还没（或正好）遍历到叶子节点时
        boolean j2=s!=null&&t!=null&&s.val==t.val; //两节点的值相等

        if (j2){ //提前判断一下，如果为true,则再比较左右节点，减少不必要的递归
            return helper(s.right,t.right)&&helper(s.left,t.left);
        }else {
            return false;
        }
    }

    //大神的思路和我差不多，写法有区别
    public boolean isSubtree2(TreeNode s, TreeNode t){
        if (s==null){
            return false;
        }
        return isSubtreeWithRoot(s,t) ||isSubtree2(s.left,t)||isSubtree2(s.right,t);
    }

    private boolean isSubtreeWithRoot(TreeNode s,TreeNode t){
        if (t==null&&s==null){
            return true;
        }
        if (t==null || s==null){
            return false;
        }
        if (t.val!=s.val){
            return false;
        }
        return isSubtreeWithRoot(s.left,t.left)&&isSubtreeWithRoot(s.right,t.right);
    }

    //官网也有我这样的思路
    //还有一种思路是先序遍历，思想我理解了【对细节的处理很是牛逼】
    //https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode/
    public boolean isSubtree3(TreeNode s,TreeNode t){
        String t1 = preList(s, true);
        String t2 = preList(t, true);
        return t1.indexOf(t2)>=0;

    }

    public String preList(TreeNode s,boolean left){
        if (s==null){
            if (left){
                return "l";
            }else {
                return "r";
            }
        }
        return "#"+s.val+preList(s.left,true)+preList(s.right,false);

    }

}
