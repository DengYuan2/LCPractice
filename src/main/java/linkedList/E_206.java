package linkedList;

// 题目地址https://leetcode-cn.com/problems/reverse-linked-list/submissions/
//几种方法的消耗都差不多: 耗时：0s  空间：38M（打败了5%的选手，嘿嘿嘿）
public class E_206 {

    //原来的做法是利用外部空间（已删除），学习数据结构时老师讲了头插法
    //头插法
    //思路，创建一个新的节点，将原链表上每个节点放到新链表的最前面
    public static ListNode reverseList1(ListNode head) {
        ListNode node = new ListNode(-1);
        ListNode next=null;
        while (head!=null){
            next=head.next; //保存后面的节点
            head.next=node.next; //该节点指向的节点是node节点后面的节点，即原来在它前面的节点
            node.next=head; //node指向该节点
            head=next; //接着插入下一个节点
        }
        return node.next;
    }

    //迭代法
    //思路：每遍历一个节点，就将该节点的后继指针指向前一个节点（第一个节点指向null）
    public static ListNode reverseList3(ListNode head){
        ListNode next=null; //记录接下来要遍历的节点
        ListNode pre=null; //记录该节点前面的节点（按照原链表的顺序）
        while (head!=null){
            next=head.next;
            head.next=pre; //后继指针指向前一个节点
            pre=head; //该节点原来是后一个节点的前面的节点，现在变成后一个节点后面的节点了
            head=next;
        }
        return pre; //注意此处是pre节点哦，head节点此时为空
    }

    //递归方法
    //参考 https://leetcode-cn.com/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
    //图非常便于理解
    public static ListNode reverseList2(ListNode head){
       if (head ==null||head.next==null){
           return head;
       }
        ListNode cur = reverseList2(head.next); //cur为最后一个节点（且一直是），第一次时head为倒数第二个节点,第二次head为倒数第三个节点
        head.next.next=head; //将head节点的下一个节点指向自己（head）
        head.next=null;
        return cur;

    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(6);
        ListNode n5 = new ListNode(2);
        ListNode n6 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode listNode = reverseList1(n1);
        System.out.println("1: "+listNode);
        listNode = reverseList2(n6);
        System.out.println("2: "+listNode);
        listNode = reverseList3(n1);
        System.out.println("3: "+listNode);
    }
}

