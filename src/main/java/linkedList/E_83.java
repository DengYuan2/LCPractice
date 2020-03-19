package linkedList;

//删除有序链表中的所有重复元素 https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/description/

public class E_83 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
//        ListNode listNode3 = deleteDuplicates3(n1);
//        System.out.println(listNode3);
//        ListNode listNode1 = deleteDuplicates1(n1);
//        System.out.println(listNode1);
        ListNode listNode = deleteDuplicates(n1);
        System.out.println(listNode);
    }

    //自己写的，感觉也不错
    // 思路：现在的节点跟后面的节点比
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = head;
        while (temp != null) {
            while (temp.next != null && temp.next.val == temp.val) { //只要与后面的节点相同，就一直循环
                temp.next = temp.next.next;
            } //此循环结束后，现在节点的后面节点肯定比它（严格地）大
            temp = temp.next;
        }
        return head;
    }


    //官方：思路简单明确啊
    public static ListNode deleteDuplicates3(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null) { //只有这样才有必要继续比
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }

        }
        return head;
    }

    //大神的递归，有点可以理解了，但我很难想到
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        head.next=deleteDuplicates(head.next);
        return head.next.val==head.val?head.next:head;
    }
}
