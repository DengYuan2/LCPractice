package linkedList;

//奇偶链表  https://leetcode-cn.com/problems/odd-even-linked-list/description/
//要求：使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)
public class M_328 {
    public static void main(String[] args) {
        M_328 m = new M_328();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
//        n5.next = n6;
//        n6.next = n7;
        ListNode listNode = m.oddEvenList(n1);
        System.out.println(listNode);
        ListNode list2 = m.oddEvenList2(n1);
        System.out.println(list2);

    }

    //我的思路：新建两个链表，一个存放奇数，一个存放偶数，最后拼接起来
    //不知道这算不算原地算法
    public ListNode oddEvenList(ListNode head) {
        ListNode cur = head;
        ListNode odd = new ListNode(0);
        ListNode o = odd;
        ListNode even = new ListNode(0);
        ListNode e = even;
        int count = 1;
        while (cur != null) {
            if (count % 2 == 1) {
                o.next = cur;
                o = o.next;
            } else {
                e.next = cur;
                e = e.next;
            }
            cur = cur.next;
            count++;
        }
        //最后一个节点的指针置空，否则可能出现栈溢出，因为倒数第二个节点还会指向最后一个节点，会出现循环 如：分割1234567，则这步之前：o:1357 e:2467
        e.next = null;
        //多此一举，因为下一行又重新赋值了
//        o.next=null;
        o.next = even.next;
        return odd.next;
    }

    //官方方法，思路一致，但完成的想法不一样，比我的简单好多，而且每一部分的最后都指向null
    //一奇一偶双指针，简洁易懂
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode e = even; //额外创建一个指针来保存偶数链表的第一个
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = even.next;
            even.next = odd.next;
            even = odd.next;
        }
        odd.next = e;
        return head;

    }
}
