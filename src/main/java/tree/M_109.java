package tree;

import javax.swing.event.ListDataEvent;
import java.util.List;

// 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树
// https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/description/
public class M_109 {
    public static void main(String[] args) {
        M_109 m = new M_109();
        int[] arr = {-10, -3, 0, 5, 9}; //, 0, 5, 9
        General g = new General();
        ListNode listNode = g.buileNodeList(arr);
//        System.out.println(listNode);
//        TreeNode res1 = m.sortedListToBST1(listNode);
//        System.out.println(res1);
//        TreeNode res2 = m.sortedListToBST2(listNode);
//        System.out.println(res2);
        TreeNode res3 = m.sortedListToBST(listNode);
        System.out.println(res3);

    }

    //我的第一想法是：遍历单链表，将数值放到数组[用ArrayList，因为不知道长度]中，然后再用E_108的方法。官方有一个方法就是类似的
    // 官方的递归方法：同E_108思路一致，但由于没有下标，故用 todo 快慢指针
    // 根据该思路，先自己写一下；
    public TreeNode sortedListToBST1(ListNode head) {
        return sortedHelper(head);
    }

    public TreeNode sortedHelper(ListNode head) {
        ListNode slow = head;
        if (slow == null) {
            return null;
        }
        if (slow.next == null) {
            TreeNode node = new TreeNode(slow.val);
            return node;
        }
        ListNode fast = head;
        ListNode pre = null; //记录慢指针的前一个，因为递归时输入的链表，最后一个节点要为null，故要找到中间节点的前一个节点A，将A的下一个节点置空
        while (fast != null && fast.next != null) { //偶数个数时取右边
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时得到的slow就是中间节点
        TreeNode node = new TreeNode(slow.val);
        //很关键的一步
        pre.next = null;
        //递归
        node.left = sortedHelper(head);
        node.right = sortedHelper(slow.next);
        return node;
    }

    //根据上面的思路，大神和官方的写法很类似，大神的写法也好理解，官方的也好理解，官方的写法如下；
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode mid = getMid(head);
        TreeNode node = new TreeNode(mid.val);
        if (mid==head){ //因为偶数时是选右边那个，所以此时肯定是只有一个节点的情况
            return node;
        }
        node.left=sortedListToBST2(head);
        node.right=sortedListToBST2(mid.next);
        return node;
    }

    public ListNode getMid(ListNode listNode) {
        ListNode pre = null;
        ListNode slow = listNode;
        ListNode fast = listNode;
        while (fast != null && fast.next != null) {
            pre=slow;
            slow=slow.next;
            fast=fast.next.next;
        }
        if (pre!=null){
            pre.next=null;
        }
        return slow;

    }

    //官方的第三种方法，并不能理解，不过看起来很厉害，以后最好仔细看看
    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }

}
