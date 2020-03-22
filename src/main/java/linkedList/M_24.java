package linkedList;

//两两交换链表中的节点
//https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
public class M_24 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode listNode = swapPairs(n1);
        System.out.println(listNode);
        ListNode listNode2 = swapPairs2(n2);
        System.out.println(listNode2);
    }

    //自己写的递归，先排好前两个，让排好后的第二个指向接下来要排的链表
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head.next;
        head.next = swapPairs(head.next.next);
        temp.next = head;
        return temp;
    }

    //官方迭代法
    public static ListNode swapPairs2(ListNode head) {
        ListNode pre = new ListNode(-1);
        pre.next=head;
        ListNode temp=pre;
        while (pre.next!= null && pre.next.next != null) {
            ListNode first = pre.next;
            ListNode second = pre.next.next;
            first.next=second.next;
            second.next=first;
            pre.next=second;
            pre=first;
        }
        return temp.next;
    }

}
