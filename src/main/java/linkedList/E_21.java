package linkedList;

//合并两有序链表 https://leetcode-cn.com/problems/merge-two-sorted-lists/description/
//思路解析；https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode/
//以下几个方法的时间和空间差不多
//todo 我发现在不同的时间提交第一个方法所得到的用时和内存消耗是有点差距的，所以关键是掌握好的方法
public class E_21 {

    //我的最初写法：感觉移动次数比较多
    //执行用时1 ms, 在所有 Java 提交中击败了80.32%的用户, 内存消耗 : 38.3 MB, 在所有 Java 提交中击败了64.47%的用户
    public static ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //将头大的往头小的链表里插，这样就相当于有了头节点
        ListNode n1 = l1.val <= l2.val ? l1 : l2; //头小的节点
        ListNode n2 = l1.val <= l2.val ? l2 : l1; //头大的节点
        ListNode temp = null; //记录n2链表中该比较谁了
        ListNode node = n1; //1、遍历n1链表，找到合适的插入位置  2、记录接下来从n1链表(部分排好序的)的哪个位置开始比较
        while (n2 != null) {
            temp = n2.next;
            while (true) { //此循环用来找出n2应该插在哪个位置
                if (node.next == null) {
                    break;
                }
                if (n2.val <= node.next.val) {
                    break;
                }
                node = node.next;
            }
            //插入适当位置
            n2.next = node.next;
            node.next = n2;
            //接下来应该从node.next开始比较，前面都不用比较了
            node = node.next;
            n2 = temp;

        }
        return n1;
    }

    //官网迭代法：
    //新建一个节点，比较两链表谁小，小的插到新链表最后面
    //执行用时1 ms, 在所有 Java 提交中击败了80.32%的用户, 内存消耗 :38.1 MB, 在所有 Java 提交中击败了67.60%的用户
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode next = node; //记录下一个连接在哪个节点的后面
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                next.next = l1;
                next = l1; //next=next.next;
                l1 = l1.next;
            } else {
                next.next = l2;
                next = l2; //next=next.next;
                l2 = l2.next;
            }
        }
        next.next = l1 == null ? l2 : l1;
//        if (l1!=null){
//            next.next=l1;
//        }
//        if (l2!=null){
//            next.next=l2;
//        }
        return node.next;
    }

    //官方递归：
    //两个链表头部较小的一个与剩下元素的 merge 操作结果合并
    //根据思路自己写出来的哦，骄傲！！
    //执行用时1 ms, 在所有 Java 提交中击败了80.32%的用户, 内存消耗 :38.4 MB, 在所有 Java 提交中击败了62.75%的用户
    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(1);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n4.next = n5;
        n5.next = n6;
        ListNode listNode3 = mergeTwoLists3(n4, n1);
        System.out.println(listNode3);
//        ListNode listNode2 = mergeTwoLists2(n4, n1);
//        System.out.println(listNode2);
//        ListNode listNode1 = mergeTwoLists1(n4, n1);
//        System.out.println(listNode1);

    }

}
